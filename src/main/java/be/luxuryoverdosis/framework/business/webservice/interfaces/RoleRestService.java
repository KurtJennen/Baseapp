package be.luxuryoverdosis.framework.business.webservice.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;

import be.luxuryoverdosis.framework.data.dto.RoleDTO;

public interface RoleRestService {
	String readRequest(int id) throws JsonProcessingException;
	String readAllRequest() throws JsonProcessingException;
	String createOrUpdateRequest(RoleDTO roleDTO) throws JsonProcessingException;
	String deleteRequest(int id) throws JsonProcessingException;
}
