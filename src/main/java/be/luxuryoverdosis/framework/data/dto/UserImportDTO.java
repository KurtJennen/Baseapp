package be.luxuryoverdosis.framework.data.dto;

public class UserImportDTO extends BaseDTO {
	private String name;
	private String userName;
	private String encryptedPassword;
	private String email;
	private String dateExpirationAsString;
	
	public UserImportDTO() {
		super();
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
	public String getDateExpirationAsString() {
		return dateExpirationAsString;
	}
	public void setDateExpirationAsString(String dateExpirationAsString) {
		this.dateExpirationAsString = dateExpirationAsString;
	}
}
