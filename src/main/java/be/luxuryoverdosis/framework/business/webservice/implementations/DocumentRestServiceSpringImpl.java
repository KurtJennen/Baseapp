package be.luxuryoverdosis.framework.business.webservice.implementations;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;

import be.luxuryoverdosis.framework.business.service.interfaces.DocumentService;
import be.luxuryoverdosis.framework.business.thread.ThreadManager;
import be.luxuryoverdosis.framework.business.webservice.interfaces.DocumentRestService;
import be.luxuryoverdosis.framework.data.dto.DocumentDTO;
import be.luxuryoverdosis.framework.data.restwrapperdto.RestWrapperDTO;
import be.luxuryoverdosis.framework.logging.Logging;

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
		
		DocumentDTO documentDTO = documentService.readDTO(id);
		restWrapperDTO.setDto(documentDTO);
		
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
		restWrapperDTO.setDtoList(documentDTOList);
		
		Logging.info(this, "End readAllUserRequest");
		return restWrapperDTO.sendRestWrapperDto();
	}

	@Transactional
	public String createOrUpdateRequest(final DocumentDTO documentDTO) throws JsonProcessingException {
		Logging.info(this, "Begin createOrUpdateRequest");
		
		RestWrapperDTO<DocumentDTO> restWrapperDTO = createRestWrapperDTO();
		
		if(ThreadManager.getUserFromThread() == null) {
			return checkUserOnThread(restWrapperDTO);
		}
		
		DocumentDTO	savedDocumentDTO = documentService.createOrUpdateDTO(documentDTO);
		restWrapperDTO.setDto(savedDocumentDTO);
		
		Logging.info(this, "End createOrUpdateRequest");
		return restWrapperDTO.sendRestWrapperDto(); 
	}

	@Transactional
	public String deleteRequest(final int id) throws JsonProcessingException {
		Logging.info(this, "Begin deleterRequest");
		
		RestWrapperDTO<DocumentDTO> restWrapperDTO = createRestWrapperDTO();
		
		if(ThreadManager.getUserFromThread() == null) {
			return checkUserOnThread(restWrapperDTO);
		}
		
		documentService.delete(id);
		
		Logging.info(this, "Begin deleterRequest");
		return restWrapperDTO.sendRestWrapperDto(); 
	}
	
	@Transactional(readOnly=true)
	public byte[] downloadRequest(final int id) throws JsonProcessingException {
		Logging.info(this, "Begin deleterRequest");
		
//		RestWrapperDTO<DocumentDTO> restWrapperDTO = createRestWrapperDTO();
		
//		if(ThreadManager.getUserFromThread() == null) {
//			return checkUserOnThread(restWrapperDTO);
//		}
		
		byte[] fileData = documentService.downloadFile(id);
//		restWrapperDTO.setFileData(fileData);
		
		Logging.info(this, "Begin deleterRequest");
//		return restWrapperDTO.sendRestWrapperDto(); 
		return fileData;
	}

	private RestWrapperDTO<DocumentDTO> createRestWrapperDTO() {
		return new RestWrapperDTO<DocumentDTO>();
	}
	
}
