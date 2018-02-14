package be.luxuryoverdosis.framework.web.action.security;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.jmesa.facade.TableFacade;
import org.jmesa.facade.TableFacadeFactory;

import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;
import be.luxuryoverdosis.framework.business.service.interfaces.RoleService;
import be.luxuryoverdosis.framework.data.dto.RoleDTO;
import be.luxuryoverdosis.framework.data.to.RoleTO;
import be.luxuryoverdosis.framework.logging.Logging;
import be.luxuryoverdosis.framework.web.BaseWebConstants;
import be.luxuryoverdosis.framework.web.form.RoleForm;
import be.luxuryoverdosis.framework.web.jmesa.RoleJmesaTemplate;
import be.luxuryoverdosis.framework.web.message.MessageLocator;
import be.luxuryoverdosis.framework.web.sessionmanager.SessionManager;

public class RoleAction extends NavigationAction {
	public ActionForward listJmesa(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//RoleService roleService = (RoleService)SpringServiceLocator.getBean(SpringServiceConstants.ROLE_SERVICE);
		RoleService roleService = BaseSpringServiceLocator.getBean(RoleService.class);
		ArrayList<RoleTO> roleList = new ArrayList<RoleTO>();
		roleList = roleService.list();
		
		//JMesa Start	
		TableFacade tableFacade = TableFacadeFactory.createTableFacade(BaseWebConstants.ROLE_LIST, request, response);
		RoleJmesaTemplate roleJmesaTemplate = new RoleJmesaTemplate(tableFacade, roleList, request);
		String html = roleJmesaTemplate.render();
		if(html == null) {
			return null;
		}
        request.setAttribute(BaseWebConstants.ROLE_LIST, html);
		//JMesa End
        
        return (mapping.findForward("list"));
	}
	
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin List");
		ActionMessages actionMessages = new ActionMessages();
		
		SessionManager.delete(request, SessionManager.TYPE_ATTRIBUTES, SessionManager.SUBTYPE_IDS);
				
		if(listJmesa(mapping, form, request, response) == null) {
			return null;
		}
		
		actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("list.success", MessageLocator.getMessage(request, "table.role")));
		saveMessages(request, actionMessages);
			
		Logging.info(this, "End List Success");
		
		return (mapping.findForward("list"));
	}
	
	public ActionForward read(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Read");
		ActionMessages actionMessages = new ActionMessages();
		
		int id = Integer.parseInt(request.getParameter("objectId"));
		
		//RoleService roleService = (RoleService)SpringServiceLocator.getBean(SpringServiceConstants.ROLE_SERVICE);
		RoleService roleService = BaseSpringServiceLocator.getBean(RoleService.class);
		RoleDTO roleDTO = roleService.readDTO(id);
		RoleForm roleForm = (RoleForm) form;
		roleForm.setObjectId(roleDTO.getId());
		roleForm.setName(roleDTO.getName());
		
		super.setNavigationButtons(form, request);
		
		actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("read.success", MessageLocator.getMessage(request, "table.role")));
		saveMessages(request, actionMessages);
		
		Logging.info(this, "End Read Success");
		
		return mapping.getInputForward();
	}
	
	public ActionForward create(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Create");
		ActionMessages actionMessages = new ActionMessages();
		
		RoleForm roleForm = (RoleForm) form;
		roleForm.reset(mapping, request);
		
		actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("create.success", MessageLocator.getMessage(request, "table.role")));
		saveMessages(request, actionMessages);
		
		Logging.info(this, "End Create Success");
		
		return mapping.getInputForward();

	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Update");
		ActionMessages actionMessages = new ActionMessages();
		
		RoleForm roleForm = (RoleForm) form;
		
		RoleDTO roleDTO = new RoleDTO();
		roleDTO.setId(roleForm.getObjectId());
		roleDTO.setName(roleForm.getName());
		
		//RoleService roleService = (RoleService)SpringServiceLocator.getBean(SpringServiceConstants.ROLE_SERVICE);
		RoleService roleService = BaseSpringServiceLocator.getBean(RoleService.class);
		
		roleDTO = roleService.createOrUpdateDTO(roleDTO);
		if(roleForm.getObjectId() < 0) {
			actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("save.success", MessageLocator.getMessage(request, "table.role")));
		} else {
			actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("update.success", MessageLocator.getMessage(request, "table.role")));
		}
		
		roleForm.setObjectId(roleDTO.getId());
		
		saveMessages(request, actionMessages);
		
		Logging.info(this, "End Update Success");
		
		return mapping.getInputForward();
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Delete");
		ActionMessages actionMessages = new ActionMessages();
		
		int id = Integer.parseInt(request.getParameter("objectId"));
		
		//RoleService roleService = (RoleService)SpringServiceLocator.getBean(SpringServiceConstants.ROLE_SERVICE);
		RoleService roleService = BaseSpringServiceLocator.getBean(RoleService.class);
		roleService.delete(id);
		
		actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("delete.success", MessageLocator.getMessage(request, "table.role")));
		saveMessages(request, actionMessages);
		
		Logging.info(this, "End Delete Success");
		
		return list(mapping, form, request, response);
	}
}
