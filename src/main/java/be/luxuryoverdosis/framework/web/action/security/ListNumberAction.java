package be.luxuryoverdosis.framework.web.action.security;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;
import be.luxuryoverdosis.framework.business.service.interfaces.NumberService;
import be.luxuryoverdosis.framework.data.to.Number;
import be.luxuryoverdosis.framework.logging.Logging;
import be.luxuryoverdosis.framework.web.BaseWebConstants;
import be.luxuryoverdosis.framework.web.action.ajaxaction.AjaxAction;
import be.luxuryoverdosis.framework.web.message.MessageLocator;
import be.luxuryoverdosis.framework.web.sessionmanager.SessionManager;

public class ListNumberAction extends AjaxAction {
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin List");
		ActionMessages actionMessages = new ActionMessages();
		
		String previous = request.getParameter(BaseWebConstants.PREVIOUS);
		
		SessionManager.delete(request, SessionManager.TYPE_ATTRIBUTES, SessionManager.SUBTYPE_IDS);
				
		if(BaseWebConstants.DELETE.equals(previous)) {
			actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("delete.success", MessageLocator.getMessage(request, "table.number")));
		}
		actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("list.success", MessageLocator.getMessage(request, "table.number")));
		saveMessages(request, actionMessages);
			
		Logging.info(this, "End List Success");
		
		return mapping.getInputForward();
	}
	
	public ActionForward create(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Create");
		Logging.info(this, "End Create Success");
		
		return (mapping.findForward(BaseWebConstants.CREATE));
	}
	
	public ActionForward ajaxList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Logging.info(this, "Begin Ajax");
        
        ArrayList<Number> numberList = getNumberService().list();
        if (numberList.size() > 0) {
            super.sendAsJson(response, numberList);
        }
        
        Logging.info(this, "End Ajax Success");
        
        return null;
    }
	
	private NumberService getNumberService() {
		return BaseSpringServiceLocator.getBean(NumberService.class);
	}
}
