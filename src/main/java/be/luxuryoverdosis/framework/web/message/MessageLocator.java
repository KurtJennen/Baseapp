package be.luxuryoverdosis.framework.web.message;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.Globals;
import org.apache.struts.util.MessageResources;

public final class MessageLocator {
	private MessageLocator() {
	}
	
	public static String getMessage(final HttpServletRequest request, final String messageKey, final Object[] messageValue) {
		Locale locale = getLocale(request);
		
		MessageResources messageResources = (MessageResources) request.getAttribute(Globals.MESSAGES_KEY);
				
		return messageResources.getMessage(locale, messageKey, messageValue);
	}

	public static Locale getLocale(final HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		
		Locale locale = (Locale) httpSession.getAttribute(Globals.LOCALE_KEY);
		if (locale == null) {
			locale = Locale.getDefault();
		}
		return locale;
	}
	
	public static String getMessage(final HttpServletRequest request, final String messageKey) {
		return getMessage(request, messageKey, null);
	}	
}
