package be.luxuryoverdosis.framework.web.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import be.luxuryoverdosis.framework.logging.Logging;
import be.luxuryoverdosis.framework.web.BaseWebConstants;

public class DetailNumberForm extends BaseForm {
	private static final long serialVersionUID = 1L;
	
	private String applicationCode;
	private String year;
	private int number;
	private String type;
	
	public String getApplicationCode() {
		return applicationCode;
	}
	public void setApplicationCode(final String applicationCode) {
		this.applicationCode = applicationCode;
	}
	public String getYear() {
		return year;
	}
	public void setYear(final String year) {
		this.year = year;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(final int number) {
		this.number = number;
	}
	public String getType() {
		return type;
	}
	public void setType(final String type) {
		this.type = type;
	}

	public void reset(final ActionMapping mapping, final HttpServletRequest request) {
		super.reset(mapping, request);
		this.setYear("");
		this.setNumber(0);
		this.setType("");
	}
	
	public ActionErrors validate(final ActionMapping mapping, final HttpServletRequest request) {
		Logging.info(this, "Begin Validating");
		
		ActionErrors errors = new ActionErrors();
		
		if (this.getMethod().equals(BaseWebConstants.UPDATE)) {
			errors = super.validate(mapping, request);
		}
		
		if (errors.size() > 0) {
			request.setAttribute(BaseWebConstants.ERROR, 1);
		}
			
		Logging.info(this, "End Validating");
		
		return errors;
	}
}
