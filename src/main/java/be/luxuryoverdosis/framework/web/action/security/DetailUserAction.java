package be.luxuryoverdosis.framework.web.action.security;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import be.luxuryoverdosis.framework.base.tool.DateTool;
import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;
import be.luxuryoverdosis.framework.business.service.interfaces.RoleService;
import be.luxuryoverdosis.framework.business.service.interfaces.UserService;
import be.luxuryoverdosis.framework.data.dto.RoleDTO;
import be.luxuryoverdosis.framework.data.dto.UserDTO;
import be.luxuryoverdosis.framework.data.to.User;
import be.luxuryoverdosis.framework.logging.Logging;
import be.luxuryoverdosis.framework.web.form.DetailUserForm;
import be.luxuryoverdosis.framework.web.message.MessageLocator;

public class DetailUserAction extends NavigationAction {	
	public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Search");
		Logging.info(this, "End Search Success");
		
		return (mapping.findForward("search"));
	}
	
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin List");
		Logging.info(this, "End List Success");
		
		return (mapping.findForward("list"));
	}
	
	public ActionForward read(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Read");

		ActionMessages actionMessages = new ActionMessages();
		
		int id = Integer.parseInt(request.getParameter("objectId"));
		
		//UserService userService = (UserService)SpringServiceLocator.getBean(SpringServiceConstants.USER_SERVICE);
		UserService userService = BaseSpringServiceLocator.getBean(UserService.class);
		UserDTO userDTO = userService.readDTO(id);
		DetailUserForm userForm = (DetailUserForm) form;
		userForm.setObjectId(userDTO.getId());
		userForm.setName(userDTO.getName());
		userForm.setUserName(userDTO.getUserName());
		userForm.setPassword(userDTO.getPassword());
		userForm.setPasswordConfirm(userDTO.getPassword());
		userForm.setEmail(userDTO.getEmail());
		userForm.setDateExpirationAsString(userDTO.getDateExpirationAsString());
		userForm.setRoleId(userDTO.getRoleId());
		
		super.setNavigationButtons(form, request);
		
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
		
		actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("create.success", MessageLocator.getMessage(request, "table.user")));
		saveMessages(request, actionMessages);
		
		Logging.info(this, "End Create Success");
		
		return mapping.getInputForward();
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Update");
		ActionMessages actionMessages = new ActionMessages();
		
		ActionForward actionForward = mapping.getInputForward();
		
		DetailUserForm userForm = (DetailUserForm) form;

		UserDTO userDTO = new UserDTO();
		userDTO.setId(userForm.getObjectId());
		userDTO.setName(userForm.getName());
		userDTO.setUserName(userForm.getUserName());
		userDTO.setPassword(userForm.getPassword());
		userDTO.setEmail(userForm.getEmail());
		userDTO.setRoleId(userForm.getRoleId());
		
		//UserService userService = (UserService)SpringServiceLocator.getBean(SpringServiceConstants.USER_SERVICE);
		UserService userService = BaseSpringServiceLocator.getBean(UserService.class);
		userDTO = userService.createOrUpdateDTO(userDTO);
		if(userForm.getObjectId() < 0) {
			actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("save.success", MessageLocator.getMessage(request, "table.user")));
			if(userForm.getRoleId() == 0) {
				actionForward = mapping.findForward("login");
			}
		} else {
			actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("update.success", MessageLocator.getMessage(request, "table.user")));
		}
		
		userForm.setObjectId(userDTO.getId());
		userForm.setDateExpirationAsString(userDTO.getDateExpirationAsString());
		
		saveMessages(request, actionMessages);
		
		Logging.info(this, "End Update Success");
		
		return actionForward;
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Delete");
		ActionMessages actionMessages = new ActionMessages();
		
		int id = Integer.parseInt(request.getParameter("objectId"));
		
		//UserService userService = (UserService)SpringServiceLocator.getBean(SpringServiceConstants.USER_SERVICE);
		UserService userService = BaseSpringServiceLocator.getBean(UserService.class);
		userService.delete(id);
		
		actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("delete.success", MessageLocator.getMessage(request, "table.user")));
		saveMessages(request, actionMessages);
		
		Logging.info(this, "End Delete Success");
		
		return list(mapping, form, request, response);
	}
	
	public ActionForward activateYear(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin ActivateYear");
		
		DetailUserForm userForm = (DetailUserForm) form;
		
		//UserService userService = (UserService)SpringServiceLocator.getBean(SpringServiceConstants.USER_SERVICE);
		UserService userService = BaseSpringServiceLocator.getBean(UserService.class);
		User user = userService.activate(userForm.getObjectId(), UserService.YEAR);
		
		userForm.setRoleId(user.getRole().getId());
		userForm.setDateExpirationAsString(DateTool.formatUtilDate(user.getDateExpiration()));
		
		Logging.info(this, "End ActivateYear");
		return mapping.getInputForward();
	}
	
	public ActionForward activateHalfYear(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin ActivateHalfYear");
		
		DetailUserForm userForm = (DetailUserForm) form;
		
		//UserService userService = (UserService)SpringServiceLocator.getBean(SpringServiceConstants.USER_SERVICE);
		UserService userService = BaseSpringServiceLocator.getBean(UserService.class);
		User user = userService.activate(userForm.getObjectId(), UserService.HALF_YEAR);
		
		userForm.setRoleId(user.getRole().getId());
		userForm.setDateExpirationAsString(DateTool.formatUtilDate(user.getDateExpiration()));
		
		Logging.info(this, "End ActivateHalfYear");
		return mapping.getInputForward();
	}
	
	public ActionForward deactivate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Deactivate");
		
		DetailUserForm userForm = (DetailUserForm) form;
		
		//UserService userService = (UserService)SpringServiceLocator.getBean(SpringServiceConstants.USER_SERVICE);
		UserService userService = BaseSpringServiceLocator.getBean(UserService.class);
		User user = userService.deactivate(userForm.getObjectId());
		
		userForm.setRoleId(user.getRole().getId());
		userForm.setDateExpirationAsString(DateTool.formatUtilDate(user.getDateExpiration()));
		
		Logging.info(this, "End Deactivate");
		return mapping.getInputForward();
	}
	
	public ActionForward readJob(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin ReadJob");
		Logging.info(this, "End Read Success");
		
		return mapping.findForward("readJob");
	}
	
	public ActionForward ajaxSearchAllRole(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Ajax");
		
		DetailUserForm userForm = (DetailUserForm) form;
		
		RoleService roleService = BaseSpringServiceLocator.getBean(RoleService.class);
		ArrayList<RoleDTO> roleList = roleService.listDTO(userForm.getRoleIdValue());
		if (roleList.size() > 0) {
			super.sendAsJson(response, roleList);
		}
		
		Logging.info(this, "End Ajax Success");
		
		return null;
	}
	
	public ActionForward ajaxSearchOneRole(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Ajax");
		
		DetailUserForm userForm = (DetailUserForm) form;
		
		RoleService roleService = BaseSpringServiceLocator.getBean(RoleService.class);
		RoleDTO roleDTO = roleService.readDTO(userForm.getRoleId());
		
		super.sendAsJson(response, roleDTO);
		
		Logging.info(this, "End Ajax Success");
		
		return null;
	}
	
}
