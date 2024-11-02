package be.luxuryoverdosis.framework.web.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;

import be.luxuryoverdosis.framework.logging.Logging;
import be.luxuryoverdosis.framework.web.BaseWebConstants;
import be.luxuryoverdosis.framework.web.message.MessageLocator;

public class ListUserForm extends ListForm {
	private static final long serialVersionUID = 1L;
	
	private FormFile formFile;
	private int[] selectedIdsExportUserJob;
	private int[] selectedIdsImportUserJob;
	
	public FormFile getFormFile() {
		return formFile;
	}
	public void setFormFile(final FormFile formFile) {
		this.formFile = formFile;
	}
	public int[] getSelectedIdsExportUserJob() {
		return selectedIdsExportUserJob;
	}
	public void setSelectedIdsExportUserJob(final int[] selectedIdsExportUserJob) {
		this.selectedIdsExportUserJob = selectedIdsExportUserJob;
	}
	public int[] getSelectedIdsImportUserJob() {
		return selectedIdsImportUserJob;
	}
	public void setSelectedIdsImportUserJob(final int[] selectedIdsImportUserJob) {
		this.selectedIdsImportUserJob = selectedIdsImportUserJob;
	}
	
	public void reset(final ActionMapping mapping, final HttpServletRequest request) {
		super.reset(mapping, request);
		this.setFormFile(null);
	}
	
	public ActionErrors validate(final ActionMapping mapping, final HttpServletRequest request) {
		Logging.info(this, "Begin Validating");
		
		ActionErrors errors = super.validate(mapping, request);
		
		if (this.getMethod().equals(BaseWebConstants.IMPORT_USER_JOB)) {
			if (StringUtils.isEmpty(this.getFormFile().getFileName())) {
				errors.add("formFile", new ActionMessage("errors.required", MessageLocator.getMessage(request, "file")));
			}
		}
		
		//super.checkOnlyOneSelected(mapping, request, errors, BaseWebConstants.READ, getSelectedIds());
		super.checkOnlyOneSelected(mapping, request, errors, BaseWebConstants.READ_EXPORT_USER_JOB, getSelectedIdsExportUserJob());
		super.checkOnlyOneSelected(mapping, request, errors, BaseWebConstants.READ_IMPORT_USER_JOB, getSelectedIdsImportUserJob());
		super.checkOnlyOneOrMoreSelected(mapping, request, errors, BaseWebConstants.DELETE_EXPORT_USER_JOB, getSelectedIdsExportUserJob());
		super.checkOnlyOneOrMoreSelected(mapping, request, errors, BaseWebConstants.DELETE_IMPORT_USER_JOB, getSelectedIdsImportUserJob());
		
		if (errors.size() > 0) {
			request.setAttribute(BaseWebConstants.ERROR, 1);
		}
			
		Logging.info(this, "End Validating");
		
		return errors;
	}
}
