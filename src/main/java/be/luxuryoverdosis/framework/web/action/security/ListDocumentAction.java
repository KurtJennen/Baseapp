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
import be.luxuryoverdosis.framework.business.service.interfaces.DocumentService;
import be.luxuryoverdosis.framework.data.dto.DocumentDTO;
import be.luxuryoverdosis.framework.logging.Logging;
import be.luxuryoverdosis.framework.web.BaseWebConstants;
import be.luxuryoverdosis.framework.web.action.ajaxaction.AjaxAction;
import be.luxuryoverdosis.framework.web.message.MessageLocator;
import be.luxuryoverdosis.framework.web.sessionmanager.SessionManager;

public class ListDocumentAction extends AjaxAction {
	private void storeListsInSession(final HttpServletRequest request, final ActionMessages actionMessages) {
		SessionManager.delete(request, SessionManager.TYPE_ATTRIBUTES, SessionManager.SUBTYPE_LIST);
	}
	
	public ActionForward list(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin List");
		ActionMessages actionMessages = new ActionMessages();
		
		String previous = request.getParameter(BaseWebConstants.PREVIOUS);
		
		SessionManager.delete(request, SessionManager.TYPE_ATTRIBUTES, SessionManager.SUBTYPE_IDS);
		
		storeListsInSession(request, actionMessages);
				
		if (BaseWebConstants.DELETE.equals(previous)) {
			actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("delete.success", MessageLocator.getMessage(request, "table.document")));
		}
		actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("list.success", MessageLocator.getMessage(request, "table.document")));
		saveMessages(request, actionMessages);
			
		Logging.info(this, "End List Success");
		
		return mapping.getInputForward();
	}
	
	public ActionForward create(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Create");
		Logging.info(this, "End Create Success");
		
		return (mapping.findForward(BaseWebConstants.CREATE));
	}
	
	public ActionForward ajaxList(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        Logging.info(this, "Begin Ajax");
        
        ArrayList<DocumentDTO> documentList = getDocumentService().listDTO();
        if (documentList.size() > 0) {
            super.sendAsJson(response, documentList);
        }
        
        Logging.info(this, "End Ajax Success");
        
        return null;
    }
	
	private DocumentService getDocumentService() {
		return BaseSpringServiceLocator.getBean(DocumentService.class);
	}
}
