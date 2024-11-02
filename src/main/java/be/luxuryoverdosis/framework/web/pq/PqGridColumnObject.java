package be.luxuryoverdosis.framework.web.pq;

import org.apache.commons.lang.StringUtils;

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
	private String renderFunction = StringUtils.EMPTY;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(final String title) {
		this.title = title;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(final String dataType) {
		this.dataType = dataType;
	}
	public String getDataIndx() {
		return dataIndx;
	}
	public void setDataIndx(final String dataIndx) {
		this.dataIndx = dataIndx;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(final String width) {
		this.width = width;
	}
	public String getAlign() {
		return align;
	}
	public void setAlign(final String align) {
		this.align = align;
	}
	public boolean getSortable() {
		return sortable;
	}
	public void setSortable(final boolean sortable) {
		this.sortable = sortable;
	}
	public boolean getResizable() {
		return resizable;
	}
	public void setResizable(final boolean resizable) {
		this.resizable = resizable;
	}
	public boolean isTotalizable() {
		return totalizable;
	}
	public void setTotalizable(final boolean totalizable) {
		this.totalizable = totalizable;
	}
	public boolean isCurrency() {
		return currency;
	}
	public void setCurrency(final boolean currency) {
		this.currency = currency;
	}
	public String getFilterType() {
		return filterType;
	}
	public void setFilterType(final String filterType) {
		this.filterType = filterType;
	}
	public String getFilterCondition() {
		return filterCondition;
	}
	public void setFilterCondition(final String filterCondition) {
		this.filterCondition = filterCondition;
	}
	public String getRenderFunction() {
		return renderFunction;
	}
	public void setRenderFunction(final String renderFunction) {
		this.renderFunction = renderFunction;
	}
	
}
