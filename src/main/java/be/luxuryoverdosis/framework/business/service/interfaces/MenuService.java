package be.luxuryoverdosis.framework.business.service.interfaces;

import java.util.ArrayList;

import net.sf.navigator.menu.MenuRepository;
import net.sf.navigator.util.LoadableResourceException;
import be.luxuryoverdosis.framework.data.dto.MenuDTO;
import be.luxuryoverdosis.framework.data.to.Menu;

public interface MenuService {
	public MenuDTO createOrUpdateDTO(MenuDTO menuDTO);
	public MenuDTO readDTO(int id);
	
	public Menu createOrUpdate(Menu menu);
	public Menu read(int id);
	public Menu readFullName(String fullName, int userId);
	public void delete(int id);
	
	public ArrayList<MenuDTO> list(int userId);
	
	public ArrayList<MenuDTO> produceMenu(MenuRepository menuRepository, int userId) throws LoadableResourceException;
	public ArrayList<MenuDTO> updateMenu(MenuRepository menuRepository, int[] id, String[] hidden, String[] disabled, String[] forPay, String[] payed, int userId) throws LoadableResourceException;
	
	public MenuRepository produceAlterredMenu(MenuRepository menuRepository);
}
