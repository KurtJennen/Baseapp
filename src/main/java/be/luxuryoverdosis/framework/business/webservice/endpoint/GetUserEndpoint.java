package be.luxuryoverdosis.framework.business.webservice.endpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;
import be.luxuryoverdosis.framework.business.webservice.interfaces.UserEndpointService;
import be.luxuryoverdosis.generated.user.schema.v1.CreateOrUpdateUserRequest;
import be.luxuryoverdosis.generated.user.schema.v1.CreateOrUpdateUserResponse;
import be.luxuryoverdosis.generated.user.schema.v1.DeleteUserRequest;
import be.luxuryoverdosis.generated.user.schema.v1.DeleteUserResponse;
import be.luxuryoverdosis.generated.user.schema.v1.ReadAllUsersRequest;
import be.luxuryoverdosis.generated.user.schema.v1.ReadAllUsersResponse;
import be.luxuryoverdosis.generated.user.schema.v1.ReadUserRequest;
import be.luxuryoverdosis.generated.user.schema.v1.ReadUserResponse;

@Endpoint
public class GetUserEndpoint {
	@PayloadRoot(localPart = "ReadUserRequest", namespace = "http://www.luxuryoverdosis.be/user/schema/v1")
	@ResponsePayload
	public ReadUserResponse readUserRequest(@RequestPayload final ReadUserRequest request) throws Exception {		
		return getUserEndpointService().readUserRequest(request);
	}

	@PayloadRoot(localPart = "ReadAllUsersRequest", namespace = "http://www.luxuryoverdosis.be/user/schema/v1")
	@ResponsePayload
	public ReadAllUsersResponse readAllUsersResponse(@RequestPayload final ReadAllUsersRequest request) {
		return getUserEndpointService().readAllUsersRequest(request);
	}
	
	@PayloadRoot(localPart = "CreateOrUpdateUserRequest", namespace = "http://www.luxuryoverdosis.be/user/schema/v1")
	@ResponsePayload
	public CreateOrUpdateUserResponse createOrUpdateUserRequest(@RequestPayload final CreateOrUpdateUserRequest request) {
		return getUserEndpointService().createOrUpdateUserRequest(request);
	}
	
	@PayloadRoot(localPart = "DeleteUserRequest", namespace = "http://www.luxuryoverdosis.be/user/schema/v1")
	@ResponsePayload
	public DeleteUserResponse deleteUserRequest(@RequestPayload final DeleteUserRequest request) {
		return getUserEndpointService().deleteUserRequest(request);
	}
	
	private UserEndpointService getUserEndpointService() {
		return BaseSpringServiceLocator.getBean(UserEndpointService.class);
	}
	
}
