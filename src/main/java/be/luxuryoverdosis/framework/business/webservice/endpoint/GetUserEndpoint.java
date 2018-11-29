package be.luxuryoverdosis.framework.business.webservice.endpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;
import be.luxuryoverdosis.framework.business.webservice.interfaces.UserEndpointService;
import be.luxuryoverdosis.user.schema.v1.CreateOrUpdateUserRequest;
import be.luxuryoverdosis.user.schema.v1.CreateOrUpdateUserResponse;
import be.luxuryoverdosis.user.schema.v1.DeleteUserRequest;
import be.luxuryoverdosis.user.schema.v1.DeleteUserResponse;
import be.luxuryoverdosis.user.schema.v1.ReadAllUsersRequest;
import be.luxuryoverdosis.user.schema.v1.ReadAllUsersResponse;
import be.luxuryoverdosis.user.schema.v1.ReadUserRequest;
import be.luxuryoverdosis.user.schema.v1.ReadUserResponse;

@Endpoint
public class GetUserEndpoint {
	@PayloadRoot(localPart="ReadUserRequest", namespace="http://www.luxuryoverdosis.be/user/schema/v1")
	@ResponsePayload
	public ReadUserResponse readUserRequest(@RequestPayload ReadUserRequest request) throws Exception {		
		return getUserEndpointService().readUserRequest(request);
	}

	@PayloadRoot(localPart="ReadAllUsersRequest", namespace="http://www.luxuryoverdosis.be/user/schema/v1")
	@ResponsePayload
	public ReadAllUsersResponse readAllUsersResponse(@RequestPayload ReadAllUsersRequest request) {
		return getUserEndpointService().readAllUsersRequest(request);
	}
	
	@PayloadRoot(localPart="CreateOrUpdateUserRequest", namespace="http://www.luxuryoverdosis.be/user/schema/v1")
	@ResponsePayload
	public CreateOrUpdateUserResponse createOrUpdateUserRequest(@RequestPayload CreateOrUpdateUserRequest request) {
		return getUserEndpointService().createOrUpdateUserRequest(request);
	}
	
	@PayloadRoot(localPart="DeleteUserRequest", namespace="http://www.luxuryoverdosis.be/user/schema/v1")
	@ResponsePayload
	public DeleteUserResponse deleteUserRequest(@RequestPayload DeleteUserRequest request) {
		return getUserEndpointService().deleteUserRequest(request);
	}
	
	private UserEndpointService getUserEndpointService() {
		return BaseSpringServiceLocator.getBean(UserEndpointService.class);
	}
	
}
