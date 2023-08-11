package be.luxuryoverdosis.framework.web.tag;

import javax.servlet.jsp.JspException;

import be.luxuryoverdosis.framework.web.ui.DateButtonObject;

public class DateButton extends CommonTag {
	private static final long serialVersionUID = 1L;
	
//	PageContext pageContext;
	private String property;
	private String tabindex;
	private String value;
	private boolean disabled;
//	private String roles;
	
	private DateButtonObject dateButtonObject;

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
//	public void setRoles(String roles) {
//		this.roles = roles;
//	}

//	public DateButtonObject getDateButtonObject() {
//		return dateButtonObject;
//	}
//	public void setDateButtonObject(DateButtonObject dateButtonObject) {
//		this.dateButtonObject = dateButtonObject;
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
			dateButtonObject = new DateButtonObject();
			dateButtonObject.setProperty(property);
			dateButtonObject.setTabindex(tabindex);
			dateButtonObject.setValue(value);
			dateButtonObject.setDisabled(disabled);
		}
		catch (Exception e) {
		}
		return EVAL_BODY_BUFFERED;
	}

	public int doEndTag() throws JspException {
		try {
//			HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
//			UserDTO userDTO = (UserDTO)request.getSession().getAttribute(BaseWebConstants.USER);
			
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
//				templateData.put("templateData", dateButtonObject);
//				
//				Template template = configuration.getTemplate("dateButtonTemplate.ftl");
//				template.process(templateData, out);
				produceTemplate("dateButtonTemplate.ftl", dateButtonObject);
			}
		}
		catch (Exception e) {
		}
			
		return EVAL_PAGE;
	}
}
