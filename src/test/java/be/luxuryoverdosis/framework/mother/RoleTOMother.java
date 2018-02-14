package be.luxuryoverdosis.framework.mother;

import be.luxuryoverdosis.Constants;
import be.luxuryoverdosis.framework.data.to.RoleTO;

public class RoleTOMother {
	public static RoleTO produceRoleTO() {
		RoleTO roleTO = new RoleTO();
		roleTO.setId(0);
		roleTO.setName(Constants.ROLE_BEHEERDER);
		
		return roleTO;
	}
}
