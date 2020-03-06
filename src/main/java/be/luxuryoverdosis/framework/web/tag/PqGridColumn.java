package be.luxuryoverdosis.framework.web.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import be.luxuryoverdosis.framework.web.message.MessageLocator;
import be.luxuryoverdosis.framework.web.pq.PqGridColumnObject;

public class PqGridColumn implements Tag {
	PageContext pageContext;
	private Tag parent;
	private String titleKey;
	private String dataType;
	private String dataIndx;
	private String width;
	private String align = "left";
	private boolean sortable = true;
	private boolean resizable = true;
	private boolean currency = false;
	private String filterType = "textbox";
	private String filterCondition = "contain";

	public void setTitleKey(String titleKey) {
		this.titleKey = titleKey;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public void setDataIndx(String dataIndx) {
		this.dataIndx = dataIndx;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public void setAlign(String align) {
		this.align = align;
	}
	public void setSortable(boolean sortable) {
		this.sortable = sortable;
	}
	public void setResizable(boolean resizable) {
		this.resizable = resizable;
	}
	public void setFilterType(String filterType) {
		this.filterType = filterType;
	}
	public void setFilterCondition(String filterCondition) {
		this.filterCondition = filterCondition;
	}
	public void setCurrency(boolean currency) {
		this.currency = currency;
	}
	
	public void setParent(Tag t) {
		parent = t;
	}
	
	public void setPageContext(PageContext p) {
		pageContext = p;
	}
	
	public void release() {
	}
	
	public Tag getParent() {
		return null;
	}
	
	public int doStartTag() throws JspException {
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		
		PqGrid pqGridTag = (PqGrid) parent;
		
		PqGridColumnObject gridColumnObject = new PqGridColumnObject();
		gridColumnObject.setTitle(MessageLocator.getMessage(request, titleKey));
		gridColumnObject.setDataType(dataType);
		gridColumnObject.setDataIndx(dataIndx);
		gridColumnObject.setWidth(width);
		gridColumnObject.setAlign(align);
		gridColumnObject.setSortable(sortable);
		gridColumnObject.setResizable(resizable);
		gridColumnObject.setCurrency(currency);
		gridColumnObject.setFilterType(filterType);
		if (currency) {
			gridColumnObject.setFilterCondition("begin");
		} else {
			gridColumnObject.setFilterCondition(filterCondition);
		}
		
		
		pqGridTag.getPqGridObject().getPqGridColumnObjects().add(gridColumnObject);
			
		return EVAL_BODY_INCLUDE;
	}

	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
}
