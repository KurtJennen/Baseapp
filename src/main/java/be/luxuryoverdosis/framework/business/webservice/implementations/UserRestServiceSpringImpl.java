package be.luxuryoverdosis.framework.business.webservice.implementations;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;
import be.luxuryoverdosis.framework.business.service.interfaces.RoleService;
import be.luxuryoverdosis.framework.business.service.interfaces.UserService;
import be.luxuryoverdosis.framework.business.thread.ThreadManager;
import be.luxuryoverdosis.framework.business.webservice.interfaces.UserRestService;
import be.luxuryoverdosis.framework.data.dto.UserDTO;
import be.luxuryoverdosis.framework.data.factory.UserFactory;
import be.luxuryoverdosis.framework.data.restwrapperdto.UserRestWrapperDTO;
import be.luxuryoverdosis.framework.data.to.Role;
import be.luxuryoverdosis.framework.data.to.User;
import be.luxuryoverdosis.framework.logging.Logging;
import be.luxuryoverdosis.framework.web.exception.ServiceException;

@Service
public class UserRestServiceSpringImpl implements UserRestService {
	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;
	
	@Transactional(readOnly=true)
	public String readUserRequest(String name) throws JsonProcessingException {
		Logging.info(this, "Begin readUserRequest");
		
		UserRestWrapperDTO userRestWrapperDTO = createUserRestWrapperDTO();
		
		if(ThreadManager.getUserFromThread() == null) {
			String error = BaseSpringServiceLocator.getMessage("security.access.denied");
			return sendRestErrorWrapperDto(userRestWrapperDTO, error);
		}
		
		
		User user = userService.readName(name);
		if (user == null) {
			String error = BaseSpringServiceLocator.getMessage("exists.not", new Object[]{BaseSpringServiceLocator.getMessage("table.user")});
			return sendRestErrorWrapperDto(userRestWrapperDTO, error);
		} 
		
		userRestWrapperDTO.setUserDTO(UserFactory.produceUserDTO(user));
		
		Logging.info(this, "End readUserRequest");
		return sendRestWrapperDto(userRestWrapperDTO);
	}

	@Transactional(readOnly=true)
	public String readAllUsersRequest() throws JsonProcessingException {
		Logging.info(this, "Begin readAllUsersRequest");
		
		UserRestWrapperDTO userRestWrapperDTO = createUserRestWrapperDTO();
		
		if(ThreadManager.getUserFromThread() == null) {
			String error = BaseSpringServiceLocator.getMessage("security.access.denied");
			return sendRestErrorWrapperDto(userRestWrapperDTO, error);
		}
		
		ArrayList<UserDTO> userDTOList = userService.listDTO();
		if (userDTOList == null) {
			String error =  BaseSpringServiceLocator.getMessage("exists.not", new Object[]{BaseSpringServiceLocator.getMessage("table.user")});
			return sendRestErrorWrapperDto(userRestWrapperDTO, error);
		}
		
		userRestWrapperDTO.setUserDTOList(userDTOList);
		
		Logging.info(this, "End readAllUsersRequest");
		return sendRestWrapperDto(userRestWrapperDTO);
	}

	@Transactional
	public String createOrUpdateUserRequest(String name, String userName, String encryptedPassword, String email, String roleName) throws JsonProcessingException {
		Logging.info(this, "Begin createOrUpdateUserRequest");
		
		UserRestWrapperDTO userRestWrapperDTO = createUserRestWrapperDTO();
		
		if(ThreadManager.getUserFromThread() == null) {
			String error = BaseSpringServiceLocator.getMessage("security.access.denied");
			return sendRestErrorWrapperDto(userRestWrapperDTO, error);
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
			String error = BaseSpringServiceLocator.getMessage("exists.not", new Object[]{BaseSpringServiceLocator.getMessage("table.role")});
			return sendRestErrorWrapperDto(userRestWrapperDTO, error);
		}
		user.setRole(role);
		
		try {
			user = userService.createOrUpdate(user);
			userRestWrapperDTO.setUserDTO(UserFactory.produceUserDTO(user));
			if (isNew) {
				Logging.info(this, "End createOrUpdateUserRequest");
				String message = BaseSpringServiceLocator.getMessage("save.success", new Object[]{BaseSpringServiceLocator.getMessage("table.user")});
				return sendRestMessageWrapperDto(userRestWrapperDTO, message);
			} else {
				Logging.info(this, "End createOrUpdateUserRequest");
				String message = BaseSpringServiceLocator.getMessage("update.success", new Object[]{BaseSpringServiceLocator.getMessage("table.user")});
				return sendRestMessageWrapperDto(userRestWrapperDTO, message);
			}
		} catch (ServiceException e) {
			return sendRestErrorWrapperDto(userRestWrapperDTO, e.getMessage());
		}
	}

	@Transactional
	public String deleteUserRequest(String name) throws JsonProcessingException {
		Logging.info(this, "Begin deleteUserRequest");
		
		UserRestWrapperDTO userRestWrapperDTO = createUserRestWrapperDTO();
		
		if(ThreadManager.getUserFromThread() == null) {
			String error = BaseSpringServiceLocator.getMessage("security.access.denied");
			return sendRestErrorWrapperDto(userRestWrapperDTO, error);
		}
		
		User user = userService.readName(name);
		userRestWrapperDTO.setUserDTO(UserFactory.produceUserDTO(user));
		if(user != null) {
			userService.delete(user.getId());
			Logging.info(this, "Begin deleteUserRequest");
			String message = BaseSpringServiceLocator.getMessage("delete.success", new Object[]{BaseSpringServiceLocator.getMessage("table.user")});
			return sendRestMessageWrapperDto(userRestWrapperDTO, message);
		} else {
			Logging.info(this, "Begin deleteUserRequest");
			String message = BaseSpringServiceLocator.getMessage("exists.not", new Object[]{BaseSpringServiceLocator.getMessage("table.user")});
			return sendRestMessageWrapperDto(userRestWrapperDTO, message);
		}
	}

	private UserRestWrapperDTO createUserRestWrapperDTO() {
		UserRestWrapperDTO userRestWrapperDTO = new UserRestWrapperDTO();
		return userRestWrapperDTO;
	}
	
	private String sendRestMessageWrapperDto(UserRestWrapperDTO userRestWrapperDTO, String message) throws JsonProcessingException {
		userRestWrapperDTO.getMessages().add(message);
		return sendRestWrapperDto(userRestWrapperDTO);
	}
	
	private String sendRestErrorWrapperDto(UserRestWrapperDTO userRestWrapperDTO, String error) throws JsonProcessingException {
		userRestWrapperDTO.getErrors().add(error);
		return sendRestWrapperDto(userRestWrapperDTO);
	}
	
	private String sendRestWrapperDto(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
}
