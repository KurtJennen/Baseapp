package be.luxuryoverdosis.framework.business.webservice.implementations;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;
import be.luxuryoverdosis.framework.business.service.interfaces.RoleService;
import be.luxuryoverdosis.framework.business.service.interfaces.UserService;
import be.luxuryoverdosis.framework.business.thread.ThreadManager;
import be.luxuryoverdosis.framework.business.webservice.interfaces.UserRestService;
import be.luxuryoverdosis.framework.data.dto.UserDTO;
import be.luxuryoverdosis.framework.data.to.Role;
import be.luxuryoverdosis.framework.data.to.User;
import be.luxuryoverdosis.framework.logging.Logging;
import be.luxuryoverdosis.framework.web.exception.ServiceException;
import net.sf.json.JSONArray;

@Service
public class UserRestServiceSpringImpl implements UserRestService {
	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;
	
	@Transactional(readOnly=true)
	public String readUserRequest(String name) {
		Logging.info(this, "Begin readUserRequest");
		
		if(ThreadManager.getUserFromThread() == null) {
			return BaseSpringServiceLocator.getMessage("security.access.denied");
		}
		
		UserDTO userDTO = null;
		User user = userService.readName(name);
		if (user == null) {
			return  BaseSpringServiceLocator.getMessage("exists.not", new Object[]{BaseSpringServiceLocator.getMessage("table.user")});
		} 
		
		userDTO = userService.readDTO(user.getId());
		if (userDTO == null) {
			return BaseSpringServiceLocator.getMessage("exists.not", new Object[]{BaseSpringServiceLocator.getMessage("table.user")});
		}
		
		Logging.info(this, "End readUserRequest");
		return JSONArray.fromObject(userDTO).toString();
	}
	
	@Transactional(readOnly=true)
	public String readAllUsersRequest() {
		Logging.info(this, "Begin readAllUsersRequest");
		
		if(ThreadManager.getUserFromThread() == null) {
			return BaseSpringServiceLocator.getMessage("security.access.denied");
		}
		
		ArrayList<UserDTO> userDTOList = userService.listDTO();
		if (userDTOList == null) {
			return BaseSpringServiceLocator.getMessage("exists.not", new Object[]{BaseSpringServiceLocator.getMessage("table.user")});
		}
		
		Logging.info(this, "End readAllUsersRequest");
		return JSONArray.fromObject(userDTOList).toString();
	}

	@Transactional
	public String createOrUpdateUserRequest(String name, String userName, String encryptedPassword, String email, String roleName) {
		Logging.info(this, "Begin createOrUpdateUserRequest");
		
		if(ThreadManager.getUserFromThread() == null) {
			return BaseSpringServiceLocator.getMessage("security.access.denied");
		}
		
		boolean isNew = false;
		
		User user = userService.readName(name);
		if (user == null) {
			user = new User();
			user.setId(-1);
			isNew = true;
		}
		
		user.setEmail(email);
		user.setEncryptedPassword(encryptedPassword);
		user.setName(name);
		user.setUserName(userName);
		
		Role role = roleService.readName(roleName);
		if (role == null) {
			return BaseSpringServiceLocator.getMessage("exists.not", new Object[]{BaseSpringServiceLocator.getMessage("table.role")});
		}
		user.setRole(role);
		
		try {
			userService.createOrUpdate(user);
			if (isNew) {
				Logging.info(this, "End createOrUpdateUserRequest");
				return BaseSpringServiceLocator.getMessage("save.success", new Object[]{BaseSpringServiceLocator.getMessage("table.user")});
			} else {
				Logging.info(this, "End createOrUpdateUserRequest");
				return BaseSpringServiceLocator.getMessage("update.success", new Object[]{BaseSpringServiceLocator.getMessage("table.user")});
			}
		} catch (ServiceException e) {
			return e.getMessage();
		}
	}

	@Transactional
	public String deleteUserRequest(String name) {
		Logging.info(this, "Begin deleteUserRequest");
		
		if(ThreadManager.getUserFromThread() == null) {
			return BaseSpringServiceLocator.getMessage("security.access.denied");
		}
		
		User user = userService.readName(name);
		if(user != null) {
			userService.delete(user.getId());
			Logging.info(this, "Begin deleteUserRequest");
			return BaseSpringServiceLocator.getMessage("delete.success", new Object[]{BaseSpringServiceLocator.getMessage("table.user")});
		} else {
			Logging.info(this, "Begin deleteUserRequest");
			return BaseSpringServiceLocator.getMessage("exists.not", new Object[]{BaseSpringServiceLocator.getMessage("table.user")});
		}
	}
}
