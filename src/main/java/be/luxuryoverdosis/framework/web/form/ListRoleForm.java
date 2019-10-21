package be.luxuryoverdosis.framework.web.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import be.luxuryoverdosis.framework.logging.Logging;
import be.luxuryoverdosis.framework.web.BaseWebConstants;

public class ListRoleForm extends BaseForm {
    private static final long serialVersionUID = 1L;
    
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
    }
    
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        Logging.info(this, "Begin Validating");
        
        ActionErrors errors = new ActionErrors();
        
        super.checkOnlyOneSelected(mapping, request, errors, BaseWebConstants.READ, getSelectedIds());
        
        if(errors.size() > 0) {
            request.setAttribute(BaseWebConstants.ERROR, 1);
        }
            
        Logging.info(this, "End Validating");
        
        return errors;
    }
}