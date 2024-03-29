package be.luxuryoverdosis.framework.business.webservice.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;

import be.luxuryoverdosis.framework.data.dto.UserDTO;

public interface UserRestService {
	public String readRequest(String name, String password) throws JsonProcessingException;
	public String readRequest(int id) throws JsonProcessingException;
	public String readAllRequest() throws JsonProcessingException;
	public String createOrUpdateRequest(UserDTO userDTO) throws JsonProcessingException;
	public String deleteRequest(int id) throws JsonProcessingException;
}
