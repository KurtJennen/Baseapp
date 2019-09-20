package be.luxuryoverdosis.framework.web.action.security;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import be.luxuryoverdosis.baseapp.Constants;
import be.luxuryoverdosis.baseapp.business.service.SpringServiceLocator;
import be.luxuryoverdosis.framework.BaseConstants;
import be.luxuryoverdosis.framework.base.FileContentType;
import be.luxuryoverdosis.framework.base.tool.ResponseTool;
import be.luxuryoverdosis.framework.business.service.BaseSpringServiceConstants;
import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;
import be.luxuryoverdosis.framework.business.service.interfaces.DocumentService;
import be.luxuryoverdosis.framework.business.service.interfaces.ReportService;
import be.luxuryoverdosis.framework.business.service.interfaces.UserService;
import be.luxuryoverdosis.framework.data.dto.UserDTO;
import be.luxuryoverdosis.framework.logging.Logging;
import be.luxuryoverdosis.framework.web.BaseWebConstants;
import be.luxuryoverdosis.framework.web.action.search.SearchAction;
import be.luxuryoverdosis.framework.web.form.SearchUserForm;
import be.luxuryoverdosis.framework.web.message.MessageLocator;
import be.luxuryoverdosis.framework.web.sessionmanager.SessionManager;

public class SearchUserAction extends SearchAction {
	@Override
	public String getSearchServiceName() {
		return BaseSpringServiceConstants.SEARCH_USER;
	}
	
	private void storeListsInSession(HttpServletRequest request, ActionMessages actionMessages) {
		SessionManager.delete(request, SessionManager.TYPE_ATTRIBUTES, SessionManager.SUBTYPE_LIST);
		
		actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("list.success", MessageLocator.getMessage(request, "table.document")));
		SessionManager.putInSession(request, BaseWebConstants.DOCUMENT_LIST, getDocumentService().list(BaseConstants.DOCUMENTYPE_USER));
	}
		
	public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Search");
		ActionMessages actionMessages = new ActionMessages();
		
		String previous = request.getParameter(BaseWebConstants.PREVIOUS);
		
		SessionManager.delete(request, SessionManager.TYPE_ATTRIBUTES, SessionManager.SUBTYPE_IDS);
		
		storeListsInSession(request, actionMessages);
						
		if(BaseWebConstants.DELETE.equals(previous)) {
			actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("delete.success", MessageLocator.getMessage(request, "table.query")));
		}
		actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("search.success", MessageLocator.getMessage(request, "table.user")));
		saveMessages(request, actionMessages);
		
		Logging.info(this, "End Search Success");
		
		return mapping.getInputForward();
	}
	
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin List");
		Logging.info(this, "End List Success");
		
		return (mapping.findForward(BaseWebConstants.LIST));
	}
	
	public ActionForward create(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Create");
		Logging.info(this, "End Create Success");
		
		return (mapping.findForward(BaseWebConstants.CREATE));
	}
	
	public void report(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Report");
		
		//ReportService reportService = (ReportService)SpringServiceLocator.getBean(SpringServiceConstants.REPORT_SERVICE);
		ReportService reportService = SpringServiceLocator.getBean(ReportService.class);
		String realPathReport = request.getSession().getServletContext().getRealPath(Constants.REPORT_USERS_PATH);
		byte[] pdfByteArray = reportService.create(realPathReport);
		
		ResponseTool.writeResponseForDownload(response, Constants.FILE_USERS, FileContentType.APPLICATION_PDF, pdfByteArray);
		
		response.flushBuffer();
		
		Logging.info(this, "End Report");
	}
	
	public void createDocument(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin CreateDocument");
		ActionMessages actionMessages = new ActionMessages();
		
		SearchUserForm searchUserForm = (SearchUserForm) form;
		
		File file = getUserService().createDocument(searchUserForm.getDocumentId());
		
		ResponseTool.initializeResponseForDownload(response, file.getName(), FileContentType.APPLICATION_VND_OASIS_OPENDOCUMENT_TEXT, Long.valueOf(file.length()).intValue());
		
		getUserService().writeDocument(file, response.getOutputStream());
		
		actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("reset.success", MessageLocator.getMessage(request, "table.query")));
		saveMessages(request, actionMessages);
		
		Logging.info(this, "End CreateDocument Success");
	}
	
	public ActionForward ajaxSearchAllUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Ajax");
		
		SearchUserForm searchUserForm = (SearchUserForm) form;
		
		ArrayList<UserDTO> userList = getUserService().listDTO(searchUserForm.getNameIdValue());
		if (userList.size() > 0) {
			super.sendAsJson(response, userList);
		}
		
		Logging.info(this, "End Ajax Success");
		
		return null;
	}
	
	public ActionForward ajaxSearchOneUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Ajax");
		
		SearchUserForm searchUserForm = (SearchUserForm) form;
		
		UserDTO userDTO = getUserService().readDTO(searchUserForm.getNameId());
		
		super.sendAsJson(response, userDTO);
		
		Logging.info(this, "End Ajax Success");
		
		return null;
	}
	
	private UserService getUserService() {
		return BaseSpringServiceLocator.getBean(UserService.class);
	}
	
	private DocumentService getDocumentService() {
        return BaseSpringServiceLocator.getBean(DocumentService.class);
    }
}
