package be.luxuryoverdosis.framework.business.webservice.interfaces;

public interface UserRestService {
	public String readUserRequest(String name);
	public String readAllUsersRequest();
	public String createOrUpdateUserRequest(String name, String userName, String encryptedPassword, String email, String roleName);
	public String deleteUserRequest(String name);
}
