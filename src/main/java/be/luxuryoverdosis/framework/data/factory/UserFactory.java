package be.luxuryoverdosis.framework.data.factory;

import be.luxuryoverdosis.framework.base.tool.DateTool;
import be.luxuryoverdosis.framework.business.encryption.Encryption;
import be.luxuryoverdosis.framework.data.dto.UserDTO;
import be.luxuryoverdosis.framework.data.to.User;

public class UserFactory {
	private UserFactory() {
	}
	
	public static UserDTO produceUserDTO(final User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setName(user.getName());
		userDTO.setUserName(user.getUserName());
		userDTO.setPassword(Encryption.decode(user.getEncryptedPassword()));
		userDTO.setEmail(user.getEmail());
		userDTO.setDateExpirationAsString(DateTool.formatUtilDate(user.getDateExpiration()));
		if(user.getRole() != null) {
			userDTO.setRoleId(user.getRole().getId());
		}
		
		return userDTO;
	}
	
	public static User produceUser(User user, final UserDTO userDTO) {
		if(user == null) {
			user = new User();
		}
		user.setId(userDTO.getId());
		user.setName(userDTO.getName());
		user.setUserName(userDTO.getUserName());
		//user.setEncryptedPassword(userDTO.getPassword());
		user.setEncryptedPassword(Encryption.encode(userDTO.getPassword()));
		user.setEmail(userDTO.getEmail());
		
		return user;
	}
}
