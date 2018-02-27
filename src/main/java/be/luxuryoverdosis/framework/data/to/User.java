package be.luxuryoverdosis.framework.data.to;

import java.util.Date;

import javax.xml.bind.annotation.XmlType;

@XmlType
public class User extends BaseTO {
	private String name;
	private String userName;
	private String encryptedPassword;
	private String email;
	private Date dateExpiration;
	private Role role;
	
	private String roleName;
	
	public User() {
		super();
	}
	
	public User(int id, String name, String userName, String email, Date dateExpiration, String roleName) {
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
	public String getEncryptedPassword() {
		return encryptedPassword;
	}
	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
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
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
}
