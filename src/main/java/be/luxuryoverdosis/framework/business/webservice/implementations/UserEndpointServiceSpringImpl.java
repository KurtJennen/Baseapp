package be.luxuryoverdosis.framework.business.webservice.implementations;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;
import be.luxuryoverdosis.framework.business.service.interfaces.RoleService;
import be.luxuryoverdosis.framework.business.service.interfaces.UserService;
import be.luxuryoverdosis.framework.business.thread.ThreadManager;
import be.luxuryoverdosis.framework.business.webservice.interfaces.UserEndpointService;
import be.luxuryoverdosis.framework.data.to.Role;
import be.luxuryoverdosis.framework.data.to.User;
import be.luxuryoverdosis.framework.logging.Logging;
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

@Service
public class UserEndpointServiceSpringImpl implements UserEndpointService {
	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;
	
	@Transactional(readOnly=true)
	public ReadUserResponse readUserRequest(ReadUserRequest request) {
		Logging.info(this, "Begin readUserRequest");
		
		ReadUserResponse readUserResponse = new ReadUserResponse();
		Message message = new Message();
		
		if(ThreadManager.getUserFromThread() == null) {
			message.setMessage(BaseSpringServiceLocator.getMessage("security.access.denied"));
			readUserResponse.setMessage(message);
			return readUserResponse;
		}
		
		User user = userService.readName(request.getName());
		if(user != null) {
			be.luxuryoverdosis.user.schema.v1.User userWs = createUser(user);
			
			readUserResponse.setUser(userWs);
			
			message.setMessage(BaseSpringServiceLocator.getMessage("read.success", new Object[]{BaseSpringServiceLocator.getMessage("table.user")}));
			readUserResponse.setMessage(message);
		} else {
			message.setMessage(BaseSpringServiceLocator.getMessage("exists.not", new Object[]{BaseSpringServiceLocator.getMessage("table.user")}));
			readUserResponse.setMessage(message);
		}
		
		Logging.info(this, "End readUserRequest");
		return readUserResponse;
	}
	
	@Transactional(readOnly=true)
	public ReadAllUsersResponse readAllUsersRequest(ReadAllUsersRequest request) {
		Logging.info(this, "Begin readAllUsersRequest");
		
		ReadAllUsersResponse readAllUsersResponse = new ReadAllUsersResponse();
		Message message = new Message();
		
		if(ThreadManager.getUserFromThread() == null) {
			message.setMessage(BaseSpringServiceLocator.getMessage("security.access.denied"));
			readAllUsersResponse.setMessage(message);
			return readAllUsersResponse;
		}
		
		ArrayList<User> userList = userService.list();
		for (User user : userList) {
			be.luxuryoverdosis.user.schema.v1.User userWs = createUser(user);
			
			readAllUsersResponse.getUser().add(userWs);
		}
		
		message.setMessage(BaseSpringServiceLocator.getMessage("list.success.count", new Object[]{BaseSpringServiceLocator.getMessage("table.user"), userList.size()}));
		readAllUsersResponse.setMessage(message);
		
		Logging.info(this, "End readAllUsersRequest");
		return readAllUsersResponse;
	}

	@Transactional
	public CreateOrUpdateUserResponse createOrUpdateUserRequest(CreateOrUpdateUserRequest request) {
		Logging.info(this, "Begin createOrUpdateUserRequest");
		
		CreateOrUpdateUserResponse createOrUpdateUserResponse = new CreateOrUpdateUserResponse();
		Message message = new Message();
		
		if(ThreadManager.getUserFromThread() == null) {
			message.setMessage(BaseSpringServiceLocator.getMessage("security.access.denied"));
			createOrUpdateUserResponse.setMessage(message);
			return createOrUpdateUserResponse;
		}
		
		boolean isNew = false;
		
		be.luxuryoverdosis.user.schema.v1.User userWs = request.getUser();
		
		User user = userService.readName(userWs.getName());
		if(user == null) {
			user = new User();
			user.setId(-1);
			isNew = true;
		}
		
		user.setEmail(userWs.getEmail());
		user.setEncryptedPassword(userWs.getEncryptedPassword());
		user.setName(userWs.getName());
		user.setUserName(userWs.getUserName());
		
		Role role = roleService.readName(userWs.getRole());
		user.setRole(role);
		
		try {
			userService.createOrUpdate(user);
			if (isNew) {
				message.setMessage(BaseSpringServiceLocator.getMessage("save.success", new Object[]{BaseSpringServiceLocator.getMessage("table.user")}));
			} else {
				message.setMessage(BaseSpringServiceLocator.getMessage("update.success", new Object[]{BaseSpringServiceLocator.getMessage("table.user")}));
			}
		} catch (ServiceException e) {
			message.setMessage(e.getMessage());
		}
		
		createOrUpdateUserResponse.setMessage(message);
		
		Logging.info(this, "End createOrUpdateUserRequest");
		return createOrUpdateUserResponse;
	}

	@Transactional
	public DeleteUserResponse deleteUserRequest(DeleteUserRequest request) {
		Logging.info(this, "Begin deleteUserRequest");
		
		DeleteUserResponse deleteUserResponse = new DeleteUserResponse();
		Message message = new Message();
		
		if(ThreadManager.getUserFromThread() == null) {
			message.setMessage(BaseSpringServiceLocator.getMessage("security.access.denied"));
			deleteUserResponse.setMessage(message);
			return deleteUserResponse;
		}
		
		User user = userService.readName(request.getName());
		
		if(user != null) {
			userService.delete(user.getId());
			message.setMessage(BaseSpringServiceLocator.getMessage("delete.success", new Object[]{BaseSpringServiceLocator.getMessage("table.user")}));
		} else {
			message.setMessage(BaseSpringServiceLocator.getMessage("exists.not", new Object[]{BaseSpringServiceLocator.getMessage("table.user")}));
			deleteUserResponse.setMessage(message);
		}
		
		deleteUserResponse.setMessage(message);
		
		Logging.info(this, "End deleteUserRequest");
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
}
