package be.luxuryoverdosis.framework.web.action.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionRedirect;
import org.apache.struts.actions.DispatchAction;

import be.luxuryoverdosis.framework.BaseConstants;
import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;
import be.luxuryoverdosis.framework.business.service.interfaces.UserService;
import be.luxuryoverdosis.framework.business.thread.ThreadManager;
import be.luxuryoverdosis.framework.data.dto.UserDTO;
import be.luxuryoverdosis.framework.data.wrapperdto.LoginWrapperDTO;
import be.luxuryoverdosis.framework.logging.Logging;
import be.luxuryoverdosis.framework.web.BaseWebConstants;
import be.luxuryoverdosis.framework.web.form.LoginForm;
import be.luxuryoverdosis.framework.web.message.MessageLocator;
import be.luxuryoverdosis.framework.web.message.MessageManager;
import be.luxuryoverdosis.framework.web.sessionmanager.SessionManager;
import net.sf.navigator.menu.MenuRepository;

public class LoginAction extends DispatchAction {
	public ActionForward index(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Index");
		
		LoginForm loginForm = (LoginForm) form;
		loginForm.reset(mapping, request);
		
		loginForm.setActivation(getUserService().isActiviation());
				
		MessageManager.syncDisplayTagWithStruts(request);
		
		Logging.info(this, "End Search Success");
		
		return (mapping.getInputForward());
	}
	
	public ActionForward login(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Login");
		ActionMessages actionMessages = new ActionMessages();
		
		LoginForm loginForm = (LoginForm) form;
		
		MenuRepository menuRepository = (MenuRepository) request.getSession().getServletContext().getAttribute(MenuRepository.MENU_REPOSITORY_KEY);
		menuRepository.reload();
		
		LoginWrapperDTO loginWrapperDTO = getUserService().getLoginWrapperDTO(loginForm.getName(), menuRepository);
		
		UserDTO userDTO = loginWrapperDTO.getUserDTO();
		boolean activation = loginWrapperDTO.isActivation();
		int days = loginWrapperDTO.getDays();
		
		if (userDTO == null) {
			return addActionMessage(mapping, request, actionMessages, MessageLocator.getMessage(request, "login.user"));
		}
		if (userDTO != null && (!loginForm.getName().equals(userDTO.getName()) || !loginForm.getPassword().equals(userDTO.getPassword()))) {
			return addActionMessage(mapping, request, actionMessages, MessageLocator.getMessage(request, "login.name.or.password"));
		}
		if (days == 0 && activation) {
			return addActionMessage(mapping, request, actionMessages, MessageLocator.getMessage(request, "login.reset"));
		}
		
		if (days > 0 && days <= UserService.DAYS_OF_WARNING && activation) {			
			actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("login.warning", String.valueOf(days)));
		}
		actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("login.success", MessageLocator.getMessage(request, "login")));
		saveMessages(request, actionMessages);
		
		SessionManager.putInSession(request, BaseWebConstants.USER, userDTO);
		ThreadManager.setUserOnThread(userDTO);
		request.getSession().setMaxInactiveInterval(BaseConstants.ZESTIG * BaseConstants.ZESTIG * BaseConstants.DUIZEND);
		
		request.getSession().getServletContext().setAttribute(MenuRepository.MENU_REPOSITORY_KEY, loginWrapperDTO.getMenuRepository());
					
		Logging.info(this, "End Login Success");
		return (mapping.findForward(BaseWebConstants.SUCCESS));
	}

	private ActionForward addActionMessage(final ActionMapping mapping, final HttpServletRequest request, final ActionMessages actionMessages, final String message) {
		actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("login.failed", message));
		saveMessages(request, actionMessages);
		request.setAttribute(BaseWebConstants.ERROR, 1);
		Logging.error(this, "End Login Failed");
		return (mapping.findForward(BaseWebConstants.FAILED));
	}
	
	public ActionForward logout(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Logout");
		
		LoginForm loginForm = (LoginForm) form;
		loginForm.reset(mapping, request);
		request.getSession().removeAttribute(BaseWebConstants.USER);
		request.getSession().invalidate();
		
		Logging.error(this, "End Logout Failed");
		
		return new ActionRedirect(mapping.findForward(BaseWebConstants.LOGIN));
	}
	
	public ActionForward register(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Register");
		Logging.info(this, "End Register Success");
		
		return (mapping.findForward(BaseWebConstants.REGISTER));
	}
	
	private UserService getUserService() {
		return BaseSpringServiceLocator.getBean(UserService.class);
	}
}
