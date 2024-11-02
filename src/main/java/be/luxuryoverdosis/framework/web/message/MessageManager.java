package be.luxuryoverdosis.framework.web.message;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.Globals;
import org.apache.struts.util.MessageResources;

import be.luxuryoverdosis.framework.BaseConstants;
import be.luxuryoverdosis.framework.base.Config;

public final class MessageManager {
	private  MessageManager() {
	}
	
	private static final String APPLICATION_RESOURCES = "be.luxuryoverdosis.resources.locale.ApplicationResources";
	
	public static void syncDisplayTagWithStruts(final HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		String fmtLocale = (String) Config.getInstance().getValue(javax.servlet.jsp.jstl.core.Config.FMT_LOCALE);
		Locale locale = null;
		if (fmtLocale.length() == BaseConstants.TWEE) {
			locale = new Locale(fmtLocale.substring(BaseConstants.NUL, BaseConstants.TWEE));
		}
		if (fmtLocale.length() == BaseConstants.VIJF) {
			locale = new Locale(fmtLocale.substring(BaseConstants.NUL, BaseConstants.TWEE), fmtLocale.substring(BaseConstants.DRIE, BaseConstants.VIJF));
		}
		if (fmtLocale.length() == BaseConstants.ACHT) {
			locale = new Locale(fmtLocale.substring(BaseConstants.NUL, BaseConstants.TWEE), fmtLocale.substring(BaseConstants.DRIE, BaseConstants.VIJF), fmtLocale.substring(BaseConstants.ZES, BaseConstants.ACHT));
		}
		if (locale == null) {
			locale = Locale.getDefault();
		}
		session.setAttribute(Globals.LOCALE_KEY, locale);
		
		String fmtLocalizationContext = (String) Config.getInstance().getValue(javax.servlet.jsp.jstl.core.Config.FMT_LOCALIZATION_CONTEXT);
		if (fmtLocalizationContext == null || fmtLocalizationContext.equals("")) {
			fmtLocalizationContext = APPLICATION_RESOURCES;
		}
		session.setAttribute(Globals.MESSAGES_KEY, MessageResources.getMessageResources(fmtLocalizationContext));
	}
}
