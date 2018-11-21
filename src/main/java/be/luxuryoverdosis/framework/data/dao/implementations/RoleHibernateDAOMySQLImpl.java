package be.luxuryoverdosis.framework.data.dao.implementations;

import java.util.ArrayList;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import be.luxuryoverdosis.framework.base.SearchQuery;
import be.luxuryoverdosis.framework.data.dao.AbstractHibernateDaoSupport;
import be.luxuryoverdosis.framework.data.dao.QueryParameters;
import be.luxuryoverdosis.framework.data.dao.interfaces.RoleHibernateDAO;
import be.luxuryoverdosis.framework.data.dto.RoleDTO;
import be.luxuryoverdosis.framework.data.to.Role;
import be.luxuryoverdosis.framework.logging.Logging;

@Repository
public class RoleHibernateDAOMySQLImpl extends AbstractHibernateDaoSupport implements RoleHibernateDAO {
	public Role createOrUpdate(final Role role) {
		Logging.info(this, "Begin createRole");
		getCurrentSession().saveOrUpdate(role);
		Logging.info(this, "End createRole");
		return role;
	}

	public Role read(final int id) {
		Logging.info(this, "Begin readRole");
		Role role = (Role) getCurrentSession().load(Role.class, id);
		Logging.info(this, "End readRole");
		return role;
	}
	
	@SuppressWarnings("unchecked")
	public Role readName(final String name) {
		Logging.info(this, "Begin readNameRole");
		
		Query<Role> query = getCurrentSession().getNamedQuery(Role.SELECT_ROLES_BY_NAME);
		query.setParameter(QueryParameters.NAME, name);
		ArrayList<Role> arrayList = (ArrayList<Role>) query.list();
		
		Role role = null;
		if(!arrayList.isEmpty()) {
			role = (Role)arrayList.iterator().next();
		}
		Logging.info(this, "End readNameRole");
		return role;
	}

	public void delete(final int id) {
		Logging.info(this, "Begin deleteRole");
		getCurrentSession().delete(this.read(id));
		Logging.info(this, "End deleteRole");		
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Role> list() {
		Logging.info(this, "Begin listRole");
		
		Query<Role> query = getCurrentSession().getNamedQuery(Role.SELECT_ROLES);
		ArrayList<Role> arrayList = (ArrayList<Role>) query.list();
		
		Logging.info(this, "End listRole");
		return arrayList;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<RoleDTO> listDTO(String searchValue) {
		Logging.info(this, "Begin listRole");
		
		Query<RoleDTO> query = getCurrentSession().getNamedQuery(Role.SELECT_ROLES_DTO_BY_NAME);
		query.setParameter(QueryParameters.NAME, SearchQuery.PROCENT + searchValue + SearchQuery.PROCENT);
		ArrayList<RoleDTO> arrayList = (ArrayList<RoleDTO>) query.list();
		
		Logging.info(this, "End listRole");
		return arrayList;
	}
	
	@SuppressWarnings("unchecked")
	public long count(final String name, final int id) {
		Logging.info(this, "Begin countRole");
		
		Query<Long> query = getCurrentSession().getNamedQuery(Role.COUNT_ROLES_BY_NAME);
		query.setParameter(QueryParameters.NAME, name);
		query.setParameter(QueryParameters.ID, id);
		ArrayList<Long> arrayList = (ArrayList<Long>) query.list();
		long count = arrayList.iterator().next().longValue();
		
		Logging.info(this, "End countRole");
		return count;
	}
}
