package be.luxuryoverdosis.framework.web.action.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionRedirect;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;

import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;
import be.luxuryoverdosis.framework.business.service.interfaces.DocumentService;
import be.luxuryoverdosis.framework.data.dto.DocumentDTO;
import be.luxuryoverdosis.framework.data.dto.FileDTO;
import be.luxuryoverdosis.framework.logging.Logging;
import be.luxuryoverdosis.framework.web.BaseWebConstants;
import be.luxuryoverdosis.framework.web.form.DetailDocumentForm;
import be.luxuryoverdosis.framework.web.message.MessageLocator;

public class DetailDocumentAction extends DispatchAction {
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin List");
		Logging.info(this, "End List Success");
		
		return (mapping.findForward("list"));
	}
	
	public ActionForward read(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Read");
		ActionMessages actionMessages = new ActionMessages();
		
		int id = Integer.parseInt(request.getParameter(BaseWebConstants.ID));
		String previous = request.getParameter(BaseWebConstants.PREVIOUS);
		
		DocumentDTO documentDTO = getDocumentService().readDTO(id);
		DetailDocumentForm documentForm = (DetailDocumentForm) form;
		documentForm.setId(documentDTO.getId());
		documentForm.setType(documentDTO.getType());
		documentForm.setFileName(documentDTO.getFileDTO().getFileName());
		documentForm.setFileSize(documentDTO.getFileDTO().getFileSize());
		documentForm.setContentType(documentDTO.getFileDTO().getContentType());
		
		if(BaseWebConstants.SAVE.equals(previous)) {
			actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("save.success", MessageLocator.getMessage(request, "table.document")));
		}
		if(BaseWebConstants.UPDATE.equals(previous)) {
			actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("update.success", MessageLocator.getMessage(request, "table.document")));
		}
		actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("read.success", MessageLocator.getMessage(request, "table.document")));
		saveMessages(request, actionMessages);
		
		Logging.info(this, "End Read Success");
		
		return mapping.getInputForward();
	}
	
	public ActionForward create(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Create");
		ActionMessages actionMessages = new ActionMessages();
		
		DetailDocumentForm documentForm = (DetailDocumentForm) form;
		documentForm.reset(mapping, request);
		
		actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("create.success", MessageLocator.getMessage(request, "table.document")));
		saveMessages(request, actionMessages);
		
		Logging.info(this, "End Create Success");
		
		return mapping.getInputForward();

	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Update");
		
		ActionRedirect actionRedirect = new ActionRedirect(mapping.findForward("read"));
		
		DetailDocumentForm documentForm = (DetailDocumentForm) form;
		
		FormFile formFile = documentForm.getFormFile();
		FileDTO fileDTO = new FileDTO(formFile.getFileData(), formFile.getFileName(), formFile.getFileSize(), formFile.getContentType());
		
		DocumentDTO documentDTO = new DocumentDTO();
		documentDTO.setId(documentForm.getId());
		documentDTO.setType(documentForm.getType());
		documentDTO.setFileDTO(fileDTO);
		
		documentDTO = getDocumentService().createOrUpdateDTO(documentDTO);
		if(documentForm.getId() < 0) {
			actionRedirect.addParameter(BaseWebConstants.PREVIOUS, BaseWebConstants.SAVE);
		} else {
			actionRedirect.addParameter(BaseWebConstants.PREVIOUS, BaseWebConstants.UPDATE);
		}
		
		actionRedirect.addParameter(BaseWebConstants.ID, documentDTO.getId());
		
		Logging.info(this, "End Update Success");
		
		return actionRedirect;
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Delete");
		
		int id = Integer.parseInt(request.getParameter(BaseWebConstants.ID));
		
		getDocumentService().delete(id);
		
		ActionRedirect actionRedirect = new ActionRedirect(mapping.findForward("list"));
		actionRedirect.addParameter(BaseWebConstants.PREVIOUS, BaseWebConstants.DELETE);
		
		Logging.info(this, "End Delete Success");

		return actionRedirect;
	}
	
	private DocumentService getDocumentService() {
		return BaseSpringServiceLocator.getBean(DocumentService.class);
	}
}
