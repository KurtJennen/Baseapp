package be.luxuryoverdosis.framework.web.ui;

import be.luxuryoverdosis.framework.web.BaseWebConstants;

public class UiDialogObject {
	private String id;
	private String method  = BaseWebConstants.UPDATE;
	private String title;
	private String message;
	private String yesLabel;
	private String noLabel;
	private String width = "500";
	private String height = "500";
	private boolean autoOpen = false;
	private boolean modal = true;
	private boolean defaultYesButton = false;
	private boolean defaultNoButton = false;
	
	public String getId() {
		return id;
	}
	public void setId(final String id) {
		this.id = id;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(final String method) {
		this.method = method;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(final String width) {
		this.width = width;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(final String height) {
		this.height = height;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(final String title) {
		this.title = title;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(final String message) {
		this.message = message;
	}
	public String getYesLabel() {
		return yesLabel;
	}
	public void setYesLabel(final String yesLabel) {
		this.yesLabel = yesLabel;
	}
	public String getNoLabel() {
		return noLabel;
	}
	public void setNoLabel(final String noLabel) {
		this.noLabel = noLabel;
	}
	public boolean isAutoOpen() {
		return autoOpen;
	}
	public void setAutoOpen(final boolean autoOpen) {
		this.autoOpen = autoOpen;
	}
	public boolean isModal() {
		return modal;
	}
	public void setModal(final boolean modal) {
		this.modal = modal;
	}
	public boolean isDefaultYesButton() {
		return defaultYesButton;
	}
	public void setDefaultYesButton(final boolean defaultYesButton) {
		this.defaultYesButton = defaultYesButton;
	}
	public boolean isDefaultNoButton() {
		return defaultNoButton;
	}
	public void setDefaultNoButton(final boolean defaultNoButton) {
		this.defaultNoButton = defaultNoButton;
	}
}
