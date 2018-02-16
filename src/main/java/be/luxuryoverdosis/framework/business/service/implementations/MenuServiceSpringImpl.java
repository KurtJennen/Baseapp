package be.luxuryoverdosis.framework.business.service.implementations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import net.sf.navigator.menu.MenuComponent;
import net.sf.navigator.menu.MenuRepository;
import net.sf.navigator.util.LoadableResourceException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.luxuryoverdosis.framework.BaseConstants;
import be.luxuryoverdosis.framework.base.MenuLevel;
import be.luxuryoverdosis.framework.business.service.interfaces.MenuService;
import be.luxuryoverdosis.framework.business.thread.ThreadManager;
import be.luxuryoverdosis.framework.data.dao.interfaces.MenuHibernateDAO;
import be.luxuryoverdosis.framework.data.dao.interfaces.UserHibernateDAO;
import be.luxuryoverdosis.framework.data.dto.MenuDTO;
import be.luxuryoverdosis.framework.data.factory.MenuFactory;
import be.luxuryoverdosis.framework.data.to.Menu;
import be.luxuryoverdosis.framework.data.to.UserTO;
import be.luxuryoverdosis.framework.logging.Logging;

@Service
public class MenuServiceSpringImpl implements MenuService {
	@Resource
	private MenuHibernateDAO menuHibernateDAO;
	@Resource
	private UserHibernateDAO userHibernateDAO;
	
	@Transactional
	public MenuDTO createOrUpdateDTO(final MenuDTO menuDTO) {
		Logging.info(this, "Begin createMenuDTO");
		
		Menu menu = new Menu();
		if(menuDTO.getId() > 0) {
			menu = this.read(menuDTO.getId());
		}
		menu = MenuFactory.produceMenu(menu, menuDTO);
		if(menuDTO.getUserId() > 0) {
			menu.setUser(userHibernateDAO.read(menuDTO.getUserId()));
		}
		
		menu = this.createOrUpdate(menu);
		
		Logging.info(this, "End createMenuDTO");
		return this.readDTO(menu.getId());
	}
	
	@Transactional(readOnly=true)
	public MenuDTO readDTO(final int id) {
		Logging.info(this, "Begin readMenuDTO");
		
		Menu menu = this.read(id);
		
		MenuDTO menuDTO = MenuFactory.produceMenuDTO(menu, null);
		
		Logging.info(this, "End readMenuDTO");
		return menuDTO;
	}
	
	@Transactional
	public Menu createOrUpdate(final Menu menu) {
		Logging.info(this, "Begin createMenu");
		
		Menu result = menuHibernateDAO.createOrUpdate(menu);
		
		Logging.info(this, "End createMenu");
		return result;
	}
	
	@Transactional(readOnly=true)
	public Menu read(final int id) {
		Logging.info(this, "Begin readMenu");
		Menu result = null;
		result = menuHibernateDAO.read(id);
		Logging.info(this, "End readMenu)");
		return result;
	}
	
	@Transactional(readOnly=true)
	public Menu readFullName(final String fullName, final int userId) {
		Logging.info(this, "Begin readFullNameMenu");
		Menu result = null;
		result = menuHibernateDAO.readFullName(fullName, userId);
		Logging.info(this, "End readFullNameMenu");
		return result;
	}

	@Transactional
	public void delete(final int id) {
		Logging.info(this, "Begin deleteMenu");
		menuHibernateDAO.delete(id);
		Logging.info(this, "End deleteMenu");
	}

	@Transactional(readOnly=true)
	public ArrayList<MenuDTO> list(int userId) {
		Logging.info(this, "Begin listMenu");
		ArrayList<MenuDTO> arrayList = null;
		arrayList = menuHibernateDAO.list(userId);
		Logging.info(this, "End listMenu");
		return arrayList;
	}
	
	@Transactional(readOnly=true)
	public ArrayList<MenuDTO> produceMenu(MenuRepository menuRepository, int userId) throws LoadableResourceException {
		menuRepository.reload();
		ArrayList<MenuDTO> defaultMenuList = produceDefaultMenu(menuRepository);
		
		for (MenuDTO menuDTO : defaultMenuList) {
			Menu menu = menuHibernateDAO.readFullName(menuDTO.getFullName(), userId);
			if(menu != null) {
				MenuFactory.produceMenuDTO(menu, menuDTO);
			}
		}
		
		produceAlterredMenu(menuRepository);
		
		return defaultMenuList;
	}

	@SuppressWarnings("unchecked")
	private ArrayList<MenuDTO> produceDefaultMenu(MenuRepository menuRepository) {
		int level = 0;
		
		MenuLevel menuLevel = new MenuLevel();
		menuLevel.next(level);
		
		ArrayList<MenuDTO> menuList = new ArrayList<MenuDTO>();
		
		MenuComponent[] menuComponents = menuRepository.getTopMenusAsArray();
		
		for(MenuComponent menuComponent : menuComponents) {
			MenuDTO menuDTO = MenuFactory.produceMenuDTO(menuComponent, "", menuLevel.current(), level);
			menuList.add(menuDTO);
			recursiveMenuComponent(menuList, menuComponent.getComponents(), menuComponent.getName() + BaseConstants.SLASH, menuLevel, level);
			menuLevel.next(level);
		}
		
		return menuList;
	}
	
	@SuppressWarnings("unchecked")
	private void recursiveMenuComponent(List<MenuDTO> menuList, List<MenuComponent> menuComponents, String parentTitle, MenuLevel menuLevel, int level) {
		level++;
		for(MenuComponent menuComponent : menuComponents) {
			menuLevel.next(level);
			MenuDTO menuDTO = MenuFactory.produceMenuDTO(menuComponent, parentTitle, menuLevel.current(), level);
			menuList.add(menuDTO);
			if(menuComponent.getComponents().size() > 0) {
				recursiveMenuComponent(menuList, menuComponent.getComponents(), parentTitle + menuComponent.getName() + BaseConstants.SLASH, menuLevel, level);
			}
		}
	}
	
	@Transactional
	public ArrayList<MenuDTO> updateMenu(MenuRepository menuRepository, int[] id, String[] hidden, String[] disabled, String[] forPay, String[] payed, int userId) throws LoadableResourceException {
		menuRepository.reload();
		ArrayList<MenuDTO> defaulMenuList = produceDefaultMenu(menuRepository);
		
		int index = 0;
		for (MenuDTO menuDTO : defaulMenuList) {
			menuDTO.setId(id[index]);
			menuDTO.setHidden(hidden[index]);
			menuDTO.setDisabled(disabled[index]);
			menuDTO.setForPay(forPay[index]);
			menuDTO.setPayed(payed[index]);
			menuDTO.setUserId(userId);
			createOrUpdateDTO(menuDTO);
			index++;
		}
		
		return produceMenu(menuRepository, userId);
	}
	
	@Transactional(readOnly=true)
	public MenuRepository produceAlterredMenu(MenuRepository menuRepository) {
		UserTO userTO = ThreadManager.getUserFromThread();
		int userId = userTO.getId();
		
		disableMenuItems(menuRepository, userId);
		forPayMenuItems(menuRepository, userId);
		hiddenMenuItems(menuRepository, userId);
		
		return menuRepository;
	}
	
	private void disableMenuItems(MenuRepository menuRepository, int userId) {
		ArrayList<MenuDTO> disabledMenuList = menuHibernateDAO.listDisabled(userId, BaseConstants.CODE_JA);
		
		for (MenuDTO menuDTO : disabledMenuList) {
			MenuComponent menuComponent = menuRepository.getMenu(menuDTO.getFullName(), BaseConstants.SLASH);
			menuComponent.setAction("detailMenu.do?method=disabled");
		}
	}
	
	private void forPayMenuItems(MenuRepository menuRepository, int userId) {
		ArrayList<MenuDTO> forPayMenuList = menuHibernateDAO.listForPayAndPayed(userId, BaseConstants.CODE_NEE);
		
		for (MenuDTO menuDTO : forPayMenuList) {
			MenuComponent menuComponent = menuRepository.getMenu(menuDTO.getFullName(), BaseConstants.SLASH);
			menuComponent.setAction("detailMenu.do?method=forPay");
		}
	}

	@SuppressWarnings("rawtypes")
	private void hiddenMenuItems(MenuRepository menuRepository, int userId) {
		ArrayList<MenuDTO> hiddenMenuList = menuHibernateDAO.listHidden(userId, BaseConstants.CODE_JA);
		
		for (MenuDTO menuDTO : hiddenMenuList) {
			MenuComponent menuComponent = menuRepository.getMenu(menuDTO.getFullName(), BaseConstants.SLASH);
			if(menuComponent != null) {
				MenuComponent parent = menuComponent.getParent();
				if(parent != null) {
					for (Iterator iterator = parent.getComponents().iterator(); iterator.hasNext();) {
			            MenuComponent child = (MenuComponent) iterator.next();
			            if(child.getName().equals(menuDTO.getName())) {
			            	child.setParent(null);
			            	iterator.remove();
			            }
			        }
					if(parent.getComponents().size() == 0) {
						MenuComponent grandParent = parent.getParent();
						if(grandParent != null) {
							for (Iterator iterator = grandParent.getComponents().iterator(); iterator.hasNext();) {
								MenuComponent child = (MenuComponent) iterator.next();
								if(child.getName().equals(parent.getName())) {
									child.setParent(null);
									iterator.remove();
								}
							}
						}
					}
				}
			}
		}
	}

}
