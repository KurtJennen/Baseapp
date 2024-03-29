package be.luxuryoverdosis.framework.data.factory;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import be.luxuryoverdosis.framework.base.tool.DateTool;
import be.luxuryoverdosis.framework.business.encryption.Encryption;
import be.luxuryoverdosis.framework.data.dto.UserDTO;
import be.luxuryoverdosis.framework.data.dto.UserImportDTO;
import be.luxuryoverdosis.framework.data.to.User;
import be.luxuryoverdosis.framework.web.exception.ServiceException;

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
		userDTO.setDateExpiration(user.getDateExpiration());
		userDTO.setDateExpirationAsString(DateTool.formatUtilDate(user.getDateExpiration()));
		
		return userDTO;
	}
	
	public static UserDTO produceUserDTO(UserDTO userDTO, final UserImportDTO userImportDTO) {
		if(userDTO == null) {
			userDTO = new UserDTO();
			userDTO.setRegister(true);
		}
		userDTO.setName(userImportDTO.getName());
		userDTO.setUserName(userImportDTO.getUserName());
		userDTO.setPassword(Encryption.decode(userImportDTO.getEncryptedPassword()));
		userDTO.setEmail(userImportDTO.getEmail());
		try {
			Date dateExpiration = DateTool.parseSqlTimestamp(userImportDTO.getDateExpirationAsString());
			userDTO.setDateExpirationAsString(DateTool.formatUtilDate(dateExpiration));
		} catch (Exception e) {
			throw new ServiceException("errors.date", new String[] {"date"});
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
		if(!StringUtils.isEmpty(userDTO.getPassword())) {
			user.setEncryptedPassword(Encryption.encode(userDTO.getPassword()));
		}
		if(!StringUtils.isEmpty(userDTO.getEncryptedPassword())) {
			user.setEncryptedPassword(userDTO.getEncryptedPassword());
		}
		user.setEmail(userDTO.getEmail());
		user.setDateExpiration(userDTO.getDateExpiration());
		try {
			if(userDTO.getDateExpirationAsString() != null) {
				user.setDateExpiration(DateTool.parseUtilTimestamp(userDTO.getDateExpirationAsString()));
			}
		} catch (Exception e) {
			throw new ServiceException("errors.date", new String[] {"date"});
		}
		
		return user;
	}
	
}
