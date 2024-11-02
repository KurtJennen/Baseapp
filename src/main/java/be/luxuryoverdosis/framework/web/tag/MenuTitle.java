package be.luxuryoverdosis.framework.web.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import be.luxuryoverdosis.framework.data.dto.UserDTO;
import be.luxuryoverdosis.framework.web.BaseWebConstants;
import be.luxuryoverdosis.framework.web.message.MessageLocator;

@Deprecated
public class MenuTitle implements Tag {
	private PageContext pageContext;
	private String key;
	private String roles;

	public void setKey(final String key) {
		this.key = key;
	}
	
	public void setRoles(final String roles) {
		this.roles = roles;
	}

	public void setParent(final Tag t) {
	}
	
	public void setPageContext(final PageContext p) {
		pageContext = p;
	}
	
	public void release() {
	}
	
	public Tag getParent() {
		return null;
	}
	
	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
			UserDTO userDTO = (UserDTO) request.getSession().getAttribute(BaseWebConstants.USER);
			
			boolean enabled = false;
			if (userDTO != null) {
				if (roles != null) {
					String[] seperatedRoles = roles.split(",");
					for (int i = 0; i < seperatedRoles.length; i++) {
						//if(seperatedRoles[i].equals(user.getRole().getName())) {
						if (userDTO.getRoles().contains(seperatedRoles[i])) {
							enabled = true;
						}
					}
					//pos1 = roles.indexOf(user.getRole().getName());
				} else {
					enabled = true;
				}
			} else {
				enabled = true;
			}
			
			if (enabled) {
				out.print(MessageLocator.getMessage(request, key));
				out.println("<br />");
			}
		} catch (Exception e) {
		}
		return EVAL_BODY_INCLUDE;
	}

	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
}
