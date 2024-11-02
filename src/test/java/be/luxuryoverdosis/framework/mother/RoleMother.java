package be.luxuryoverdosis.framework.mother;

import be.luxuryoverdosis.framework.business.enumeration.RoleNameEnum;
import be.luxuryoverdosis.framework.data.to.Role;

public class RoleMother {
	protected RoleMother() {
	}
	
	public static Role produceRole() {
		Role role = new Role();
		role.setId(0);
		role.setName(RoleNameEnum.BEHEERDER.getCode());
		
		return role;
	}
}
