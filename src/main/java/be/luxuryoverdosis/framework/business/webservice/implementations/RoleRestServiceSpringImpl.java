package be.luxuryoverdosis.framework.business.webservice.implementations;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;

import be.luxuryoverdosis.framework.base.SearchQuery;
import be.luxuryoverdosis.framework.business.service.interfaces.RoleService;
import be.luxuryoverdosis.framework.business.thread.ThreadManager;
import be.luxuryoverdosis.framework.business.webservice.interfaces.RoleRestService;
import be.luxuryoverdosis.framework.data.dto.RoleDTO;
import be.luxuryoverdosis.framework.data.restwrapperdto.RestWrapperDTO;
import be.luxuryoverdosis.framework.logging.Logging;

@Service
public class RoleRestServiceSpringImpl extends BaseRestService implements RoleRestService {
	@Resource
	private RoleService roleService;
	
	@Transactional(readOnly = true)
	public String readRequest(final int id) throws JsonProcessingException {
		Logging.info(this, "Begin readRequest");
		
		RestWrapperDTO<RoleDTO> restWrapperDTO = createRestWrapperDTO();
		
		if (ThreadManager.getUserFromThread() == null) {
			return checkUserOnThread(restWrapperDTO);
		}
		
		RoleDTO roleDTO = roleService.readDTO(id);
		restWrapperDTO.setDto(roleDTO);
		
		Logging.info(this, "End readRequest");
		return restWrapperDTO.sendRestWrapperDto();
	}
	
	@Transactional(readOnly = true)
	public String readAllRequest() throws JsonProcessingException {
		Logging.info(this, "Begin readAllRequest");
		
		RestWrapperDTO<RoleDTO> restWrapperDTO = createRestWrapperDTO();
		
		if (ThreadManager.getUserFromThread() == null) {
			return checkUserOnThread(restWrapperDTO);
		}
		
		ArrayList<RoleDTO> roleDTOList = roleService.listDTO(SearchQuery.PROCENT);
		restWrapperDTO.setDtoList(roleDTOList);
		
		Logging.info(this, "End readAllUserRequest");
		return restWrapperDTO.sendRestWrapperDto();
	}

	@Transactional
	public String createOrUpdateRequest(final RoleDTO roleDTO) throws JsonProcessingException {
		Logging.info(this, "Begin createOrUpdateRequest");
		
		RestWrapperDTO<RoleDTO> restWrapperDTO = createRestWrapperDTO();
		
		if (ThreadManager.getUserFromThread() == null) {
			return checkUserOnThread(restWrapperDTO);
		}
		
		RoleDTO savedRoleDTO =  roleService.createOrUpdateDTO(roleDTO);
		restWrapperDTO.setDto(savedRoleDTO);
		
		Logging.info(this, "End createOrUpdateRequest");
		return restWrapperDTO.sendRestWrapperDto();
	}

	@Transactional
	public String deleteRequest(final int id) throws JsonProcessingException {
		Logging.info(this, "Begin deleteRequest");
		
		RestWrapperDTO<RoleDTO> restWrapperDTO = createRestWrapperDTO();
		
		if (ThreadManager.getUserFromThread() == null) {
			return checkUserOnThread(restWrapperDTO);
		}
		
		roleService.delete(id);
		
		Logging.info(this, "End deleteRequest");
		return restWrapperDTO.sendRestWrapperDto();
	}

	private RestWrapperDTO<RoleDTO> createRestWrapperDTO() {
		return new RestWrapperDTO<RoleDTO>();
	}
	
}
