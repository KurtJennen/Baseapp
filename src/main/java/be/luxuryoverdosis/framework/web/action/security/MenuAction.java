package be.luxuryoverdosis.framework.web.action.security;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.navigator.menu.MenuRepository;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;
import be.luxuryoverdosis.framework.business.service.interfaces.MenuService;
import be.luxuryoverdosis.framework.business.service.interfaces.UserService;
import be.luxuryoverdosis.framework.business.thread.ThreadManager;
import be.luxuryoverdosis.framework.data.to.User;
import be.luxuryoverdosis.framework.logging.Logging;
import be.luxuryoverdosis.framework.web.BaseWebConstants;
import be.luxuryoverdosis.framework.web.form.MenuForm;
import be.luxuryoverdosis.framework.web.message.MessageLocator;
import be.luxuryoverdosis.framework.web.sessionmanager.SessionManager;

public class MenuAction extends DispatchAction {
	
	private void storeListsInSession(HttpServletRequest request, ActionMessages actionMessages) {
		SessionManager.delete(request, SessionManager.TYPE_ATTRIBUTES, SessionManager.SUBTYPE_LIST);
		
		//UserService userService = (UserService)SpringServiceLocator.getBean(SpringServiceConstants.USER_SERVICE);
		UserService userService = BaseSpringServiceLocator.getBean(UserService.class);
		ArrayList<User> userList = new ArrayList<User>();
		userList = userService.list();
		actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("list.success", MessageLocator.getMessage(request, "table.user")));
		SessionManager.putInSession(request, BaseWebConstants.USER_LIST, userList);
	}
	
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin List");
		ActionMessages actionMessages = new ActionMessages();
		
		SessionManager.delete(request, SessionManager.TYPE_ATTRIBUTES, SessionManager.SUBTYPE_IDS);
		
		storeListsInSession(request, actionMessages);
		
		User user = ThreadManager.getUserFromThread();
		
		MenuRepository menuRepository = (MenuRepository) request.getSession().getServletContext().getAttribute(MenuRepository.MENU_REPOSITORY_KEY);
		//MenuService menuService = (MenuService)SpringServiceLocator.getBean(SpringServiceConstants.MENU_SERVICE);
		MenuService menuService = BaseSpringServiceLocator.getBean(MenuService.class);
		
		MenuForm menuForm = (MenuForm) form;
		if(menuForm.getUserId() < 0) {
			menuForm.setUserId(user.getId());
		}
		menuForm.setMenus(menuService.produceMenu(menuRepository, menuForm.getUserId()));
		
		actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("read.success", MessageLocator.getMessage(request, "table.menu")));
		saveMessages(request, actionMessages);
		
		Logging.info(this, "End List Success");
		
		return mapping.getInputForward();
	}
	
	public ActionForward changeUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin List");
		ActionMessages actionMessages = new ActionMessages();
		
		storeListsInSession(request, actionMessages);
		
		MenuRepository menuRepository = (MenuRepository) request.getSession().getServletContext().getAttribute(MenuRepository.MENU_REPOSITORY_KEY);
		//MenuService menuService = (MenuService)SpringServiceLocator.getBean(SpringServiceConstants.MENU_SERVICE);
		MenuService menuService = BaseSpringServiceLocator.getBean(MenuService.class);
		
		MenuForm menuForm = (MenuForm) form;
		menuForm.setMenus(menuService.produceMenu(menuRepository, menuForm.getUserId()));
		
		actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("read.success", MessageLocator.getMessage(request, "table.menu")));
		saveMessages(request, actionMessages);
		
		Logging.info(this, "End List Success");
		
		return mapping.getInputForward();
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Save");
		ActionMessages actionMessages = new ActionMessages();
		
		MenuRepository menuRepository = (MenuRepository) request.getSession().getServletContext().getAttribute(MenuRepository.MENU_REPOSITORY_KEY);
		//MenuService menuService = (MenuService)SpringServiceLocator.getBean(SpringServiceConstants.MENU_SERVICE);
		MenuService menuService = BaseSpringServiceLocator.getBean(MenuService.class);
		
		MenuForm menuForm = (MenuForm) form;
		menuService.updateMenu(menuRepository, menuForm.getIds(), menuForm.getHidden(), menuForm.getDisabled(), menuForm.getForPay(), menuForm.getPayed(), menuForm.getUserId());
		
		actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("save.success", MessageLocator.getMessage(request, "table.menu")));
		saveMessages(request, actionMessages);
		
		Logging.info(this, "End Save Success");
		
		return list(mapping, menuForm, request, response);
	}
	
	public ActionForward disabled(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Disabled");
		Logging.info(this, "End Disabled");
		
		return mapping.findForward("disabled");
	}
	
	public ActionForward forPay(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin ForPay");
		Logging.info(this, "End ForPay");
		
		return mapping.findForward("forPay");
	}


}
