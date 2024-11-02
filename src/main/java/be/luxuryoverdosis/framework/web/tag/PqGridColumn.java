package be.luxuryoverdosis.framework.web.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;

import org.apache.commons.lang.StringUtils;

import be.luxuryoverdosis.framework.web.message.MessageLocator;
import be.luxuryoverdosis.framework.web.pq.PqGridColumnObject;

public class PqGridColumn extends CommonTag {
	private static final long serialVersionUID = 1L;
	
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

	public void setTitle(final String title) {
		this.title = title;
	}
	public void setTitleKey(final String titleKey) {
		this.titleKey = titleKey;
	}
	public void setDataType(final String dataType) {
		this.dataType = dataType;
	}
	public void setDataIndx(final String dataIndx) {
		this.dataIndx = dataIndx;
	}
	public void setWidth(final String width) {
		this.width = width;
	}
	public void setAlign(final String align) {
		this.align = align;
	}
	public void setVisible(final boolean visible) {
		this.visible = visible;
	}
	public void setSortable(final boolean sortable) {
		this.sortable = sortable;
	}
	public void setResizable(final boolean resizable) {
		this.resizable = resizable;
	}
	public void setCurrency(final boolean currency) {
		this.currency = currency;
	}
	public void setFilterType(final String filterType) {
		this.filterType = filterType;
	}
	public void setFilterCondition(final String filterCondition) {
		this.filterCondition = filterCondition;
	}
	public void setTotalizable(final boolean totalizable) {
		this.totalizable = totalizable;
	}
	public void setRenderFunction(final String renderFunction) {
		this.renderFunction = renderFunction;
	}
	
	public void setParent(final Tag t) {
		parent = t;
	}
	
	public void release() {
	}
	
	public Tag getParent() {
		return parent;
	}
	
	private Tag getParent(Tag tag) {
		while (true) {
			Tag parent = tag.getParent();
			
			if (parent == null) {
				return null;
			}
			if (parent != null && parent instanceof PqGrid) {
				return parent;
			}
			
			tag = parent;
		}
	}
	
	public int doStartTag() throws JspException {
		HttpServletRequest request = (HttpServletRequest) getPageContext().getRequest();
		
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
