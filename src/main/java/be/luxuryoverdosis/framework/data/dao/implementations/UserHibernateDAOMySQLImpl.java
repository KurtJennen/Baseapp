package be.luxuryoverdosis.framework.data.dao.implementations;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import be.luxuryoverdosis.framework.base.Query;
import be.luxuryoverdosis.framework.data.dao.AbstractHibernateDaoSupport;
import be.luxuryoverdosis.framework.data.dao.interfaces.UserHibernateDAO;
import be.luxuryoverdosis.framework.data.dto.UserDTO;
import be.luxuryoverdosis.framework.data.to.UserTO;
import be.luxuryoverdosis.framework.logging.Logging;

@Repository
public class UserHibernateDAOMySQLImpl extends AbstractHibernateDaoSupport implements UserHibernateDAO {
	public UserTO createOrUpdate(final UserTO userTO) {
		Logging.info(this, "Begin createUser");
		getHibernateTemplate().saveOrUpdate(userTO);
		Logging.info(this, "End createUser");
		return userTO;
	}

	public UserTO read(final int id) {
		Logging.info(this, "Begin readUser");
		UserTO userTO = (UserTO) getHibernateTemplate().load(UserTO.class, id);
		Logging.info(this, "End readUser");
		return userTO;
	}
	
	@SuppressWarnings("unchecked")
	public UserTO readName(final String name) {
		Logging.info(this, "Begin readNameUser");
		ArrayList<UserTO> arrayList = (ArrayList<UserTO>) getHibernateTemplate().find("from UserTO u where u.name = ?", new Object[]{name});
		UserTO userTO = null;
		if(!arrayList.isEmpty()) {
			userTO = (UserTO)arrayList.iterator().next();
		}
		Logging.info(this, "End readNameUser");
		return userTO;
	}

	public void delete(final int id) {
		Logging.info(this, "Begin deleteUser");
		getHibernateTemplate().delete((UserTO) getHibernateTemplate().load(UserTO.class, id));
		Logging.info(this, "End deleteUser");		
	}

	@SuppressWarnings("unchecked")
	public ArrayList<UserTO> list() {
		Logging.info(this, "Begin listUser");
		ArrayList<UserTO> arrayList = (ArrayList<UserTO>) getHibernateTemplate().find("from UserTO");
		Logging.info(this, "End listUser");
		return arrayList;
	}
	
	private static final String listHql = "SELECT new be.luxuryoverdosis.framework.data.dto.UserDTO(" +
			"u.id, " +
			"u.name, " +
			"u.userName, " +
			"u.email " +
			") " +
			"from UserTO u";
	
	@SuppressWarnings("unchecked")
	public ArrayList<UserDTO> listDTO() {
		Logging.info(this, "Begin listUser");
		ArrayList<UserDTO> arrayList = (ArrayList<UserDTO>) getHibernateTemplate().find(listHql);
		Logging.info(this, "End listUser");
		return arrayList;
	}
	
	private static final String listHql1 = "SELECT new be.luxuryoverdosis.framework.data.dto.UserDTO(" +
			"u.id, " +
			"u.name, " +
			"u.userName, " +
			"u.email " +
			") " +
			"from UserTO u " +
			"where u.name like ?";
	
	@SuppressWarnings("unchecked")
	public ArrayList<UserDTO> listDTO(String searchValue) {
		Logging.info(this, "Begin listUser");
		ArrayList<UserDTO> arrayList = (ArrayList<UserDTO>) getHibernateTemplate().find(listHql1, new Object[]{Query.PROCENT + searchValue + Query.PROCENT});
		Logging.info(this, "End listUser");
		return arrayList;
	}
	
	@SuppressWarnings("unchecked")
	public long count(final String name, final int id) {
		Logging.info(this, "Begin countUser(String, int)");
		ArrayList<Long> arrayList = (ArrayList<Long>) getHibernateTemplate().find("select count(*) from UserTO u where u.name = ? and u.id <> ?", new Object[]{name, id});
		long count = arrayList.iterator().next().longValue();
		Logging.info(this, "End countUser(String, int)");
		return count;
	}
	
	@SuppressWarnings("unchecked")
	public long count(final int roleId) {
		Logging.info(this, "Begin countUser(int)");
		ArrayList<Long> arrayList = (ArrayList<Long>) getHibernateTemplate().find("select count(*) from UserTO u where u.role.id = ?", new Object[]{roleId});
		long count = arrayList.iterator().next().longValue();
		Logging.info(this, "End countUser(int)");
		return count;
	}
}
