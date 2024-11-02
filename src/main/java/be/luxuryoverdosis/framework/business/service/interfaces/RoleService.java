package be.luxuryoverdosis.framework.business.service.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.dto.RoleDTO;
import be.luxuryoverdosis.framework.data.to.Role;

public interface RoleService {
	RoleDTO createOrUpdateDTO(RoleDTO roleDTO);
	RoleDTO readDTO(int id);
	
	Role createOrUpdate(Role role);
	Role read(int id);
	Role readName(String name);
	void delete(int id);
	
	ArrayList<Role> list();
	ArrayList<RoleDTO> listDTO(String searchValue);
	ArrayList<RoleDTO> listNotInUserRoleForUserDTO(int userId);
	
	long count(String name, int id);
}
