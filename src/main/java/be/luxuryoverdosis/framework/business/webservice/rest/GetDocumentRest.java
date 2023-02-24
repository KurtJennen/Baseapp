package be.luxuryoverdosis.framework.business.webservice.rest;

import java.io.ByteArrayInputStream;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;

import be.luxuryoverdosis.framework.base.FileContentType;
import be.luxuryoverdosis.framework.business.enumeration.DocumentTypeEnum;
import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;
import be.luxuryoverdosis.framework.business.webservice.interfaces.DocumentRestService;
import be.luxuryoverdosis.framework.data.dto.DocumentDTO;
import be.luxuryoverdosis.framework.data.restwrapperdto.RestWrapperDTO;

@RestController
@RequestMapping("/document")
@CrossOrigin(origins = {"${rest.origin}"})
public class GetDocumentRest {
	@RequestMapping(value = "/readRequest", method = RequestMethod.GET, produces = FileContentType.REST_RESPONSE_JSON_UTF8)
	public String readRequest(@RequestParam(value="id") int id) throws JsonProcessingException {
		try {
			return getDocumentRestService().readRequest(id);
		} catch (Exception e) {
			return createWrapperDTO(e);
		}
	}
	
	@RequestMapping(value = "/readAllRequest", method = RequestMethod.GET, produces = FileContentType.REST_RESPONSE_JSON_UTF8)
	public String readAllRequest() throws JsonProcessingException {
		try {
			return getDocumentRestService().readAllRequest();
		} catch (Exception e) {
			return createWrapperDTO(e);
		}
	}

	@RequestMapping(value = "/createOrUpdateRequest", method = {RequestMethod.PUT, RequestMethod.POST}, produces = FileContentType.REST_RESPONSE_JSON_UTF8, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String createOrUpdateRequest(@RequestParam(value="id") int id, @RequestParam(value="myFile") MultipartFile file) throws JsonProcessingException {
		try {
			DocumentDTO documentDTO = new DocumentDTO();
			documentDTO.setId(id);
			documentDTO.setType(DocumentTypeEnum.USER.getCode());
			documentDTO.setFileData(file.getBytes());
			documentDTO.setFileName(file.getOriginalFilename());
			documentDTO.setFileSize(Long.valueOf(file.getSize()).intValue());
			documentDTO.setContentType(file.getContentType());
			
			return getDocumentRestService().createOrUpdateRequest(documentDTO);
		} catch (Exception e) {
			return createWrapperDTO(e);
		}
	}
	
	@RequestMapping(value = "/deleteRequest", method = RequestMethod.DELETE, produces = FileContentType.REST_RESPONSE_JSON_UTF8)
	public String deleteRequest(@RequestParam(value="id") int id) throws JsonProcessingException {
		try {
			return getDocumentRestService().deleteRequest(id);
		} catch (Exception e) {
			return createWrapperDTO(e);
		}
	}
	
	@RequestMapping(value = "/downloadRequest", method = RequestMethod.GET)
	public ResponseEntity<Resource> downloadRequest(@RequestParam(value="id") int id) throws JsonProcessingException {
		try {
			byte [] fileData = getDocumentRestService().downloadRequest(id);
			
			InputStreamResource inputStreamResource = new InputStreamResource(new ByteArrayInputStream(fileData));
//			return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).body(inputStreamResource);
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentLength(fileData.length);
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.set(HttpHeaders.CONTENT_DISPOSITION, "\"attachment;filename=Test.odt");
			
			return new ResponseEntity<Resource>(inputStreamResource, headers, HttpStatus.OK);
		} catch (Exception e) {
			return null;
		}
	}
	
	private DocumentRestService getDocumentRestService() {
		return BaseSpringServiceLocator.getBean(DocumentRestService.class);
	}
	
	private String createWrapperDTO(Exception e) throws JsonProcessingException {
		RestWrapperDTO<DocumentDTO> restWrapperDTO = new RestWrapperDTO<DocumentDTO>();
		return restWrapperDTO.sendRestErrorWrapperDto(e.getMessage());
	}
}
