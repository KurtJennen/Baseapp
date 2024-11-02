package be.luxuryoverdosis.framework.data.dto;

import java.util.ArrayList;
import java.util.Date;

import be.luxuryoverdosis.framework.base.tool.DateTool;

public class UserDTO extends BaseDTO {
	private String name;
	private String userName;
	private String password;
	private String encryptedPassword;
	private String email;
	private Date dateExpiration;
	private String dateExpirationAsString;
	private boolean isActivation;
	private boolean isRegister;
	private ArrayList<String> roles = new ArrayList<String>();
	
	private int[] linkedRoleIds;
	private int[] unlinkedRoleIds;
	
	public UserDTO() {
		super();
	}
	
	public UserDTO(final int id, final String name, final String userName, final String email) {
		super();
		setId(id);
		this.name = name;
		this.userName = userName;
		this.email = email;
	}
	
	public UserDTO(final int id, final String name, final String userName, final String email, final Date dateExpiration) {
		super();
		setId(id);
		this.name = name;
		this.userName = userName;
		this.email = email;
		this.dateExpiration = dateExpiration;
		this.dateExpirationAsString = DateTool.formatUtilDate(dateExpiration);
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
	public String getPassword() {
		return password;
	}
	public void setPassword(final String password) {
		this.password = password;
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
	public Date getDateExpiration() {
		return dateExpiration;
	}
	public void setDateExpiration(final Date dateExpiration) {
		this.dateExpiration = dateExpiration;
	}
	public String getDateExpirationAsString() {
		return dateExpirationAsString;
	}
	public void setDateExpirationAsString(final String dateExpirationAsString) {
		this.dateExpirationAsString = dateExpirationAsString;
	}
	public boolean isActivation() {
		return isActivation;
	}
	public void setActivation(final boolean isActivation) {
		this.isActivation = isActivation;
	}
	public boolean isRegister() {
		return isRegister;
	}
	public void setRegister(final boolean isRegister) {
		this.isRegister = isRegister;
	}
//	public BigDecimal getBedrag() {
//		return new BigDecimal(Math.random() * 10000).setScale(1, RoundingMode.HALF_UP);
//	}
//	public String getPq_rowcls() {
//		return "lightgreen";
//	}
//	public PqUserCellcls getPq_cellcls() {
//		PqUserCellcls pqUserCellcls = new PqUserCellcls();
//		pqUserCellcls.setName("lightgreen");
//		if(userName.startsWith("k")) {
//			pqUserCellcls.setUserName("lightpink");
//		}
//		
//		return pqUserCellcls;
//	}
	public ArrayList<String> getRoles() {
		return roles;
	}
	public void setRoles(final ArrayList<String> roles) {
		this.roles = roles;
	}
	
	public int[] getLinkedRoleIds() {
		return linkedRoleIds;
	}
	public void setLinkedRoleIds(final int[] linkedRoleIds) {
		this.linkedRoleIds = linkedRoleIds;
	}
	public int[] getUnlinkedRoleIds() {
		return unlinkedRoleIds;
	}
	public void setUnlinkedRoleIds(final int[] unlinkedRoleIds) {
		this.unlinkedRoleIds = unlinkedRoleIds;
	}
}
