package be.luxuryoverdosis.framework.web.tag;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import be.luxuryoverdosis.framework.web.BaseWebConstants;
import be.luxuryoverdosis.framework.web.message.MessageLocator;
import be.luxuryoverdosis.framework.web.pq.PqGridObject;
import be.luxuryoverdosis.framework.web.sessionmanager.UrlManager;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

public class PqGrid implements Tag {
	PageContext pageContext;
	private String id;
	private String titleKey;
	private String selectedIds;
	private String rowClickMethod = BaseWebConstants.READ;
	private String url;
	private String width = "99%";
	private String height = "520";
	private int freezeCols = 1;
	private int rPP = 14;
	
	private PqGridObject pqGridObject;

	public void setId(String id) {
		this.id = id;
	}
	public void setTitleKey(String titleKey) {
		this.titleKey = titleKey;
	}
	public void setSelectedIds(String selectedIds) {
		this.selectedIds = selectedIds;
	}
	public void setRowClickMethod(String rowClickMethod) {
		this.rowClickMethod = rowClickMethod;
	}
	public void setPqGridObject(PqGridObject pqGridObject) {
		this.pqGridObject = pqGridObject;
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
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		
		pqGridObject = new PqGridObject();
		pqGridObject.setId(id);
		pqGridObject.setTitle(MessageLocator.getMessage(request, titleKey));
		pqGridObject.setSelectedIds(selectedIds);
		pqGridObject.setRowClickMethod(rowClickMethod);
		pqGridObject.setUrl(UrlManager.composeUrl(request, url));
		pqGridObject.setWidth(width);
		pqGridObject.setHeight(height);
		pqGridObject.setFreezeCols(freezeCols);
		pqGridObject.setrPP(rPP);
		pqGridObject.setExportUrl(UrlManager.composeUrl(request, "/rest/export"));
		pqGridObject.setExportLabelCsv(MessageLocator.getMessage(request, "export.csv"));
		pqGridObject.setExportLabelExcel(MessageLocator.getMessage(request, "export.excel"));
		pqGridObject.setLocale(MessageLocator.getLocale(request).getLanguage());
		
		return EVAL_BODY_INCLUDE;
	}

	public int doEndTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			
			Configuration configuration = new Configuration();
			configuration.setClassForTemplateLoading(this.getClass(), "../../../resources/templates/");
			configuration.setDefaultEncoding("UTF-8");
			configuration.setLocale(Locale.US);
			configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
			
			Map<String, Object> templateData = new HashMap<String, Object>();
			templateData.put("templateData", pqGridObject);
			
			Template template = configuration.getTemplate("pqTemplate.ftl");
			template.process(templateData, out);
			
		}
		catch (Exception e) {
		}
		
		
		
		
		return EVAL_PAGE;
	}
}
