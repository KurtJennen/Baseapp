package be.luxuryoverdosis.framework.web.tag;

import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.ReflectionUtils;

import be.luxuryoverdosis.framework.BaseConstants;
import be.luxuryoverdosis.framework.data.to.User;
import be.luxuryoverdosis.framework.web.BaseWebConstants;
import be.luxuryoverdosis.framework.web.message.MessageLocator;

public class EnumSelect implements Tag {
	PageContext pageContext;
	private String clazz;
	private String method;
	private String property;
	private String tabindex;
	private String value;
	private boolean disabled;
	private String roles;
	private String onchange = StringUtils.EMPTY;
	
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public void setTabindex(String tabindex) {
		this.tabindex = tabindex;
	}
	
	public void setValue(String value) {
		this.value = value;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public void setOnchange(String onchange) {
		this.onchange = onchange;
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
			
			List<String> keyList = getKeysForClass();
			
			if(enabled && !disabled) {
				out.print("<select name=\"" + property + "\" tabindex=\"" + tabindex + "\" onchange=\"" + onchange + "\">");
			} else {
				out.print("<select name=\"" + property + "\" tabindex=\"" + tabindex + "\" onchange=\"" + onchange + "\" disabled=\"disabled\">");
			}
	    	
			for(String key : keyList) {
				String keyMessage = getKeyMessage(request, key);
				if(key.equals(value)) {
					out.print("<option value=\"" + key + "\" selected=\"selected\">" + keyMessage + "</option>");
				} else {
					out.print("<option value=\"" + key + "\">" + keyMessage + "</option>");
				}
			}
			
			out.println("</select>");
		}
		catch (Exception e) {
		}
		return EVAL_BODY_INCLUDE;
	}

	private String getKeyMessage(HttpServletRequest request, String key) {
		String[] keys = clazz.split("\\.");
		String keyLabel = MessageLocator.getMessage(request, keys[keys.length - 1] + BaseConstants.POINT + key);
		return keyLabel;
	}

	@SuppressWarnings("unchecked")
	private List<String> getKeysForClass() throws ClassNotFoundException {
		Class<?> enumClass = Class.forName(clazz);
		
		Method enumMethod = ReflectionUtils.findMethod(enumClass, method);
		
		return (List<String>) ReflectionUtils.invokeMethod(enumMethod, null);
	}

	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
}
