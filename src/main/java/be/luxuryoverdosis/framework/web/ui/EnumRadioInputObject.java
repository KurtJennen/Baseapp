package be.luxuryoverdosis.framework.web.ui;

public class EnumRadioInputObject {
	private String property;
	private String tabindex;
	private String value;
	private boolean disabled;
	private boolean checked;
	private String keyMessage;
	
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
	public boolean isDisabled() {
		return disabled;
	}
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public String getKeyMessage() {
		return keyMessage;
	}
	public void setKeyMessage(String keyMessage) {
		this.keyMessage = keyMessage;
	}
	
}
