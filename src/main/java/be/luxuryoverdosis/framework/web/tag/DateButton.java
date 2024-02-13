package be.luxuryoverdosis.framework.web.tag;

import javax.servlet.jsp.JspException;

import org.apache.commons.lang.StringUtils;

import be.luxuryoverdosis.framework.web.ui.DateButtonObject;

public class DateButton extends CommonTag {
	private static final long serialVersionUID = 1L;
	
	private String property;
	private String tabindex;
	private String value;
	private boolean disabled;
	private String onchange = StringUtils.EMPTY;
	
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
	public void setOnchange(String onchange) {
		this.onchange = onchange;
	}

	public int doStartTag() throws JspException {
		try {
			dateButtonObject = new DateButtonObject();
			dateButtonObject.setProperty(property);
			dateButtonObject.setTabindex(tabindex);
			dateButtonObject.setValue(value);
			dateButtonObject.setDisabled(disabled);
			dateButtonObject.setOnchange(onchange);
		}
		catch (Exception e) {
		}
		return EVAL_BODY_BUFFERED;
	}

	public int doEndTag() throws JspException {
		try {
			if(isEnabled()) {
				produceTemplate("dateButtonTemplate.ftl", dateButtonObject);
			}
		}
		catch (Exception e) {
		}
			
		return EVAL_PAGE;
	}
}
