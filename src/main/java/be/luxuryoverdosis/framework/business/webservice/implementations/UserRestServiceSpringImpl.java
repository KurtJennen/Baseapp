package be.luxuryoverdosis.framework.business.webservice.implementations;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import be.luxuryoverdosis.framework.business.encryption.Encryption;
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
	public String readUserRequest(final int id) throws JsonProcessingException {
		Logging.info(this, "Begin readUserRequest");
		
		UserRestWrapperDTO userRestWrapperDTO = createUserRestWrapperDTO();
		
		if(ThreadManager.getUserFromThread() == null) {
			String error = BaseSpringServiceLocator.getMessage("security.access.denied");
			return sendRestErrorWrapperDto(userRestWrapperDTO, error);
		}
		
		User user = userService.read(id);
		if (user == null) {
			String error = BaseSpringServiceLocator.getMessage("exists.not", new Object[]{BaseSpringServiceLocator.getMessage("table.user")});
			return sendRestErrorWrapperDto(userRestWrapperDTO, error);
		}
		
		userRestWrapperDTO.setUserDTO(produceUserDTO(user));
		
		Logging.info(this, "End readUserRequest");
		return sendRestWrapperDto(userRestWrapperDTO);
	}
	
	@Transactional(readOnly=true)
	public String readUserRequest(final String name, final String password) throws JsonProcessingException {
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
	public String createOrUpdateUserRequest(final UserDTO userDTO) throws JsonProcessingException {
		Logging.info(this, "Begin createOrUpdateUserRequest");
		
		UserRestWrapperDTO userRestWrapperDTO = createUserRestWrapperDTO();
		
		if(ThreadManager.getUserFromThread() == null) {
			String error = BaseSpringServiceLocator.getMessage("security.access.denied");
			return sendRestErrorWrapperDto(userRestWrapperDTO, error);
		}
		
		boolean isNew = false;
		
		User user = null;
		if(userDTO.getId() > 0) {
			user = userService.read(userDTO.getId());
		} 
		if(user == null) {
			user = userService.readName(userDTO.getName());
		}
		if (user == null) {
			user = new User();
			isNew = true;
		}
		
		user.setEmail(userDTO.getEmail());
		user.setEncryptedPassword(Encryption.encode(userDTO.getPassword()));
		user.setName(userDTO.getName());
		user.setUserName(userDTO.getUserName());
		user.setDateExpiration(userDTO.getDateExpiration());
		
		try {
			user = userService.createOrUpdate(user, userDTO.getRoles().toArray(new String[0]));
			userRestWrapperDTO.setUserDTO(produceUserDTO(user));
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
	public String deleteUserRequest(int id) throws JsonProcessingException {
		Logging.info(this, "Begin deleteUserRequest");
		
		UserRestWrapperDTO userRestWrapperDTO = createUserRestWrapperDTO();
		
		if(ThreadManager.getUserFromThread() == null) {
			String error = BaseSpringServiceLocator.getMessage("security.access.denied");
			return sendRestErrorWrapperDto(userRestWrapperDTO, error);
		}
		
		User user = userService.read(id);
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
