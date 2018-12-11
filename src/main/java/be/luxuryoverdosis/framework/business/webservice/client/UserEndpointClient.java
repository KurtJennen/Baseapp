package be.luxuryoverdosis.framework.business.webservice.client;

import be.luxuryoverdosis.baseapp.business.service.SpringServiceLocator;
import be.luxuryoverdosis.framework.business.webservice.interfaces.UserEndpointClientService;
import be.luxuryoverdosis.generated.user.schema.v1.CreateOrUpdateUserResponse;
import be.luxuryoverdosis.generated.user.schema.v1.DeleteUserResponse;
import be.luxuryoverdosis.generated.user.schema.v1.Message;
import be.luxuryoverdosis.generated.user.schema.v1.ReadAllUsersResponse;
import be.luxuryoverdosis.generated.user.schema.v1.ReadUserResponse;
import be.luxuryoverdosis.generated.user.schema.v1.User;

public class UserEndpointClient {

	public static void main(String[] args) {
		SpringServiceLocator.getSpringServiceLocator();
		
		//SOAP webservice
		UserEndpointClientService userEndpointClientService = SpringServiceLocator.getBean(UserEndpointClientService.class);
		
		//ReadUserRequest
		ReadUserResponse readUserResponse = userEndpointClientService.readUserRequest("root");
		printMessage(readUserResponse.getMessage());
		print(readUserResponse.getUser());
		
		//ReadAllUsersRequest
		ReadAllUsersResponse readAllUsersResponse = userEndpointClientService.readAllUsersRequest();
		printMessage(readAllUsersResponse.getMessage());
		if(readAllUsersResponse.getUser() != null) {
			for(User user : readAllUsersResponse.getUser()) {
				print(user);
			}
		}
		
		//CreateOrUpdateUserRequest
		CreateOrUpdateUserResponse createOrUpdateUserResponse = userEndpointClientService.createOrUpdateUserRequest("tst", "Test", "cm9vdA==", "kurt.jennen@skynet.be", "BEHEERDER");
		printMessage(createOrUpdateUserResponse.getMessage());
		
		//DeleteUserResponse
		DeleteUserResponse deleteUserResponse = userEndpointClientService.deleteUserRequest("tst");
		printMessage(deleteUserResponse.getMessage());
		
	}
	
	private static void print(User user) {
		System.out.println(user.getName());
		System.out.println(user.getUserName());
		System.out.println(user.getRole());
		System.out.println(user.getDateExpiration());
		System.out.println(user.getEmail());
		System.out.println(user.getEncryptedPassword());
	}

	private static void printMessage(Message message) {
		System.out.println(message.getMessage());
	}
	
	

}
