package be.luxuryoverdosis.framework.data.dao.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.business.enumeration.JaNeeEnum;
import be.luxuryoverdosis.framework.data.dto.MenuDTO;
import be.luxuryoverdosis.framework.data.to.Menu;

public interface MenuHibernateDAO {
	Menu createOrUpdate(Menu menu);
	Menu read(int id);
	Menu readFullName(String fullName, int userId);
	void delete(int id);
	void deleteForUser(int userId);
	
	ArrayList<MenuDTO> list(int userId);
	ArrayList<MenuDTO> listHidden(int userId, JaNeeEnum hidden);
	ArrayList<MenuDTO> listDisabled(int userId, JaNeeEnum disabled);
	ArrayList<MenuDTO> listForPayAndPayed(int userId, JaNeeEnum payed);
	
	long count(int userId);
	long count(String fullName, int userId);
}
