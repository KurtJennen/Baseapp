package be.luxuryoverdosis.framework.business.webservice.client;

import be.luxuryoverdosis.baseapp.business.service.SpringServiceLocator;
import be.luxuryoverdosis.framework.business.webservice.interfaces.UserEndpointServiceClient;
import be.luxuryoverdosis.generated.user.schema.v1.CreateOrUpdateUserResponse;
import be.luxuryoverdosis.generated.user.schema.v1.DeleteUserResponse;
import be.luxuryoverdosis.generated.user.schema.v1.Message;
import be.luxuryoverdosis.generated.user.schema.v1.ReadAllUsersResponse;
import be.luxuryoverdosis.generated.user.schema.v1.ReadUserResponse;
import be.luxuryoverdosis.generated.user.schema.v1.Role;
import be.luxuryoverdosis.generated.user.schema.v1.User;

public class UserEndpointClient {

	public static void main(String[] args) {
		SpringServiceLocator.getSpringServiceLocator();
		
		//ReadUserRequest
		ReadUserResponse readUserResponse = getUserEndpointServiceClient().readUserRequest("root");
		printMessage(readUserResponse.getMessage());
		print(readUserResponse.getUser());
		
		//ReadAllUsersRequest
		ReadAllUsersResponse readAllUsersResponse = getUserEndpointServiceClient().readAllUsersRequest();
		printMessage(readAllUsersResponse.getMessage());
		if(readAllUsersResponse.getUser() != null) {
			for(User user : readAllUsersResponse.getUser()) {
				print(user);
			}
		}
		
		//CreateOrUpdateUserRequest
		CreateOrUpdateUserResponse createOrUpdateUserResponse = getUserEndpointServiceClient().createOrUpdateUserRequest("tst", "Test", "cm9vdA==", "kurt.jennen@skynet.be", "BEHEERDER");
		printMessage(createOrUpdateUserResponse.getMessage());
		
		//DeleteUserResponse
		DeleteUserResponse deleteUserResponse = getUserEndpointServiceClient().deleteUserRequest("tst");
		printMessage(deleteUserResponse.getMessage());
		
	}
	
	private static void print(User user) {
		System.out.println(user.getName());
		System.out.println(user.getUserName());
		System.out.println(user.getDateExpiration());
		System.out.println(user.getEmail());
		System.out.println(user.getEncryptedPassword());
		
		for(Role role : user.getRoles().getRole()) {
			System.out.println(role.getName());
		}
	}

	private static void printMessage(Message message) {
		System.out.println(message.getMessage());
	}
	
	//SOAP webservice
	private static UserEndpointServiceClient getUserEndpointServiceClient() {
		return SpringServiceLocator.getBean(UserEndpointServiceClient.class);
	}

}
