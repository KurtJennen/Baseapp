package be.luxuryoverdosis.framework.web.message;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.Globals;
import org.apache.struts.util.MessageResources;

import be.luxuryoverdosis.framework.base.Config;

public class MessageManager {
	private static final String APPLICATION_RESOURCES = "be.luxuryoverdosis.resources.locale.ApplicationResources";
	
	public static void syncDisplayTagWithStruts(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		String fmt_locale = (String) Config.getInstance().getValue(javax.servlet.jsp.jstl.core.Config.FMT_LOCALE);
		Locale locale = null;
		if(fmt_locale.length() == 2) {
			locale = new Locale(fmt_locale.substring(0, 2));
		}
		if(fmt_locale.length() == 5) {
			locale = new Locale(fmt_locale.substring(0, 2), fmt_locale.substring(3, 5));
		}
		if(fmt_locale.length() == 8) {
			locale = new Locale(fmt_locale.substring(0, 2), fmt_locale.substring(3, 5), fmt_locale.substring(6, 8));
		}
		if(locale == null) {
			locale = Locale.getDefault();
		}
		session.setAttribute(Globals.LOCALE_KEY, locale);
		
		String fmt_localization_context = (String) Config.getInstance().getValue(javax.servlet.jsp.jstl.core.Config.FMT_LOCALIZATION_CONTEXT);
		if(fmt_localization_context == null || fmt_localization_context.equals("")) {
			fmt_localization_context = APPLICATION_RESOURCES;
		}
		session.setAttribute(Globals.MESSAGES_KEY, MessageResources.getMessageResources(fmt_localization_context));
	}
}
