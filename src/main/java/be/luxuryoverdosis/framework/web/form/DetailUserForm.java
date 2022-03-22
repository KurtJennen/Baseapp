package be.luxuryoverdosis.framework.web.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import be.luxuryoverdosis.framework.base.tool.DateTool;
import be.luxuryoverdosis.framework.logging.Logging;
import be.luxuryoverdosis.framework.web.BaseWebConstants;
import be.luxuryoverdosis.framework.web.message.MessageLocator;

public class DetailUserForm extends BaseForm {
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String userName;
	private String password;
	private String passwordConfirm;
	private String email;
	private String date;
	private boolean isActivation;
//	private int roleId;
//	private String roleIdValue;
	
	private int[] linkedRoleIds;
	private int[] unlinkedRoleIds;

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
	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public boolean isActivation() {
		return isActivation;
	}
	public void setActivation(boolean isActivation) {
		this.isActivation = isActivation;
	}
//	public int getRoleId() {
//		return roleId;
//	}
//	public void setRoleId(int roleId) {
//		this.roleId = roleId;
//	}
//	public String getRoleIdValue() {
//		return roleIdValue;
//	}
//	public void setRoleIdValue(String roleIdValue) {
//		this.roleIdValue = roleIdValue;
//	}
	
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
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		this.setName("");
		this.setUserName("");
		this.setPassword("");
		this.setPasswordConfirm("");
		this.setEmail("");
		this.setDate(DateTool.formatUtilDate(DateTool.getDefaultDateFromCalendar()));
	}
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		Logging.info(this, "Begin Validating");
		
		ActionErrors errors = new ActionErrors();
		
		if(this.getMethod().equals(BaseWebConstants.UPDATE)) {
			errors = super.validate(mapping, request);
			
			if(!password.equals(passwordConfirm)) {
				errors.add("password", new ActionMessage("equal", MessageLocator.getMessage(request, "security.password"), MessageLocator.getMessage(request, "security.password.confirm")));
				errors.add("passwordConfirm", new ActionMessage("equal", MessageLocator.getMessage(request, "security.password.confirm"), MessageLocator.getMessage(request, "security.password")));
			}
		}
		
		if(errors.size() > 0) {
			request.setAttribute(BaseWebConstants.ERROR, 1);
		}
			
		Logging.info(this, "End Validating");
		
		return errors;
	}
}
