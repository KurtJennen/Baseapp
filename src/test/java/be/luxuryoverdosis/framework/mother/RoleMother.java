package be.luxuryoverdosis.framework.mother;

import be.luxuryoverdosis.framework.BaseConstants;
import be.luxuryoverdosis.framework.data.to.Role;

public class RoleMother {
	public static Role produceRole() {
		Role role = new Role();
		role.setId(0);
		role.setName(BaseConstants.ROLE_BEHEERDER);
		
		return role;
	}
}
