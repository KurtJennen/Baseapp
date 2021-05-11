package be.luxuryoverdosis.framework.mother;

import be.luxuryoverdosis.framework.data.to.UserRole;

public class UserRoleMother {
	public static UserRole produceUserRole() {
		UserRole userRole = new UserRole();
		userRole.setId(0);
		userRole.setRole(RoleMother.produceRole());
		userRole.setUser(UserMother.produceUser());
		
		return userRole;
	}
}
