package be.luxuryoverdosis.framework.web.action.security;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;
import org.jmesa.facade.TableFacade;
import org.jmesa.facade.TableFacadeFactory;

import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;
import be.luxuryoverdosis.framework.business.service.interfaces.DocumentService;
import be.luxuryoverdosis.framework.data.dto.DocumentDTO;
import be.luxuryoverdosis.framework.data.dto.FileDTO;
import be.luxuryoverdosis.framework.data.to.Document;
import be.luxuryoverdosis.framework.logging.Logging;
import be.luxuryoverdosis.framework.web.BaseWebConstants;
import be.luxuryoverdosis.framework.web.form.DocumentForm;
import be.luxuryoverdosis.framework.web.jmesa.DocumentJmesaTemplate;
import be.luxuryoverdosis.framework.web.message.MessageLocator;
import be.luxuryoverdosis.framework.web.sessionmanager.SessionManager;

public class DocumentAction extends DispatchAction {
	private void storeListsInSession(HttpServletRequest request, ActionMessages actionMessages) {
		SessionManager.delete(request, SessionManager.TYPE_ATTRIBUTES, SessionManager.SUBTYPE_LIST);
	}
	
	public ActionForward listJmesa(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//DocumentService documentService = (DocumentService)SpringServiceLocator.getBean(SpringServiceConstants.DOCUMENT_SERVICE);
		DocumentService documentService = BaseSpringServiceLocator.getBean(DocumentService.class);
		ArrayList<Document> documentList = new ArrayList<Document>();
		documentList = documentService.list();
		
		//JMesa Start	
		TableFacade tableFacade = TableFacadeFactory.createTableFacade(BaseWebConstants.DOCUMENT_LIST, request, response);
		DocumentJmesaTemplate documentJmesaTemplate = new DocumentJmesaTemplate(tableFacade, documentList, request);
		String html = documentJmesaTemplate.render();
		if(html == null) {
			return null;
		}
        request.setAttribute(BaseWebConstants.DOCUMENT_LIST, html);
		//JMesa End
        
        return (mapping.findForward("list"));
	}
	
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin List");
		ActionMessages actionMessages = new ActionMessages();
		
		SessionManager.delete(request, SessionManager.TYPE_ATTRIBUTES, SessionManager.SUBTYPE_IDS);
		
		storeListsInSession(request, actionMessages);
				
		if(listJmesa(mapping, form, request, response) == null) {
			return null;
		}
		
		actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("list.success", MessageLocator.getMessage(request, "table.document")));
		saveMessages(request, actionMessages);
			
		Logging.info(this, "End List Success");
		
		return (mapping.findForward("list"));
	}
	
	public ActionForward read(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Read");
		ActionMessages actionMessages = new ActionMessages();
		
		int id = Integer.parseInt(request.getParameter("objectId"));
		
		//DocumentService documentService = (DocumentService)SpringServiceLocator.getBean(SpringServiceConstants.DOCUMENT_SERVICE);
		DocumentService documentService = BaseSpringServiceLocator.getBean(DocumentService.class);
		DocumentDTO documentDTO = documentService.readDTO(id);
		DocumentForm documentForm = (DocumentForm) form;
		documentForm.setObjectId(documentDTO.getId());
		documentForm.setType(documentDTO.getType());
		documentForm.setFileName(documentDTO.getFileDTO().getFileName());
		documentForm.setFileSize(documentDTO.getFileDTO().getFileSize());
		documentForm.setContentType(documentDTO.getFileDTO().getContentType());
		
		actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("read.success", MessageLocator.getMessage(request, "table.document")));
		saveMessages(request, actionMessages);
		
		Logging.info(this, "End Read Success");
		
		return mapping.getInputForward();
	}
	
	public ActionForward create(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Create");
		ActionMessages actionMessages = new ActionMessages();
		
		DocumentForm documentForm = (DocumentForm) form;
		documentForm.reset(mapping, request);
		
		actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("create.success", MessageLocator.getMessage(request, "table.document")));
		saveMessages(request, actionMessages);
		
		Logging.info(this, "End Create Success");
		
		return mapping.getInputForward();

	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Update");
		ActionMessages actionMessages = new ActionMessages();
		
		DocumentForm documentForm = (DocumentForm) form;
		
		//DocumentService documentService = (DocumentService)SpringServiceLocator.getBean(SpringServiceConstants.DOCUMENT_SERVICE);
		DocumentService documentService = BaseSpringServiceLocator.getBean(DocumentService.class);
		FormFile formFile = documentForm.getFormFile();
		FileDTO fileDTO = new FileDTO(formFile.getFileData(), formFile.getFileName(), formFile.getFileSize(), formFile.getContentType());
		
		DocumentDTO documentDTO = new DocumentDTO();
		documentDTO.setId(documentForm.getObjectId());
		documentDTO.setType(documentForm.getType());
		documentDTO.setFileDTO(fileDTO);
		
		documentDTO = documentService.createOrUpdateDTO(documentDTO);
		if(documentForm.getObjectId() < 0) {
			actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("save.success", MessageLocator.getMessage(request, "table.document")));
		} else {
			actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("update.success", MessageLocator.getMessage(request, "table.document")));
		}
		
		documentForm.setObjectId(documentDTO.getId());
		documentForm.setType(documentDTO.getType());
		documentForm.setFileName(documentDTO.getFileDTO().getFileName());
		documentForm.setFileSize(documentDTO.getFileDTO().getFileSize());
		documentForm.setContentType(documentDTO.getFileDTO().getContentType());
		
		saveMessages(request, actionMessages);
		
		Logging.info(this, "End Update Success");
		
		return mapping.getInputForward();
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Delete");
		ActionMessages actionMessages = new ActionMessages();
		
		int id = Integer.parseInt(request.getParameter("objectId"));
		
		//DocumentService documentService = (DocumentService)SpringServiceLocator.getBean(SpringServiceConstants.DOCUMENT_SERVICE);
		DocumentService documentService = BaseSpringServiceLocator.getBean(DocumentService.class);
		documentService.delete(id);
		
		actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("delete.success", MessageLocator.getMessage(request, "table.document")));
		saveMessages(request, actionMessages);
		
		Logging.info(this, "End Delete Success");
		
		return list(mapping, form, request, response);
	}
}
