package be.luxuryoverdosis.framework.business.webservice.implementations;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;

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
import be.luxuryoverdosis.framework.data.restwrapperdto.RestWrapperDTO;
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
		
		RestWrapperDTO<UserDTO> userRestWrapperDTO = createUserRestWrapperDTO();
		
		if(ThreadManager.getUserFromThread() == null) {
			String error = BaseSpringServiceLocator.getMessage("security.access.denied");
			return userRestWrapperDTO.sendRestErrorWrapperDto(error);
		}
		
		User user = userService.read(id);
		if (user == null) {
			String error = BaseSpringServiceLocator.getMessage("exists.not", new Object[]{BaseSpringServiceLocator.getMessage("table.user")});
			return userRestWrapperDTO.sendRestErrorWrapperDto(error);
		}
		
		userRestWrapperDTO.setDto(produceUserDTO(user));
		
		Logging.info(this, "End readUserRequest");
		return userRestWrapperDTO.sendRestWrapperDto();
	}
	
	@Transactional(readOnly=true)
	public String readUserRequest(final String name, final String password) throws JsonProcessingException {
		Logging.info(this, "Begin readUserRequest");
		
		RestWrapperDTO<UserDTO> userRestWrapperDTO = createUserRestWrapperDTO();
		
		if(ThreadManager.getUserFromThread() == null) {
			String error = BaseSpringServiceLocator.getMessage("security.access.denied");
			return userRestWrapperDTO.sendRestErrorWrapperDto( error);
		}
		
		User user = userService.readName(name);
		if (user == null || !user.getEncryptedPassword().equals(password)) {
			String error = BaseSpringServiceLocator.getMessage("exists.not", new Object[]{BaseSpringServiceLocator.getMessage("table.user")});
			return userRestWrapperDTO.sendRestErrorWrapperDto(error);
		}
		
		userRestWrapperDTO.setDto(produceUserDTO(user));
		
		Logging.info(this, "End readUserRequest");
		return userRestWrapperDTO.sendRestWrapperDto();
	}

	@Transactional(readOnly=true)
	public String readAllUsersRequest() throws JsonProcessingException {
		Logging.info(this, "Begin readAllUsersRequest");
		
		RestWrapperDTO<UserDTO> userRestWrapperDTO = createUserRestWrapperDTO();
		
		if(ThreadManager.getUserFromThread() == null) {
			String error = BaseSpringServiceLocator.getMessage("security.access.denied");
			return userRestWrapperDTO.sendRestErrorWrapperDto(error);
		}
		
		ArrayList<UserDTO> userDTOList = userService.listDTO();
		if (userDTOList == null) {
			String error =  BaseSpringServiceLocator.getMessage("exists.not", new Object[]{BaseSpringServiceLocator.getMessage("table.user")});
			return userRestWrapperDTO.sendRestErrorWrapperDto(error);
		}
		for (UserDTO userDTO : userDTOList) {
			fillUserRoles(userDTO);
		}
		
		userRestWrapperDTO.setDtoList(userDTOList);
		
		Logging.info(this, "End readAllUsersRequest");
		return userRestWrapperDTO.sendRestWrapperDto();
	}

	@Transactional
	public String createOrUpdateUserRequest(final UserDTO userDTO) throws JsonProcessingException {
		Logging.info(this, "Begin createOrUpdateUserRequest");
		
		RestWrapperDTO<UserDTO> userRestWrapperDTO = createUserRestWrapperDTO();
		
		if(ThreadManager.getUserFromThread() == null) {
			String error = BaseSpringServiceLocator.getMessage("security.access.denied");
			return userRestWrapperDTO.sendRestErrorWrapperDto(error);
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
			userRestWrapperDTO.setDto(produceUserDTO(user));
			if (isNew) {
				Logging.info(this, "End createOrUpdateUserRequest");
				String message = BaseSpringServiceLocator.getMessage("save.success", new Object[]{BaseSpringServiceLocator.getMessage("table.user")});
				return userRestWrapperDTO.sendRestMessageWrapperDto(message);
			} else {
				Logging.info(this, "End createOrUpdateUserRequest");
				String message = BaseSpringServiceLocator.getMessage("update.success", new Object[]{BaseSpringServiceLocator.getMessage("table.user")});
				return userRestWrapperDTO.sendRestMessageWrapperDto(message);
			}
		} catch (ServiceException e) {
			return userRestWrapperDTO.sendRestErrorWrapperDto(e.getMessage());
		}
	}

	@Transactional
	public String deleteUserRequest(int id) throws JsonProcessingException {
		Logging.info(this, "Begin deleteUserRequest");
		
		RestWrapperDTO<UserDTO> userRestWrapperDTO = createUserRestWrapperDTO();
		
		if(ThreadManager.getUserFromThread() == null) {
			String error = BaseSpringServiceLocator.getMessage("security.access.denied");
			return userRestWrapperDTO.sendRestErrorWrapperDto( error);
		}
		
		User user = userService.read(id);
		userRestWrapperDTO.setDto(produceUserDTO(user));
		if(user != null) {
			userService.delete(user.getId());
			Logging.info(this, "Begin deleteUserRequest");
			String message = BaseSpringServiceLocator.getMessage("delete.success", new Object[]{BaseSpringServiceLocator.getMessage("table.user")});
			return userRestWrapperDTO.sendRestMessageWrapperDto(message);
		} else {
			Logging.info(this, "Begin deleteUserRequest");
			String message = BaseSpringServiceLocator.getMessage("exists.not", new Object[]{BaseSpringServiceLocator.getMessage("table.user")});
			return userRestWrapperDTO.sendRestMessageWrapperDto(message);
		}
	}

	private RestWrapperDTO<UserDTO> createUserRestWrapperDTO() {
		return new RestWrapperDTO<UserDTO>();
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
