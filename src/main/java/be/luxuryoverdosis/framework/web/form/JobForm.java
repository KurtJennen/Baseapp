package be.luxuryoverdosis.framework.web.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import be.luxuryoverdosis.framework.logging.Logging;
import be.luxuryoverdosis.framework.web.BaseWebConstants;

public class JobForm extends BaseForm {
	private static final long serialVersionUID = 1L;
	
	private String jobName;
	
	public String getJobName() {
		return jobName;
	}

	public void setJobName(final String jobName) {
		this.jobName = jobName;
	}

	public void reset(final ActionMapping mapping, final HttpServletRequest request) {
		super.reset(mapping, request);
		this.setJobName("");
	}
	
	public ActionErrors validate(final ActionMapping mapping, final HttpServletRequest request) {
		Logging.info(this, "Begin Validating");
		
		ActionErrors errors = new ActionErrors();
		
		if (errors.size() > 0) {
			request.setAttribute(BaseWebConstants.ERROR, 1);
		}
			
		Logging.info(this, "End Validating");
		
		return errors;
	}
}
