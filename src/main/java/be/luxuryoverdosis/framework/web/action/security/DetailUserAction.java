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
import be.luxuryoverdosis.framework.business.service.interfaces.UserService;
import be.luxuryoverdosis.framework.data.dto.RoleDTO;
import be.luxuryoverdosis.framework.data.dto.UserDTO;
import be.luxuryoverdosis.framework.data.to.User;
import be.luxuryoverdosis.framework.logging.Logging;
import be.luxuryoverdosis.framework.web.BaseWebConstants;
import be.luxuryoverdosis.framework.web.form.DetailUserForm;
import be.luxuryoverdosis.framework.web.message.MessageLocator;

public class DetailUserAction extends NavigationAction {
	public String getNameIds() {
		return BaseWebConstants.USER_IDS;
	}
	
	public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Search");
		Logging.info(this, "End Search Success");
		
		return (mapping.findForward("search"));
	}
	
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin List");
		Logging.info(this, "End List Success");
		
		return (mapping.findForward("list"));
		//return new ActionRedirect(mapping.findForward("list"));
	}
	
	public ActionForward read(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Read");

		ActionMessages actionMessages = new ActionMessages();
		
		int id = Integer.parseInt(request.getParameter("id"));
		String previous = request.getParameter(BaseWebConstants.PREVIOUS);
		
		//UserService userService = (UserService)SpringServiceLocator.getBean(SpringServiceConstants.USER_SERVICE);
		UserService userService = BaseSpringServiceLocator.getBean(UserService.class);
		UserDTO userDTO = userService.readDTO(id);
		DetailUserForm userForm = (DetailUserForm) form;
		userForm.setId(userDTO.getId());
		userForm.setName(userDTO.getName());
		userForm.setUserName(userDTO.getUserName());
		userForm.setPassword(userDTO.getPassword());
		userForm.setPasswordConfirm(userDTO.getPassword());
		userForm.setEmail(userDTO.getEmail());
		userForm.setDateExpirationAsString(userDTO.getDateExpirationAsString());
		userForm.setRoleId(userDTO.getRoleId());
		
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
		
		actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("create.success", MessageLocator.getMessage(request, "table.user")));
		saveMessages(request, actionMessages);
		
		Logging.info(this, "End Create Success");
		
		return mapping.getInputForward();
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Update");
		//ActionMessages actionMessages = new ActionMessages();
		
		//ActionForward actionForward = mapping.getInputForward();
		ActionRedirect actionRedirect = null;
		
		DetailUserForm userForm = (DetailUserForm) form;

		UserDTO userDTO = new UserDTO();
		userDTO.setId(userForm.getId());
		userDTO.setName(userForm.getName());
		userDTO.setUserName(userForm.getUserName());
		userDTO.setPassword(userForm.getPassword());
		userDTO.setEmail(userForm.getEmail());
		userDTO.setRoleId(userForm.getRoleId());
		
		if(userForm.getRoleId() == 0) {
			actionRedirect = new ActionRedirect(mapping.findForward("login"));
		} else {
			actionRedirect = new ActionRedirect(mapping.findForward("read"));
		}
		
		//UserService userService = (UserService)SpringServiceLocator.getBean(SpringServiceConstants.USER_SERVICE);
		UserService userService = BaseSpringServiceLocator.getBean(UserService.class);
		userDTO = userService.createOrUpdateDTO(userDTO);
		if(userForm.getId() < 0) {
			//actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("save.success", MessageLocator.getMessage(request, "table.user")));
			actionRedirect.addParameter(BaseWebConstants.PREVIOUS, BaseWebConstants.SAVE);
//			if(userForm.getRoleId() == 0) {
//				actionForward = mapping.findForward("login");
//			}
		} else {
			//actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("update.success", MessageLocator.getMessage(request, "table.user")));
			actionRedirect.addParameter(BaseWebConstants.PREVIOUS, BaseWebConstants.UPDATE);
		}
		
		//userForm.setId(userDTO.getId());
		//userForm.setDateExpirationAsString(userDTO.getDateExpirationAsString());
		
		actionRedirect.addParameter("id", userDTO.getId());
		
		//saveMessages(request, actionMessages);
		
		Logging.info(this, "End Update Success");
		
		return actionRedirect;
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Delete");
		//ActionMessages actionMessages = new ActionMessages();
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		//UserService userService = (UserService)SpringServiceLocator.getBean(SpringServiceConstants.USER_SERVICE);
		UserService userService = BaseSpringServiceLocator.getBean(UserService.class);
		userService.delete(id);
		
		//actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("delete.success", MessageLocator.getMessage(request, "table.user")));
		//saveMessages(request, actionMessages);
		
		Logging.info(this, "End Delete Success");
		
		//return list(mapping, form, request, response);
		ActionRedirect actionRedirect = new ActionRedirect(mapping.findForward("list"));
		actionRedirect.addParameter(BaseWebConstants.PREVIOUS, BaseWebConstants.DELETE);
		return actionRedirect;
	}
	
	public ActionForward activateYear(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin ActivateYear");
		
		DetailUserForm userForm = (DetailUserForm) form;
		
		//UserService userService = (UserService)SpringServiceLocator.getBean(SpringServiceConstants.USER_SERVICE);
		UserService userService = BaseSpringServiceLocator.getBean(UserService.class);
		User user = userService.activate(userForm.getId(), UserService.YEAR);
		
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
		User user = userService.activate(userForm.getId(), UserService.HALF_YEAR);
		
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
		User user = userService.deactivate(userForm.getId());
		
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
