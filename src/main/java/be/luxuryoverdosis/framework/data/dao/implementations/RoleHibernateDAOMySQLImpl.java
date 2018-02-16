package be.luxuryoverdosis.framework.data.dao.implementations;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import be.luxuryoverdosis.framework.base.SearchQuery;
import be.luxuryoverdosis.framework.data.dao.AbstractHibernateDaoSupport;
import be.luxuryoverdosis.framework.data.dao.interfaces.RoleHibernateDAO;
import be.luxuryoverdosis.framework.data.dto.RoleDTO;
import be.luxuryoverdosis.framework.data.to.Role;
import be.luxuryoverdosis.framework.logging.Logging;

@Repository
public class RoleHibernateDAOMySQLImpl extends AbstractHibernateDaoSupport implements RoleHibernateDAO {
	public Role createOrUpdate(final Role role) {
		Logging.info(this, "Begin createRole");
		getHibernateTemplate().saveOrUpdate(role);
		Logging.info(this, "End createRole");
		return role;
	}

	public Role read(final int id) {
		Logging.info(this, "Begin readRole");
		Role role = (Role) getHibernateTemplate().load(Role.class, id);
		Logging.info(this, "End readRole");
		return role;
	}
	
	@SuppressWarnings("unchecked")
	public Role readName(final String name) {
		Logging.info(this, "Begin readNameRole");
		ArrayList<Role> arrayList = (ArrayList<Role>) getHibernateTemplate().find("from Role r where r.name = ?", new Object[]{name});
		Role role = null;
		if(!arrayList.isEmpty()) {
			role = (Role)arrayList.iterator().next();
		}
		Logging.info(this, "End readNameRole");
		return role;
	}

	public void delete(final int id) {
		Logging.info(this, "Begin deleteRole");
		getHibernateTemplate().delete((Role) getHibernateTemplate().load(Role.class, id));
		Logging.info(this, "End deleteRole");		
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Role> list() {
		Logging.info(this, "Begin listRole");
		ArrayList<Role> arrayList = (ArrayList<Role>) getHibernateTemplate().find("from Role");
		Logging.info(this, "End listRole");
		return arrayList;
	}
	
	private static final String listHql = "SELECT new be.luxuryoverdosis.framework.data.dto.RoleDTO(" +
			"r.id, " +
			"r.name " +
			") " +
			"from Role r " +
			"where name like ?";
	
	
	@SuppressWarnings("unchecked")
	public ArrayList<RoleDTO> listDTO(String searchValue) {
		Logging.info(this, "Begin listRole");
		ArrayList<RoleDTO> arrayList = (ArrayList<RoleDTO>) getHibernateTemplate().find(listHql,  new Object[]{SearchQuery.PROCENT + searchValue + SearchQuery.PROCENT});
		Logging.info(this, "End listRole");
		return arrayList;
	}
	
	@SuppressWarnings("unchecked")
	public long count(final String name, final int id) {
		Logging.info(this, "Begin countRole");
		ArrayList<Long> arrayList = (ArrayList<Long>) getHibernateTemplate().find("select count(*) from Role r where r.name = ? and r.id <> ?", new Object[]{name, id});
		long count = arrayList.iterator().next().longValue();
		Logging.info(this, "End countRole");
		return count;
	}
}
