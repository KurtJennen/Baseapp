package be.luxuryoverdosis.framework.data.restwrapperdto;

import java.util.ArrayList;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import be.luxuryoverdosis.framework.data.dto.BaseDTO;

public class RestWrapperDTO<T> {
	private ArrayList<String> errors = new ArrayList<String>();
	private ArrayList<String> messages = new ArrayList<String>();
	private T dto;
	private ArrayList<T> dtoList = new ArrayList<T>();
	private HashMap<String, ArrayList<? extends BaseDTO>> lists = new HashMap<>();
	private byte[] fileData;

	public ArrayList<String> getErrors() {
		return errors;
	}
	public void setErrors(final ArrayList<String> errors) {
		this.errors = errors;
	}
	public ArrayList<String> getMessages() {
		return messages;
	}
	public void setMessages(final ArrayList<String> messages) {
		this.messages = messages;
	}
	public T getDto() {
		return dto;
	}
	public void setDto(final T dto) {
		this.dto = dto;
	}
	public ArrayList<T> getDtoList() {
		return dtoList;
	}
	public void setDtoList(final ArrayList<T> dtoList) {
		this.dtoList = dtoList;
	}
	public HashMap<String, ArrayList<? extends BaseDTO>> getLists() {
		return lists;
	}
	public void setLists(final HashMap<String, ArrayList<? extends BaseDTO>> lists) {
		this.lists = lists;
	}
	public void addList(final String name, final ArrayList<? extends BaseDTO> list) {
		this.lists.put(name, list);
	}
	public byte[] getFileData() {
		return fileData;
	}
	public void setFileData(final byte[] fileData) {
		this.fileData = fileData;
	}
	
	public String sendRestMessageWrapperDto(final String message) throws JsonProcessingException {
		messages.add(message);
		return sendRestWrapperDto();
	}
	
	public String sendRestErrorWrapperDto(final String error) throws JsonProcessingException {
		errors.add(error);
		return sendRestWrapperDto();
	}
	
	public String sendRestWrapperDto() throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(this);
	}
}
