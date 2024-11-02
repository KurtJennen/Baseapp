package be.luxuryoverdosis.framework.business.service.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.business.enumeration.JaNeeEnum;
import be.luxuryoverdosis.framework.data.dto.MenuDTO;
import be.luxuryoverdosis.framework.data.to.Menu;
import be.luxuryoverdosis.framework.data.wrapperdto.MenuWrapperDTO;
import net.sf.navigator.menu.MenuRepository;
import net.sf.navigator.util.LoadableResourceException;

public interface MenuService {
	MenuDTO createOrUpdateDTO(MenuDTO menuDTO);
	MenuDTO readDTO(int id);
	
	Menu createOrUpdate(Menu menu);
	Menu read(int id);
	Menu readFullName(String fullName, int userId);
	void delete(int id);
	void deleteForUser(int userId);
	
	ArrayList<MenuDTO> list(int userId);
	
	MenuWrapperDTO getMenuWrapperDTO(MenuRepository menuRepository, int userId)  throws LoadableResourceException;
	
	ArrayList<MenuDTO> produceMenu(MenuRepository menuRepository, int userId) throws LoadableResourceException;
	ArrayList<MenuDTO> updateMenu(MenuRepository menuRepository, int[] id, JaNeeEnum[] hidden, JaNeeEnum[] disabled, JaNeeEnum[] forPay, JaNeeEnum[] payed, int userId) throws LoadableResourceException;
	
	MenuRepository produceAlterredMenu(MenuRepository menuRepository, int userId);
}
