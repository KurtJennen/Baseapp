package be.luxuryoverdosis.framework.web.tag;

import java.util.Currency;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.apache.commons.lang.StringUtils;

import be.luxuryoverdosis.framework.web.BaseWebConstants;
import be.luxuryoverdosis.framework.web.form.BaseForm;
import be.luxuryoverdosis.framework.web.message.MessageLocator;
import be.luxuryoverdosis.framework.web.pq.PqGridObject;
import be.luxuryoverdosis.framework.web.sessionmanager.UrlManager;

public class PqGrid extends CommonTag {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String title;
	private String titleKey;
	private String nameSelectedIds;
	private String rowClickMethod = BaseWebConstants.READ;
	private String url;
	private String width = "99%";
	private String height = "520";
	private int freezeCols = 1;
	private boolean paging = true;
	private int rPP = 14;
	private boolean clickable = true;
	
	private PqGridObject pqGridObject;

	public void setId(String id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setTitleKey(String titleKey) {
		this.titleKey = titleKey;
	}
	public void setNameSelectedIds(String nameSelectedIds) {
		this.nameSelectedIds = nameSelectedIds;
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
	public void setPaging(boolean paging) {
		this.paging = paging;
	}
	public void setrPP(int rPP) {
		this.rPP = rPP;
	}
	public void setClickable(boolean clickable) {
		this.clickable = clickable;
	}
	
	public PqGridObject getPqGridObject() {
		return pqGridObject;
	}
	
	public int doStartTag() throws JspException {
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		
		BaseForm baseForm = (BaseForm) request.getAttribute("org.apache.struts.taglib.html.BEAN");
		
		pqGridObject = new PqGridObject();
		pqGridObject.setId(id);
		
		if (!StringUtils.isEmpty(title)) {
			pqGridObject.setTitle(title);
		}
		if (!StringUtils.isEmpty(titleKey)) {
			pqGridObject.setTitle(MessageLocator.getMessage(request, titleKey));
		}
		
		pqGridObject.setNameSelectedIds(nameSelectedIds);
		pqGridObject.setSelectedIds(baseForm.getSelectedIds());
		
		if(clickable && isEnabled()) {
			pqGridObject.setRowClickMethod(rowClickMethod);
		} else {
			pqGridObject.setRowClickMethod(StringUtils.EMPTY);
		}
		
		pqGridObject.setUrl(UrlManager.composeUrl(request, url));
		pqGridObject.setWidth(width);
		pqGridObject.setHeight(height);
		pqGridObject.setFreezeCols(freezeCols);
		pqGridObject.setPaging(paging);
		pqGridObject.setrPP(rPP);
		pqGridObject.setExportUrl(UrlManager.composeUrl(request, "/rest/export"));
		pqGridObject.setExportLabelCsv(MessageLocator.getMessage(request, "export.csv"));
		pqGridObject.setExportLabelExcel(MessageLocator.getMessage(request, "export.excel"));
		
		Locale locale =  MessageLocator.getLocale(request);
		Currency currency = Currency.getInstance(locale);
		
		pqGridObject.setLanguage(locale.getLanguage());
		pqGridObject.setLocale(locale.getLanguage() + '-' + locale.getCountry());
		pqGridObject.setCurrency(currency.getCurrencyCode());
		
		return EVAL_BODY_INCLUDE;
	}

	public int doEndTag() throws JspException {
		try {
			produceTemplate("pqTemplate.ftl", pqGridObject);
		}
		catch (Exception e) {
		}
		
		return EVAL_PAGE;
	}
}
