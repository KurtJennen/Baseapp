package be.luxuryoverdosis.framework.mother;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import be.luxuryoverdosis.framework.business.encryption.Encryption;
import be.luxuryoverdosis.framework.data.to.UserTO;

public class UserTOMother {

	public static UserTO produceUserTO() {
		UserTO userTO = new UserTO();
		userTO.setId(0);
		userTO.setName("Kurt Jennen");
		userTO.setUserName("kj");
		userTO.setEncryptedPassword(Encryption.encode("kj"));
		userTO.setEmail("kurt.jennen@skynet.be");
		
		return userTO;
	}
	
	public static UserTO produceUserTODate() {
		UserTO userTO = new UserTO();
		userTO.setId(0);
		userTO.setName("Kurt Jennen");
		userTO.setUserName("kj");
		userTO.setEncryptedPassword(Encryption.encode("kj"));
		userTO.setEmail("kurt.jennen@skynet.be");
		userTO.setDateExpiration(new Date());
		
		return userTO;
	}
	
	public static UserTO produceUserTODateSpecific() {
		UserTO userTO = new UserTO();
		userTO.setId(0);
		userTO.setName("Kurt Jennen");
		userTO.setUserName("kj");
		userTO.setEncryptedPassword(Encryption.encode("kj"));
		userTO.setEmail("kurt.jennen@skynet.be");
		
		Calendar defaultCalendar = Calendar.getInstance();
		defaultCalendar.clear();
		defaultCalendar.set(2100, 0, 1);
		
		userTO.setDateExpiration(defaultCalendar.getTime());
		
		return userTO;
	}
	
	public static UserTO produceUserTORole() {
		UserTO userTO = new UserTO();
		userTO.setId(0);
		userTO.setName("Kurt Jennen");
		userTO.setUserName("kj");
		userTO.setEncryptedPassword(Encryption.encode("kj"));
		userTO.setEmail("kurt.jennen@skynet.be");
		userTO.setDateExpiration(new Date());
		userTO.setRole(RoleMother.produceRole());
		
		return userTO;
	}
	
	public static ArrayList<UserTO> produceListUserTO() {
		ArrayList<UserTO> arrayList = new ArrayList<UserTO>();
		arrayList.add(UserTOMother.produceUserTO());
		
		return arrayList;
	}
	
}
