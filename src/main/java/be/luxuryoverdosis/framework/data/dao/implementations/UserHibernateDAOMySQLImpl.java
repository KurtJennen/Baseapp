package be.luxuryoverdosis.framework.data.dao.implementations;

import java.util.ArrayList;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import be.luxuryoverdosis.framework.base.SearchQuery;
import be.luxuryoverdosis.framework.data.dao.AbstractHibernateDaoSupport;
import be.luxuryoverdosis.framework.data.dao.BaseQueryParameters;
import be.luxuryoverdosis.framework.data.dao.interfaces.UserHibernateDAO;
import be.luxuryoverdosis.framework.data.dto.UserDTO;
import be.luxuryoverdosis.framework.data.to.User;
import be.luxuryoverdosis.framework.logging.Logging;

@Repository
public class UserHibernateDAOMySQLImpl extends AbstractHibernateDaoSupport implements UserHibernateDAO {

	public User createOrUpdate(final User user) {
		Logging.info(this, "Begin createUser");
		getCurrentSession().saveOrUpdate(user);
		Logging.info(this, "End createUser");
		return user;
	}

	public User read(final int id) {
		Logging.info(this, "Begin readUser");
		User user = (User) getCurrentSession().load(User.class, id);
		Logging.info(this, "End readUser");
		return user;
	}
	
	@SuppressWarnings("unchecked")
	public User readName(final String name) {
		Logging.info(this, "Begin readNameUser");
		
		Query<User> query = getCurrentSession().getNamedQuery(User.SELECT_USERS_BY_NAME);
		query.setParameter(BaseQueryParameters.NAME, name);
		ArrayList<User> arrayList = (ArrayList<User>) query.list();
		
		User user = null;
		if(!arrayList.isEmpty()) {
			user = (User)arrayList.iterator().next();
		}
		Logging.info(this, "End readNameUser");
		return user;
	}

	public void delete(final int id) {
		Logging.info(this, "Begin deleteUser");
		getCurrentSession().delete(this.read(id));
		Logging.info(this, "End deleteUser");		
	}

	@SuppressWarnings("unchecked")
	public ArrayList<User> list() {
		Logging.info(this, "Begin listUser");
		
		Query<User> query = getCurrentSession().getNamedQuery(User.SELECT_USERS);
		ArrayList<User> arrayList = (ArrayList<User>) query.list();
		
		Logging.info(this, "End listUser");
		return arrayList;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<UserDTO> listDTO() {
		Logging.info(this, "Begin listUser");
		
		Query<UserDTO> query = getCurrentSession().getNamedQuery(User.SELECT_USERS_DTO);
		ArrayList<UserDTO> arrayList = (ArrayList<UserDTO>) query.list();
		
		Logging.info(this, "End listUser");
		return arrayList;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<UserDTO> listDTO(String searchValue) {
		Logging.info(this, "Begin listUser");
		
		Query<UserDTO> query = getCurrentSession().getNamedQuery(User.SELECT_USERS_DTO_BY_NAME);
		query.setParameter(BaseQueryParameters.NAME, SearchQuery.PROCENT + searchValue + SearchQuery.PROCENT);
		ArrayList<UserDTO> arrayList = (ArrayList<UserDTO>) query.list();
		
		Logging.info(this, "End listUser");
		return arrayList;
	}
	
	@SuppressWarnings("unchecked")
	public long count(final String name, final int id) {
		Logging.info(this, "Begin countUser(String, int)");
		
		Query<Long> query = getCurrentSession().getNamedQuery(User.COUNT_USERS_BY_NAME);
		query.setParameter(BaseQueryParameters.NAME, name);
		query.setParameter(BaseQueryParameters.ID, id);
		ArrayList<Long> arrayList = (ArrayList<Long>) query.list();
		long count = arrayList.iterator().next().longValue();
		
		Logging.info(this, "End countUser(String, int)");
		return count;
	}
	
	@SuppressWarnings("unchecked")
	public long count(final int roleId) {
		Logging.info(this, "Begin countUser(int)");

		Query<Long> query = getCurrentSession().getNamedQuery(User.COUNT_USERS_BY_ROLE);
		query.setParameter(BaseQueryParameters.ROLE_ID, roleId);
		ArrayList<Long> arrayList = (ArrayList<Long>) query.list();
		long count = arrayList.iterator().next().longValue();
		
		Logging.info(this, "End countUser(int)");
		return count;
	}
}
