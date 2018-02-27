package be.luxuryoverdosis.framework.data.to;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.CallbackException;
import org.hibernate.Session;
import org.hibernate.classic.Lifecycle;

import be.luxuryoverdosis.framework.business.thread.ThreadManager;

public class BaseTO implements Lifecycle {
	private	int id = -1;
	private int version = -1;
	private String userAdd = "root";
	private String userUpdate = "root";
	private Date dateAdd;
	private Date dateUpdate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}	
	public String getUserAdd() {
		return userAdd;
	}
	public void setUserAdd(String userAdd) {
		this.userAdd = userAdd;
	}
	public String getUserUpdate() {
		return userUpdate;
	}
	public void setUserUpdate(String userUpdate) {
		this.userUpdate = userUpdate;
	}
	public Date getDateAdd() {
		return dateAdd;
	}
	public void setDateAdd(Date dateAdd) {
		this.dateAdd = dateAdd;
	}
	public Date getDateUpdate() {
		return dateUpdate;
	}
	public void setDateUpdate(Date dateUpdate) {
		this.dateUpdate = dateUpdate;
	}
	
	public boolean onDelete(Session arg0) throws CallbackException {
		return false;
	}
	
	public void onLoad(Session arg0, Serializable arg1) {
	}
	
	public boolean onSave(Session arg0) throws CallbackException {
		User user = (User) ThreadManager.getUserFromThread();
		
		if(user != null) {
			userAdd = user.getName();
			userUpdate = user.getName();
		}
		dateAdd = new Date(System.currentTimeMillis());
		dateUpdate = new Date(System.currentTimeMillis());
		
		return false;
	}
	
	public boolean onUpdate(Session arg0) throws CallbackException {
		User user = (User) ThreadManager.getUserFromThread();
		
		if(user != null) {
			userUpdate = user.getName();
		}		
		dateUpdate = new Date(System.currentTimeMillis());
		
		return false;
	}
}
