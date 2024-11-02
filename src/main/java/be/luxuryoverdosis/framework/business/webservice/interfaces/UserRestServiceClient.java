package be.luxuryoverdosis.framework.business.webservice.interfaces;

import be.luxuryoverdosis.framework.data.dto.UserDTO;
import be.luxuryoverdosis.framework.data.restwrapperdto.RestWrapperDTO;

public interface UserRestServiceClient {
	RestWrapperDTO<UserDTO> readUserRequest(String name, String encryptedPassword);
	RestWrapperDTO<UserDTO> readAllUsersRequest();
	RestWrapperDTO<UserDTO> createOrUpdateUserRequest(String name, String userName, String encryptedPassword, String email, String[] roleName);
	RestWrapperDTO<UserDTO> deleteUserRequest(int id);
}
