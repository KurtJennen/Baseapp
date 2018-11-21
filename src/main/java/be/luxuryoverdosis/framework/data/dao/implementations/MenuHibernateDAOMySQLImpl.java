package be.luxuryoverdosis.framework.data.dao.implementations;

import java.util.ArrayList;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import be.luxuryoverdosis.framework.data.dao.AbstractHibernateDaoSupport;
import be.luxuryoverdosis.framework.data.dao.QueryParameters;
import be.luxuryoverdosis.framework.data.dao.interfaces.MenuHibernateDAO;
import be.luxuryoverdosis.framework.data.dto.MenuDTO;
import be.luxuryoverdosis.framework.data.to.Menu;
import be.luxuryoverdosis.framework.logging.Logging;

@Repository
public class MenuHibernateDAOMySQLImpl extends AbstractHibernateDaoSupport implements MenuHibernateDAO {
	public Menu createOrUpdate(final Menu menu) {
		Logging.info(this, "Begin createMenu");
		getCurrentSession().saveOrUpdate(menu);
		Logging.info(this, "End createMenu");
		return menu;
	}

	public Menu read(final int id) {
		Logging.info(this, "Begin readMenu");
		Menu menu = (Menu) getCurrentSession().load(Menu.class, id);
		Logging.info(this, "End readMenu");
		return menu;
	}
	
	@SuppressWarnings("unchecked")
	public Menu readFullName(final String fullName, final int userId) {
		Logging.info(this, "Begin readFullName");
		
		Query<Menu> query = getCurrentSession().getNamedQuery(Menu.SELECT_MENUS_BY_FULL_NAME_AND_USER);
		query.setParameter(QueryParameters.FULL_NAME, fullName);
		query.setParameter(QueryParameters.USER_ID, userId);
		ArrayList<Menu> arrayList = (ArrayList<Menu>) query.list();
		
		Menu menu = null;
		if(!arrayList.isEmpty()) {
			menu = (Menu)arrayList.iterator().next();
		}
		Logging.info(this, "End readFullName");
		return menu;
	}

	public void delete(final int id) {
		Logging.info(this, "Begin deleteMenu");
		getCurrentSession().delete(this.read(id));
		Logging.info(this, "End deleteMenu");		
	}
	
	@SuppressWarnings("unchecked")
	public void deleteForUser(int userId) {
		Logging.info(this, "Begin deleteForUserMenu");
		
		Query<Long> query = getCurrentSession().getNamedQuery(Menu.DELETE_MENUS_BY_USER);
		query.setParameter(QueryParameters.USER_ID, userId);
		query.executeUpdate();
		
		Logging.info(this, "End deleteForUserMenu");	
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<MenuDTO> list(final int userId) {
		Logging.info(this, "Begin listMenu");
		
		Query<MenuDTO> query = getCurrentSession().getNamedQuery(Menu.SELECT_MENUS_DTO_BY_USER);
		query.setParameter(QueryParameters.USER_ID, userId);
		ArrayList<MenuDTO> arrayList = (ArrayList<MenuDTO>) query.list();
		
		Logging.info(this, "End listMenu");
		return arrayList;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<MenuDTO> listHidden(final int userId, final String hidden) {
		Logging.info(this, "Begin listHiddenMenu");

		Query<MenuDTO> query = getCurrentSession().getNamedQuery(Menu.SELECT_MENUS_HIDDEN_DTO_BY_USER);
		query.setParameter(QueryParameters.USER_ID, userId);
		query.setParameter(QueryParameters.HIDDEN, hidden);
		ArrayList<MenuDTO> arrayList = (ArrayList<MenuDTO>) query.list();
		
		Logging.info(this, "End listHiddenMenu");
		return arrayList;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<MenuDTO> listDisabled(final int userId, final String disabled) {
		Logging.info(this, "Begin listDisabledMenu");
		
		Query<MenuDTO> query = getCurrentSession().getNamedQuery(Menu.SELECT_MENUS_DISABLED_DTO_BY_USER);
		query.setParameter(QueryParameters.USER_ID, userId);
		query.setParameter(QueryParameters.DISABLED, disabled);
		ArrayList<MenuDTO> arrayList = (ArrayList<MenuDTO>) query.list();
		
		Logging.info(this, "End listDisabledMenu");
		return arrayList;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<MenuDTO> listForPayAndPayed(final int userId, final String payed) {
		Logging.info(this, "Begin listForPayAndPayedMenu");
		
		Query<MenuDTO> query = getCurrentSession().getNamedQuery(Menu.SELECT_MENUS_FORPAY_DTO_BY_USER_AND_PAYED);
		query.setParameter(QueryParameters.USER_ID, userId);
		query.setParameter(QueryParameters.PAYED, payed);
		ArrayList<MenuDTO> arrayList = (ArrayList<MenuDTO>) query.list();
		
		Logging.info(this, "End listForPayAndPayedMenu");
		return arrayList;
	}
	
	@SuppressWarnings("unchecked")
	public long count(final int userId) {
		Logging.info(this, "Begin countMenu");
		
		Query<Long> query = getCurrentSession().getNamedQuery(Menu.COUNT_MENUS_BY_USER);
		query.setParameter(QueryParameters.USER_ID, userId);
		ArrayList<Long> arrayList = (ArrayList<Long>) query.list();
		long count = arrayList.iterator().next().longValue();
		
		Logging.info(this, "End countMenu)");
		return count;
	}
	
	@SuppressWarnings("unchecked")
	public long count(final String fullName, final int userId) {
		Logging.info(this, "Begin countMenu");
		
		Query<Long> query = getCurrentSession().getNamedQuery(Menu.COUNT_MENUS_BY_FULL_NAME_AND_USER);
		query.setParameter(QueryParameters.FULL_NAME, fullName);
		query.setParameter(QueryParameters.USER_ID, userId);
		ArrayList<Long> arrayList = (ArrayList<Long>) query.list();
		long count = arrayList.iterator().next().longValue();
		
		Logging.info(this, "End countMenu)");
		return count;
	}
	
}
