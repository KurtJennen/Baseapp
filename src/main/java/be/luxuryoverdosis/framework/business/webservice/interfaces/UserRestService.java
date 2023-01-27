package be.luxuryoverdosis.framework.business.webservice.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface UserRestService {
	public String readUserRequest(String name, String password) throws JsonProcessingException;
	public String readAllUsersRequest() throws JsonProcessingException;
	public String createOrUpdateUserRequest(String name, String userName, String encryptedPassword, String email, String[] roleNames) throws JsonProcessingException;
	public String deleteUserRequest(String name) throws JsonProcessingException;
}
