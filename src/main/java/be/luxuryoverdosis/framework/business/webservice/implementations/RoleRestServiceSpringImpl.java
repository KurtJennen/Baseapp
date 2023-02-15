package be.luxuryoverdosis.framework.business.webservice.implementations;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;

import be.luxuryoverdosis.framework.base.SearchQuery;
import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;
import be.luxuryoverdosis.framework.business.service.interfaces.RoleService;
import be.luxuryoverdosis.framework.business.thread.ThreadManager;
import be.luxuryoverdosis.framework.business.webservice.interfaces.RoleRestService;
import be.luxuryoverdosis.framework.data.dto.RoleDTO;
import be.luxuryoverdosis.framework.data.factory.RoleFactory;
import be.luxuryoverdosis.framework.data.restwrapperdto.RestWrapperDTO;
import be.luxuryoverdosis.framework.data.to.Role;
import be.luxuryoverdosis.framework.logging.Logging;
import be.luxuryoverdosis.framework.web.exception.ServiceException;

@Service
public class RoleRestServiceSpringImpl extends BaseRestService implements RoleRestService {
	@Resource
	private RoleService roleService;
	
	@Transactional(readOnly=true)
	public String readRequest(final int id) throws JsonProcessingException {
		Logging.info(this, "Begin readRequest");
		
		RestWrapperDTO<RoleDTO> restWrapperDTO = createRestWrapperDTO();
		
		if(ThreadManager.getUserFromThread() == null) {
			return checkUserOnThread(restWrapperDTO);
		}
		
		Role role = roleService.read(id);
		if (role == null) {
			String error = BaseSpringServiceLocator.getMessage("exists.not", new Object[]{BaseSpringServiceLocator.getMessage("table.role")});
			return restWrapperDTO.sendRestErrorWrapperDto(error);
		}
		
		restWrapperDTO.setDto(produceRoleDTO(role));
		
		Logging.info(this, "End readRequest");
		return restWrapperDTO.sendRestWrapperDto();
	}
	
	@Transactional(readOnly=true)
	public String readAllRequest() throws JsonProcessingException {
		Logging.info(this, "Begin readAllRequest");
		
		RestWrapperDTO<RoleDTO> restWrapperDTO = createRestWrapperDTO();
		
		if(ThreadManager.getUserFromThread() == null) {
			return checkUserOnThread(restWrapperDTO);
		}
		
		ArrayList<RoleDTO> roleDTOList = roleService.listDTO(SearchQuery.PROCENT);
		if (roleDTOList == null) {
			String error =  BaseSpringServiceLocator.getMessage("exists.not", new Object[]{BaseSpringServiceLocator.getMessage("table.role")});
			return restWrapperDTO.sendRestErrorWrapperDto(error);
		}
		
		restWrapperDTO.setDtoList(roleDTOList);
		
		Logging.info(this, "End readAllUserRequest");
		return restWrapperDTO.sendRestWrapperDto();
	}

	@Transactional(rollbackFor = UnexpectedRollbackException.class)
	public String createOrUpdateRequest(final RoleDTO roleDTO) throws JsonProcessingException {
		Logging.info(this, "Begin createOrUpdateRequest");
		
		RestWrapperDTO<RoleDTO> restWrapperDTO = createRestWrapperDTO();
		
		if(ThreadManager.getUserFromThread() == null) {
			return checkUserOnThread(restWrapperDTO);
		}
		
		boolean isNew = false;
		
		Role role = null;
		if(roleDTO.getId() > 0) {
			role = roleService.read(roleDTO.getId());
		} 
		if (role == null) {
			role = new Role();
			isNew = true;
		}
		
		role.setName(roleDTO.getName());
		
		try {
			role = roleService.createOrUpdate(role);
			restWrapperDTO.setDto(produceRoleDTO(role));
			if (isNew) {
				Logging.info(this, "End createOrUpdateRequest");
				String message = BaseSpringServiceLocator.getMessage("save.success", new Object[]{BaseSpringServiceLocator.getMessage("table.role")});
				return restWrapperDTO.sendRestMessageWrapperDto(message);
			} else {
				Logging.info(this, "End createOrUpdateRequest");
				String message = BaseSpringServiceLocator.getMessage("update.success", new Object[]{BaseSpringServiceLocator.getMessage("table.role")});
				return restWrapperDTO.sendRestMessageWrapperDto(message);
			}
		} catch (ServiceException e) {
			//return restWrapperDTO.sendRestErrorWrapperDto(e.getMessage());
			throw e;
		}
	}

	@Transactional
	public String deleteRequest(final int id) throws JsonProcessingException {
		Logging.info(this, "Begin deleterRequest");
		
		RestWrapperDTO<RoleDTO> restWrapperDTO = createRestWrapperDTO();
		
		if(ThreadManager.getUserFromThread() == null) {
			return checkUserOnThread(restWrapperDTO);
		}
		
		Role role = roleService.read(id);
		restWrapperDTO.setDto(produceRoleDTO(role));
		if(role != null) {
			roleService.delete(role.getId());
			Logging.info(this, "Begin deleteRequest");
			String message = BaseSpringServiceLocator.getMessage("delete.success", new Object[]{BaseSpringServiceLocator.getMessage("table.user")});
			return restWrapperDTO.sendRestMessageWrapperDto(message);
		} else {
			Logging.info(this, "Begin deleteRequest");
			String message = BaseSpringServiceLocator.getMessage("exists.not", new Object[]{BaseSpringServiceLocator.getMessage("table.user")});
			return restWrapperDTO.sendRestMessageWrapperDto(message);
		}
	}

	private RestWrapperDTO<RoleDTO> createRestWrapperDTO() {
		return new RestWrapperDTO<RoleDTO>();
	}
	
	private RoleDTO produceRoleDTO(Role role) {
		return RoleFactory.produceRoleDTO(role);
	}
}
