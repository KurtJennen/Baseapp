package be.luxuryoverdosis.framework.web.tag;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.ReflectionUtils;

import be.luxuryoverdosis.framework.BaseConstants;
import be.luxuryoverdosis.framework.web.message.MessageLocator;
import be.luxuryoverdosis.framework.web.ui.EnumSelectObject;
import be.luxuryoverdosis.framework.web.ui.EnumSelectOptionObject;

public class EnumSelect extends CommonTag {
	private static final long serialVersionUID = 1L;
	
	private String clazz;
	private String method;
	private String property;
	private String tabindex;
	private String value;
	private String onchange = StringUtils.EMPTY;
	private boolean disabled;
	private boolean multiple;
	
	private EnumSelectObject enumSelectObject;
	private ArrayList<EnumSelectOptionObject> options; 
	
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
	public void setOnchange(String onchange) {
		this.onchange = onchange;
	}
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	public void setMultiple(boolean multiple) {
		this.multiple = multiple;
	}
	
	public int doStartTag() throws JspException {
		try {
			HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
			
			enumSelectObject = new EnumSelectObject();
			enumSelectObject.setProperty(property);
			enumSelectObject.setTabindex(tabindex);
			enumSelectObject.setOnchange(onchange);
			enumSelectObject.setDisabled(disabled);
			enumSelectObject.setMultiple(multiple);
			
			options = new ArrayList<EnumSelectOptionObject>();
			
			List<String> keyList = getKeysForClass();
			for(String key : keyList) {
				EnumSelectOptionObject enumSelectOptionObject = new EnumSelectOptionObject();
				enumSelectOptionObject.setKey(key);
				enumSelectOptionObject.setKeyMessage(getKeyMessage(request, key));
				if(key.equals(value)) {
					enumSelectOptionObject.setSelected(true);
				}
				
				options.add(enumSelectOptionObject);
			}
			
			enumSelectObject.setOptions(options);
		}
		catch (Exception e) {
		}
		return EVAL_BODY_BUFFERED;
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
		try {
			if(isEnabled()) {
				produceTemplate("enumSelectTemplate.ftl", enumSelectObject);
			}
		}
		catch (Exception e) {
		}
		
		return EVAL_PAGE;
	}
}
