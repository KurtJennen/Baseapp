package be.luxuryoverdosis.framework.business.webservice.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;

import be.luxuryoverdosis.framework.data.dto.DocumentDTO;

public interface DocumentRestService {
	public String readRequest(int id) throws JsonProcessingException;
	public String readAllRequest() throws JsonProcessingException;
	public String createOrUpdateRequest(DocumentDTO DocumentDTO) throws JsonProcessingException;
	public String deleteRequest(int id) throws JsonProcessingException;
}
