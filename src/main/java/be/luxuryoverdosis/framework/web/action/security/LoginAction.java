package be.luxuryoverdosis.framework.web.action.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.navigator.menu.MenuRepository;
import net.sf.navigator.util.LoadableResourceException;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import be.luxuryoverdosis.framework.business.encryption.Encryption;
import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;
import be.luxuryoverdosis.framework.business.service.interfaces.MenuService;
import be.luxuryoverdosis.framework.business.service.interfaces.UserService;
import be.luxuryoverdosis.framework.business.thread.ThreadManager;
import be.luxuryoverdosis.framework.data.to.User;
import be.luxuryoverdosis.framework.logging.Logging;
import be.luxuryoverdosis.framework.web.BaseWebConstants;
import be.luxuryoverdosis.framework.web.form.LoginForm;
import be.luxuryoverdosis.framework.web.message.MessageLocator;
import be.luxuryoverdosis.framework.web.message.MessageManager;
import be.luxuryoverdosis.framework.web.sessionmanager.SessionManager;

public class LoginAction extends DispatchAction {
	public ActionForward index(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Index");
		
		LoginForm loginForm = (LoginForm) form;
		loginForm.reset(mapping, request);
				
		MessageManager.syncDisplayTagWithStruts(request);
		
		Logging.info(this, "End Search Success");
		
		return (mapping.getInputForward());
	}
	
	public ActionForward login(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Login");
		ActionMessages actionMessages = new ActionMessages();
		
		LoginForm loginForm = (LoginForm) form;
		
		String encryptedPassword = Encryption.encode(loginForm.getPassword());
		
		//UserService userService = (UserService)SpringServiceLocator.getBean(SpringServiceConstants.USER_SERVICE);
		UserService userService = BaseSpringServiceLocator.getBean(UserService.class);
		
		User user = userService.readName(loginForm.getName());
		if(user != null && loginForm.getName().equals(user.getName()) && encryptedPassword.equals(user.getEncryptedPassword())) {
			actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("login.success", MessageLocator.getMessage(request, "login")));
			saveMessages(request, actionMessages);
			
			int days = userService.daysBeforeDeactivate(user);
			if(days == 0) {
				user = userService.deactivate(user.getId());
				loginForm.setDeactivation(true);
				loginForm.setDeactivationMessage(MessageLocator.getMessage(request, "login.reset"));
			}	
			if(days > 0 && days <= UserService.DAYS_OF_WARNING){			
				loginForm.setDeactivation(true);
				loginForm.setDeactivationMessage(MessageLocator.getMessage(request, "login.warning", String.valueOf(days)));
			}
			
			SessionManager.putInSession(request, BaseWebConstants.USER, user);
			ThreadManager.setUserOnThread(user);
			request.getSession().setMaxInactiveInterval(60 * 60 * 1000);
			
			alterMenu(request);
						
			Logging.info(this, "End Login Success");
			return (mapping.findForward("success"));
		} else {
			actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("login.failed", MessageLocator.getMessage(request, "login")));
			saveMessages(request, actionMessages);
			request.setAttribute(BaseWebConstants.ERROR, 1);
			Logging.error(this, "End Login Failed");
			return (mapping.findForward("failed"));
		}
	}

	private void alterMenu(HttpServletRequest request) throws LoadableResourceException {
		MenuRepository menuRepository = (MenuRepository) request.getSession().getServletContext().getAttribute(MenuRepository.MENU_REPOSITORY_KEY);
		menuRepository.reload();
		
		//MenuService menuService = (MenuService)SpringServiceLocator.getBean(SpringServiceConstants.MENU_SERVICE);
		MenuService menuService = BaseSpringServiceLocator.getBean(MenuService.class);
		menuRepository = menuService.produceAlterredMenu(menuRepository);
		request.getSession().getServletContext().setAttribute(MenuRepository.MENU_REPOSITORY_KEY, menuRepository);
	}
	
	public ActionForward logout(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Logout");
		
		LoginForm loginForm = (LoginForm) form;
		loginForm.reset(mapping, request);
		request.getSession().removeAttribute(BaseWebConstants.USER);
		request.getSession().invalidate();
		
		Logging.error(this, "End Logout Failed");
		
		return (mapping.getInputForward());
	}
	
	public ActionForward register(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Register");
		Logging.info(this, "End Register Success");
		
		return (mapping.findForward("register"));
	}
}
