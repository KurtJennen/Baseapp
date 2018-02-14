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

import be.luxuryoverdosis.Constants;
import be.luxuryoverdosis.framework.base.FileContentType;
import be.luxuryoverdosis.framework.base.tool.ResponseTool;
import be.luxuryoverdosis.framework.business.service.BaseSpringServiceConstants;
import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;
import be.luxuryoverdosis.framework.business.service.interfaces.DocumentService;
import be.luxuryoverdosis.framework.business.service.interfaces.RoleService;
import be.luxuryoverdosis.framework.business.service.interfaces.UserService;
import be.luxuryoverdosis.framework.data.dto.UserDTO;
import be.luxuryoverdosis.framework.data.to.DocumentTO;
import be.luxuryoverdosis.framework.data.to.RoleTO;
import be.luxuryoverdosis.framework.logging.Logging;
import be.luxuryoverdosis.framework.web.BaseWebConstants;
import be.luxuryoverdosis.framework.web.action.search.SearchAction;
import be.luxuryoverdosis.framework.web.form.SearchUserForm;
import be.luxuryoverdosis.framework.web.message.MessageLocator;
import be.luxuryoverdosis.framework.web.sessionmanager.SessionManager;

public class SearchUserAction extends SearchAction {
	@Override
	public String getSearchService() {
		return BaseSpringServiceConstants.SEARCH_USER;
	}
	
	private void storeListsInSession(HttpServletRequest request, ActionMessages actionMessages) {
		SessionManager.delete(request, SessionManager.TYPE_ATTRIBUTES, SessionManager.SUBTYPE_LIST);
		
		//RoleService roleService = (RoleService)SpringServiceLocator.getBean(SpringServiceConstants.ROLE_SERVICE);
		RoleService roleService = BaseSpringServiceLocator.getBean(RoleService.class);
		ArrayList<RoleTO> roleList = new ArrayList<RoleTO>();
		roleList = roleService.list();
		actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("list.success", MessageLocator.getMessage(request, "table.role")));
		SessionManager.putInSession(request, BaseWebConstants.ROLE_LIST, roleList);
		
		//DocumentService documentService = (DocumentService)SpringServiceLocator.getBean(SpringServiceConstants.DOCUMENT_SERVICE);
		DocumentService documentService = BaseSpringServiceLocator.getBean(DocumentService.class);
		ArrayList<DocumentTO> documentList = new ArrayList<DocumentTO>();
		documentList = documentService.list(Constants.DOCUMENTYPE_USER);
		actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("list.success", MessageLocator.getMessage(request, "table.document")));
		SessionManager.putInSession(request, BaseWebConstants.DOCUMENT_LIST, documentList);
	}
		
	public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Search");
		ActionMessages actionMessages = new ActionMessages();
		
		SessionManager.delete(request, SessionManager.TYPE_ATTRIBUTES, SessionManager.SUBTYPE_IDS);
		
		storeListsInSession(request, actionMessages);
						
		actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("search.success", MessageLocator.getMessage(request, "table.user")));
		saveMessages(request, actionMessages);
		
		Logging.info(this, "End Search Success");
		
		return mapping.getInputForward();
	}
	
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin List");
		Logging.info(this, "End List Success");
		
		return (mapping.findForward("list"));
	}
	
	public ActionForward create(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Create");
		Logging.info(this, "End Create Success");
		
		return (mapping.findForward("create"));
	}
	
	public void createDocument(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin CreateDocument");
		ActionMessages actionMessages = new ActionMessages();
		
		//SearchForm searchForm = (SearchForm) form;
		SearchUserForm searchUserForm = (SearchUserForm) form;
		
		//UserService userService = (UserService)SpringServiceLocator.getBean(SpringServiceConstants.USER_SERVICE);
		UserService userService = BaseSpringServiceLocator.getBean(UserService.class);
		File file = userService.createDocument(searchUserForm.getDocumentId());
		
		ResponseTool.initializeResponseForDownload(response, file.getName(), FileContentType.APPLICATION_VND_OASIS_OPENDOCUMENT_TEXT, Long.valueOf(file.length()).intValue());
		
		userService.writeDocument(file, response.getOutputStream());
		
		actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("reset.success", MessageLocator.getMessage(request, "table.query")));
		saveMessages(request, actionMessages);
		
		Logging.info(this, "End CreateDocument Success");
	}
	
	public ActionForward ajaxSearchAllUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Ajax");
		
		SearchUserForm searchUserForm = (SearchUserForm) form;
		
		UserService userService = BaseSpringServiceLocator.getBean(UserService.class);
		ArrayList<UserDTO> userList = userService.listDTO(searchUserForm.getNameIdValue());
		if (userList.size() > 0) {
			super.sendAsJson(response, userList);
		}
		
		Logging.info(this, "End Ajax Success");
		
		return null;
	}
	
	public ActionForward ajaxSearchOneUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Ajax");
		
		SearchUserForm searchUserForm = (SearchUserForm) form;
		
		UserService userService = BaseSpringServiceLocator.getBean(UserService.class);
		UserDTO userDTO = userService.readDTO(searchUserForm.getNameId());
		
		super.sendAsJson(response, userDTO);
		
		Logging.info(this, "End Ajax Success");
		
		return null;
	}
}
