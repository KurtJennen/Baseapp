package be.luxuryoverdosis.framework.web.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.validator.ValidatorForm;

import be.luxuryoverdosis.framework.web.BaseWebConstants;
import be.luxuryoverdosis.framework.web.message.MessageLocator;

public class BaseForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;
	private String method;
	private int[] ids;
    private int[] selectedIds;
    private int selectedTab = 0;
	private int id;
	private int version;
	private int documentId;
	private boolean firstVisible;
	private boolean previousVisible;
	private boolean nextVisible;
	private boolean lastVisible;
	
	public int[] getIds() {
		return ids;
	}
	public void setIds(int[] ids) {
		this.ids = ids;
	}
    public int[] getSelectedIds() {
        return selectedIds;
    }
    public void setSelectedIds(int[] selectedIds) {
        this.selectedIds = selectedIds;
    }
	public int getSelectedTab() {
		return selectedTab;
	}
	public void setSelectedTab(int selectedTab) {
		this.selectedTab = selectedTab;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public int getDocumentId() {
		return documentId;
	}
	public void setDocumentId(int documentId) {
		this.documentId = documentId;
	}
	public boolean isFirstVisible() {
		return firstVisible;
	}
	public void setFirstVisible(boolean firstVisible) {
		this.firstVisible = firstVisible;
	}
	public boolean isPreviousVisible() {
		return previousVisible;
	}
	public void setPreviousVisible(boolean previousVisible) {
		this.previousVisible = previousVisible;
	}
	public boolean isNextVisible() {
		return nextVisible;
	}
	public void setNextVisible(boolean nextVisible) {
		this.nextVisible = nextVisible;
	}
	public boolean isLastVisible() {
		return lastVisible;
	}
	public void setLastVisible(boolean lastVisible) {
		this.lastVisible = lastVisible;
	}
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		this.setMethod("");
		this.setId(-1);
		this.setVersion(-1);
		this.setDocumentId(-1);
		this.setFirstVisible(true);
		this.setPreviousVisible(true);
		this.setNextVisible(true);
		this.setLastVisible(true);
	}
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		
		errors = super.validate(mapping, request);
		
		if(this.getMethod().equals(BaseWebConstants.CREATE_DOCUMENT)) {
			if(documentId < 0) {
				errors.add("documentId", new ActionMessage("errors.required", MessageLocator.getMessage(request, "document")));
			}
		}
		
		return errors;
	}
	
	public void checkOnlyOneSelected(ActionMapping mapping, HttpServletRequest request, ActionErrors errors, String method, int[] ids) {
		if(this.getMethod().equals(method)) {
			if(ids == null || ids.length == 0) {
				errors.add("", new ActionMessage("errors.selected.one"));
			}
			if(ids != null && ids.length > 1) {
				errors.add("", new ActionMessage("errors.selected.more"));
			}
		}
	}
	
	public void checkOnlyOneOrMoreSelected(ActionMapping mapping, HttpServletRequest request, ActionErrors errors, String method, int[] ids) {
		if(this.getMethod().equals(method)) {
			if(ids == null || ids.length == 0) {
				errors.add("", new ActionMessage("errors.selected.one.more"));
			}
		}
	}
}
