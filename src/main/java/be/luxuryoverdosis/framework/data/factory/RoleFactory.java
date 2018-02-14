package be.luxuryoverdosis.framework.data.factory;

import be.luxuryoverdosis.framework.data.dto.RoleDTO;
import be.luxuryoverdosis.framework.data.to.RoleTO;

public class RoleFactory {
	private RoleFactory() {
	}
	
	public static RoleDTO produceRoleDTO(final RoleTO roleTO) {
		RoleDTO roleDTO = new RoleDTO();
		roleDTO.setId(roleTO.getId());
		roleDTO.setName(roleTO.getName());
		
		return roleDTO;
	}
	
	public static RoleTO produceRole(RoleTO roleTO, final RoleDTO roleDTO) {
		if(roleTO == null) {
			roleTO = new RoleTO();
		}
		roleTO.setId(roleDTO.getId());
		roleTO.setName(roleDTO.getName());
		
		return roleTO;
	}
}
