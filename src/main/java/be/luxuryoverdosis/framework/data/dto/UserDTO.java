package be.luxuryoverdosis.framework.data.dto;

public class UserDTO {
	private int id;
	private String name;
	private String userName;
	private String password;
	private String email;
	private String dateExpirationAsString;
	private int roleId;
	
	public UserDTO() {
		super();
	}
	
	public UserDTO(int id, String name, String userName, String email) {
		super();
		this.id = id;
		this.name = name;
		this.userName = userName;
		this.email = email;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	
}
