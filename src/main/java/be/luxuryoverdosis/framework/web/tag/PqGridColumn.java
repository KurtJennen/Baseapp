package be.luxuryoverdosis.framework.web.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import org.apache.commons.lang.StringUtils;

import be.luxuryoverdosis.framework.web.message.MessageLocator;
import be.luxuryoverdosis.framework.web.pq.PqGridColumnObject;

public class PqGridColumn extends CommonTag {
	private static final long serialVersionUID = 1L;
	
//	PageContext pageContext;
	private Tag parent;
	private String title;
	private String titleKey;
	private String dataType;
	private String dataIndx;
	private String width;
	private String align = "left";
	private boolean visible = true;
	private boolean sortable = true;
	private boolean resizable = true;
	private boolean totalizable = false;
	private boolean currency = false;
	private String filterType = "textbox";
	private String filterCondition = "contain";
	private String renderFunction = StringUtils.EMPTY;

	public void setTitle(String title) {
		this.title = title;
	}
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
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public void setSortable(boolean sortable) {
		this.sortable = sortable;
	}
	public void setResizable(boolean resizable) {
		this.resizable = resizable;
	}
	public void setCurrency(boolean currency) {
		this.currency = currency;
	}
	public void setFilterType(String filterType) {
		this.filterType = filterType;
	}
	public void setFilterCondition(String filterCondition) {
		this.filterCondition = filterCondition;
	}
	public void setTotalizable(boolean totalizable) {
		this.totalizable = totalizable;
	}
	public void setRenderFunction(String renderFunction) {
		this.renderFunction = renderFunction;
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
		return parent;
	}
	
	private Tag getParent(Tag tag) {
		while(true) {
			Tag parent = tag.getParent();
			
			if(parent == null) {
				return null;
			}
			if(parent != null && parent instanceof PqGrid){
				return parent;
			}
			
			tag = parent;
		}
	}
	
	public int doStartTag() throws JspException {
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		
		PqGrid pqGridTag = (PqGrid) getParent(this);
		
		if (visible) {
			PqGridColumnObject gridColumnObject = new PqGridColumnObject();
			if (!StringUtils.isEmpty(title)) {
				gridColumnObject.setTitle(title);
			}
			if (!StringUtils.isEmpty(titleKey)) {
				gridColumnObject.setTitle(MessageLocator.getMessage(request, titleKey));
			}
			gridColumnObject.setDataType(dataType);
			gridColumnObject.setDataIndx(dataIndx);
			gridColumnObject.setWidth(width);
			gridColumnObject.setAlign(align);
			gridColumnObject.setSortable(sortable);
			gridColumnObject.setResizable(resizable);
			gridColumnObject.setCurrency(currency);
			gridColumnObject.setFilterType(filterType);
			gridColumnObject.setTotalizable(totalizable);
			if (currency) {
				gridColumnObject.setFilterCondition("begin");
			} else {
				gridColumnObject.setFilterCondition(filterCondition);
			}
			gridColumnObject.setRenderFunction(renderFunction);
			if (totalizable) {
				pqGridTag.getPqGridObject().setSummary(true);
			}
			
			pqGridTag.getPqGridObject().getPqGridColumnObjects().add(gridColumnObject);
		}
			
		return EVAL_BODY_INCLUDE;
	}

	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
}
