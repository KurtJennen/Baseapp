package be.luxuryoverdosis.framework.web.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;

import be.luxuryoverdosis.framework.base.FileType;
import be.luxuryoverdosis.framework.logging.Logging;
import be.luxuryoverdosis.framework.web.BaseWebConstants;
import be.luxuryoverdosis.framework.web.message.MessageLocator;

public class DetailDocumentForm extends BaseForm {
	private static final long serialVersionUID = 1L;
	
	private FormFile formFile;
	private String type;
	private String fileName;
	private int fileSize;
	private String contentType;
	private String fancytext;
	
	
	public FormFile getFormFile() {
		return formFile;
	}
	public void setFormFile(FormFile formFile) {
		this.formFile = formFile;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getFileSize() {
		return fileSize;
	}
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getFancytext() {
		return fancytext;
	}
	public void setFancytext(String fancytext) {
		this.fancytext = fancytext;
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		this.setFormFile(null);
		this.setType("");
		this.setFileName("");
		this.setFileSize(0);
		this.setContentType("");
		this.setFancytext("Word nog niet bewaard");
	}
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		Logging.info(this, "Begin Validating");
		
		ActionErrors errors = new ActionErrors();
		
		if(this.getMethod().equals(BaseWebConstants.UPDATE)) {
			errors = super.validate(mapping, request);
			
			if(!this.getFormFile().getFileName().endsWith(FileType.ODT)) {
				errors.add("formFile", new ActionMessage("ends.not.with", new String[] {MessageLocator.getMessage(request, "file"), FileType.ODT}));
			}
		}
		
		if(errors.size() > 0) {
			request.setAttribute(BaseWebConstants.ERROR, 1);
		}
			
		Logging.info(this, "End Validating");
		
		return errors;
	}
}
