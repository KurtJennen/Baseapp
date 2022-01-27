package be.luxuryoverdosis.framework.web.ui;

import be.luxuryoverdosis.framework.web.BaseWebConstants;

public class UiDialogObject {
	private String id;
	private String method  = BaseWebConstants.UPDATE;
	private String title;
	private String saveLabel;
	private String cancelLabel;
	private String width = "500";
	private String height = "500";
	private boolean autoOpen = false;
	private boolean modal = true;
	private boolean defaultSaveButton = false;
	private boolean defaultCancelButton = false;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSaveLabel() {
		return saveLabel;
	}
	public void setSaveLabel(String saveLabel) {
		this.saveLabel = saveLabel;
	}
	public String getCancelLabel() {
		return cancelLabel;
	}
	public void setCancelLabel(String cancelLabel) {
		this.cancelLabel = cancelLabel;
	}
	public boolean isAutoOpen() {
		return autoOpen;
	}
	public void setAutoOpen(boolean autoOpen) {
		this.autoOpen = autoOpen;
	}
	public boolean isModal() {
		return modal;
	}
	public void setModal(boolean modal) {
		this.modal = modal;
	}
	public boolean isDefaultSaveButton() {
		return defaultSaveButton;
	}
	public void setDefaultSaveButton(boolean defaultSaveButton) {
		this.defaultSaveButton = defaultSaveButton;
	}
	public boolean isDefaultCancelButton() {
		return defaultCancelButton;
	}
	public void setDefaultCancelButton(boolean defaultCancelButton) {
		this.defaultCancelButton = defaultCancelButton;
	}
}
