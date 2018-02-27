package be.luxuryoverdosis.framework.web.action.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionRedirect;

import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;
import be.luxuryoverdosis.framework.business.service.interfaces.RoleService;
import be.luxuryoverdosis.framework.data.dto.RoleDTO;
import be.luxuryoverdosis.framework.logging.Logging;
import be.luxuryoverdosis.framework.web.BaseWebConstants;
import be.luxuryoverdosis.framework.web.form.DetailRoleForm;
import be.luxuryoverdosis.framework.web.message.MessageLocator;

public class DetailRoleAction extends NavigationAction {
	public String getNameIds() {
		return BaseWebConstants.ROLE_IDS;
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
		
		int id = Integer.parseInt(request.getParameter("objectId"));
		String previous = request.getParameter(BaseWebConstants.PREVIOUS);
		
		//RoleService roleService = (RoleService)SpringServiceLocator.getBean(SpringServiceConstants.ROLE_SERVICE);
		RoleService roleService = BaseSpringServiceLocator.getBean(RoleService.class);
		RoleDTO roleDTO = roleService.readDTO(id);
		DetailRoleForm roleForm = (DetailRoleForm) form;
		roleForm.setObjectId(roleDTO.getId());
		roleForm.setName(roleDTO.getName());
		
		super.setNavigationButtons(form, request);
		
		if(BaseWebConstants.SAVE.equals(previous)) {
			actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("save.success", MessageLocator.getMessage(request, "table.role")));
		}
		if(BaseWebConstants.UPDATE.equals(previous)) {
			actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("update.success", MessageLocator.getMessage(request, "table.role")));
		}
		actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("read.success", MessageLocator.getMessage(request, "table.role")));
		saveMessages(request, actionMessages);
		
		Logging.info(this, "End Read Success");
		
		return mapping.getInputForward();
	}
	
	public ActionForward create(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Create");
		ActionMessages actionMessages = new ActionMessages();
		
		DetailRoleForm roleForm = (DetailRoleForm) form;
		roleForm.reset(mapping, request);
		
		actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("create.success", MessageLocator.getMessage(request, "table.role")));
		saveMessages(request, actionMessages);
		
		Logging.info(this, "End Create Success");
		
		return mapping.getInputForward();

	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Update");
		
		ActionRedirect actionRedirect = new ActionRedirect(mapping.findForward("read"));
		//ActionMessages actionMessages = new ActionMessages();
		
		DetailRoleForm roleForm = (DetailRoleForm) form;
		
		RoleDTO roleDTO = new RoleDTO();
		roleDTO.setId(roleForm.getObjectId());
		roleDTO.setName(roleForm.getName());
		
		//RoleService roleService = (RoleService)SpringServiceLocator.getBean(SpringServiceConstants.ROLE_SERVICE);
		RoleService roleService = BaseSpringServiceLocator.getBean(RoleService.class);
		
		roleDTO = roleService.createOrUpdateDTO(roleDTO);
		if(roleForm.getObjectId() < 0) {
			//actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("save.success", MessageLocator.getMessage(request, "table.role")));
			actionRedirect.addParameter(BaseWebConstants.PREVIOUS, BaseWebConstants.SAVE);
		} else {
			//actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("update.success", MessageLocator.getMessage(request, "table.role")));
			actionRedirect.addParameter(BaseWebConstants.PREVIOUS, BaseWebConstants.UPDATE);
		}
		
		//roleForm.setObjectId(roleDTO.getId());
		
		actionRedirect.addParameter("objectId", roleDTO.getId());
		
		//saveMessages(request, actionMessages);
		
		Logging.info(this, "End Update Success");
		
		return actionRedirect;
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Delete");
		//ActionMessages actionMessages = new ActionMessages();
		
		int id = Integer.parseInt(request.getParameter("objectId"));
		
		//RoleService roleService = (RoleService)SpringServiceLocator.getBean(SpringServiceConstants.ROLE_SERVICE);
		RoleService roleService = BaseSpringServiceLocator.getBean(RoleService.class);
		roleService.delete(id);
		
		//actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("delete.success", MessageLocator.getMessage(request, "table.role")));
		//saveMessages(request, actionMessages);
		
		Logging.info(this, "End Delete Success");
		
		//return list(mapping, form, request, response);
		ActionRedirect actionRedirect = new ActionRedirect(mapping.findForward("list"));
		actionRedirect.addParameter(BaseWebConstants.PREVIOUS, BaseWebConstants.DELETE);
		return actionRedirect;
	}
}
