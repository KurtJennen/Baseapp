package be.luxuryoverdosis.framework.data.restwrapperdto;

import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RestWrapperDTO<T> {
	private ArrayList<String> errors = new ArrayList<String>();
	private ArrayList<String> messages = new ArrayList<String>();
	private T dto;
	private ArrayList<T> dtoList = new ArrayList<T>();

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
	public T getDto() {
		return dto;
	}
	public void setDto(T dto) {
		this.dto = dto;
	}
	public ArrayList<T> getDtoList() {
		return dtoList;
	}
	public void setDtoList(ArrayList<T> dtoList) {
		this.dtoList = dtoList;
	}
	
	public String sendRestMessageWrapperDto(String message) throws JsonProcessingException {
		messages.add(message);
		return sendRestWrapperDto();
	}
	
	public String sendRestErrorWrapperDto(String error) throws JsonProcessingException {
		errors.add(error);
		return sendRestWrapperDto();
	}
	
	public String sendRestWrapperDto() throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(this);
	}
}
