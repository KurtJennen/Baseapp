package be.luxuryoverdosis.framework.web.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import be.luxuryoverdosis.framework.data.to.User;
import be.luxuryoverdosis.framework.web.BaseWebConstants;

public class DateButton implements Tag {
	PageContext pageContext;
	private String property;
	private String tabindex;
	private String value;
	private boolean disabled;
	private String roles;

	public void setProperty(String property) {
		this.property = property;
	}
	
	public void setTabindex(String tabindex) {
		this.tabindex = tabindex;
	}
	
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	
	public void setValue(String value) {
		this.value = value;
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
						//if(seperatedRoles[i].equals(user.getRole().getName())) {
						if(user.getRoles().contains(seperatedRoles[i])) {
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
			
			//<input type="text" name="date" tabindex="1" value="11/07/2007" id="date">
			
			if(enabled && !disabled) {
				out.print("<input type=\"text\" name=\"" + property + "\" tabindex=\"" + tabindex + "\" value=\"" + value + "\" id=\"" + property +  "\">");
			} else {
				out.print("<input type=\"text\" name=\"" + property + "\" tabindex=\"" + tabindex + "\" value=\"" + value + "\" id=\"" + property + "\" disabled=\"disabled\">");
			}
			
			out.print("<script>");
			out.print("doJQueryUiDatepicker('" + property + "');");
			out.print("</script>");
			
		}
		catch (Exception e) {
		}
		return EVAL_BODY_INCLUDE;
	}

	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
}
