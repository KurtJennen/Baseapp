package be.luxuryoverdosis.framework.business.webservice.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;

import be.luxuryoverdosis.framework.data.dto.UserDTO;

public interface UserRestService {
	public String readUserRequest(String name, String password) throws JsonProcessingException;
	public String readUserRequest(int id) throws JsonProcessingException;
	public String readAllUsersRequest() throws JsonProcessingException;
	public String createOrUpdateUserRequest(UserDTO userDTO) throws JsonProcessingException;
	public String deleteUserRequest(String name) throws JsonProcessingException;
}
