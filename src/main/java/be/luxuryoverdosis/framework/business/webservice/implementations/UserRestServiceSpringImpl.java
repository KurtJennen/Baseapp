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
import be.luxuryoverdosis.framework.data.dao.interfaces.UserRoleHibernateDAO;
import be.luxuryoverdosis.framework.data.dto.UserDTO;
import be.luxuryoverdosis.framework.data.dto.UserRoleDTO;
import be.luxuryoverdosis.framework.data.factory.UserFactory;
import be.luxuryoverdosis.framework.data.restwrapperdto.UserRestWrapperDTO;
import be.luxuryoverdosis.framework.data.to.User;
import be.luxuryoverdosis.framework.logging.Logging;
import be.luxuryoverdosis.framework.web.exception.ServiceException;

@Service
public class UserRestServiceSpringImpl implements UserRestService {
	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;
	@Resource
	private UserRoleHibernateDAO userRoleHibernateDAO;
	
	@Transactional(readOnly=true)
	public String readUserRequest(String name, String password) throws JsonProcessingException {
		Logging.info(this, "Begin readUserRequest");
		
		UserRestWrapperDTO userRestWrapperDTO = createUserRestWrapperDTO();
		
		if(ThreadManager.getUserFromThread() == null) {
			String error = BaseSpringServiceLocator.getMessage("security.access.denied");
			return sendRestErrorWrapperDto(userRestWrapperDTO, error);
		}
		
		User user = userService.readName(name);
		if (user == null || !user.getEncryptedPassword().equals(password)) {
			String error = BaseSpringServiceLocator.getMessage("exists.not", new Object[]{BaseSpringServiceLocator.getMessage("table.user")});
			return sendRestErrorWrapperDto(userRestWrapperDTO, error);
		}
		
		userRestWrapperDTO.setUserDTO(produceUserDTO(user));
		
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
		for (UserDTO userDTO : userDTOList) {
			fillUserRoles(userDTO);
		}
		
		userRestWrapperDTO.setUserDTOList(userDTOList);
		
		Logging.info(this, "End readAllUsersRequest");
		return sendRestWrapperDto(userRestWrapperDTO);
	}

	@Transactional
	public String createOrUpdateUserRequest(String name, String userName, String encryptedPassword, String email, String[] roleNames) throws JsonProcessingException {
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
		
		try {
			user = userService.createOrUpdate(user, roleNames);
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
		userRestWrapperDTO.setUserDTO(produceUserDTO(user));
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
	
	private UserDTO produceUserDTO(User user) {
		UserDTO userDTO = UserFactory.produceUserDTO(user);
		
		return fillUserRoles(userDTO);
	}
	private UserDTO fillUserRoles(UserDTO userDTO) {
		ArrayList<UserRoleDTO> userRolesList = userRoleHibernateDAO.listDTO(userDTO.getId());
		for (UserRoleDTO userRoleDTO : userRolesList) {
			userDTO.getRoles().add(userRoleDTO.getRoleName());
		}
		
		return userDTO;
	}
}
