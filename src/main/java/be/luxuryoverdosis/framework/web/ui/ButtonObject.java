package be.luxuryoverdosis.framework.web.ui;

public class ButtonObject {
	private String buttonType;
	private String method;
	private String image;
	private String title;
	private boolean showKey = false;
	private String type = "submit";
	private String dialogId;
	
	public String getButtonType() {
		return buttonType;
	}
	public void setButtonType(final String buttonType) {
		this.buttonType = buttonType;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(final String method) {
		this.method = method;
	}
	public String getImage() {
		return image;
	}
	public void setImage(final String image) {
		this.image = image;
	}
	public String getTitle() {
		return title;
	} 
	public void setTitle(final String title) {
		this.title = title;
	}
	public boolean isShowKey() {
		return showKey;
	}
	public void setShowKey(final boolean showKey) {
		this.showKey = showKey;
	}
	public String getType() {
		return type;
	}
	public void setType(final String type) {
		this.type = type;
	}
	public String getDialogId() {
		return dialogId;
	}
	public void setDialogId(final String dialogId) {
		this.dialogId = dialogId;
	}
	
}
