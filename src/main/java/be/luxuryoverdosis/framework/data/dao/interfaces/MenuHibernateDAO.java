package be.luxuryoverdosis.framework.data.dao.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.dto.MenuDTO;
import be.luxuryoverdosis.framework.data.to.Menu;

public interface MenuHibernateDAO {
	public Menu createOrUpdate(Menu menu);
	public Menu read(int id);
	public Menu readFullName(String fullName, int userId);
	public void delete(int id);
	public void deleteForUser(int userId);
	
	public ArrayList<MenuDTO> list(int userId);
	public ArrayList<MenuDTO> listHidden(int userId, String hidden);
	public ArrayList<MenuDTO> listDisabled(int userId, String disabled);
	public ArrayList<MenuDTO> listForPayAndPayed(int userId, String payed);
	
	public long count(int userId);
	public long count(String fullName, int userId);
}
