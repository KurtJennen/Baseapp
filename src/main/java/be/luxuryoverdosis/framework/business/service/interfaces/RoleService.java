package be.luxuryoverdosis.framework.business.service.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.dto.RoleDTO;
import be.luxuryoverdosis.framework.data.to.Role;

public interface RoleService {
	public RoleDTO createOrUpdateDTO(RoleDTO roleDTO);
	public RoleDTO readDTO(int id);
	
	public Role createOrUpdate(Role role);
	public Role read(int id);
	public Role readName(String name);
	public void delete(int id);
	
	public ArrayList<Role> list();
	public ArrayList<RoleDTO> listDTO(String searchValue);
	public ArrayList<RoleDTO> listNotInUserRoleForUserDTO(int userId);
	
	public long count(String name, int id);
}
