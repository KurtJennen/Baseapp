package be.luxuryoverdosis.framework.data.dto;


public class UserRoleDTO extends BaseDTO {
	private int roleId;
	private String roleName;
	
	public UserRoleDTO() {
		super();
	}
	
	public UserRoleDTO(final int id, final int roleId, final String roleName) {
		super();
		setId(id);
		this.roleId = roleId;
		this.roleName = roleName;
	}
	
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(final int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(final String roleName) {
		this.roleName = roleName;
	}
}
