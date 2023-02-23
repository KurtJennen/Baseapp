package be.luxuryoverdosis.framework.business.webservice.implementations;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;

import be.luxuryoverdosis.framework.business.encryption.Encryption;
import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;
import be.luxuryoverdosis.framework.business.service.interfaces.UserService;
import be.luxuryoverdosis.framework.business.thread.ThreadManager;
import be.luxuryoverdosis.framework.business.webservice.interfaces.UserRestService;
import be.luxuryoverdosis.framework.data.dao.interfaces.RoleHibernateDAO;
import be.luxuryoverdosis.framework.data.dao.interfaces.UserRoleHibernateDAO;
import be.luxuryoverdosis.framework.data.dto.UserDTO;
import be.luxuryoverdosis.framework.data.dto.UserRoleDTO;
import be.luxuryoverdosis.framework.data.restwrapperdto.RestWrapperDTO;
import be.luxuryoverdosis.framework.logging.Logging;

@Service
public class UserRestServiceSpringImpl extends BaseRestService implements UserRestService  {
	@Resource
	private UserService userService;
	@Resource
	private RoleHibernateDAO roleHibernateDAO;
	@Resource
	private UserRoleHibernateDAO userRoleHibernateDAO;
	
	@Transactional(readOnly=true)
	public String readRequest(final int id) throws JsonProcessingException {
		Logging.info(this, "Begin readRequest");
		
		RestWrapperDTO<UserDTO> restWrapperDTO = createRestWrapperDTO();
		
		if(ThreadManager.getUserFromThread() == null) {
			return checkUserOnThread(restWrapperDTO);
		}
		
		UserDTO userDTO = userService.readDTO(id);		
		restWrapperDTO.setDto(userDTO);
		
		fillUserRoles(userDTO);
		
		Logging.info(this, "End readRequest");
		return restWrapperDTO.sendRestWrapperDto();
	}
	
	@Transactional(readOnly=true)
	public String readRequest(final String name, final String password) throws JsonProcessingException {
		Logging.info(this, "Begin readRequest");
		
		RestWrapperDTO<UserDTO> restWrapperDTO = createRestWrapperDTO();
		
		if(ThreadManager.getUserFromThread() == null) {
			return checkUserOnThread(restWrapperDTO);
		}
		
		UserDTO userDTO = userService.readNameDTO(name);
		if (userDTO != null && !userDTO.getPassword().equals(Encryption.decode(password))) {
			String error = BaseSpringServiceLocator.getMessage("exists.not", new Object[]{BaseSpringServiceLocator.getMessage("table.user")});
			return restWrapperDTO.sendRestErrorWrapperDto(error);
		}
		
		restWrapperDTO.setDto(userDTO);
		
		Logging.info(this, "End readRequest");
		return restWrapperDTO.sendRestWrapperDto();
	}

	@Transactional(readOnly=true)
	public String readAllRequest() throws JsonProcessingException {
		Logging.info(this, "Begin readAllRequest");
		
		RestWrapperDTO<UserDTO> restWrapperDTO = createRestWrapperDTO();
		
		if(ThreadManager.getUserFromThread() == null) {
			return checkUserOnThread(restWrapperDTO);
		}
		
		ArrayList<UserDTO> userDTOList = userService.listDTO();
		restWrapperDTO.setDtoList(userDTOList);
		
		Logging.info(this, "End readAllRequest");
		return restWrapperDTO.sendRestWrapperDto();
	}

	@Transactional
	public String createOrUpdateRequest(final UserDTO userDTO) throws JsonProcessingException {
		Logging.info(this, "Begin createOrUpdateRequest");
		
		RestWrapperDTO<UserDTO> restWrapperDTO = createRestWrapperDTO();
		
		if(ThreadManager.getUserFromThread() == null) {
			return checkUserOnThread(restWrapperDTO);
		}
		
		UserDTO savedUserDTO = userService.createOrUpdateDTO(userDTO, userDTO.getRoles().toArray(new String[0]));
		fillUserRoles(savedUserDTO);
		restWrapperDTO.setDto(savedUserDTO);
		
		Logging.info(this, "End createOrUpdateRequest");
		return restWrapperDTO.sendRestWrapperDto();
	}

	@Transactional
	public String deleteRequest(int id) throws JsonProcessingException {
		Logging.info(this, "Begin deleteRequest");
		
		RestWrapperDTO<UserDTO> restWrapperDTO = createRestWrapperDTO();
		
		if(ThreadManager.getUserFromThread() == null) {
			return checkUserOnThread(restWrapperDTO);
		}
		
		userService.delete(id);
		
		Logging.info(this, "End deleteRequest");
		return restWrapperDTO.sendRestWrapperDto();
	}

	private RestWrapperDTO<UserDTO> createRestWrapperDTO() {
		return new RestWrapperDTO<UserDTO>();
	}
	
	private UserDTO fillUserRoles(UserDTO userDTO) {
		userDTO.setRoles(new ArrayList<String>());
		
		ArrayList<UserRoleDTO> userRolesList = userRoleHibernateDAO.listDTO(userDTO.getId());
		for (UserRoleDTO userRoleDTO : userRolesList) {
			userDTO.getRoles().add(userRoleDTO.getRoleName());
		}
		
		return userDTO;
	}
}
