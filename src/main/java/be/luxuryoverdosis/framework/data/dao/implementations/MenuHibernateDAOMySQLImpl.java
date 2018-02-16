package be.luxuryoverdosis.framework.data.dao.implementations;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import be.luxuryoverdosis.framework.data.dao.AbstractHibernateDaoSupport;
import be.luxuryoverdosis.framework.data.dao.interfaces.MenuHibernateDAO;
import be.luxuryoverdosis.framework.data.dto.MenuDTO;
import be.luxuryoverdosis.framework.data.to.Menu;
import be.luxuryoverdosis.framework.logging.Logging;

@Repository
public class MenuHibernateDAOMySQLImpl extends AbstractHibernateDaoSupport implements MenuHibernateDAO {
	public Menu createOrUpdate(final Menu menu) {
		Logging.info(this, "Begin createMenu");
		getHibernateTemplate().saveOrUpdate(menu);
		Logging.info(this, "End createMenu");
		return menu;
	}

	public Menu read(final int id) {
		Logging.info(this, "Begin readMenu");
		Menu menu = (Menu) getHibernateTemplate().load(Menu.class, id);
		Logging.info(this, "End readMenu");
		return menu;
	}
	
	@SuppressWarnings("unchecked")
	public Menu readFullName(final String fullName, final int userId) {
		Logging.info(this, "Begin readFullName");
		ArrayList<Menu> arrayList = (ArrayList<Menu>) getHibernateTemplate().find("from Menu m where m.fullName = ? and m.user.id = ?", new Object[]{fullName, userId});
		Menu menu = null;
		if(!arrayList.isEmpty()) {
			menu = (Menu)arrayList.iterator().next();
		}
		Logging.info(this, "End readFullName");
		return menu;
	}

	public void delete(final int id) {
		Logging.info(this, "Begin deleteMenu");
		getHibernateTemplate().delete((Menu) getHibernateTemplate().load(Menu.class, id));
		Logging.info(this, "End deleteMenu");		
	}
	
	public void deleteForUser(int userId) {
		Logging.info(this, "Begin deleteForUserMenu");
		getHibernateTemplate().bulkUpdate("delete from Menu m where m.user.id = ?", new Object[]{userId});
		Logging.info(this, "End deleteForUserMenu");	
	}
	
	private static final String listSelectHql = "SELECT new be.luxuryoverdosis.framework.data.dto.MenuDTO(" +
		"m.id, " +
		"m.fullName, " +
		"m.name, " +
		"m.title, " +
		"m.fullLevel, " +
		"m.level, " +
		"m.hidden, " +
		"m.disabled, " +
		"m.forPay, " +
		"m.payed, " +
		"u.id " +
		") " +
		"from Menu m " +
		"inner join m.user u ";
	
	private static final String listHql = listSelectHql +
		"where u.id = ? " +
		"order by m.fullLevel";

	@SuppressWarnings("unchecked")
	public ArrayList<MenuDTO> list(final int userId) {
		Logging.info(this, "Begin listMenu");
		ArrayList<MenuDTO> arrayList = (ArrayList<MenuDTO>) getHibernateTemplate().find(listHql, new Object[]{userId});
		Logging.info(this, "End listMenu");
		return arrayList;
	}
	
	private static final String listHiddenHql = listSelectHql +
		"where u.id = ? " +
		"and m.hidden = ? " +
		"order by m.fullLevel desc";
	
	@SuppressWarnings("unchecked")
	public ArrayList<MenuDTO> listHidden(final int userId, final String hidden) {
		Logging.info(this, "Begin listHiddenMenu");
		ArrayList<MenuDTO> arrayList = (ArrayList<MenuDTO>) getHibernateTemplate().find(listHiddenHql, new Object[]{userId, hidden});
		Logging.info(this, "End listHiddenMenu");
		return arrayList;
	}
	
	private static final String listDisabledHql = listSelectHql +
		"where u.id = ? " +
		"and m.disabled = ? " +
		"order by m.fullLevel desc";
	
	@SuppressWarnings("unchecked")
	public ArrayList<MenuDTO> listDisabled(final int userId, final String disabled) {
		Logging.info(this, "Begin listDisabledMenu");
		ArrayList<MenuDTO> arrayList = (ArrayList<MenuDTO>) getHibernateTemplate().find(listDisabledHql, new Object[]{userId, disabled});
		Logging.info(this, "End listDisabledMenu");
		return arrayList;
	}
	
	private static final String listForPayAndPayedHql = listSelectHql +
		"where u.id = ? " +
		"and m.forPay = 'J' " +
		"and m.payed = ? " +
		"order by m.fullLevel desc";
	
	@SuppressWarnings("unchecked")
	public ArrayList<MenuDTO> listForPayAndPayed(final int userId, final String payed) {
		Logging.info(this, "Begin listForPayAndPayedMenu");
		ArrayList<MenuDTO> arrayList = (ArrayList<MenuDTO>) getHibernateTemplate().find(listForPayAndPayedHql, new Object[]{userId, payed});
		Logging.info(this, "End listForPayAndPayedMenu");
		return arrayList;
	}
	
	@SuppressWarnings("unchecked")
	public long count(final int userId) {
		Logging.info(this, "Begin countMenu");
		ArrayList<Long> arrayList = (ArrayList<Long>) getHibernateTemplate().find("select count(*) from Menu m where m.user.id = ?", new Object[]{userId});
		long count = arrayList.iterator().next().longValue();
		Logging.info(this, "End countMenu)");
		return count;
	}
	
	@SuppressWarnings("unchecked")
	public long count(final String fullName, final int userId) {
		Logging.info(this, "Begin countMenu");
		ArrayList<Long> arrayList = (ArrayList<Long>) getHibernateTemplate().find("select count(*) from Menu m where m.fullName = ? and m.user.id = ?", new Object[]{fullName, userId});
		long count = arrayList.iterator().next().longValue();
		Logging.info(this, "End countMenu)");
		return count;
	}
	
}
