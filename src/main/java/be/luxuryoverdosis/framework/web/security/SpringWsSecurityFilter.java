package be.luxuryoverdosis.framework.web.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.luxuryoverdosis.framework.base.tool.ResponseTool;
import be.luxuryoverdosis.framework.business.encryption.Encryption;
import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;
import be.luxuryoverdosis.framework.business.service.interfaces.UserService;
import be.luxuryoverdosis.framework.data.to.User;
import be.luxuryoverdosis.framework.web.exception.ServiceException;


public class SpringWsSecurityFilter implements Filter {
	
	private static final String BASIC = "Basic";
	private static final String AUTHORIZATION = "authorization";
	
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("SpringWsSecurityFilter Initialized");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		System.out.println("SpringWsSecurityFilter");
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		
		String authorizationHeader = httpServletRequest.getHeader(AUTHORIZATION);
		String base64Credentials = authorizationHeader.substring(BASIC.length()).trim();
        String credentials = Encryption.decode(base64Credentials);
        final String[] values = credentials.split(":",2);
        
        UserService userService = BaseSpringServiceLocator.getBean(UserService.class);
        User user = userService.readName(values[0]);
        if(user == null || !user.getEncryptedPassword().equals(Encryption.encode(values[0]))) {
        	try {
				ResponseTool.writeResponseForAuthentication(httpServletResponse, BaseSpringServiceLocator.getMessage("security.access.denied"));
			} catch (Exception e) {
				throw new ServiceException("errors.exception.type", new String[]{e.getClass().getName().toLowerCase()});
			}
        } else {
        	filterChain.doFilter(request, response);
        }
        
	}
	
	public void destroy() {
		System.out.println("SpringWsSecurityFilter Destroyed");
	}
}
