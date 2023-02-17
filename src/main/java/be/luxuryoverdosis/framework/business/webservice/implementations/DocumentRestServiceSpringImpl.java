package be.luxuryoverdosis.framework.business.webservice.implementations;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;

import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;
import be.luxuryoverdosis.framework.business.service.interfaces.DocumentService;
import be.luxuryoverdosis.framework.business.thread.ThreadManager;
import be.luxuryoverdosis.framework.business.webservice.interfaces.DocumentRestService;
import be.luxuryoverdosis.framework.data.dto.DocumentDTO;
import be.luxuryoverdosis.framework.data.factory.DocumentFactory;
import be.luxuryoverdosis.framework.data.restwrapperdto.RestWrapperDTO;
import be.luxuryoverdosis.framework.data.to.Document;
import be.luxuryoverdosis.framework.logging.Logging;
import be.luxuryoverdosis.framework.web.exception.ServiceException;

@Service
public class DocumentRestServiceSpringImpl extends BaseRestService implements DocumentRestService {
	@Resource
	private DocumentService documentService;
	
	@Transactional(readOnly=true)
	public String readRequest(final int id) throws JsonProcessingException {
		Logging.info(this, "Begin readRequest");
		
		RestWrapperDTO<DocumentDTO> restWrapperDTO = createRestWrapperDTO();
		
		if(ThreadManager.getUserFromThread() == null) {
			return checkUserOnThread(restWrapperDTO);
		}
		
		Document document = documentService.read(id);
		if (document == null) {
			String error = BaseSpringServiceLocator.getMessage("exists.not", new Object[]{BaseSpringServiceLocator.getMessage("table.document")});
			return restWrapperDTO.sendRestErrorWrapperDto(error);
		}
		
		restWrapperDTO.setDto(produceDocumentDTO(document));
		
		Logging.info(this, "End readRequest");
		return restWrapperDTO.sendRestWrapperDto();
	}
	
	@Transactional(readOnly=true)
	public String readAllRequest() throws JsonProcessingException {
		Logging.info(this, "Begin readAllRequest");
		
		RestWrapperDTO<DocumentDTO> restWrapperDTO = createRestWrapperDTO();
		
		if(ThreadManager.getUserFromThread() == null) {
			return checkUserOnThread(restWrapperDTO);
		}
		
		ArrayList<DocumentDTO> documentDTOList = documentService.listDTO();
		if (documentDTOList == null) {
			String error =  BaseSpringServiceLocator.getMessage("exists.not", new Object[]{BaseSpringServiceLocator.getMessage("table.document")});
			return restWrapperDTO.sendRestErrorWrapperDto(error);
		}
		
		restWrapperDTO.setDtoList(documentDTOList);
		
		Logging.info(this, "End readAllUserRequest");
		return restWrapperDTO.sendRestWrapperDto();
	}

	@Transactional(rollbackFor = UnexpectedRollbackException.class)
	public String createOrUpdateRequest(final DocumentDTO documentDTO) throws JsonProcessingException {
		Logging.info(this, "Begin createOrUpdateRequest");
		
		RestWrapperDTO<DocumentDTO> restWrapperDTO = createRestWrapperDTO();
		
		if(ThreadManager.getUserFromThread() == null) {
			return checkUserOnThread(restWrapperDTO);
		}
		
		boolean isNew = false;
		
		Document document = null;
		if(documentDTO.getId() > 0) {
			document = documentService.read(documentDTO.getId());
		} 
		if (document == null) {
			document = new Document();
			isNew = true;
		}
		
		document = DocumentFactory.produceDocument(document, documentDTO);
		//document.setName(documentDTO.getName());
		
		try {
			document = documentService.createOrUpdate(document);
			restWrapperDTO.setDto(produceDocumentDTO(document));
			if (isNew) {
				Logging.info(this, "End createOrUpdateRequest");
				String message = BaseSpringServiceLocator.getMessage("save.success", new Object[]{BaseSpringServiceLocator.getMessage("table.document")});
				return restWrapperDTO.sendRestMessageWrapperDto(message);
			} else {
				Logging.info(this, "End createOrUpdateRequest");
				String message = BaseSpringServiceLocator.getMessage("update.success", new Object[]{BaseSpringServiceLocator.getMessage("table.document")});
				return restWrapperDTO.sendRestMessageWrapperDto(message);
			}
		} catch (ServiceException e) {
			//return restWrapperDTO.sendRestErrorWrapperDto(e.getMessage());
			throw e;
		}
	}

	@Transactional
	public String deleteRequest(final int id) throws JsonProcessingException {
		Logging.info(this, "Begin deleterRequest");
		
		RestWrapperDTO<DocumentDTO> restWrapperDTO = createRestWrapperDTO();
		
		if(ThreadManager.getUserFromThread() == null) {
			return checkUserOnThread(restWrapperDTO);
		}
		
		Document document = documentService.read(id);
		restWrapperDTO.setDto(produceDocumentDTO(document));
		if(document != null) {
			documentService.delete(document.getId());
			Logging.info(this, "Begin deleteRequest");
			String message = BaseSpringServiceLocator.getMessage("delete.success", new Object[]{BaseSpringServiceLocator.getMessage("table.user")});
			return restWrapperDTO.sendRestMessageWrapperDto(message);
		} else {
			Logging.info(this, "Begin deleteRequest");
			String message = BaseSpringServiceLocator.getMessage("exists.not", new Object[]{BaseSpringServiceLocator.getMessage("table.user")});
			return restWrapperDTO.sendRestMessageWrapperDto(message);
		}
	}

	private RestWrapperDTO<DocumentDTO> createRestWrapperDTO() {
		return new RestWrapperDTO<DocumentDTO>();
	}
	
	private DocumentDTO produceDocumentDTO(Document document) {
		return DocumentFactory.produceDocumentDTO(document);
	}
}
