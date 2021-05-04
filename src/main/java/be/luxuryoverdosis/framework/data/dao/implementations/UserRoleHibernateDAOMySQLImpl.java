package be.luxuryoverdosis.framework.data.dao.implementations;

import java.util.ArrayList;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import be.luxuryoverdosis.framework.data.dao.AbstractHibernateDaoSupport;
import be.luxuryoverdosis.framework.data.dao.BaseQueryParameters;
import be.luxuryoverdosis.framework.data.dao.interfaces.UserRoleHibernateDAO;
import be.luxuryoverdosis.framework.data.dto.UserRoleDTO;
import be.luxuryoverdosis.framework.data.to.UserRole;
import be.luxuryoverdosis.framework.logging.Logging;

@Repository
public class UserRoleHibernateDAOMySQLImpl extends AbstractHibernateDaoSupport implements UserRoleHibernateDAO {
	public UserRole createOrUpdate(final UserRole userRole) {
		Logging.info(this, "Begin createUserRole");
		getCurrentSession().saveOrUpdate(userRole);
		Logging.info(this, "End createUserRole");
		return userRole;
	}
	
	@SuppressWarnings("unchecked")
	public void delete(int userId, int roleId) {
		Logging.info(this, "Begin deleteUserRole");
		
		Query<UserRole> query = getCurrentSession().getNamedQuery(UserRole.DELETE_USERROLES_BY_USER_AND_ROLE);
		query.setParameter(BaseQueryParameters.USER_ID, userId);
		query.setParameter(BaseQueryParameters.ROLE_ID, roleId);
		query.executeUpdate();
		
		Logging.info(this, "End deleteUserRole");
	}

	@SuppressWarnings("unchecked")
	public ArrayList<UserRoleDTO> listDTO(int userId) {
		Logging.info(this, "Begin listUserRole");
		
		Query<UserRoleDTO> query = getCurrentSession().getNamedQuery(UserRole.SELECT_USERROLES_DTO_BY_USER);
		query.setParameter(BaseQueryParameters.USER_ID, userId);
		
		ArrayList<UserRoleDTO> arrayList = (ArrayList<UserRoleDTO>) query.list();
		
		Logging.info(this, "End listUserRole");
		return arrayList;
	}
	
	@SuppressWarnings("unchecked")
	public long countUser(final int userId) {
		Logging.info(this, "Begin countUser(int)");
		
		Query<Long> query = getCurrentSession().getNamedQuery(UserRole.COUNT_USERROLES_BY_USER);
		query.setParameter(BaseQueryParameters.USER_ID, userId);
		ArrayList<Long> arrayList = (ArrayList<Long>) query.list();
		long count = arrayList.iterator().next().longValue();
		
		Logging.info(this, "End countUser(int)");
		return count;
	}
	
	@SuppressWarnings("unchecked")
	public long countRole(final int roleId) {
		Logging.info(this, "Begin countRole(int)");
		
		Query<Long> query = getCurrentSession().getNamedQuery(UserRole.COUNT_USERROLES_BY_ROLE);
		query.setParameter(BaseQueryParameters.ROLE_ID, roleId);
		ArrayList<Long> arrayList = (ArrayList<Long>) query.list();
		long count = arrayList.iterator().next().longValue();
		
		Logging.info(this, "End countRole(int)");
		return count;
	}
	
}
