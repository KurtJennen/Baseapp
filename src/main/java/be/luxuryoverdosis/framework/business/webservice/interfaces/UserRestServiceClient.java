package be.luxuryoverdosis.framework.business.webservice.interfaces;

import be.luxuryoverdosis.framework.data.restwrapperdto.UserRestWrapperDTO;

public interface UserRestServiceClient {
	public UserRestWrapperDTO readUserRequest(String name);
	public UserRestWrapperDTO readAllUsersRequest();
	public UserRestWrapperDTO createOrUpdateUserRequest(String name, String userName, String encryptedPassword, String email, String roleNames);
	public UserRestWrapperDTO deleteUserRequest(String name);
}
