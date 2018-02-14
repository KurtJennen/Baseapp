package be.luxuryoverdosis.framework.web.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import be.luxuryoverdosis.framework.logging.Logging;
import be.luxuryoverdosis.framework.web.BaseWebConstants;

public class LoginForm extends BaseForm {
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String password;
	private boolean deactivation;
	private String deactivationMessage;
		
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isDeactivation() {
		return deactivation;
	}
	public void setDeactivation(boolean deactivation) {
		this.deactivation = deactivation;
	}	
	public String getDeactivationMessage() {
		return deactivationMessage;
	}
	public void setDeactivationMessage(String deactivationMessage) {
		this.deactivationMessage = deactivationMessage;
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		this.setName("");
		this.setPassword("");
		this.setDeactivation(false);
		this.setDeactivationMessage("");
	}
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		Logging.info(this, "Begin Validating");
		
		ActionErrors errors =  new ActionErrors();
		
		if(this.getMethod().equals(BaseWebConstants.LOGIN)) {
			errors = super.validate(mapping, request);
		}
		
		if(errors.size() > 0) {
			request.setAttribute(BaseWebConstants.ERROR, 1);
		}
		
		Logging.info(this, "End Validating");
		
		return errors;
	}
}
