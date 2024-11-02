package be.luxuryoverdosis.framework.data.factory;

import be.luxuryoverdosis.framework.business.enumeration.JaNeeEnum;
import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;
import be.luxuryoverdosis.framework.data.dto.MenuDTO;
import be.luxuryoverdosis.framework.data.to.Menu;
import net.sf.navigator.menu.MenuComponent;

public final class MenuFactory {
	private MenuFactory() {
	}
	
	public static MenuDTO produceMenuDTO(final MenuComponent menuComponent, final String parentTitle, final String fullLevel, final int level) {
		MenuDTO menuDTO = new MenuDTO();
		menuDTO.setId(-1);
		menuDTO.setFullName(parentTitle + menuComponent.getName());
		menuDTO.setName(menuComponent.getName());
		menuDTO.setTitle(BaseSpringServiceLocator.getMessage(menuComponent.getTitle()));
		menuDTO.setFullLevel(fullLevel);
		menuDTO.setLevel(level);
		menuDTO.setHidden(JaNeeEnum.NEE);
		menuDTO.setDisabled(JaNeeEnum.NEE);
		menuDTO.setForPay(JaNeeEnum.NEE);
		menuDTO.setPayed(JaNeeEnum.NEE);
		
		return menuDTO;
	}
	
	public static MenuDTO produceMenuDTO(final Menu menu, MenuDTO menuDTO) {
		if (menuDTO == null) {
			menuDTO = new MenuDTO();
		}
		menuDTO.setId(menu.getId());
		menuDTO.setFullName(menu.getFullName());
		menuDTO.setName(menu.getName());
		menuDTO.setTitle(menu.getTitle());
		menuDTO.setLevel(menu.getLevel());
		menuDTO.setHidden(menu.getHidden());
		menuDTO.setDisabled(menu.getDisabled());
		menuDTO.setForPay(menu.getForPay());
		menuDTO.setPayed(menu.getPayed());
		if (menu.getUser() != null) {
			menuDTO.setUserId(menu.getUser().getId());
		}
		
		return menuDTO;
	}
	
	public static Menu produceMenu(Menu menu, final MenuDTO menuDTO) {
		if (menu == null) {
			menu = new Menu();
		}
		menu.setId(menuDTO.getId());
		menu.setFullName(menuDTO.getFullName());
		menu.setName(menuDTO.getName());
		menu.setTitle(menuDTO.getTitle());
		menu.setFullLevel(menuDTO.getFullLevel());
		menu.setLevel(menuDTO.getLevel());
		menu.setHidden(menuDTO.getHidden());
		menu.setDisabled(menuDTO.getDisabled());
		menu.setForPay(menuDTO.getForPay());
		menu.setPayed(menuDTO.getPayed());
		
		return menu;
	}
}
