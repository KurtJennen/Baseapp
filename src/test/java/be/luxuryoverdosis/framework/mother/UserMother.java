package be.luxuryoverdosis.framework.mother;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import be.luxuryoverdosis.framework.business.encryption.Encryption;
import be.luxuryoverdosis.framework.data.to.User;

public class UserMother {
	public static final int YEAR = 2100;
	public static final int MONTH = 0;
	public static final int DAY = 1;
	
	protected UserMother() {
	}

	public static User produceUser() {
		User user = new User();
		user.setId(0);
		user.setName("Kurt Jennen");
		user.setUserName("kj");
		user.setEncryptedPassword(Encryption.encode("kj"));
		user.setEmail("kurt.jennen@skynet.be");
		
		return user;
	}
	
	public static User produceUserDate() {
		User user = produceUser();
		user.setDateExpiration(new Date());
		
		return user;
	}
	
	public static User produceUserDateSpecific() {
		User user = produceUser();
		
		Calendar defaultCalendar = Calendar.getInstance();
		defaultCalendar.clear();
		defaultCalendar.set(YEAR, MONTH, DAY);
		
		user.setDateExpiration(defaultCalendar.getTime());
		
		return user;
	}
	
	public static User produceUserRole() {
		User user = produceUser();
		user.setDateExpiration(new Date());
		
		return user;
	}
	
	public static ArrayList<User> produceListUser() {
		ArrayList<User> arrayList = new ArrayList<User>();
		arrayList.add(UserMother.produceUser());
		
		return arrayList;
	}
	
}
