package be.luxuryoverdosis.framework.web.message;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.Globals;
import org.apache.struts.util.MessageResources;

public class MessageLocator {	
	public static String getMessage(HttpServletRequest request, String messageKey, String messageValue, String bundleKey) {
		Locale locale = getLocale(request);
		
		if(bundleKey == null) {
			bundleKey = Globals.MESSAGES_KEY;
		}
		
		MessageResources messageResources = (MessageResources)request.getAttribute(Globals.MESSAGES_KEY);
				
		return messageResources.getMessage(locale, messageKey, messageValue);
	}

	public static Locale getLocale(HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		
		Locale locale = (Locale)httpSession.getAttribute(Globals.LOCALE_KEY);
		if(locale == null) {
			locale = Locale.getDefault();
		}
		return locale;
	}
	
	public static String getMessage(HttpServletRequest request, String messageKey, String messageValue) {
		return getMessage(request, messageKey, messageValue, null);
	}
	
	public static String getMessage(HttpServletRequest request, String messageKey) {
		return getMessage(request, messageKey, null, null);
	}	
}
