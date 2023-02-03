package be.luxuryoverdosis.framework.business.webservice.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import be.luxuryoverdosis.framework.base.FileContentType;
import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;
import be.luxuryoverdosis.framework.business.webservice.interfaces.UserRestService;
import be.luxuryoverdosis.framework.data.dto.UserDTO;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = {"${rest.origin}"})
public class GetUserRest {
	@RequestMapping(value = "/readUserRequest", method = RequestMethod.GET, produces = FileContentType.REST_RESPONSE_JSON_UTF8)
	public String readUserRequest(@RequestParam(value="id") int id) throws JsonProcessingException {
		return getUserRestService().readUserRequest(id);
	}
	
	@RequestMapping(value = "/readUserRequestName", method = RequestMethod.GET, produces = FileContentType.REST_RESPONSE_JSON_UTF8)
	public String readUserRequest(@RequestParam(value="name") String name, @RequestParam(value="password") String password) throws JsonProcessingException {
		return getUserRestService().readUserRequest(name, password);
	}
	
	@RequestMapping(value = "/readAllUsersRequest", method = RequestMethod.GET, produces = FileContentType.REST_RESPONSE_JSON_UTF8)
	public String readAllUsersRequest() throws JsonProcessingException {
		return getUserRestService().readAllUsersRequest();
	}

//	@RequestMapping(value = "/createOrUpdateUserRequest", method = RequestMethod.PUT, produces = FileContentType.REST_RESPONSE_JSON_UTF8)
//	public String createOrUpdateUserRequest(@RequestHeader(value="name") String name,
//			@RequestHeader(value="userName") String userName,
//			@RequestHeader(value="encryptedPassword") String encryptedPassword,
//			@RequestHeader(value="email") String email,
//			@RequestHeader(value="roleNames") String[] roleNames) throws JsonProcessingException {
//		return getUserRestService().createOrUpdateUserRequest(name, userName, encryptedPassword, email, roleNames);
//	}
	@RequestMapping(value = "/updateUserRequest", method = RequestMethod.PUT, produces = FileContentType.REST_RESPONSE_JSON_UTF8)
	public String updateUserRequest(@RequestBody() UserDTO userDTO) throws JsonProcessingException {
		return getUserRestService().createOrUpdateUserRequest(userDTO);
	}
	
//	@RequestMapping(value = "/createUserRequest", method = RequestMethod.POST, produces = FileContentType.REST_RESPONSE_JSON_UTF8)
//	public String createUserRequest(@RequestBody() UserDTO userDTO) throws JsonProcessingException {
//		return getUserRestService().createOrUpdateUserRequest(userDTO.getName(), userDTO.getUserName(), userDTO.getPassword(), userDTO.getEmail(), userDTO.getRoles().toArray(new String[0]));
//	}
	
	@RequestMapping(value = "/deleteUserRequest", method = RequestMethod.DELETE, produces = FileContentType.REST_RESPONSE_JSON_UTF8)
	public String deleteUserRequest(@RequestHeader(value="name") String name) throws JsonProcessingException {
		return getUserRestService().deleteUserRequest(name);
	}
	
	private UserRestService getUserRestService() {
		return BaseSpringServiceLocator.getBean(UserRestService.class);
	}
	
}
