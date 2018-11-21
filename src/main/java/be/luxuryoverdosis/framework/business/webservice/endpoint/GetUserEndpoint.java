package be.luxuryoverdosis.framework.business.webservice.endpoint;

import java.util.ArrayList;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;
import be.luxuryoverdosis.framework.business.service.interfaces.RoleService;
import be.luxuryoverdosis.framework.business.service.interfaces.UserService;
import be.luxuryoverdosis.framework.business.thread.ThreadManager;
import be.luxuryoverdosis.framework.data.to.Role;
import be.luxuryoverdosis.framework.data.to.User;
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
		
		if(ThreadManager.getUserFromThread() == null) {
			message.setMessage(BaseSpringServiceLocator.getMessage("security.access.denied"));
			readUserResponse.setMessage(message);
			return readUserResponse;
		}
		
		User user = getUserService().readName(request.getName());
		if(user != null) {
			be.luxuryoverdosis.user.schema.v1.User userWs = createUser(user);
			
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
		
		if(ThreadManager.getUserFromThread() == null) {
			message.setMessage(BaseSpringServiceLocator.getMessage("security.access.denied"));
			readAllUsersResponse.setMessage(message);
			return readAllUsersResponse;
		}
		
		ArrayList<User> userList = getUserService().list();
		for (User user : userList) {
			be.luxuryoverdosis.user.schema.v1.User userWs = createUser(user);
			
			readAllUsersResponse.getUser().add(userWs);
		}
		
		message.setMessage(BaseSpringServiceLocator.getMessage("list.success.count", new Object[]{BaseSpringServiceLocator.getMessage("table.user"), userList.size()}));
		readAllUsersResponse.setMessage(message);
			
		return readAllUsersResponse;
	}
	
	@PayloadRoot(localPart="CreateOrUpdateUserRequest", namespace="http://www.luxuryoverdosis.be/user/schema/v1")
	@ResponsePayload
	public CreateOrUpdateUserResponse createOrUpdateUserRequest(@RequestPayload CreateOrUpdateUserRequest request) {
		CreateOrUpdateUserResponse createOrUpdateUserResponse = new CreateOrUpdateUserResponse();
		Message message = new Message();
		
		if(ThreadManager.getUserFromThread() == null) {
			message.setMessage(BaseSpringServiceLocator.getMessage("security.access.denied"));
			createOrUpdateUserResponse.setMessage(message);
			return createOrUpdateUserResponse;
		}
		
		boolean isNew = false;
		
		be.luxuryoverdosis.user.schema.v1.User userWs = request.getUser();
		
		User user = getUserService().readName(userWs.getName());
		if(user == null) {
			user = new User();
			user.setId(-1);
			isNew = true;
		}
		
		user.setEmail(userWs.getEmail());
		user.setEncryptedPassword(userWs.getEncryptedPassword());
		user.setName(userWs.getName());
		user.setUserName(userWs.getUserName());
		
		Role role = getRoleService().readName(userWs.getRole());
		user.setRole(role);
		
		try {
			getUserService().createOrUpdate(user);
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
		
		if(ThreadManager.getUserFromThread() == null) {
			message.setMessage(BaseSpringServiceLocator.getMessage("security.access.denied"));
			deleteUserResponse.setMessage(message);
			return deleteUserResponse;
		}
		
		User user = getUserService().readName(request.getName());
		
		if(user != null) {
			getUserService().delete(user.getId());
			message.setMessage(BaseSpringServiceLocator.getMessage("delete.success", new Object[]{BaseSpringServiceLocator.getMessage("table.user")}));
		} else {
			message.setMessage(BaseSpringServiceLocator.getMessage("exists.not", new Object[]{BaseSpringServiceLocator.getMessage("table.user")}));
			deleteUserResponse.setMessage(message);
		}
		
		deleteUserResponse.setMessage(message);
		
		return deleteUserResponse;
	}
	
	private be.luxuryoverdosis.user.schema.v1.User createUser(User user) {
		be.luxuryoverdosis.user.schema.v1.User userWs = new be.luxuryoverdosis.user.schema.v1.User();
		//userWs.setDateExpiration(user.getDateExpiration());
		userWs.setEmail(user.getEmail());
		userWs.setEncryptedPassword(user.getEncryptedPassword());
		userWs.setName(user.getName());
		userWs.setRole(user.getRole().getName());
		userWs.setUserName(user.getUserName());
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
