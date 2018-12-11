package be.luxuryoverdosis.framework.business.webservice.interfaces;

import be.luxuryoverdosis.generated.user.schema.v1.CreateOrUpdateUserRequest;
import be.luxuryoverdosis.generated.user.schema.v1.CreateOrUpdateUserResponse;
import be.luxuryoverdosis.generated.user.schema.v1.DeleteUserRequest;
import be.luxuryoverdosis.generated.user.schema.v1.DeleteUserResponse;
import be.luxuryoverdosis.generated.user.schema.v1.ReadAllUsersRequest;
import be.luxuryoverdosis.generated.user.schema.v1.ReadAllUsersResponse;
import be.luxuryoverdosis.generated.user.schema.v1.ReadUserRequest;
import be.luxuryoverdosis.generated.user.schema.v1.ReadUserResponse;

public interface UserEndpointService {
	public ReadUserResponse readUserRequest(ReadUserRequest request);
	public ReadAllUsersResponse readAllUsersRequest(ReadAllUsersRequest request);
	public CreateOrUpdateUserResponse createOrUpdateUserRequest(CreateOrUpdateUserRequest request);
	public DeleteUserResponse deleteUserRequest(DeleteUserRequest request);
}
