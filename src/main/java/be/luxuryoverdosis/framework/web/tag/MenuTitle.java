package be.luxuryoverdosis.framework.web.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import be.luxuryoverdosis.framework.data.to.User;
import be.luxuryoverdosis.framework.web.BaseWebConstants;
import be.luxuryoverdosis.framework.web.message.MessageLocator;

public class MenuTitle implements Tag {
	PageContext pageContext;
	private String key;
	private String roles;

	public void setKey(String key) {
		this.key = key;
	}
	
	public void setRoles(String roles) {
		this.roles = roles;
	}

	public void setParent(Tag t) {
	}
	
	public void setPageContext(PageContext p) {
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
			HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
			User user = (User)request.getSession().getAttribute(BaseWebConstants.USER);
			
			boolean enabled = false;
			if(user != null) {
				if(roles != null) {
					String[] seperatedRoles = roles.split(",");
					for(int i = 0; i < seperatedRoles.length; i++) {
						if(seperatedRoles[i].equals(user.getRole().getName())) {
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
			
			if(enabled) {
				out.print(MessageLocator.getMessage(request, key));
				out.println("<br />");
			}
		}
		catch (Exception e) {
		}
		return EVAL_BODY_INCLUDE;
	}

	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
}
