package be.luxuryoverdosis.framework.web.tag;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;

import org.apache.commons.lang.StringUtils;

import be.luxuryoverdosis.framework.data.dto.UserDTO;
import be.luxuryoverdosis.framework.web.BaseWebConstants;
import be.luxuryoverdosis.framework.web.enumeration.ButtonTypeEnum;
import be.luxuryoverdosis.framework.web.message.MessageLocator;
import be.luxuryoverdosis.framework.web.ui.ButtonObject;
import be.luxuryoverdosis.framework.web.ui.UiDialogObject;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

public class Button extends BodyTagSupport {
	private static final long serialVersionUID = 1L;
	
	PageContext pageContext;
	private ButtonTypeEnum buttonType = ButtonTypeEnum.DEFAULT;
	private String method;
	private String image;
	private String key;
	private boolean showKey = false;
	private String messageKey =  "message.confirm";
	private String roles;
	private String type = "submit";
	private String dialogId;
	
	private ButtonObject buttonObject;
	private UiDialogObject uiDialogObject;
	
	public void setButtonType(ButtonTypeEnum buttonType) {
		this.buttonType = buttonType;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public void setShowKey(boolean showKey) {
		this.showKey = showKey;
	}
	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setDialogId(String dialogId) {
		this.dialogId = dialogId;
	}
	
	public ButtonObject getButtonObject() {
		return buttonObject;
	}
	public void setButtonObject(ButtonObject buttonObject) {
		this.buttonObject = buttonObject;
	}
	public UiDialogObject getUiDialogObject() {
		return uiDialogObject;
	}
	public void setUiDialogObject(UiDialogObject uiDialogObject) {
		this.uiDialogObject = uiDialogObject;
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
//			JspWriter out = pageContext.getOut();
			HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
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
//			
//			if(enabled) {
//				if(StringUtils.isEmpty(dialogId)) {
//					out.print("<button onclick=\"javascript:doAction('" + method + "');\" title=\"" + MessageLocator.getMessage(request, key) + "\" type=\"" + type + "\">");
//				} else {
//					out.print("<button onclick=\"javascript:doDialogCopy('" + dialogId + "','" + method + "');\" title=\"" + MessageLocator.getMessage(request, key) + "\" type=\"" + type + "\">");
//				}
//				out.print("<img src=\"images/" + image + "\"/>");
//				if(showKey) {
//					out.print("&nbsp;");
//					out.print(MessageLocator.getMessage(request, key));
//				}
//				out.println("</button>");
				buttonObject = new ButtonObject();
				buttonObject.setButtonType(buttonType.getCode());
				if(StringUtils.contains(method, BaseWebConstants.DELETE)) {
					buttonObject.setButtonType(ButtonTypeEnum.CONFIRM.getCode());
				}
				buttonObject.setMethod(method);
				buttonObject.setImage(image);
				buttonObject.setTitle(MessageLocator.getMessage(request, key));
				buttonObject.setShowKey(showKey);
				buttonObject.setType(type);
				buttonObject.setDialogId(dialogId);
				
				if(ButtonTypeEnum.CONFIRM.getCode().equals(buttonObject.getButtonType())) {
					buttonObject.setType("button");
					
					uiDialogObject = new UiDialogObject();
					uiDialogObject.setId(method);
					uiDialogObject.setMethod(method);
					
					uiDialogObject.setTitle(MessageLocator.getMessage(request, key));
					uiDialogObject.setMessage(MessageLocator.getMessage(request, messageKey));
					
					uiDialogObject.setYesLabel(MessageLocator.getMessage(request, "yes"));
					uiDialogObject.setNoLabel(MessageLocator.getMessage(request, "no"));
					
					uiDialogObject.setWidth("250");
					uiDialogObject.setHeight("150");
					uiDialogObject.setAutoOpen(false);
					uiDialogObject.setModal(true);
					uiDialogObject.setDefaultYesButton(true);
					uiDialogObject.setDefaultNoButton(true);
					
					Object dialog = request.getAttribute(id + StringUtils.capitalize(BaseWebConstants.DIALOG));
					if(dialog != null && (boolean)dialog) {
						uiDialogObject.setAutoOpen(true);
					}
				}
//			}
		}
		catch (Exception e) {
		}
		return EVAL_BODY_BUFFERED;
	}

	public int doEndTag() throws JspException {
		try {
			HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
			UserDTO userDTO = (UserDTO)request.getSession().getAttribute(BaseWebConstants.USER);
			
			boolean enabled = false;
			if(userDTO != null) {
				if(roles != null) {
					String[] seperatedRoles = roles.split(",");
					for(int i = 0; i < seperatedRoles.length; i++) {
						if(userDTO.getRoles().contains(seperatedRoles[i])) {
							enabled = true;
						}
					}
				} else {
					enabled = true;
				}
			} else {
				enabled = true;
			}
			
			if(enabled) {
				JspWriter out = pageContext.getOut();
				
				Configuration configuration = new Configuration();
				configuration.setClassForTemplateLoading(this.getClass(), "../../../resources/templates/");
				configuration.setDefaultEncoding("UTF-8");
				configuration.setLocale(Locale.US);
				configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
				
				Map<String, Object> templateData = new HashMap<String, Object>();
				templateData.put("templateData", buttonObject);
				
				Template template = configuration.getTemplate("buttonTemplate.ftl");
				template.process(templateData, out);
				
				if(ButtonTypeEnum.CONFIRM.getCode().equals(buttonObject.getButtonType())) {
					templateData = new HashMap<String, Object>();
					templateData.put("templateData", uiDialogObject);
					
					template = configuration.getTemplate("uiConfirmDialogTemplate.ftl");
					template.process(templateData, out);
				}
			}
		}
		catch (Exception e) {
		}
		
		return EVAL_PAGE;
	}
}
