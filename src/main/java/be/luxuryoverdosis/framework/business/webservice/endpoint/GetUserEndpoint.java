package be.luxuryoverdosis.framework.business.webservice.endpoint;

import java.util.ArrayList;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;
import be.luxuryoverdosis.framework.business.service.interfaces.RoleService;
import be.luxuryoverdosis.framework.business.service.interfaces.UserService;
import be.luxuryoverdosis.framework.data.to.Role;
import be.luxuryoverdosis.framework.data.to.UserTO;
import be.luxuryoverdosis.framework.web.exception.ServiceException;
import be.luxuryoverdosis.user.schema.v1.CreateOrUpdateUserRequest;
import be.luxuryoverdosis.user.schema.v1.CreateOrUpdateUserResponse;
import be.luxuryoverdosis.user.schema.v1.DeleteUserRequest;
import be.luxuryoverdosis.user.schema.v1.DeleteUserResponse;
import be.luxuryoverdosis.user.schema.v1.Message;
import be.luxuryoverdosis.user.schema.v1.ReadAllUsersRequest;
import be.luxuryoverdosis.user.schema.v1.ReadAllUsersResponse;
import be.luxuryoverdosis.user.schema.v1.ReadUserRequest;
import be.luxuryoverdosis.user.schema.v1.ReadUserResponse;

@Endpoint
public class GetUserEndpoint {

	@PayloadRoot(localPart="ReadUserRequest", namespace="http://www.luxuryoverdosis.be/user/schema/v1")
	@ResponsePayload
	public ReadUserResponse readUserRequest(@RequestPayload ReadUserRequest request) throws Exception {
		ReadUserResponse readUserResponse = new ReadUserResponse();
		Message message = new Message();
		
		UserTO userTO = getUserService().readName(request.getName());
		if(userTO != null) {
			be.luxuryoverdosis.user.schema.v1.User userWs = createUser(userTO);
			
			readUserResponse.setUser(userWs);
			
			message.setMessage(BaseSpringServiceLocator.getMessage("read.success", new Object[]{BaseSpringServiceLocator.getMessage("table.user")}));
			readUserResponse.setMessage(message);
		} else {
			message.setMessage(BaseSpringServiceLocator.getMessage("exists.not", new Object[]{BaseSpringServiceLocator.getMessage("table.user")}));
			readUserResponse.setMessage(message);
		}
		
		return readUserResponse;
	}

	@PayloadRoot(localPart="ReadAllUsersRequest", namespace="http://www.luxuryoverdosis.be/user/schema/v1")
	@ResponsePayload
	public ReadAllUsersResponse readAllUsersResponse(@RequestPayload ReadAllUsersRequest request) {
		ReadAllUsersResponse readAllUsersResponse = new ReadAllUsersResponse();
		Message message = new Message();
		
		ArrayList<UserTO> userList = getUserService().list();
		for (UserTO userTO : userList) {
			be.luxuryoverdosis.user.schema.v1.User userWs = createUser(userTO);
			
			readAllUsersResponse.getUser().add(userWs);
		}
		
		message.setMessage(BaseSpringServiceLocator.getMessage("list.success.count", new Object[]{BaseSpringServiceLocator.getMessage("table.user"), userList.size()}));
		readAllUsersResponse.setMessage(message);
			
		return readAllUsersResponse;
	}
	
	@PayloadRoot(localPart="CreateOrUpdateUserRequest", namespace="http://www.luxuryoverdosis.be/user/schema/v1")
	@ResponsePayload
	public CreateOrUpdateUserResponse createOrUpdateUserRequest(@RequestPayload CreateOrUpdateUserRequest request) {
		boolean isNew = false;
		
		be.luxuryoverdosis.user.schema.v1.User userWs = request.getUser();
		
		UserTO userTO = getUserService().readName(userWs.getName());
		if(userTO == null) {
			userTO = new UserTO();
			userTO.setId(-1);
			isNew = true;
		}
		
		userTO.setEmail(userWs.getEmail());
		userTO.setEncryptedPassword(userWs.getEncryptedPassword());
		userTO.setName(userWs.getName());
		userTO.setUserName(userWs.getUserName());
		
		Role role = getRoleService().readName(userWs.getRole());
		userTO.setRole(role);
		
		CreateOrUpdateUserResponse createOrUpdateUserResponse = new CreateOrUpdateUserResponse();
		Message message = new Message();
		
		try {
			getUserService().createOrUpdate(userTO);
			if (isNew) {
				message.setMessage(BaseSpringServiceLocator.getMessage("save.success", new Object[]{BaseSpringServiceLocator.getMessage("table.user")}));
			} else {
				message.setMessage(BaseSpringServiceLocator.getMessage("update.success", new Object[]{BaseSpringServiceLocator.getMessage("table.user")}));
			}
		} catch (ServiceException e) {
			message.setMessage(e.getMessage());
		}
		
		createOrUpdateUserResponse.setMessage(message);
		
		return createOrUpdateUserResponse;
	}
	
	@PayloadRoot(localPart="DeleteUserRequest", namespace="http://www.luxuryoverdosis.be/user/schema/v1")
	@ResponsePayload
	public DeleteUserResponse deleteUserRequest(@RequestPayload DeleteUserRequest request) {
		DeleteUserResponse deleteUserResponse = new DeleteUserResponse();
		Message message = new Message();
		
		UserTO userTO = getUserService().readName(request.getName());
		
		if(userTO != null) {
			getUserService().delete(userTO.getId());
			message.setMessage(BaseSpringServiceLocator.getMessage("delete.success", new Object[]{BaseSpringServiceLocator.getMessage("table.user")}));
		} else {
			message.setMessage(BaseSpringServiceLocator.getMessage("exists.not", new Object[]{BaseSpringServiceLocator.getMessage("table.user")}));
			deleteUserResponse.setMessage(message);
		}
		
		deleteUserResponse.setMessage(message);
		
		return deleteUserResponse;
	}
	
	private be.luxuryoverdosis.user.schema.v1.User createUser(UserTO userTO) {
		be.luxuryoverdosis.user.schema.v1.User userWs = new be.luxuryoverdosis.user.schema.v1.User();
		//userWs.setDateExpiration(user.getDateExpiration());
		userWs.setEmail(userTO.getEmail());
		userWs.setEncryptedPassword(userTO.getEncryptedPassword());
		userWs.setName(userTO.getName());
		userWs.setRole(userTO.getRoleName());
		userWs.setUserName(userTO.getUserName());
		return userWs;
	}
	
	private UserService getUserService() {
		//return (UserService) SpringServiceLocator.getBean(SpringServiceConstants.USER_SERVICE);
		return BaseSpringServiceLocator.getBean(UserService.class);
	}
	
	private RoleService getRoleService() {
		//return (RoleService) SpringServiceLocator.getBean(SpringServiceConstants.ROLE_SERVICE);
		return BaseSpringServiceLocator.getBean(RoleService.class);
	}

}
