package be.luxuryoverdosis.framework.business.webservice.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import be.luxuryoverdosis.framework.base.FileContentType;
import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;
import be.luxuryoverdosis.framework.business.webservice.interfaces.DocumentRestService;
import be.luxuryoverdosis.framework.data.dto.DocumentDTO;
import be.luxuryoverdosis.framework.data.restwrapperdto.RestWrapperDTO;
import be.luxuryoverdosis.framework.web.exception.ServiceException;

@RestController
@RequestMapping("/document")
@CrossOrigin(origins = {"${rest.origin}"})
public class GetDocumentRest {
	@RequestMapping(value = "/readRequest", method = RequestMethod.GET, produces = FileContentType.REST_RESPONSE_JSON_UTF8)
	public String readRequest(@RequestParam(value="id") int id) throws JsonProcessingException {
		return getDocumentRestService().readRequest(id);
	}
	
	@RequestMapping(value = "/readAllRequest", method = RequestMethod.GET, produces = FileContentType.REST_RESPONSE_JSON_UTF8)
	public String readAllRequest() throws JsonProcessingException {
		return getDocumentRestService().readAllRequest();
	}

	@RequestMapping(value = "/createOrUpdateRequest", method = {RequestMethod.PUT, RequestMethod.POST}, produces = FileContentType.REST_RESPONSE_JSON_UTF8)
	public String createOrUpdateRequest(@RequestBody() DocumentDTO documentDTO) throws JsonProcessingException {
		try {
			return getDocumentRestService().createOrUpdateRequest(documentDTO);
		} catch (ServiceException e) {
			RestWrapperDTO<DocumentDTO> restWrapperDTO = new RestWrapperDTO<DocumentDTO>();
			return restWrapperDTO.sendRestErrorWrapperDto(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/deleteRequest", method = RequestMethod.DELETE, produces = FileContentType.REST_RESPONSE_JSON_UTF8)
	public String deleteRequest(@RequestParam(value="id") int id) throws JsonProcessingException {
		return getDocumentRestService().deleteRequest(id);
	}
	
	private DocumentRestService getDocumentRestService() {
		return BaseSpringServiceLocator.getBean(DocumentRestService.class);
	}
	
}
