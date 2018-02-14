package be.luxuryoverdosis.framework.data.dao.implementations;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import be.luxuryoverdosis.framework.base.Query;
import be.luxuryoverdosis.framework.data.dao.AbstractHibernateDaoSupport;
import be.luxuryoverdosis.framework.data.dao.interfaces.RoleHibernateDAO;
import be.luxuryoverdosis.framework.data.dto.RoleDTO;
import be.luxuryoverdosis.framework.data.to.RoleTO;
import be.luxuryoverdosis.framework.logging.Logging;

@Repository
public class RoleHibernateDAOMySQLImpl extends AbstractHibernateDaoSupport implements RoleHibernateDAO {
	public RoleTO createOrUpdate(final RoleTO roleTO) {
		Logging.info(this, "Begin createRole");
		getHibernateTemplate().saveOrUpdate(roleTO);
		Logging.info(this, "End createRole");
		return roleTO;
	}

	public RoleTO read(final int id) {
		Logging.info(this, "Begin readRole");
		RoleTO roleTO = (RoleTO) getHibernateTemplate().load(RoleTO.class, id);
		Logging.info(this, "End readRole");
		return roleTO;
	}
	
	@SuppressWarnings("unchecked")
	public RoleTO readName(final String name) {
		Logging.info(this, "Begin readNameRole");
		ArrayList<RoleTO> arrayList = (ArrayList<RoleTO>) getHibernateTemplate().find("from RoleTO r where r.name = ?", new Object[]{name});
		RoleTO roleTO = null;
		if(!arrayList.isEmpty()) {
			roleTO = (RoleTO)arrayList.iterator().next();
		}
		Logging.info(this, "End readNameRole");
		return roleTO;
	}

	public void delete(final int id) {
		Logging.info(this, "Begin deleteRole");
		getHibernateTemplate().delete((RoleTO) getHibernateTemplate().load(RoleTO.class, id));
		Logging.info(this, "End deleteRole");		
	}

	@SuppressWarnings("unchecked")
	public ArrayList<RoleTO> list() {
		Logging.info(this, "Begin listRole");
		ArrayList<RoleTO> arrayList = (ArrayList<RoleTO>) getHibernateTemplate().find("from RoleTO");
		Logging.info(this, "End listRole");
		return arrayList;
	}
	
	private static final String listHql = "SELECT new be.luxuryoverdosis.framework.data.dto.RoleDTO(" +
			"r.id, " +
			"r.name " +
			") " +
			"from RoleTO r " +
			"where name like ?";
	
	
	@SuppressWarnings("unchecked")
	public ArrayList<RoleDTO> listDTO(String searchValue) {
		Logging.info(this, "Begin listRole");
		ArrayList<RoleDTO> arrayList = (ArrayList<RoleDTO>) getHibernateTemplate().find(listHql,  new Object[]{Query.PROCENT + searchValue + Query.PROCENT});
		Logging.info(this, "End listRole");
		return arrayList;
	}
	
	@SuppressWarnings("unchecked")
	public long count(final String name, final int id) {
		Logging.info(this, "Begin countRole");
		ArrayList<Long> arrayList = (ArrayList<Long>) getHibernateTemplate().find("select count(*) from RoleTO r where r.name = ? and r.id <> ?", new Object[]{name, id});
		long count = arrayList.iterator().next().longValue();
		Logging.info(this, "End countRole");
		return count;
	}
}
