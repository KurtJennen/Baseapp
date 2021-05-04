package be.luxuryoverdosis.framework.data.dao.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.dto.RoleDTO;
import be.luxuryoverdosis.framework.data.to.Role;

public interface RoleHibernateDAO {
	public Role createOrUpdate(Role role);
	public Role read(int id);
	public Role readName(String name);
	public void delete(int id);
	
	public ArrayList<Role> list();
	public ArrayList<RoleDTO> listDTO(String searchValue);
	public ArrayList<RoleDTO> listNotInUserRoleForUserDTO(int userId);
	
	public long count(String name, int id);
}
