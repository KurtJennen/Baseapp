package be.luxuryoverdosis.framework.data.document;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import be.luxuryoverdosis.framework.data.to.User;

@XmlRootElement(name="users")
public class UserDocument {
	private ArrayList<User> users;

	@XmlElement(name="user")
	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

}
