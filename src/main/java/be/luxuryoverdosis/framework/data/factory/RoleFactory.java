package be.luxuryoverdosis.framework.data.factory;

import be.luxuryoverdosis.framework.data.dto.RoleDTO;
import be.luxuryoverdosis.framework.data.to.Role;

public final class RoleFactory {
	private RoleFactory() {
	}
	
	public static RoleDTO produceRoleDTO(final Role role) {
		RoleDTO roleDTO = new RoleDTO();
		roleDTO.setId(role.getId());
		roleDTO.setName(role.getName());
		
		return roleDTO;
	}
	
	public static Role produceRole(Role role, final RoleDTO roleDTO) {
		if (role == null) {
			role = new Role();
		}
		role.setId(roleDTO.getId());
		role.setName(roleDTO.getName());
		
		return role;
	}
}
