package be.luxuryoverdosis.framework.web.ui;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;

public class EnumSelectObject {
	private String property;
	private String tabindex;
	private String value;
	private String onchange = StringUtils.EMPTY;
	private boolean disabled;
	private ArrayList<EnumSelectOptionObject> options = new ArrayList<EnumSelectOptionObject>();
	
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getTabindex() {
		return tabindex;
	}
	public void setTabindex(String tabindex) {
		this.tabindex = tabindex;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getOnchange() {
		return onchange;
	}
	public void setOnchange(String onchange) {
		this.onchange = onchange;
	}
	public boolean isDisabled() {
		return disabled;
	}
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	public ArrayList<EnumSelectOptionObject> getOptions() {
		return options;
	}
	public void setOptions(ArrayList<EnumSelectOptionObject> options) {
		this.options = options;
	}
	
}
