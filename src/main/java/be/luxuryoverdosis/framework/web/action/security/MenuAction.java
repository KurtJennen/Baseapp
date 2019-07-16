package be.luxuryoverdosis.framework.web.action.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;
import be.luxuryoverdosis.framework.business.service.interfaces.MenuService;
import be.luxuryoverdosis.framework.business.thread.ThreadManager;
import be.luxuryoverdosis.framework.data.to.User;
import be.luxuryoverdosis.framework.data.wrapperdto.MenuWrapperDTO;
import be.luxuryoverdosis.framework.logging.Logging;
import be.luxuryoverdosis.framework.web.BaseWebConstants;
import be.luxuryoverdosis.framework.web.form.MenuForm;
import be.luxuryoverdosis.framework.web.message.MessageLocator;
import be.luxuryoverdosis.framework.web.sessionmanager.SessionManager;
import net.sf.navigator.menu.MenuRepository;

public class MenuAction extends DispatchAction {
	
	private void storeListsInSession(HttpServletRequest request, ActionMessages actionMessages, ActionForm form) throws Exception {
		SessionManager.delete(request, SessionManager.TYPE_ATTRIBUTES, SessionManager.SUBTYPE_LIST);
		
		User user = ThreadManager.getUserFromThread();
		
		MenuForm menuForm = (MenuForm) form;
		if(menuForm.getUserId() < 0) {
			menuForm.setUserId(user.getId());
		}
		
		MenuWrapperDTO menuWrapperDTO = getMenuService().getMenuWrapperDTO(getMenuRepository(request), menuForm.getUserId());
		
		menuForm.setMenus(menuWrapperDTO.getMenuDTOList());
		actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("read.success", MessageLocator.getMessage(request, "table.menu")));
		
		SessionManager.putInSession(request, BaseWebConstants.USER_LIST, menuWrapperDTO.getUserList());
		actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("list.success", MessageLocator.getMessage(request, "table.user")));
	}
	
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin List");
		ActionMessages actionMessages = new ActionMessages();
		
		SessionManager.delete(request, SessionManager.TYPE_ATTRIBUTES, SessionManager.SUBTYPE_IDS);
		
		storeListsInSession(request, actionMessages, form);
		
		Logging.info(this, "End List Success");
		
		return mapping.getInputForward();
	}
	
	public ActionForward changeUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin ChangeUser");
		ActionMessages actionMessages = new ActionMessages();
		
		storeListsInSession(request, actionMessages, form);
		
		Logging.info(this, "End ChangeUser Success");
		
		return mapping.getInputForward();
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Update");
		ActionMessages actionMessages = new ActionMessages();
		
		MenuRepository menuRepository = (MenuRepository) request.getSession().getServletContext().getAttribute(MenuRepository.MENU_REPOSITORY_KEY);
		
		MenuForm menuForm = (MenuForm) form;
		getMenuService().updateMenu(menuRepository, menuForm.getIds(), menuForm.getJaNeeTypeHidden(), menuForm.getJaNeeTypeDisabled(), menuForm.getJaNeeTypeForPay(), menuForm.getJaNeeTypePayed(), menuForm.getUserId());
		
		actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("save.success", MessageLocator.getMessage(request, "table.menu")));
		saveMessages(request, actionMessages);
		
		Logging.info(this, "End pdate Success");
		
		return list(mapping, menuForm, request, response);
	}
	
	public ActionForward disabled(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Disabled");
		Logging.info(this, "End Disabled");
		
		return mapping.findForward(BaseWebConstants.DISABLED);
	}
	
	public ActionForward forPay(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin ForPay");
		Logging.info(this, "End ForPay");
		
		return mapping.findForward(BaseWebConstants.FOR_PAY);
	}

	private MenuService getMenuService() {
		return BaseSpringServiceLocator.getBean(MenuService.class);
	}
	
	private MenuRepository getMenuRepository(HttpServletRequest request) {
		return (MenuRepository) request.getSession().getServletContext().getAttribute(MenuRepository.MENU_REPOSITORY_KEY);
	}

}
