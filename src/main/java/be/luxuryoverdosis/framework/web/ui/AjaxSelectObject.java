package be.luxuryoverdosis.framework.web.ui;

import be.luxuryoverdosis.framework.BaseConstants;

public class AjaxSelectObject {
	private String property;
	private String methodAll;
	private String methodOne;
	private String tabindex;
	private boolean disabled;
	private String image;
	private String size;
	private int minLength = BaseConstants.DRIE;
	private String maxLength;
	private String width = "90";
	private String maxHeight = "500";
	private String title;
	private String callbackActionMethodOne;
	private String callbackActionMethodBlur;
	
	private String concatenatedFields;
	private String[] seperatedArray;
	private String value;
	private String searchNoResultKey;
	
	public String getProperty() {
		return property;
	}
	public void setProperty(final String property) {
		this.property = property;
	}
	public String getMethodAll() {
		return methodAll;
	}
	public void setMethodAll(final String methodAll) {
		this.methodAll = methodAll;
	}
	public String getMethodOne() {
		return methodOne;
	}
	public void setMethodOne(final String methodOne) {
		this.methodOne = methodOne;
	}
	public String getTabindex() {
		return tabindex;
	}
	public void setTabindex(final String tabindex) {
		this.tabindex = tabindex;
	}
	public boolean isDisabled() {
		return disabled;
	}
	public void setDisabled(final boolean disabled) {
		this.disabled = disabled;
	}
	public String getImage() {
		return image;
	}
	public void setImage(final String image) {
		this.image = image;
	}
	public String getSize() {
		return size;
	}
	public void setSize(final String size) {
		this.size = size;
	}
	public int getMinLength() {
		return minLength;
	}
	public void setMinLength(final int minLength) {
		this.minLength = minLength;
	}
	public String getMaxLength() {
		return maxLength;
	}
	public void setMaxLength(final String maxLength) {
		this.maxLength = maxLength;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(final String width) {
		this.width = width;
	}
	public String getMaxHeight() {
		return maxHeight;
	}
	public void setMaxHeight(final String maxHeight) {
		this.maxHeight = maxHeight;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(final String title) {
		this.title = title;
	}
	public String getCallbackActionMethodOne() {
		return callbackActionMethodOne;
	}
	public void setCallbackActionMethodOne(final String callbackActionMethodOne) {
		this.callbackActionMethodOne = callbackActionMethodOne;
	}
	public String getCallbackActionMethodBlur() {
		return callbackActionMethodBlur;
	}
	public void setCallbackActionMethodBlur(final String callbackActionMethodBlur) {
		this.callbackActionMethodBlur = callbackActionMethodBlur;
	}
	public String getConcatenatedFields() {
		return concatenatedFields;
	}
	public void setConcatenatedFields(final String concatenatedFields) {
		this.concatenatedFields = concatenatedFields;
	}
	public String getSearchNoResultKey() {
		return searchNoResultKey;
	}
	public void setSearchNoResultKey(final String searchNoResultKey) {
		this.searchNoResultKey = searchNoResultKey;
	}
	public String[] getSeperatedArray() {
		return seperatedArray;
	}
	public void setSeperatedArray(final String[] seperatedArray) {
		this.seperatedArray = seperatedArray;
	}
	public String getValue() {
		return value;
	}
	public void setValue(final String value) {
		this.value = value;
	}
	
}
