package be.luxuryoverdosis.framework.web.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.taglib.TagUtils;

import be.luxuryoverdosis.framework.data.dto.UserDTO;
import be.luxuryoverdosis.framework.web.BaseWebConstants;
import be.luxuryoverdosis.framework.web.message.MessageLocator;

public class AjaxSelect implements Tag {
	PageContext pageContext;
	private String property;
	private String methodAll;
	private String methodOne;
	private String fieldsAll;
	private String fieldsOne;
	private String image = "zoom.png";
	private String size;
	private String maxLength;
    private String width = "90";
    private String maxHeight = "500";
	private String key;
	private String roles;
	private String callbackActionMethodOne = StringUtils.EMPTY;
	private String callbackActionMethodBlur = StringUtils.EMPTY;
	
	public void setProperty(String property) {
		this.property = property;
	}

	public void setMethodAll(String methodAll) {
		this.methodAll = methodAll;
	}

	public void setMethodOne(String methodOne) {
		this.methodOne = methodOne;
	}

	public void setFieldsAll(String fieldsAll) {
		this.fieldsAll = fieldsAll;
	}

	public void setFieldsOne(String fieldsOne) {
		this.fieldsOne = fieldsOne;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public void setMaxLength(String maxLength) {
		this.maxLength = maxLength;
	}
	
    public void setWidth(String width) {
        this.width = width;
    }

    public void setMaxHeight(String maxHeight) {
        this.maxHeight = maxHeight;
    }

	public void setKey(String key) {
		this.key = key;
	}
	
	public void setRoles(String roles) {
		this.roles = roles;
	}

	public void setCallbackActionMethodOne(String callbackActionMethodOne) {
		this.callbackActionMethodOne = callbackActionMethodOne;
	}

	public void setCallbackActionMethodBlur(String callbackActionMethodBlur) {
		this.callbackActionMethodBlur = callbackActionMethodBlur;
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
			UserDTO userDTO = (UserDTO)request.getSession().getAttribute(BaseWebConstants.USER);
			
			boolean enabled = false;
			if(userDTO != null) {
				if(roles != null) {
					String[] seperatedRoles = roles.split(",");
					for(int i = 0; i < seperatedRoles.length; i++) {
						//if(seperatedRoles[i].equals(user.getRole().getName())) {
						if(userDTO.getRoles().contains(seperatedRoles[i])) {
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
				//JavaScript
				
				String[] seperatedArray = fieldsOne.split(",");
				
				StringBuffer concatenatedFields = new StringBuffer();
				concatenatedFields.append("[");
				for (int i = 0; i < seperatedArray.length; i++) {
					concatenatedFields.append("'" + seperatedArray[i] + "'");
					if (i != seperatedArray.length - 1) {
						concatenatedFields.append(",");
					}
				}
				concatenatedFields.append("]");
				
				String searchNoResultKey =  MessageLocator.getMessage(request, "search.no.result");
				
				out.println("<script type=\"text/javascript\">");
				out.println("$(document).ready(function() {");
				out.println("doAjaxSelect('" + property + "', '" + methodAll + "', '" + methodOne + "', " + concatenatedFields.toString() + ", '" + searchNoResultKey + "');");
				out.println("});");
				out.println("</script>");
				
				//Javascript template
				seperatedArray = fieldsAll.split(",");
				
				out.println("<script id=\"" + property + "Tmpl\" type=\"text/x-query-tmpl\">");
				out.println("<tr id=\"\\${id}\" onclick=\"javascript:doAjaxSelectClicked('" + property + "', '" + methodOne + "', ${id}, " + concatenatedFields.toString() + ", '" + callbackActionMethodOne  + "');\" onmouseover=\"this.style.background='#fdecae'\" onmouseout=\"this.style.background='#e3e3e3'\">");
				for (int i = 0; i < seperatedArray.length; i++) {
					out.println("<td>${" + seperatedArray[i] + "}</td>");
				}
				out.println("</tr>");
				out.println("</script>");
				
				//Search
				Object waarde = TagUtils.getInstance().lookup(pageContext, "org.apache.struts.taglib.html.BEAN", property, null);
				
				out.println("<input type=\"hidden\" name=\"" + property + "\" value=\"" + waarde.toString() + "\" id=\"" + property + "\">");
				out.println("<input type=\"text\" name=\"" + property + "Value\" maxlength=\"" + maxLength + "\" size=\"" + size + "\" value=\"\" id=\"" + property + "Value\" onblur=\"javascript:doAjaxBlur('" + property + "', '" + callbackActionMethodBlur  + "')\" autocomplete=\"off\">");
				out.println("<button id=\"" + property + "Button\" type=\"button\" title=\"" + MessageLocator.getMessage(request, key) + "\">");
				out.println("<img src=\"images/" + image + "\"/>");
				out.println("</button>");

				//Template
                out.println("<div style=\"width: " + width + "%; max-height:" + maxHeight + "px; position: fixed; overflow: auto; z-index: 1;\">");
				out.println("<table id=\"" + property + "Result\" style=\"background-color: #e3e3e3; border: 1px solid white; width: 100%;\">");
				out.println("</table>");
				out.print("</div>");
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
