package be.luxuryoverdosis.framework.business.webservice.interfaces;

import be.luxuryoverdosis.generated.user.schema.v1.CreateOrUpdateUserResponse;
import be.luxuryoverdosis.generated.user.schema.v1.DeleteUserResponse;
import be.luxuryoverdosis.generated.user.schema.v1.ReadAllUsersResponse;
import be.luxuryoverdosis.generated.user.schema.v1.ReadUserResponse;

public interface UserEndpointServiceClient {
	ReadUserResponse readUserRequest(String name);
	ReadAllUsersResponse readAllUsersRequest();
	CreateOrUpdateUserResponse createOrUpdateUserRequest(String name, String userName, String encryptedPassword, String email, String roleName);
	DeleteUserResponse deleteUserRequest(String name);
}
