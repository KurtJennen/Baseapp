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
    private int selectedTab1 = 0;
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
	public void setIds(final int[] ids) {
		this.ids = ids;
	}
    public int[] getSelectedIds() {
        return selectedIds;
    }
    public void setSelectedIds(final int[] selectedIds) {
        this.selectedIds = selectedIds;
    }
	public int getSelectedTab() {
		return selectedTab;
	}
	public void setSelectedTab(final int selectedTab) {
		this.selectedTab = selectedTab;
	}
	public int getSelectedTab1() {
		return selectedTab1;
	}
	public void setSelectedTab1(final int selectedTab1) {
		this.selectedTab1 = selectedTab1;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(final String method) {
		this.method = method;
	}
	public int getId() {
		return id;
	}
	public void setId(final int id) {
		this.id = id;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(final int version) {
		this.version = version;
	}
	public int getDocumentId() {
		return documentId;
	}
	public void setDocumentId(final int documentId) {
		this.documentId = documentId;
	}
	public boolean isFirstVisible() {
		return firstVisible;
	}
	public void setFirstVisible(final boolean firstVisible) {
		this.firstVisible = firstVisible;
	}
	public boolean isPreviousVisible() {
		return previousVisible;
	}
	public void setPreviousVisible(final boolean previousVisible) {
		this.previousVisible = previousVisible;
	}
	public boolean isNextVisible() {
		return nextVisible;
	}
	public void setNextVisible(final boolean nextVisible) {
		this.nextVisible = nextVisible;
	}
	public boolean isLastVisible() {
		return lastVisible;
	}
	public void setLastVisible(final boolean lastVisible) {
		this.lastVisible = lastVisible;
	}
	public void reset(final ActionMapping mapping, final HttpServletRequest request) {
		super.reset(mapping, request);
		this.setMethod("");
		this.setSelectedTab(0);
		this.setId(-1);
		this.setVersion(-1);
		this.setDocumentId(-1);
		this.setFirstVisible(true);
		this.setPreviousVisible(true);
		this.setNextVisible(true);
		this.setLastVisible(true);
	}
	
	public ActionErrors validate(final ActionMapping mapping, final HttpServletRequest request) {
		ActionErrors errors = super.validate(mapping, request);
		
		if (this.getMethod().equals(BaseWebConstants.CREATE_DOCUMENT)) {
			if (documentId < 0) {
				errors.add("documentId", new ActionMessage("errors.required", MessageLocator.getMessage(request, "document")));
			}
		}
		
		return errors;
	}
	
	public void checkOnlyOneSelected(final ActionMapping mapping, final HttpServletRequest request, final ActionErrors errors, final String method, final int[] ids) {
		if (this.getMethod().equals(method)) {
			if (ids == null || ids.length == 0) {
				errors.add("", new ActionMessage("errors.selected.one"));
			}
			if (ids != null && ids.length > 1) {
				errors.add("", new ActionMessage("errors.selected.more"));
			}
		}
	}
	
	public void checkOnlyOneOrMoreSelected(final ActionMapping mapping, final HttpServletRequest request, final ActionErrors errors, final String method, final int[] ids) {
		if (this.getMethod().equals(method)) {
			if (ids == null || ids.length == 0) {
				errors.add("", new ActionMessage("errors.selected.one.more"));
			}
		}
	}
}
