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
	public void setName(final String name) {
		this.name = name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(final String userName) {
		this.userName = userName;
	}
	public String getEncryptedPassword() {
		return encryptedPassword;
	}
	public void setEncryptedPassword(final String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(final String email) {
		this.email = email;
	}
	public String getDateExpirationAsString() {
		return dateExpirationAsString;
	}
	public void setDateExpirationAsString(final String dateExpirationAsString) {
		this.dateExpirationAsString = dateExpirationAsString;
	}
}
