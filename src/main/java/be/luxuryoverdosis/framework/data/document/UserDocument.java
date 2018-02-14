package be.luxuryoverdosis.framework.data.document;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import be.luxuryoverdosis.framework.data.to.UserTO;

@XmlRootElement(name="users")
public class UserDocument {
	private ArrayList<UserTO> users;

	@XmlElement(name="user")
	public ArrayList<UserTO> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<UserTO> users) {
		this.users = users;
	}

}
