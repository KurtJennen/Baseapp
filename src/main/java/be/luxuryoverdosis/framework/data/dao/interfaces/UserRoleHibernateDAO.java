package be.luxuryoverdosis.framework.data.dao.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.dto.UserRoleDTO;
import be.luxuryoverdosis.framework.data.to.UserRole;

public interface UserRoleHibernateDAO {
	public UserRole createOrUpdate(UserRole userRole);
	public void delete(int userId, int roleId);
	
    public ArrayList<UserRoleDTO> listDTO(int userId);
	
	public long countUser(int userId);
	public long countRole(int roleId);
}
