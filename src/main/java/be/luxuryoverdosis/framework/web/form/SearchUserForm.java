package be.luxuryoverdosis.framework.web.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import be.luxuryoverdosis.framework.business.query.SearchSelect;
import be.luxuryoverdosis.framework.business.service.BaseSpringServiceConstants;
import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;
import be.luxuryoverdosis.framework.logging.Logging;
import be.luxuryoverdosis.framework.web.BaseWebConstants;

public class SearchUserForm extends SearchForm {
	private static final long serialVersionUID = 1L;
	
	private int nameId;
	private String nameIdValue;
	
	
	public int getNameId() {
		return nameId;
	}
	public void setNameId(int nameId) {
		this.nameId = nameId;
	}
	public String getNameIdValue() {
		return nameIdValue;
	}
	public void setNameIdValue(String nameIdValue) {
		this.nameIdValue = nameIdValue;
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		this.setNameId(-1);
		this.setNameIdValue("");
	}
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		Logging.info(this, "Begin Validating");
		
		ActionErrors errors = new ActionErrors();
		
		errors = super.validate(mapping, request);
		
		if(errors.size() > 0) {
			request.setAttribute(BaseWebConstants.ERROR, 1);
		}
			
		Logging.info(this, "End Validating");
		
		return errors;
	}
	
	public SearchSelect getSearchSelect() {
		return (SearchSelect)BaseSpringServiceLocator.getBean(BaseSpringServiceConstants.SEARCH_USER);
	}
}
