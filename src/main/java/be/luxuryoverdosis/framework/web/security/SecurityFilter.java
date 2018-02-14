package be.luxuryoverdosis.framework.web.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import be.luxuryoverdosis.framework.business.thread.ThreadManager;
import be.luxuryoverdosis.framework.data.to.UserTO;
import be.luxuryoverdosis.framework.web.BaseWebConstants;

public class SecurityFilter implements Filter {
	private static final String UNSECURED = "unsecured"; 
	private static final String LOGIN = "login";
	
	private FilterConfig filterConfig = null;
	
	public void init(FilterConfig filterConfig) throws ServletException {
		//System.out.println("SecurityFilter Initialized");
		this.filterConfig = filterConfig;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		//System.out.println("SecurityFilter");
		
		String unsecurePath = filterConfig.getInitParameter(SecurityFilter.UNSECURED);
		String loginPath = filterConfig.getInitParameter(SecurityFilter.LOGIN);
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String path = httpServletRequest.getServletPath();
		UserTO userTO = (UserTO) httpServletRequest.getSession().getAttribute(BaseWebConstants.USER);
		
		if(!path.startsWith(unsecurePath)) {
			if(userTO == null) {
				request.getRequestDispatcher(loginPath).forward(request,response);
			} else {
				ThreadManager.setUserOnThread(userTO);
			}
		}
		
		filterChain.doFilter(request, response);
	}
	
	public void destroy() {
		//System.out.println("SecurityFilter Destroyed");
		this.filterConfig = null;
	}
}
