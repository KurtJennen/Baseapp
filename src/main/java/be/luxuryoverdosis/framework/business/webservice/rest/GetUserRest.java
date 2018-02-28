package be.luxuryoverdosis.framework.business.webservice.rest;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;
import be.luxuryoverdosis.framework.business.service.interfaces.RoleService;
import be.luxuryoverdosis.framework.business.service.interfaces.UserService;
import be.luxuryoverdosis.framework.business.thread.ThreadManager;
import be.luxuryoverdosis.framework.data.dto.UserDTO;
import be.luxuryoverdosis.framework.data.to.Role;
import be.luxuryoverdosis.framework.data.to.User;
import be.luxuryoverdosis.framework.web.exception.ServiceException;
import net.sf.json.JSONArray;

@RestController
@RequestMapping("/user")
public class GetUserRest {
	@RequestMapping(value = "/readUserRequest", method = RequestMethod.GET)
	//public String readUserRequest(@RequestParam(value="name") String name) throws Exception {
	public String readUserRequest(@RequestHeader(value="name") String name) throws Exception {
		if(ThreadManager.getUserFromThread() == null) {
			return BaseSpringServiceLocator.getMessage("security.access.denied");
		}
		
		UserDTO userDTO = null;
		User user = getUserService().readName(name);
		if (user == null) {
			return  BaseSpringServiceLocator.getMessage("exists.not", new Object[]{BaseSpringServiceLocator.getMessage("table.user")});
		} 
		
		userDTO = getUserService().readDTO(user.getId());
		if (userDTO == null) {
			return BaseSpringServiceLocator.getMessage("exists.not", new Object[]{BaseSpringServiceLocator.getMessage("table.user")});
		}
		
		return JSONArray.fromObject(userDTO).toString();
	}
	
	@RequestMapping(value = "/readAllUsersRequest", method = RequestMethod.GET)
	public String readUserRequest() throws Exception {
		if(ThreadManager.getUserFromThread() == null) {
			return BaseSpringServiceLocator.getMessage("security.access.denied");
		}
		
		ArrayList<UserDTO> userDTOList = getUserService().listDTO();
		if (userDTOList == null) {
			return BaseSpringServiceLocator.getMessage("exists.not", new Object[]{BaseSpringServiceLocator.getMessage("table.user")});
		}
		
		return JSONArray.fromObject(userDTOList).toString();
	}

	@RequestMapping(value = "/createOrUpdateUserRequest", method = RequestMethod.POST)
	public String createOrUpdateUserRequest(@RequestHeader(value="name") String name,
			@RequestHeader(value="userName") String userName,
			@RequestHeader(value="encryptedPassword") String encryptedPassword,
			@RequestHeader(value="email") String email,
			@RequestHeader(value="roleName") String roleName) throws Exception {
		
		if(ThreadManager.getUserFromThread() == null) {
			return BaseSpringServiceLocator.getMessage("security.access.denied");
		}
		
		boolean isNew = false;
		
		User user = getUserService().readName(name);
		if (user == null) {
			user = new User();
			user.setId(-1);
			isNew = true;
		}
		
		user.setEmail(email);
		user.setEncryptedPassword(encryptedPassword);
		user.setName(name);
		user.setUserName(userName);
		
		Role role = getRoleService().readName(roleName);
		if (role == null) {
			return BaseSpringServiceLocator.getMessage("exists.not", new Object[]{BaseSpringServiceLocator.getMessage("table.role")});
		}
		user.setRole(role);
		
		try {
			getUserService().createOrUpdate(user);
			if (isNew) {
				return BaseSpringServiceLocator.getMessage("save.success", new Object[]{BaseSpringServiceLocator.getMessage("table.user")});
			} else {
				return BaseSpringServiceLocator.getMessage("update.success", new Object[]{BaseSpringServiceLocator.getMessage("table.user")});
			}
		} catch (ServiceException e) {
			return e.getMessage();
		}
	}
	
	@RequestMapping(value = "/deleteUserRequest", method = RequestMethod.POST)
	public String deleteUserRequest(@RequestHeader(value="name") String name) {
		if(ThreadManager.getUserFromThread() == null) {
			return BaseSpringServiceLocator.getMessage("security.access.denied");
		}
		
		User user = getUserService().readName(name);
		if(user != null) {
			getUserService().delete(user.getId());
			return BaseSpringServiceLocator.getMessage("delete.success", new Object[]{BaseSpringServiceLocator.getMessage("table.user")});
		} else {
			return BaseSpringServiceLocator.getMessage("exists.not", new Object[]{BaseSpringServiceLocator.getMessage("table.user")});
		}
	}
	
	private UserService getUserService() {
		return BaseSpringServiceLocator.getBean(UserService.class);
	}
	
	private RoleService getRoleService() {
		return BaseSpringServiceLocator.getBean(RoleService.class);
	}
	
}
