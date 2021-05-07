package be.luxuryoverdosis.framework.business.webservice.implementations;


import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import be.luxuryoverdosis.framework.business.webservice.interfaces.UserEndpointServiceClient;
import be.luxuryoverdosis.generated.user.schema.v1.CreateOrUpdateUserRequest;
import be.luxuryoverdosis.generated.user.schema.v1.CreateOrUpdateUserResponse;
import be.luxuryoverdosis.generated.user.schema.v1.DeleteUserRequest;
import be.luxuryoverdosis.generated.user.schema.v1.DeleteUserResponse;
import be.luxuryoverdosis.generated.user.schema.v1.ReadAllUsersRequest;
import be.luxuryoverdosis.generated.user.schema.v1.ReadAllUsersResponse;
import be.luxuryoverdosis.generated.user.schema.v1.ReadUserRequest;
import be.luxuryoverdosis.generated.user.schema.v1.ReadUserResponse;
import be.luxuryoverdosis.generated.user.schema.v1.Role;
import be.luxuryoverdosis.generated.user.schema.v1.User;

@Service
public class UserEndpointServiceClientImpl implements UserEndpointServiceClient {
	@Resource(name="webServiceTemplate")
	private WebServiceTemplate webServiceTemplate;
	@Resource
	private UserWebServiceMessageSenderAuthorization userWebServiceMessageSenderAuthorization;
	@Value("${ws.user.defaultUri}")
	private String defaultUri;
	
	public ReadUserResponse readUserRequest(final String name) {
		
		ReadUserRequest readUserRequest = new ReadUserRequest();
		readUserRequest.setName(name);
		
		webServiceTemplate.setDefaultUri(defaultUri);
		webServiceTemplate.setMessageSender(userWebServiceMessageSenderAuthorization);
		ReadUserResponse readUserResponse = (ReadUserResponse) webServiceTemplate.marshalSendAndReceive(readUserRequest);
		
		return readUserResponse;
	}
	
	public ReadAllUsersResponse readAllUsersRequest() {
		ReadAllUsersRequest readAllUsersRequest = new ReadAllUsersRequest();
		
		webServiceTemplate.setDefaultUri(defaultUri);
		webServiceTemplate.setMessageSender(userWebServiceMessageSenderAuthorization);
		ReadAllUsersResponse readAllUsersResponse = (ReadAllUsersResponse) webServiceTemplate.marshalSendAndReceive(readAllUsersRequest);
		
		return readAllUsersResponse;
	}
	
	public  CreateOrUpdateUserResponse createOrUpdateUserRequest(String name, String userName, String encryptedPassword, String email, String roleName) {
		CreateOrUpdateUserRequest createOrUpdateUserRequest = new CreateOrUpdateUserRequest();
		
		User user = new User();
		user.setName(name);
		user.setUserName(userName);
		user.setEncryptedPassword(encryptedPassword);
		user.setEmail(email);
		
		Role role = new Role();
		role.setName(roleName);
		
		user.getRoles().getRole().add(role);
		createOrUpdateUserRequest.setUser(user);
		
		webServiceTemplate.setDefaultUri(defaultUri);
		webServiceTemplate.setMessageSender(userWebServiceMessageSenderAuthorization);
		CreateOrUpdateUserResponse createOrUpdateUserResponse = (CreateOrUpdateUserResponse) webServiceTemplate.marshalSendAndReceive(createOrUpdateUserRequest);
		
		return createOrUpdateUserResponse;
	}

	@Override
	public DeleteUserResponse deleteUserRequest(String name) {
		DeleteUserRequest deleteUserRequest = new DeleteUserRequest();
		deleteUserRequest.setName(name);
		
		webServiceTemplate.setDefaultUri(defaultUri);
		webServiceTemplate.setMessageSender(userWebServiceMessageSenderAuthorization);
		DeleteUserResponse deleteUserResponse = (DeleteUserResponse) webServiceTemplate.marshalSendAndReceive(deleteUserRequest);
		
		return deleteUserResponse;
	}

}
