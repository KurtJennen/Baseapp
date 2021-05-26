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
import be.luxuryoverdosis.framework.data.dao.interfaces.UserRoleHibernateDAO;
import be.luxuryoverdosis.framework.data.dto.UserRoleDTO;
import be.luxuryoverdosis.framework.data.to.User;
import be.luxuryoverdosis.framework.logging.Logging;
import be.luxuryoverdosis.framework.web.exception.ServiceException;
import be.luxuryoverdosis.generated.user.schema.v1.CreateOrUpdateUserRequest;
import be.luxuryoverdosis.generated.user.schema.v1.CreateOrUpdateUserResponse;
import be.luxuryoverdosis.generated.user.schema.v1.DeleteUserRequest;
import be.luxuryoverdosis.generated.user.schema.v1.DeleteUserResponse;
import be.luxuryoverdosis.generated.user.schema.v1.Message;
import be.luxuryoverdosis.generated.user.schema.v1.ReadAllUsersRequest;
import be.luxuryoverdosis.generated.user.schema.v1.ReadAllUsersResponse;
import be.luxuryoverdosis.generated.user.schema.v1.ReadUserRequest;
import be.luxuryoverdosis.generated.user.schema.v1.ReadUserResponse;
import be.luxuryoverdosis.generated.user.schema.v1.Role;
import be.luxuryoverdosis.generated.user.schema.v1.Roles;

@Service
public class UserEndpointServiceSpringImpl implements UserEndpointService {
	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;
	@Resource
	private UserRoleHibernateDAO userRoleHibernateDAO;
	
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
			be.luxuryoverdosis.generated.user.schema.v1.User userWs = createUser(user);
			
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
			be.luxuryoverdosis.generated.user.schema.v1.User userWs = createUser(user);
			
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
		
		be.luxuryoverdosis.generated.user.schema.v1.User userWs = request.getUser();
		
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
		
		String[] unlinkedRoleNames = new String[userWs.getRoles().getRole().size()];
		for (int i = 0; i < userWs.getRoles().getRole().size(); i++) {
			unlinkedRoleNames[i] = userWs.getRoles().getRole().get(i).getName();
		}
		
		try {
			userService.createOrUpdate(user, unlinkedRoleNames);
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
	
	private be.luxuryoverdosis.generated.user.schema.v1.User createUser(User user) {
		be.luxuryoverdosis.generated.user.schema.v1.User userWs = new be.luxuryoverdosis.generated.user.schema.v1.User();
		//userWs.setDateExpiration(user.getDateExpiration());
		userWs.setEmail(user.getEmail());
		userWs.setEncryptedPassword(user.getEncryptedPassword());
		userWs.setName(user.getName());
		userWs.setUserName(user.getUserName());
		
		fillUserRoles(user, userWs);
		
		return userWs;
	}

	private void fillUserRoles(User user, be.luxuryoverdosis.generated.user.schema.v1.User userWs) {
		Roles roles = new Roles();
		ArrayList<UserRoleDTO> userRolesList = userRoleHibernateDAO.listDTO(user.getId());
		for (UserRoleDTO userRoleDTO : userRolesList) {
			Role role = new Role();
			role.setName(userRoleDTO.getRoleName());
			
			roles.getRole().add(role);
		}
		
		userWs.setRoles(roles);
	}
	
}
