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
import be.luxuryoverdosis.framework.business.webservice.interfaces.RoleRestService;
import be.luxuryoverdosis.framework.data.dto.RoleDTO;
import be.luxuryoverdosis.framework.data.restwrapperdto.RestWrapperDTO;

@RestController
@RequestMapping("/role")
@CrossOrigin(origins = {"${rest.origin}"})
public class GetRoleRest {
	@RequestMapping(value = "/readRequest", method = RequestMethod.GET, produces = FileContentType.REST_RESPONSE_JSON_UTF8)
	public String readRequest(@RequestParam(value="id") int id) throws JsonProcessingException {
		try {
			return getRoleRestService().readRequest(id);
		} catch (Exception e) {
			return createWrapperDTO(e);
		}
	}

	@RequestMapping(value = "/readAllRequest", method = RequestMethod.GET, produces = FileContentType.REST_RESPONSE_JSON_UTF8)
	public String readAllRequest() throws JsonProcessingException {
		try {
			return getRoleRestService().readAllRequest();
		} catch (Exception e) {
			return createWrapperDTO(e);
		}
	}

	@RequestMapping(value = "/createOrUpdateRequest", method = {RequestMethod.PUT, RequestMethod.POST}, produces = FileContentType.REST_RESPONSE_JSON_UTF8)
	public String createOrUpdateRequest(@RequestBody() RoleDTO roleDTO) throws JsonProcessingException {
		try {
			return getRoleRestService().createOrUpdateRequest(roleDTO);
		}  catch (Exception e) {
			return createWrapperDTO(e);
		}
	}
	
	@RequestMapping(value = "/deleteRequest", method = RequestMethod.DELETE, produces = FileContentType.REST_RESPONSE_JSON_UTF8)
	public String deleteRequest(@RequestParam(value="id") int id) throws JsonProcessingException {
		try {
			return getRoleRestService().deleteRequest(id);
		} catch (Exception e) {
			return createWrapperDTO(e);
		}
	}
	
	private RoleRestService getRoleRestService() {
		return BaseSpringServiceLocator.getBean(RoleRestService.class);
	}
	
	private String createWrapperDTO(Exception e) throws JsonProcessingException {
		RestWrapperDTO<RoleDTO> restWrapperDTO = new RestWrapperDTO<RoleDTO>();
		return restWrapperDTO.sendRestErrorWrapperDto(e.getMessage());
	}	
	
}
