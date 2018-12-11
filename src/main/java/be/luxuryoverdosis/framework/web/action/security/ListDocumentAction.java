package be.luxuryoverdosis.framework.web.action.security;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.jmesa.facade.TableFacade;
import org.jmesa.facade.TableFacadeFactory;

import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;
import be.luxuryoverdosis.framework.business.service.interfaces.DocumentService;
import be.luxuryoverdosis.framework.data.to.Document;
import be.luxuryoverdosis.framework.logging.Logging;
import be.luxuryoverdosis.framework.web.BaseWebConstants;
import be.luxuryoverdosis.framework.web.jmesa.DocumentJmesaTemplate;
import be.luxuryoverdosis.framework.web.message.MessageLocator;
import be.luxuryoverdosis.framework.web.sessionmanager.SessionManager;

public class ListDocumentAction extends DispatchAction {
	private void storeListsInSession(HttpServletRequest request, ActionMessages actionMessages) {
		SessionManager.delete(request, SessionManager.TYPE_ATTRIBUTES, SessionManager.SUBTYPE_LIST);
	}
	
	public ActionForward listJmesa(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArrayList<Document> documentList = getDocumentService().list();
		
		//JMesa Start	
		TableFacade tableFacade = TableFacadeFactory.createTableFacade(BaseWebConstants.DOCUMENT_LIST, request, response);
		DocumentJmesaTemplate documentJmesaTemplate = new DocumentJmesaTemplate(tableFacade, documentList, request);
		String html = documentJmesaTemplate.render();
		if(html == null) {
			return null;
		}
        request.setAttribute(BaseWebConstants.DOCUMENT_LIST, html);
		//JMesa End
        
        return mapping.getInputForward();
	}
	
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin List");
		ActionMessages actionMessages = new ActionMessages();
		
		String previous = request.getParameter(BaseWebConstants.PREVIOUS);
		
		SessionManager.delete(request, SessionManager.TYPE_ATTRIBUTES, SessionManager.SUBTYPE_IDS);
		
		storeListsInSession(request, actionMessages);
				
		if(listJmesa(mapping, form, request, response) == null) {
			return null;
		}
		
		if(BaseWebConstants.DELETE.equals(previous)) {
			actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("delete.success", MessageLocator.getMessage(request, "table.document")));
		}
		actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("list.success", MessageLocator.getMessage(request, "table.document")));
		saveMessages(request, actionMessages);
			
		Logging.info(this, "End List Success");
		
		return mapping.getInputForward();
	}
	
	public ActionForward create(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Create");
		Logging.info(this, "End Create Success");
		
		return (mapping.findForward("create"));
	}
	
	public ActionForward read(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Read");
		Logging.info(this, "End Read Success");
		
		return (mapping.findForward("read"));
	}
	
	private DocumentService getDocumentService() {
		return BaseSpringServiceLocator.getBean(DocumentService.class);
	}
}
