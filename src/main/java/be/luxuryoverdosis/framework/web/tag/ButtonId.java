package be.luxuryoverdosis.framework.web.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import be.luxuryoverdosis.framework.data.to.UserTO;
import be.luxuryoverdosis.framework.web.BaseWebConstants;
import be.luxuryoverdosis.framework.web.message.MessageLocator;

public class ButtonId implements Tag {
	PageContext pageContext;
	private String method;
	private String image;
	private String key;
	private String roles;
	
	public void setMethod(String method) {
		this.method = method;
	}

	public void setImage(String image) {
		this.image = image;
	}

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
			UserTO userTO = (UserTO)request.getSession().getAttribute(BaseWebConstants.USER);
			
			boolean enabled = false;
			if(userTO != null) {
				if(roles != null) {
					String[] seperatedRoles = roles.split(",");
					for(int i = 0; i < seperatedRoles.length; i++) {
						if(seperatedRoles[i].equals(userTO.getRole().getName())) {
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
				out.print("<button onclick=\"javascript:doActionId('" + method + "',this);\" title=\"" + MessageLocator.getMessage(request, key) + "\">");
				out.print("<img src=\"images/" + image + "\"/>");
				out.println("</button>");
			} else {
//				int pos = image.indexOf(".");
//				StringBuffer newImage = new StringBuffer();
//				newImage.append(image.substring(0, pos)); 
//				newImage.append("_disabled");
//				newImage.append(image.substring(pos)); 
//				
//				out.print("<button>");
//				out.print("<img src=\"images/" + newImage.toString() + "\" title=\"" + MessageLocator.getMessage(request, key) + "\" />");
//				out.println("</button>");
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
