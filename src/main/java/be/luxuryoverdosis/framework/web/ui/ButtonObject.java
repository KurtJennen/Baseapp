package be.luxuryoverdosis.framework.web.ui;

public class ButtonObject {
	private String buttonType;
	private String method;
	private String image;
	private String key;
	private boolean showKey = false;
	private String type = "submit";
	private String dialogId;
	
	public String getButtonType() {
		return buttonType;
	}
	public void setButtonType(String buttonType) {
		this.buttonType = buttonType;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public boolean isShowKey() {
		return showKey;
	}
	public void setShowKey(boolean showKey) {
		this.showKey = showKey;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDialogId() {
		return dialogId;
	}
	public void setDialogId(String dialogId) {
		this.dialogId = dialogId;
	}
	
}
