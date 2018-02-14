package be.luxuryoverdosis.framework.data.factory;

import be.luxuryoverdosis.framework.base.tool.DateTool;
import be.luxuryoverdosis.framework.business.encryption.Encryption;
import be.luxuryoverdosis.framework.data.dto.UserDTO;
import be.luxuryoverdosis.framework.data.to.UserTO;

public class UserFactory {
	private UserFactory() {
	}
	
	public static UserDTO produceUserDTO(final UserTO userTO) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(userTO.getId());
		userDTO.setName(userTO.getName());
		userDTO.setUserName(userTO.getUserName());
		userDTO.setPassword(Encryption.decode(userTO.getEncryptedPassword()));
		userDTO.setEmail(userTO.getEmail());
		userDTO.setDateExpirationAsString(DateTool.formatUtilDate(userTO.getDateExpiration()));
		if(userTO.getRole() != null) {
			userDTO.setRoleId(userTO.getRole().getId());
		}
		
		return userDTO;
	}
	
	public static UserTO produceUser(UserTO userTO, final UserDTO userDTO) {
		if(userTO == null) {
			userTO = new UserTO();
		}
		userTO.setId(userDTO.getId());
		userTO.setName(userDTO.getName());
		userTO.setUserName(userDTO.getUserName());
		//userTO.setEncryptedPassword(userDTO.getPassword());
		userTO.setEncryptedPassword(Encryption.encode(userDTO.getPassword()));
		userTO.setEmail(userDTO.getEmail());
		
		return userTO;
	}
}
