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
	private int[] selectedIdsExportJob;
	private int[] selectedIdsImportJob;
	
	public FormFile getFormFile() {
		return formFile;
	}
	public void setFormFile(FormFile formFile) {
		this.formFile = formFile;
	}
	public int[] getSelectedIdsExportJob() {
		return selectedIdsExportJob;
	}
	public void setSelectedIdsExportJob(int[] selectedIdsExportJob) {
		this.selectedIdsExportJob = selectedIdsExportJob;
	}
	public int[] getSelectedIdsImportJob() {
		return selectedIdsImportJob;
	}
	public void setSelectedIdsImportJob(int[] selectedIdsImportJob) {
		this.selectedIdsImportJob = selectedIdsImportJob;
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		this.setFormFile(null);
	}
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		Logging.info(this, "Begin Validating");
		
		ActionErrors errors = super.validate(mapping, request);
		
		if(this.getMethod().equals(BaseWebConstants.IMPORT_USER_JOB)) {
			if(StringUtils.isEmpty(this.getFormFile().getFileName())) {
				errors.add("formFile", new ActionMessage("errors.required", MessageLocator.getMessage(request, "file")));
			}
		}
		
		//super.checkOnlyOneSelected(mapping, request, errors, BaseWebConstants.READ, getSelectedIds());
		super.checkOnlyOneSelected(mapping, request, errors, BaseWebConstants.READ_EXPORT_JOB, getSelectedIdsExportJob());
		super.checkOnlyOneSelected(mapping, request, errors, BaseWebConstants.READ_IMPORT_JOB, getSelectedIdsImportJob());
		
		if(errors.size() > 0) {
			request.setAttribute(BaseWebConstants.ERROR, 1);
		}
			
		Logging.info(this, "End Validating");
		
		return errors;
	}
}
