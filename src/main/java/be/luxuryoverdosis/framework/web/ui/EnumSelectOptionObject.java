package be.luxuryoverdosis.framework.web.ui;

public class EnumSelectOptionObject {
	private String key;
	private String keyMessage;
	private boolean selected;
	
	public String getKey() {
		return key;
	}
	public void setKey(final String key) {
		this.key = key;
	}
	public String getKeyMessage() {
		return keyMessage;
	}
	public void setKeyMessage(final String keyMessage) {
		this.keyMessage = keyMessage;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(final boolean selected) {
		this.selected = selected;
	}
	
}
