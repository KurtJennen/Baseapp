package be.luxuryoverdosis.framework.mother;

import be.luxuryoverdosis.framework.BaseConstants;
import be.luxuryoverdosis.framework.data.to.RoleTO;

public class RoleTOMother {
	public static RoleTO produceRoleTO() {
		RoleTO roleTO = new RoleTO();
		roleTO.setId(0);
		roleTO.setName(BaseConstants.ROLE_BEHEERDER);
		
		return roleTO;
	}
}
