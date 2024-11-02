package be.luxuryoverdosis.framework.web.tag;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;

import be.luxuryoverdosis.framework.data.dto.UserDTO;
import be.luxuryoverdosis.framework.web.BaseWebConstants;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

public class CommonTag extends BodyTagSupport {
	private static final long serialVersionUID = 1L;
	
	private PageContext pageContext;
	private String roles;
	
	public void setRoles(final String roles) {
		this.roles = roles;
	}
	
	public void setParent(final Tag t) {
	}
	
	public void setPageContext(final PageContext p) {
		pageContext = p;
	}
	public PageContext getPageContext() {
		return pageContext;
	}

	public void release() {
	}
	
	public Tag getParent() {
		return null;
	}

	public boolean isEnabled() {
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		UserDTO userDTO = (UserDTO) request.getSession().getAttribute(BaseWebConstants.USER);
		
		boolean enabled = false;
		if (userDTO != null) {
			if (roles != null) {
				String[] seperatedRoles = roles.split(",");
				for (int i = 0; i < seperatedRoles.length; i++) {
					if (userDTO.getRoles().contains(seperatedRoles[i])) {
						enabled = true;
					}
				}
			} else {
				enabled = true;
			}
		} else {
			enabled = true;
		}
		
		return enabled;
	}
	
	public void produceTemplate(final String name, final Object object) {
		try {
			JspWriter out = pageContext.getOut();
			
			Configuration configuration = new Configuration();
			configuration.setClassForTemplateLoading(this.getClass(), "../../../resources/templates/");
			configuration.setDefaultEncoding("UTF-8");
			configuration.setLocale(Locale.US);
			configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
			
			Map<String, Object> templateData = new HashMap<String, Object>();
			templateData.put("templateData", object);
			
			Template template = configuration.getTemplate(name);
			template.process(templateData, out);
		} catch (Exception e) {
		}
	}
}
