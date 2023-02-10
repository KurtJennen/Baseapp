package be.luxuryoverdosis.framework.business.webservice.client;

import be.luxuryoverdosis.baseapp.business.service.SpringServiceLocator;
import be.luxuryoverdosis.framework.business.webservice.interfaces.UserRestServiceClient;
import be.luxuryoverdosis.framework.data.dto.UserDTO;
import be.luxuryoverdosis.framework.data.restwrapperdto.RestWrapperDTO;

public class UserRestClient {

	public static void main(String[] args) {
		SpringServiceLocator.getSpringServiceLocator();
		
		//ReadUserRequest (GET)
		RestWrapperDTO<UserDTO> userRestWrapperDTO  = getUserRestServiceClient().readUserRequest("root");
		printErrorsAndMessages(userRestWrapperDTO);
		
		if(userRestWrapperDTO.getDto() != null) {
			print(userRestWrapperDTO.getDto());
		}
		
		
		//ReadAllUsersRequest (GET)
		userRestWrapperDTO  = getUserRestServiceClient().readAllUsersRequest();
		printErrorsAndMessages(userRestWrapperDTO);
		
		if(userRestWrapperDTO.getDtoList() != null) {
			for(UserDTO userDTO : userRestWrapperDTO.getDtoList()) {
				print(userDTO);
			}
		}
		
		//CreateOrUpdateUserRequest (POST)
		userRestWrapperDTO  = getUserRestServiceClient().createOrUpdateUserRequest("tst", "Test", "cm9vdA==", "kurt.jennen@skynet.be", "Beheerder,NormaleGebruiker");
		printErrorsAndMessages(userRestWrapperDTO);
		
		if(userRestWrapperDTO.getDto() != null) {
			print(userRestWrapperDTO.getDto());
		}
		
		//DeleteUserRequest (DELETE)
		userRestWrapperDTO  = getUserRestServiceClient().deleteUserRequest(userRestWrapperDTO.getDto().getId());
		printErrorsAndMessages(userRestWrapperDTO);
		
		if(userRestWrapperDTO.getDto() != null) {
			print(userRestWrapperDTO.getDto());
		}
		
		
	}

	private static void print(UserDTO userDTO) {
		System.out.println(userDTO.getId());
		System.out.println(userDTO.getName());
		System.out.println(userDTO.getUserName());
//		System.out.println(userDTO.getRoleName());
		System.out.println(userDTO.getDateExpirationAsString());
		System.out.println(userDTO.getEmail());
		System.out.println(userDTO.getPassword());
	}

	private static void printErrorsAndMessages(RestWrapperDTO<UserDTO> userRestWrapperDTO) {
		for(String error : userRestWrapperDTO.getErrors()) {
			System.out.println("Fout: " + error);
		}
		for(String message : userRestWrapperDTO.getMessages()) {
			System.out.println("Boodschap: " + message);
		}
	}
	
	//REST webservice
	private static UserRestServiceClient getUserRestServiceClient() {
		return SpringServiceLocator.getBean(UserRestServiceClient.class);
	}
	
}
