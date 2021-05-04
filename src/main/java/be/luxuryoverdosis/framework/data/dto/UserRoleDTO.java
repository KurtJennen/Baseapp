package be.luxuryoverdosis.framework.data.dto;

import be.luxuryoverdosis.baseapp.business.enumeration.RoleNameEnum;

public class UserRoleDTO extends BaseDTO {
	private int roleId;
	private String roleName;
	
	public UserRoleDTO() {
		super();
	}
	
	public UserRoleDTO(int id, int roleId, String roleName) {
		super();
		setId(id);
		this.roleId = roleId;
		this.roleName = roleName;
	}
	
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public String getRoleNameAsKey() {
		return RoleNameEnum.convert(roleName).getCodeAsKey();
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
