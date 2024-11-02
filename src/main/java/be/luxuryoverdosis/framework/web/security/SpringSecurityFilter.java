package be.luxuryoverdosis.framework.web.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import be.luxuryoverdosis.framework.BaseConstants;
import be.luxuryoverdosis.framework.business.encryption.Encryption;
import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;
import be.luxuryoverdosis.framework.business.service.interfaces.UserService;
import be.luxuryoverdosis.framework.business.thread.ThreadManager;
import be.luxuryoverdosis.framework.data.dto.UserDTO;


public class SpringSecurityFilter implements Filter {
	
	public void init(final FilterConfig filterConfig) throws ServletException {
		//System.out.println("SpringSecurityFilter Initialized");
	}

	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain filterChain) throws IOException, ServletException {
		//System.out.println("SpringSecurityFilter");
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		//HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		
		String authorizationHeader = httpServletRequest.getHeader(BaseConstants.AUTHORIZATION);
		if (authorizationHeader != null) {
			String base64Credentials = authorizationHeader.substring(BaseConstants.BASIC.length()).trim();
	        String credentials = Encryption.decode(base64Credentials);
	        final String[] values = credentials.split(":", 2);
	        
	        UserDTO userDTO = getUserService().readNameDTO(values[0]);
	        if (userDTO != null && userDTO.getPassword().equals(values[1])) {
	        	ThreadManager.setUserOnThread(userDTO);
	        	filterChain.doFilter(request, response);
//	        	try {
//					ResponseTool.writeResponseForAuthentication(httpServletResponse, BaseSpringServiceLocator.getMessage("security.access.denied"));
//				} catch (Exception e) {
//					throw new ServiceException("errors.exception.type", new String[]{e.getClass().getName().toLowerCase()});
//				}
	        } else {
	        	//ThreadManager.setUserOnThread(user);
	        	filterChain.doFilter(request, response);
	        }
		} else {
			filterChain.doFilter(request, response);
		}
        
	}
	
	public void destroy() {
		//System.out.println("SpringSecurityFilter Destroyed");
	}
	
	private UserService getUserService() {
		return BaseSpringServiceLocator.getBean(UserService.class);
	}
	
}
