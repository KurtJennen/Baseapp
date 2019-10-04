package be.luxuryoverdosis.framework.web.tag;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import be.luxuryoverdosis.framework.web.pq.PqGridObject;

public class PqGrid implements Tag {
	PageContext pageContext;
	private String id;
	private String title;
	private String selectedIds;
	private String url;
	private String width = "99%";
	private String height = "500";
	private int freezeCols = 1;
	private int rPP = 15;
	
	private PqGridObject pqGridObject;

	public void setId(String id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setSelectedIds(String selectedIds) {
		this.selectedIds = selectedIds;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public void setFreezeCols(int freezeCols) {
		this.freezeCols = freezeCols;
	}
	public void setrPP(int rPP) {
		this.rPP = rPP;
	}
	
	public PqGridObject getPqGridObject() {
		return pqGridObject;
	}
	
	public void setParent(Tag t) {
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
		
		pqGridObject = new PqGridObject();
		pqGridObject.setId(id);
		pqGridObject.setTitle(title);
		pqGridObject.setSelectedIds(selectedIds);
		pqGridObject.setUrl(url);
		pqGridObject.setWidth(width);
		pqGridObject.setHeight(height);
		pqGridObject.setFreezeCols(freezeCols);
		pqGridObject.setrPP(rPP);
		
		return EVAL_BODY_INCLUDE;
	}

	public int doEndTag() throws JspException {
//		try {
//			JspWriter out = pageContext.getOut();
//			
//		}
//		catch (Exception e) {
//		}
		
		Map<String, Object> templateData = new HashMap<String, Object>();
		templateData.put("templateData", pqGridObject);
		
		return EVAL_PAGE;
	}
}
