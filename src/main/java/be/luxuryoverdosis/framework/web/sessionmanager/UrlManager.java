package be.luxuryoverdosis.framework.web.sessionmanager;

import javax.servlet.http.HttpServletRequest;

import be.luxuryoverdosis.framework.BaseConstants;

public final class UrlManager {
	private UrlManager() {
	}
	
	public static String composeUrl(final HttpServletRequest request, final String servletPath) {
	 	String scheme = request.getScheme();
	    String serverName = request.getServerName();
	    int serverPort = request.getServerPort();
	    String contextPath = request.getContextPath();
//	    String servletPath = request.getServletPath();
//	    String pathInfo = request.getPathInfo();
//	    String queryString = request.getQueryString();

	    StringBuilder url = new StringBuilder();
	    url.append(scheme).append("://").append(serverName);

	    if (serverPort != BaseConstants.TACHTIG && serverPort != BaseConstants.VIERHONDERDRIEENVEERTIG) {
	        url.append(":").append(serverPort);
	    }

	    url.append(contextPath);
	    url.append(servletPath);

//	    if (pathInfo != null) {
//	        url.append(pathInfo);
//	    }
//	    if (queryString != null) {
//	        url.append("?").append(queryString);
//	    }
	    
	    return url.toString();
	}
}
