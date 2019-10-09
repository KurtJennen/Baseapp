package be.luxuryoverdosis.framework.web.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

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
        
        if(this.getMethod().equals(BaseWebConstants.READ)) {
            errors = super.validate(mapping, request);
            
            if(this.getSelectedIds() == null || this.getSelectedIds().length == 0) {
                errors.add("", new ActionMessage("errors.selected.one"));
            }
            if(this.getSelectedIds() != null && this.getSelectedIds().length > 1) {
                errors.add("", new ActionMessage("errors.selected.more"));
            }
        }
        
        if(errors.size() > 0) {
            request.setAttribute(BaseWebConstants.ERROR, 1);
        }
            
        Logging.info(this, "End Validating");
        
        return errors;
    }
}