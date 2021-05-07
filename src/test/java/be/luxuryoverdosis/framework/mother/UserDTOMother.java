package be.luxuryoverdosis.framework.mother;

import java.util.ArrayList;
import java.util.Date;

import be.luxuryoverdosis.framework.base.tool.DateTool;
import be.luxuryoverdosis.framework.data.dto.UserDTO;

public class UserDTOMother {

	public static UserDTO produceUserDTO() {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(0);
		userDTO.setName("Kurt Jennen");
		userDTO.setUserName("kj");
		userDTO.setPassword("kj");
		userDTO.setEmail("kurt.jennen@skynet.be");
		userDTO.setDateExpiration(new Date());
		userDTO.setDateExpirationAsString(DateTool.formatUtilDate(new Date()));
//		userDTO.setRoleId(1);
//		userDTO.setRoleName(RoleNameEnum.BEHEERDER.getCode());
		return userDTO;
	}
	
	public static ArrayList<UserDTO> produceListUserDTO() {
		ArrayList<UserDTO> arrayList = new ArrayList<UserDTO>();
		arrayList.add(UserDTOMother.produceUserDTO());
		
		return arrayList;
	}
	
	public static ArrayList<Long> produceListLong() {
		ArrayList<Long> arrayList = new ArrayList<Long>();
		arrayList.add(0L);
		
		return arrayList;
	}
}
