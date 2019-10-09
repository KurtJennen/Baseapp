package be.luxuryoverdosis.framework.web.sessionmanager;

import javax.servlet.http.HttpServletRequest;

public class UrlManager {
	public static String composeUrl(HttpServletRequest request, String servletPath) {
	 	String scheme = request.getScheme();
	    String serverName = request.getServerName();
	    int serverPort = request.getServerPort();
	    String contextPath = request.getContextPath();
//	    String servletPath = request.getServletPath();
//	    String pathInfo = request.getPathInfo();
//	    String queryString = request.getQueryString();

	    StringBuilder url = new StringBuilder();
	    url.append(scheme).append("://").append(serverName);

	    if (serverPort != 80 && serverPort != 443) {
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
