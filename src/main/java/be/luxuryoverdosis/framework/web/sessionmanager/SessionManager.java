package be.luxuryoverdosis.framework.web.sessionmanager;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

public final class SessionManager {
	public static final String SUBTYPE_LIST = "List";
	public static final String SUBTYPE_IDS = "Ids";
	public static final String TYPE_ATTRIBUTES = "ATTRIBUTES";
	public static final String TYPE_PARAMETERS = "PARAMETERS";
	
	private SessionManager() {
	}
	
	public static void putInSession(final HttpServletRequest request, final String listname, final Object list) {
		request.getSession().setAttribute(listname, list);
	}
	
	public static Object getFromSession(final HttpServletRequest request, final String listname) {
		return request.getSession().getAttribute(listname);
	}
	
	public static void removeFromSession(final HttpServletRequest request, final String listname) {
		request.getSession().removeAttribute(listname);
	}
	
	public static void delete(final HttpServletRequest request, final String type, final String subType) {
		if (type.equals(TYPE_ATTRIBUTES)) {
			Enumeration<String> attributes = request.getSession().getAttributeNames();
			while (attributes.hasMoreElements()) {
				String attribute = (String) attributes.nextElement();
				if (attribute.endsWith(subType)) {
					request.getSession().removeAttribute(attribute);
				}
			}
		}
		
		if (type.equals(TYPE_PARAMETERS)) {
			Enumeration<String> parameters = request.getSession().getServletContext().getInitParameterNames();
			while (parameters.hasMoreElements()) {
				String parameter = (String) parameters.nextElement();
				if (parameter.endsWith(subType)) {
					request.getSession().removeAttribute(parameter);
				}
			}
		}
	}
}
