package be.luxuryoverdosis.framework.data.dao.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.dto.UserRoleDTO;
import be.luxuryoverdosis.framework.data.to.UserRole;

public interface UserRoleHibernateDAO {
	UserRole createOrUpdate(UserRole userRole);
	void delete(int userId);
	void delete(int userId, int roleId);
	
    ArrayList<UserRoleDTO> listDTO(int userId);
	
	long countUser(int userId);
	long countRole(int roleId);
}
