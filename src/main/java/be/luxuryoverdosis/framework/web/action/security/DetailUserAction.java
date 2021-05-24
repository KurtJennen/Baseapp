package be.luxuryoverdosis.framework.web.action.security;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionRedirect;

import be.luxuryoverdosis.framework.base.tool.DateTool;
import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;
import be.luxuryoverdosis.framework.business.service.interfaces.RoleService;
import be.luxuryoverdosis.framework.business.service.interfaces.UserRoleService;
import be.luxuryoverdosis.framework.business.service.interfaces.UserService;
import be.luxuryoverdosis.framework.data.dto.RoleDTO;
import be.luxuryoverdosis.framework.data.dto.UserDTO;
import be.luxuryoverdosis.framework.data.dto.UserRoleDTO;
import be.luxuryoverdosis.framework.data.to.User;
import be.luxuryoverdosis.framework.logging.Logging;
import be.luxuryoverdosis.framework.web.BaseWebConstants;
import be.luxuryoverdosis.framework.web.form.DetailUserForm;
import be.luxuryoverdosis.framework.web.message.MessageLocator;
import be.luxuryoverdosis.framework.web.sessionmanager.SessionManager;

public class DetailUserAction extends NavigationAction {
	private void storeListsInSession(HttpServletRequest request, DetailUserForm detailUserForm) {
		 ArrayList<UserRoleDTO> linkedRolesList = getUserRoleService().listDTO(detailUserForm.getId());
        SessionManager.putInSession(request, BaseWebConstants.USER_ROLE_LINKED_LIST, linkedRolesList);
        
        ArrayList<RoleDTO> unlinkedRolesList = getRoleService().listNotInUserRoleForUserDTO(detailUserForm.getId());
        SessionManager.putInSession(request, BaseWebConstants.USER_ROLE_UNLINKED_LIST, unlinkedRolesList);
	}
	
	public String getNameIds() {
		return BaseWebConstants.USER_IDS;
	}
	
	public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Search");
		Logging.info(this, "End Search Success");
		
		return (mapping.findForward(BaseWebConstants.SEARCH));
	}
	
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin List");
		Logging.info(this, "End List Success");
		
		return (mapping.findForward(BaseWebConstants.LIST));
	}
	
	public ActionForward read(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Read");

		ActionMessages actionMessages = new ActionMessages();
		
		int id = Integer.parseInt(request.getParameter(BaseWebConstants.ID));
		String previous = request.getParameter(BaseWebConstants.PREVIOUS);
		
		UserDTO userDTO = getUserService().readDTO(id);
		DetailUserForm userForm = (DetailUserForm) form;
		userForm.setId(userDTO.getId());
		userForm.setName(userDTO.getName());
		userForm.setUserName(userDTO.getUserName());
		userForm.setPassword(userDTO.getPassword());
		userForm.setPasswordConfirm(userDTO.getPassword());
		userForm.setEmail(userDTO.getEmail());
		userForm.setDate(userDTO.getDateExpirationAsString());
		userForm.setActivation(userDTO.isActivation());
		
		storeListsInSession(request, userForm);
		
		super.setNavigationButtons(form, request);
		
		if(BaseWebConstants.SAVE.equals(previous)) {
			actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("save.success", MessageLocator.getMessage(request, "table.user")));
		}
		if(BaseWebConstants.UPDATE.equals(previous)) {
			actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("update.success", MessageLocator.getMessage(request, "table.user")));
		}
		actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("read.success", MessageLocator.getMessage(request, "table.user")));
		saveMessages(request, actionMessages);
		
		Logging.info(this, "End Read Success");
		
		return mapping.getInputForward();
	}

	public ActionForward create(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Create");
		ActionMessages actionMessages = new ActionMessages();
		
		DetailUserForm userForm = (DetailUserForm) form;
		userForm.reset(mapping, request);
		
		userForm.setActivation(getUserService().isActiviation());
		
		storeListsInSession(request, userForm);
		
        super.setNavigationButtons(form, request);
		
		actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("create.success", MessageLocator.getMessage(request, "table.user")));
		saveMessages(request, actionMessages);
		
		Logging.info(this, "End Create Success");
		
		return mapping.getInputForward();
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Update");
		
		ActionRedirect actionRedirect = null;
		
		DetailUserForm userForm = (DetailUserForm) form;

		UserDTO userDTO = new UserDTO();
		userDTO.setId(userForm.getId());
		userDTO.setName(userForm.getName());
		userDTO.setUserName(userForm.getUserName());
		userDTO.setPassword(userForm.getPassword());
		userDTO.setEmail(userForm.getEmail());
		userDTO.setDateExpirationAsString(userForm.getDate());
		userDTO.setLinkedRoleIds(userForm.getLinkedRoleIds());
		userDTO.setUnlinkedRoleIds(userForm.getUnlinkedRoleIds());
		
		if(SessionManager.getFromSession(request, BaseWebConstants.USER_ROLE_LINKED_LIST) == null) {
			actionRedirect = new ActionRedirect(mapping.findForward(BaseWebConstants.LOGIN));
			userDTO.setRegister(true);
		} else {
			actionRedirect = new ActionRedirect(mapping.findForward(BaseWebConstants.READ));
		}
		
		userDTO = getUserService().createOrUpdateDTO(userDTO);
		if(userForm.getId() < 0) {
			actionRedirect.addParameter(BaseWebConstants.PREVIOUS, BaseWebConstants.SAVE);
		} else {
			actionRedirect.addParameter(BaseWebConstants.PREVIOUS, BaseWebConstants.UPDATE);
		}
		
		actionRedirect.addParameter(BaseWebConstants.ID, userDTO.getId());
		
		Logging.info(this, "End Update Success");
		
		return actionRedirect;
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Delete");
		
		int id = Integer.parseInt(request.getParameter(BaseWebConstants.ID));
		
		getUserService().delete(id);
		
		ActionRedirect actionRedirect = new ActionRedirect(mapping.findForward(BaseWebConstants.LIST));
		actionRedirect.addParameter(BaseWebConstants.PREVIOUS, BaseWebConstants.DELETE);
		
		Logging.info(this, "End Delete Success");
		
		return actionRedirect;
	}
	
	public ActionForward activateYear(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin ActivateYear");
		
		DetailUserForm userForm = (DetailUserForm) form;
		
		User user = getUserService().activate(userForm.getId(), UserService.YEAR);
		userForm.setDate(DateTool.formatUtilDate(user.getDateExpiration()));
		
		ActionRedirect actionRedirect = new ActionRedirect(mapping.findForward(BaseWebConstants.READ));
		actionRedirect.addParameter(BaseWebConstants.ID, userForm.getId());
		
		Logging.info(this, "End ActivateYear");
		
		return actionRedirect;
	}
	
	public ActionForward activateHalfYear(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin ActivateHalfYear");
		
		DetailUserForm userForm = (DetailUserForm) form;
		
		User user = getUserService().activate(userForm.getId(), UserService.HALF_YEAR);
		userForm.setDate(DateTool.formatUtilDate(user.getDateExpiration()));
		
		ActionRedirect actionRedirect = new ActionRedirect(mapping.findForward(BaseWebConstants.READ));
		actionRedirect.addParameter(BaseWebConstants.ID, userForm.getId());
		
		Logging.info(this, "End ActivateHalfYear");
		
		return actionRedirect;
	}
	
	public ActionForward deactivate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Deactivate");
		
		DetailUserForm userForm = (DetailUserForm) form;
		
		User user = getUserService().deactivate(userForm.getId());
		userForm.setDate(DateTool.formatUtilDate(user.getDateExpiration()));
		
		ActionRedirect actionRedirect = new ActionRedirect(mapping.findForward(BaseWebConstants.READ));
		actionRedirect.addParameter(BaseWebConstants.ID, userForm.getId());
		
		Logging.info(this, "End Deactivate");
		
		return actionRedirect;
	}
	
	private UserService getUserService() {
		return BaseSpringServiceLocator.getBean(UserService.class);
	}
	
	private RoleService getRoleService() {
		return BaseSpringServiceLocator.getBean(RoleService.class);
	}
	
	private UserRoleService getUserRoleService() {
		return BaseSpringServiceLocator.getBean(UserRoleService.class);
	}
}
