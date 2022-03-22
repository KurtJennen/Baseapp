package be.luxuryoverdosis.framework.data.dto;

import java.util.ArrayList;
import java.util.Date;

import be.luxuryoverdosis.framework.base.tool.DateTool;

public class UserDTO extends BaseDTO {
	private String name;
	private String userName;
	private String password;
	private String email;
	private Date dateExpiration;
	private String dateExpirationAsString;
	private boolean isActivation;
	private boolean isRegister;
	ArrayList<String> roles = new ArrayList<String>();
	
	private int[] linkedRoleIds;
	private int[] unlinkedRoleIds;
	
	public UserDTO() {
		super();
	}
	
	public UserDTO(int id, String name, String userName, String email) {
		super();
		setId(id);
		this.name = name;
		this.userName = userName;
		this.email = email;
	}
	
	public UserDTO(int id, String name, String userName, String email, Date dateExpiration) {
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
	public boolean isActivation() {
		return isActivation;
	}
	public void setActivation(boolean isActivation) {
		this.isActivation = isActivation;
	}
	public boolean isRegister() {
		return isRegister;
	}
	public void setRegister(boolean isRegister) {
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
	public void setRoles(ArrayList<String> roles) {
		this.roles = roles;
	}
	
	public int[] getLinkedRoleIds() {
		return linkedRoleIds;
	}
	public void setLinkedRoleIds(int[] linkedRoleIds) {
		this.linkedRoleIds = linkedRoleIds;
	}
	public int[] getUnlinkedRoleIds() {
		return unlinkedRoleIds;
	}
	public void setUnlinkedRoleIds(int[] unlinkedRoleIds) {
		this.unlinkedRoleIds = unlinkedRoleIds;
	}
}
