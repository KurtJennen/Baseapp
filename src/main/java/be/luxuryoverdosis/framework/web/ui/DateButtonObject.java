package be.luxuryoverdosis.framework.web.ui;

import org.apache.commons.lang.StringUtils;

public class DateButtonObject {
	private String property;
	private String tabindex;
	private String value;
	private boolean disabled;
	private String onchange = StringUtils.EMPTY;
	
	public String getProperty() {
		return property;
	}
	public void setProperty(final String property) {
		this.property = property;
	}
	public String getTabindex() {
		return tabindex;
	}
	public void setTabindex(final String tabindex) {
		this.tabindex = tabindex;
	}
	public String getValue() {
		return value;
	}
	public void setValue(final String value) {
		this.value = value;
	}
	public boolean isDisabled() {
		return disabled;
	}
	public void setDisabled(final boolean disabled) {
		this.disabled = disabled;
	}
	public String getOnchange() {
		return onchange;
	}
	public void setOnchange(final String onchange) {
		this.onchange = onchange;
	}
	
}
