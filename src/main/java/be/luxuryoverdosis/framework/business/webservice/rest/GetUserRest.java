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
import be.luxuryoverdosis.framework.business.webservice.interfaces.UserRestService;
import be.luxuryoverdosis.framework.data.dto.UserDTO;
import be.luxuryoverdosis.framework.data.restwrapperdto.RestWrapperDTO;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = {"${rest.origin}"})
public class GetUserRest {
	@RequestMapping(value = "/readRequest", method = RequestMethod.GET, produces = FileContentType.REST_RESPONSE_JSON_UTF8)
	public String readRequest(@RequestParam(value = "id") final int id) throws JsonProcessingException {
		try {
			return getUserRestService().readRequest(id);
		} catch (Exception e) {
			return createWrapperDTO(e);
		}
	}
	
	@RequestMapping(value = "/readRequestName", method = RequestMethod.GET, produces = FileContentType.REST_RESPONSE_JSON_UTF8)
	public String readRequestName(@RequestParam(value = "name") final String name, @RequestParam(value = "password") final String password) throws JsonProcessingException {
		try {
			return getUserRestService().readRequest(name, password);
		} catch (Exception e) {
			return createWrapperDTO(e);
		}
	}
	
	@RequestMapping(value = "/readAllRequest", method = RequestMethod.GET, produces = FileContentType.REST_RESPONSE_JSON_UTF8)
	public String readAllRequest() throws JsonProcessingException {
		try {
			return getUserRestService().readAllRequest();
		} catch (Exception e) {
			return createWrapperDTO(e);
		}
		
	}

	@RequestMapping(value = "/createOrUpdateRequest", method = {RequestMethod.PUT, RequestMethod.POST}, produces = FileContentType.REST_RESPONSE_JSON_UTF8)
	public String createOrUpdateRequest(@RequestBody() final UserDTO userDTO) throws JsonProcessingException {
		try {
			return getUserRestService().createOrUpdateRequest(userDTO);
		} catch (Exception e) {
			return createWrapperDTO(e);
		}
	}
	
	@RequestMapping(value = "/deleteRequest", method = RequestMethod.DELETE, produces = FileContentType.REST_RESPONSE_JSON_UTF8)
	public String deleteRequest(@RequestParam(value = "id") final int id) throws JsonProcessingException {
		try {
			return getUserRestService().deleteRequest(id);
		} catch (Exception e) {
			return createWrapperDTO(e);
		}
	}
	
	private UserRestService getUserRestService() {
		return BaseSpringServiceLocator.getBean(UserRestService.class);
	}
	
	private String createWrapperDTO(final Exception e) throws JsonProcessingException {
		RestWrapperDTO<UserDTO> restWrapperDTO = new RestWrapperDTO<UserDTO>();
		return restWrapperDTO.sendRestErrorWrapperDto(e.getMessage());
	}	
	
}
