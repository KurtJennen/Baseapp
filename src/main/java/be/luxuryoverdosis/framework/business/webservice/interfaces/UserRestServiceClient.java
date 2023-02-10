package be.luxuryoverdosis.framework.business.webservice.interfaces;

import be.luxuryoverdosis.framework.data.dto.UserDTO;
import be.luxuryoverdosis.framework.data.restwrapperdto.RestWrapperDTO;

public interface UserRestServiceClient {
	public RestWrapperDTO<UserDTO> readUserRequest(String name);
	public RestWrapperDTO<UserDTO> readAllUsersRequest();
	public RestWrapperDTO<UserDTO> createOrUpdateUserRequest(String name, String userName, String encryptedPassword, String email, String roleNames);
	public RestWrapperDTO<UserDTO> deleteUserRequest(int id);
}
