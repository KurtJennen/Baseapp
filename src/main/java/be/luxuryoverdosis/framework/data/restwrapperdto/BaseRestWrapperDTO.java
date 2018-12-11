package be.luxuryoverdosis.framework.data.restwrapperdto;

import java.util.ArrayList;

public class BaseRestWrapperDTO {
	private ArrayList<String> errors = new ArrayList<String>();
	private ArrayList<String> messages = new ArrayList<String>();

	public ArrayList<String> getErrors() {
		return errors;
	}
	public void setErrors(ArrayList<String> errors) {
		this.errors = errors;
	}
	public ArrayList<String> getMessages() {
		return messages;
	}
	public void setMessages(ArrayList<String> messages) {
		this.messages = messages;
	}
	
}
