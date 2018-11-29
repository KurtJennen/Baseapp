package be.luxuryoverdosis.framework.business.service.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.business.enumeration.JaNeeType;
import be.luxuryoverdosis.framework.data.dto.MenuDTO;
import be.luxuryoverdosis.framework.data.to.Menu;
import be.luxuryoverdosis.framework.data.wrapperdto.MenuWrapperDTO;
import net.sf.navigator.menu.MenuRepository;
import net.sf.navigator.util.LoadableResourceException;

public interface MenuService {
	public MenuDTO createOrUpdateDTO(MenuDTO menuDTO);
	public MenuDTO readDTO(int id);
	
	public Menu createOrUpdate(Menu menu);
	public Menu read(int id);
	public Menu readFullName(String fullName, int userId);
	public void delete(int id);
	public void deleteForUser(int userId);
	
	public ArrayList<MenuDTO> list(int userId);
	
	public MenuWrapperDTO getMenuWrapperDTO(MenuRepository menuRepository, int userId)  throws LoadableResourceException;
	
	public ArrayList<MenuDTO> produceMenu(MenuRepository menuRepository, int userId) throws LoadableResourceException;
	public ArrayList<MenuDTO> updateMenu(MenuRepository menuRepository, int[] id, JaNeeType[] hidden, JaNeeType[] disabled, JaNeeType[] forPay, JaNeeType[] payed, int userId) throws LoadableResourceException;
	
	public MenuRepository produceAlterredMenu(MenuRepository menuRepository, int userId);
}
