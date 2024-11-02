package be.luxuryoverdosis.framework.web.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.apache.commons.lang.StringUtils;

import be.luxuryoverdosis.framework.web.BaseWebConstants;
import be.luxuryoverdosis.framework.web.enumeration.ButtonTypeEnum;
import be.luxuryoverdosis.framework.web.message.MessageLocator;
import be.luxuryoverdosis.framework.web.ui.ButtonObject;
import be.luxuryoverdosis.framework.web.ui.UiDialogObject;

public class Button extends CommonTag {
	private static final long serialVersionUID = 1L;
	
	private ButtonTypeEnum buttonType = ButtonTypeEnum.DEFAULT;
	private String method;
	private String image;
	private String key;
	private boolean showKey = false;
	private String messageKey =  "message.confirm";
	private String type = "submit";
	private String dialogId;
	
	private ButtonObject buttonObject;
	private UiDialogObject uiDialogObject;
	
	public void setButtonType(final ButtonTypeEnum buttonType) {
		this.buttonType = buttonType;
	}
	public void setMethod(final String method) {
		this.method = method;
	}
	public void setImage(final String image) {
		this.image = image;
	}
	public void setKey(final String key) {
		this.key = key;
	}
	public void setShowKey(final boolean showKey) {
		this.showKey = showKey;
	}
	public void setMessageKey(final String messageKey) {
		this.messageKey = messageKey;
	}
	public void setType(final String type) {
		this.type = type;
	}
	public void setDialogId(final String dialogId) {
		this.dialogId = dialogId;
	}
	
	public int doStartTag() throws JspException {
		try {
			HttpServletRequest request = (HttpServletRequest) getPageContext().getRequest();
			
			buttonObject = new ButtonObject();
			buttonObject.setButtonType(buttonType.getCode());
			if (StringUtils.contains(method, BaseWebConstants.DELETE)) {
				buttonObject.setButtonType(ButtonTypeEnum.CONFIRM.getCode());
			}
			buttonObject.setMethod(method);
			buttonObject.setImage(image);
			buttonObject.setTitle(MessageLocator.getMessage(request, key));
			buttonObject.setShowKey(showKey);
			buttonObject.setType(type);
			buttonObject.setDialogId(dialogId);
			
			if (ButtonTypeEnum.CONFIRM.getCode().equals(buttonObject.getButtonType())) {
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
				if (dialog != null && (boolean) dialog) {
					uiDialogObject.setAutoOpen(true);
				}
			}
		} catch (Exception e) {
		}
		return EVAL_BODY_BUFFERED;
	}

	public int doEndTag() throws JspException {
		try {
			if (isEnabled()) {
				produceTemplate("buttonTemplate.ftl", buttonObject);
				
				if (ButtonTypeEnum.CONFIRM.getCode().equals(buttonObject.getButtonType())) {
					produceTemplate("uiConfirmDialogTemplate.ftl", uiDialogObject);
				}
			}
		} catch (Exception e) {
		}
		
		return EVAL_PAGE;
	}
}
