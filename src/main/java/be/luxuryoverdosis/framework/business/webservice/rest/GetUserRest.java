package be.luxuryoverdosis.framework.business.webservice.rest;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;
import be.luxuryoverdosis.framework.business.service.interfaces.RoleService;
import be.luxuryoverdosis.framework.business.service.interfaces.UserService;
import be.luxuryoverdosis.framework.data.dto.UserDTO;
import be.luxuryoverdosis.framework.data.to.Role;
import be.luxuryoverdosis.framework.data.to.UserTO;
import be.luxuryoverdosis.framework.web.exception.ServiceException;
import net.sf.json.JSONArray;

@RestController
@RequestMapping("/user")
public class GetUserRest {
	String message;
	
	@RequestMapping(value = "/readUserRequest", method = RequestMethod.GET)
	//public String readUserRequest(@RequestParam(value="name") String name) throws Exception {
	public String readUserRequest(@RequestHeader(value="name") String name) throws Exception {
		UserDTO userDTO = null;
		UserTO userTO = getUserService().readName(name);
		if (userTO == null) {
			message = BaseSpringServiceLocator.getMessage("exists.not", new Object[]{BaseSpringServiceLocator.getMessage("table.user")});
		} else {
			userDTO = getUserService().readDTO(userTO.getId());
			if (userDTO == null) {
				message = BaseSpringServiceLocator.getMessage("exists.not", new Object[]{BaseSpringServiceLocator.getMessage("table.user")});
			}
		}
		
		if (message == null)  {
			return JSONArray.fromObject(userDTO).toString();
		}
		
		return message;
	}
	
	@RequestMapping(value = "/readAllUsersRequest", method = RequestMethod.GET)
	public String readUserRequest() throws Exception {
		
		ArrayList<UserDTO> userDTOList = getUserService().listDTO();
		if (userDTOList == null) {
			message = BaseSpringServiceLocator.getMessage("exists.not", new Object[]{BaseSpringServiceLocator.getMessage("table.user")});
		}
		
		if (message == null)  {
			return JSONArray.fromObject(userDTOList).toString();
		}
		
		return message;
	}

	@RequestMapping(value = "/createOrUpdateUserRequest", method = RequestMethod.POST)
	public String createOrUpdateUserRequest(@RequestHeader(value="name") String name,
			@RequestHeader(value="userName") String userName,
			@RequestHeader(value="encryptedPassword") String encryptedPassword,
			@RequestHeader(value="email") String email,
			@RequestHeader(value="roleName") String roleName) throws Exception {
		boolean isNew = false;
		String message = StringUtils.EMPTY;
		
		UserTO userTO = getUserService().readName(name);
		if (userTO == null) {
			userTO = new UserTO();
			userTO.setId(-1);
			isNew = true;
		}
		
		userTO.setEmail(email);
		userTO.setEncryptedPassword(encryptedPassword);
		userTO.setName(name);
		userTO.setUserName(userName);
		
		Role role = getRoleService().readName(roleName);
		if (role == null) {
			message = BaseSpringServiceLocator.getMessage("exists.not", new Object[]{BaseSpringServiceLocator.getMessage("table.role")});
		}
		userTO.setRole(role);
		
		try {
			getUserService().createOrUpdate(userTO);
			if (isNew) {
				message = BaseSpringServiceLocator.getMessage("save.success", new Object[]{BaseSpringServiceLocator.getMessage("table.user")});
			} else {
				message = BaseSpringServiceLocator.getMessage("update.success", new Object[]{BaseSpringServiceLocator.getMessage("table.user")});
			}
		} catch (ServiceException e) {
			message = e.getMessage();
		}
		
		return message;
	}
	
	@RequestMapping(value = "/deleteUserRequest", method = RequestMethod.POST)
	public String deleteUserRequest(@RequestHeader(value="name") String name) {
		UserTO userTO = getUserService().readName(name);
		if(userTO != null) {
			getUserService().delete(userTO.getId());
			message = BaseSpringServiceLocator.getMessage("delete.success", new Object[]{BaseSpringServiceLocator.getMessage("table.user")});
		} else {
			message = BaseSpringServiceLocator.getMessage("exists.not", new Object[]{BaseSpringServiceLocator.getMessage("table.user")});
		}
		
		return message;
	}
	
	private UserService getUserService() {
		return BaseSpringServiceLocator.getBean(UserService.class);
	}
	
	private RoleService getRoleService() {
		return BaseSpringServiceLocator.getBean(RoleService.class);
	}
	
}