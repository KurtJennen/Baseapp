package be.luxuryoverdosis.framework.web.ui;

public class AjaxSelectObject {
	private String property;
	private String methodAll;
	private String methodOne;
	private String tabindex;
	private boolean disabled;
	private String image;
	private String size;
	private int minLength = 3;
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
	public void setProperty(String property) {
		this.property = property;
	}
	public String getMethodAll() {
		return methodAll;
	}
	public void setMethodAll(String methodAll) {
		this.methodAll = methodAll;
	}
	public String getMethodOne() {
		return methodOne;
	}
	public void setMethodOne(String methodOne) {
		this.methodOne = methodOne;
	}
	public String getTabindex() {
		return tabindex;
	}
	public void setTabindex(String tabindex) {
		this.tabindex = tabindex;
	}
	public boolean isDisabled() {
		return disabled;
	}
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public int getMinLength() {
		return minLength;
	}
	public void setMinLength(int minLength) {
		this.minLength = minLength;
	}
	public String getMaxLength() {
		return maxLength;
	}
	public void setMaxLength(String maxLength) {
		this.maxLength = maxLength;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getMaxHeight() {
		return maxHeight;
	}
	public void setMaxHeight(String maxHeight) {
		this.maxHeight = maxHeight;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCallbackActionMethodOne() {
		return callbackActionMethodOne;
	}
	public void setCallbackActionMethodOne(String callbackActionMethodOne) {
		this.callbackActionMethodOne = callbackActionMethodOne;
	}
	public String getCallbackActionMethodBlur() {
		return callbackActionMethodBlur;
	}
	public void setCallbackActionMethodBlur(String callbackActionMethodBlur) {
		this.callbackActionMethodBlur = callbackActionMethodBlur;
	}
	public String getConcatenatedFields() {
		return concatenatedFields;
	}
	public void setConcatenatedFields(String concatenatedFields) {
		this.concatenatedFields = concatenatedFields;
	}
	public String getSearchNoResultKey() {
		return searchNoResultKey;
	}
	public void setSearchNoResultKey(String searchNoResultKey) {
		this.searchNoResultKey = searchNoResultKey;
	}
	public String[] getSeperatedArray() {
		return seperatedArray;
	}
	public void setSeperatedArray(String[] seperatedArray) {
		this.seperatedArray = seperatedArray;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
