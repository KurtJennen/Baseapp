package be.luxuryoverdosis.framework.business.webservice.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;

import be.luxuryoverdosis.framework.data.dto.UserDTO;

public interface UserRestService {
	String readRequest(String name, String password) throws JsonProcessingException;
	String readRequest(int id) throws JsonProcessingException;
	String readAllRequest() throws JsonProcessingException;
	String createOrUpdateRequest(UserDTO userDTO) throws JsonProcessingException;
	String deleteRequest(int id) throws JsonProcessingException;
}
