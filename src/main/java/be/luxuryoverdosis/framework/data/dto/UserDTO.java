package be.luxuryoverdosis.framework.data.dto;

import java.util.Date;

public class UserDTO extends BaseDTO {
	private String name;
	private String userName;
	private String password;
	private String email;
	private Date dateExpiration;
	private String dateExpirationAsString;
	private int roleId;
	private String roleName;
	
	public UserDTO() {
		super();
	}
	
	public UserDTO(int id, String name, String userName, String email, int roleId, String roleName) {
		super();
		setId(id);
		this.name = name;
		this.userName = userName;
		this.email = email;
		this.roleId = roleId;
		this.roleName = roleName;
	}
	
	public UserDTO(int id, String name, String userName, String email, Date dateExpiration, String roleName) {
		super();
		setId(id);
		this.name = name;
		this.userName = userName;
		this.email = email;
		this.dateExpiration = dateExpiration;
		this.roleName = roleName;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDateExpiration() {
		return dateExpiration;
	}
	public void setDateExpiration(Date dateExpiration) {
		this.dateExpiration = dateExpiration;
	}
	public String getDateExpirationAsString() {
		return dateExpirationAsString;
	}
	public void setDateExpirationAsString(String dateExpirationAsString) {
		this.dateExpirationAsString = dateExpirationAsString;
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
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
