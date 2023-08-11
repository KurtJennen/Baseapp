package be.luxuryoverdosis.framework.web.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.apache.commons.lang.StringUtils;

import be.luxuryoverdosis.framework.web.BaseWebConstants;
import be.luxuryoverdosis.framework.web.message.MessageLocator;
import be.luxuryoverdosis.framework.web.ui.UiDialogObject;

public class UiDialog extends CommonTag {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String method = BaseWebConstants.UPDATE;
	private String title;
	private String titleKey;
	private String width = "500";
	private String height = "500";
	private boolean autoOpen = false;
	private boolean modal = true;
	private boolean defaultYesButton = false;
	private boolean defaultNoButton = false;
	
	private UiDialogObject uiDialogObject;

	public void setId(String id) {
		this.id = id;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setTitleKey(String titleKey) {
		this.titleKey = titleKey;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public void setAutoOpen(boolean autoOpen) {
		this.autoOpen = autoOpen;
	}
	public void setModal(boolean modal) {
		this.modal = modal;
	}
	public void setDefaultYesButton(boolean defaultYesButton) {
		this.defaultYesButton = defaultYesButton;
	}
	public void setDefaultNoButton(boolean defaultNoButton) {
		this.defaultNoButton = defaultNoButton;
	}
	
	public UiDialogObject getUiDialogObject() {
		return uiDialogObject;
	}
	public void setUiDialogObject(UiDialogObject uiDialogObject) {
		this.uiDialogObject = uiDialogObject;
	}
	
	public int doStartTag() throws JspException {
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		
		uiDialogObject = new UiDialogObject();
		uiDialogObject.setId(id);
		uiDialogObject.setMethod(method);
		
		if (!StringUtils.isEmpty(title)) {
			uiDialogObject.setTitle(title);
		}
		if (!StringUtils.isEmpty(titleKey)) {
			uiDialogObject.setTitle(MessageLocator.getMessage(request, titleKey));
		}
		
		uiDialogObject.setYesLabel(MessageLocator.getMessage(request, "button.update"));
		uiDialogObject.setNoLabel(MessageLocator.getMessage(request, "button.cancel"));
		
		uiDialogObject.setWidth(width);
		uiDialogObject.setHeight(height);
		uiDialogObject.setAutoOpen(autoOpen);
		uiDialogObject.setModal(modal);
		uiDialogObject.setDefaultYesButton(defaultYesButton);
		uiDialogObject.setDefaultNoButton(defaultNoButton);
		
		Object dialog = request.getAttribute(id + StringUtils.capitalize(BaseWebConstants.DIALOG));
		if(dialog != null && (boolean)dialog) {
			uiDialogObject.setAutoOpen(true);
		}
		
		return EVAL_BODY_BUFFERED;
	}

	public int doEndTag() throws JspException {
		try {
			produceTemplate("uiDialogTemplate.ftl", uiDialogObject);
		}
		catch (Exception e) {
		}
		
		return EVAL_PAGE;
	}
}
