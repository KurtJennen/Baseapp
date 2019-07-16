package be.luxuryoverdosis.framework.web.exception;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;

import be.luxuryoverdosis.framework.web.BaseWebConstants;
import be.luxuryoverdosis.framework.web.message.MessageLocator;

public class ApplicationExceptionHandler extends ExceptionHandler {

	public ActionForward execute(Exception ex, ExceptionConfig ae, ActionMapping mapping, ActionForm formInstance, HttpServletRequest request, HttpServletResponse response) throws ServletException {
		ActionMessages actionMessages = new ActionMessages();
		
		String destination = Globals.ERROR_KEY;
		ActionForward actionForward = mapping.findForward(BaseWebConstants.FAILED);
				
		
		if(ex instanceof ServiceException) {
			ServiceException sex = (ServiceException) ex;
			destination = Globals.MESSAGE_KEY;
			
			if(sex.getValues() == null) {
				actionMessages.add(destination, new ActionMessage(sex.getKey()));
			} else {
				String[] messages = new String[sex.getValues().length];
				for (int i = 0; i < sex.getValues().length; i++) {
					String message = MessageLocator.getMessage(request, sex.getValues()[i]);
					if (message == null) {
						messages[i] = sex.getValues()[i];
					} else {
						messages[i] = message;
					}
				}
				actionMessages.add(destination, new ActionMessage(sex.getKey(), messages));
			}
			actionForward = mapping.getInputForward();

			request.setAttribute(BaseWebConstants.ERROR, 1);
		} else if(ex instanceof ApplicationException) {
			ApplicationException aex = (ApplicationException) ex;
			actionMessages.add(destination, new ActionMessage(aex.getApplicationMessage()));
		} else {
			actionMessages.add(destination, new ActionMessage(ex.fillInStackTrace().toString() , false));
			for (int i = 0; i < ex.getStackTrace().length; i++) {
				StackTraceElement element = ex.getStackTrace()[i];
				actionMessages.add(destination, new ActionMessage(element.toString(), false));
			}
		}
		
		request.setAttribute(destination, actionMessages);
		
		return actionForward;
	}

}
