package be.luxuryoverdosis.framework.web.pq;

public class PqGridColumnObject {
	private String title;
	private String dataType;
	private String dataIndx;
	private String width;
	private String align = "left";
	private boolean sortable = true;
	private boolean resizable = true;
	private boolean totalizable = false;
	private boolean currency = true;
	private String filterType = "textbox";
	private String filterCondition = "contain";
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getDataIndx() {
		return dataIndx;
	}
	public void setDataIndx(String dataIndx) {
		this.dataIndx = dataIndx;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getAlign() {
		return align;
	}
	public void setAlign(String align) {
		this.align = align;
	}
	public boolean getSortable() {
		return sortable;
	}
	public void setSortable(boolean sortable) {
		this.sortable = sortable;
	}
	public boolean getResizable() {
		return resizable;
	}
	public void setResizable(boolean resizable) {
		this.resizable = resizable;
	}
	public boolean isTotalizable() {
		return totalizable;
	}
	public void setTotalizable(boolean totalizable) {
		this.totalizable = totalizable;
	}
	public boolean isCurrency() {
		return currency;
	}
	public void setCurrency(boolean currency) {
		this.currency = currency;
	}
	public String getFilterType() {
		return filterType;
	}
	public void setFilterType(String filterType) {
		this.filterType = filterType;
	}
	public String getFilterCondition() {
		return filterCondition;
	}
	public void setFilterCondition(String filterCondition) {
		this.filterCondition = filterCondition;
	}
	
}
