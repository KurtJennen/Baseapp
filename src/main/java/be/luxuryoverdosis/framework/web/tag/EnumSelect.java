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
	
//	PageContext pageContext;
	private String clazz;
	private String method;
	private String property;
	private String tabindex;
	private String value;
	private String onchange = StringUtils.EMPTY;
	private boolean disabled;
//	private String roles;
	
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
//	public void setRoles(String roles) {
//		this.roles = roles;
//	}

//	public void setParent(Tag t) {
//	}
//	
//	public void setPageContext(PageContext p) {
//		pageContext = p;
//	}
//	
//	public void release() {
//	}
//	
//	public Tag getParent() {
//		return null;
//	}
	
	public int doStartTag() throws JspException {
		try {
			HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
			
			enumSelectObject = new EnumSelectObject();
			enumSelectObject.setProperty(property);
			enumSelectObject.setTabindex(tabindex);
			enumSelectObject.setOnchange(onchange);
			enumSelectObject.setDisabled(disabled);
			
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
//			HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
//			UserDTO userDTO = (UserDTO)request.getSession().getAttribute(BaseWebConstants.USER);
//			
//			boolean enabled = false;
//			if(userDTO != null) {
//				if(roles != null) {
//					String[] seperatedRoles = roles.split(",");
//					for(int i = 0; i < seperatedRoles.length; i++) {
//						if(userDTO.getRoles().contains(seperatedRoles[i])) {
//							enabled = true;
//						}
//					}
//				} else {
//					enabled = true;
//				}
//			} else {
//				enabled = true;
//			}
			
			if(isEnabled()) {
//				JspWriter out = pageContext.getOut();
//				
//				Configuration configuration = new Configuration();
//				configuration.setClassForTemplateLoading(this.getClass(), "../../../resources/templates/");
//				configuration.setDefaultEncoding("UTF-8");
//				configuration.setLocale(Locale.US);
//				configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
//				
//				Map<String, Object> templateData = new HashMap<String, Object>();
//				templateData.put("templateData", enumSelectObject);
//				
//				Template template = configuration.getTemplate("enumSelectTemplate.ftl");
//				template.process(templateData, out);
				produceTemplate("enumSelectTemplate.ftl", enumSelectObject);
			}
		}
		catch (Exception e) {
		}
		
		return EVAL_PAGE;
	}
}
