package be.luxuryoverdosis.framework.data.dao.implementations;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import be.luxuryoverdosis.framework.base.SearchQuery;
import be.luxuryoverdosis.framework.data.dao.AbstractHibernateDaoSupport;
import be.luxuryoverdosis.framework.data.dao.interfaces.UserHibernateDAO;
import be.luxuryoverdosis.framework.data.dto.UserDTO;
import be.luxuryoverdosis.framework.data.to.User;
import be.luxuryoverdosis.framework.logging.Logging;

@Repository
public class UserHibernateDAOMySQLImpl extends AbstractHibernateDaoSupport implements UserHibernateDAO {
	public User createOrUpdate(final User user) {
		Logging.info(this, "Begin createUser");
		getHibernateTemplate().saveOrUpdate(user);
		Logging.info(this, "End createUser");
		return user;
	}

	public User read(final int id) {
		Logging.info(this, "Begin readUser");
		User user = (User) getHibernateTemplate().load(User.class, id);
		Logging.info(this, "End readUser");
		return user;
	}
	
	@SuppressWarnings("unchecked")
	public User readName(final String name) {
		Logging.info(this, "Begin readNameUser");
		ArrayList<User> arrayList = (ArrayList<User>) getHibernateTemplate().find("from User u where u.name = ?", new Object[]{name});
		User user = null;
		if(!arrayList.isEmpty()) {
			user = (User)arrayList.iterator().next();
		}
		Logging.info(this, "End readNameUser");
		return user;
	}

	public void delete(final int id) {
		Logging.info(this, "Begin deleteUser");
		getHibernateTemplate().delete((User) getHibernateTemplate().load(User.class, id));
		Logging.info(this, "End deleteUser");		
	}

	@SuppressWarnings("unchecked")
	public ArrayList<User> list() {
		Logging.info(this, "Begin listUser");
		ArrayList<User> arrayList = (ArrayList<User>) getHibernateTemplate().find("from User");
		Logging.info(this, "End listUser");
		return arrayList;
	}
	
	private static final String listHql = "SELECT new be.luxuryoverdosis.framework.data.dto.UserDTO(" +
			"u.id, " +
			"u.name, " +
			"u.userName, " +
			"u.email " +
			") " +
			"from User u";
	
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
			"from User u " +
			"where u.name like ?";
	
	@SuppressWarnings("unchecked")
	public ArrayList<UserDTO> listDTO(String searchValue) {
		Logging.info(this, "Begin listUser");
		ArrayList<UserDTO> arrayList = (ArrayList<UserDTO>) getHibernateTemplate().find(listHql1, new Object[]{SearchQuery.PROCENT + searchValue + SearchQuery.PROCENT});
		Logging.info(this, "End listUser");
		return arrayList;
	}
	
	@SuppressWarnings("unchecked")
	public long count(final String name, final int id) {
		Logging.info(this, "Begin countUser(String, int)");
		ArrayList<Long> arrayList = (ArrayList<Long>) getHibernateTemplate().find("select count(*) from User u where u.name = ? and u.id <> ?", new Object[]{name, id});
		long count = arrayList.iterator().next().longValue();
		Logging.info(this, "End countUser(String, int)");
		return count;
	}
	
	@SuppressWarnings("unchecked")
	public long count(final int roleId) {
		Logging.info(this, "Begin countUser(int)");
		ArrayList<Long> arrayList = (ArrayList<Long>) getHibernateTemplate().find("select count(*) from User u where u.role.id = ?", new Object[]{roleId});
		long count = arrayList.iterator().next().longValue();
		Logging.info(this, "End countUser(int)");
		return count;
	}
}
