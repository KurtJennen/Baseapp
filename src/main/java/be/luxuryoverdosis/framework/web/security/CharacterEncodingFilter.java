package be.luxuryoverdosis.framework.web.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter {
	
	public void init(final FilterConfig filterConfig) throws ServletException {
	}

	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain filterChain) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		filterChain.doFilter(request, response);
	}
	
	public void destroy() {
	}
}
