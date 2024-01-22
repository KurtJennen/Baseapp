package be.luxuryoverdosis.framework.web.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.taglib.TagUtils;

import be.luxuryoverdosis.framework.web.message.MessageLocator;
import be.luxuryoverdosis.framework.web.ui.AjaxSelectObject;

public class AjaxSelect extends CommonTag {
	private static final long serialVersionUID = 1L;
	
	private String property;
	private String methodAll;
	private String methodOne;
	private String fieldsAll;
	private String fieldsOne;
	private String tabindex = "1";
	private boolean disabled;
	private String image = "zoom.png";
	private String size;
	private int minLength = 0;
	private String maxLength;
    private String width = "90";
    private String maxHeight = "500";
	private String key;
	private String callbackActionMethodOne = StringUtils.EMPTY;
	private String callbackActionMethodBlur = StringUtils.EMPTY;
	
	private AjaxSelectObject ajaxSelectObject;
	
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
	public void setTabindex(String tabindex) {
		this.tabindex = tabindex;
	}
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public void setMinLength(int minLength) {
		this.minLength = minLength;
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
	public void setCallbackActionMethodOne(String callbackActionMethodOne) {
		this.callbackActionMethodOne = callbackActionMethodOne;
	}
	public void setCallbackActionMethodBlur(String callbackActionMethodBlur) {
		this.callbackActionMethodBlur = callbackActionMethodBlur;
	}

	public int doStartTag() throws JspException {
		try {
			HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
			
			Object value = TagUtils.getInstance().lookup(pageContext, "org.apache.struts.taglib.html.BEAN", property, null);
			
			ajaxSelectObject = new AjaxSelectObject();
			ajaxSelectObject.setProperty(property);
			ajaxSelectObject.setMethodAll(methodAll);
			ajaxSelectObject.setMethodOne(methodOne);
			ajaxSelectObject.setTabindex(tabindex);
			ajaxSelectObject.setDisabled(disabled);
			ajaxSelectObject.setImage(image);
			ajaxSelectObject.setSize(size);
			ajaxSelectObject.setMinLength(minLength);
			ajaxSelectObject.setMaxLength(maxLength);
			ajaxSelectObject.setWidth(width);
			ajaxSelectObject.setMaxHeight(maxHeight);
			ajaxSelectObject.setTitle(MessageLocator.getMessage(request, key));
			ajaxSelectObject.setCallbackActionMethodOne(callbackActionMethodOne);
			ajaxSelectObject.setCallbackActionMethodBlur(callbackActionMethodBlur);
			
			ajaxSelectObject.setConcatenatedFields(produceConcatenatedFields());
			ajaxSelectObject.setSeperatedArray(produceSeperatedArray());
			ajaxSelectObject.setValue(value.toString());
			ajaxSelectObject.setSearchNoResultKey(MessageLocator.getMessage(request, "search.no.result"));
		}
		catch (Exception e) {
		}
		return EVAL_BODY_BUFFERED;
	}
	
	private String produceConcatenatedFields() {
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
		
		return concatenatedFields.toString();
	}
	
	private String[] produceSeperatedArray() {
		return fieldsAll.split(",");
	}

	public int doEndTag() throws JspException {
		try {
			if(isEnabled()) {
				produceTemplate("ajaxSelectTemplate.ftl", ajaxSelectObject);
			}
		}
		catch (Exception e) {
		}
		
		return EVAL_PAGE;
	}
}
