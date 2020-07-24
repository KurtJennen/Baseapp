package be.luxuryoverdosis.framework.web.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.springframework.util.StringUtils;

import be.luxuryoverdosis.framework.logging.Logging;
import be.luxuryoverdosis.framework.web.BaseWebConstants;
import be.luxuryoverdosis.framework.web.message.MessageLocator;

public class ListRoleForm extends ListForm {
	private static final long serialVersionUID = 1L;
	
	private String dialogName;
	
	public String getDialogName() {
		return dialogName;
	}
	public void setDialogName(String dialogName) {
		this.dialogName = dialogName;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		this.setDialogName("");
	}
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		Logging.info(this, "Begin Validating");
		
		ActionErrors errors = super.validate(mapping, request);
		
		if(this.getMethod().equals(BaseWebConstants.UPDATE)) {
			request.setAttribute(BaseWebConstants.DIALOG, true);
			if(StringUtils.isEmpty(dialogName)) {
				errors.add("name", new ActionMessage("errors.required", MessageLocator.getMessage(request, "security.name")));
			}
		}
		
		if(errors.size() > 0) {
			request.setAttribute(BaseWebConstants.ERROR, 1);
		}
			
		Logging.info(this, "End Validating");
		
		return errors;
	}
}
