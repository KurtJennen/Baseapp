package be.luxuryoverdosis.framework.business.webservice.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;

import be.luxuryoverdosis.framework.data.dto.DocumentDTO;

public interface DocumentRestService {
	String readRequest(int id) throws JsonProcessingException;
	String readAllRequest() throws JsonProcessingException;
	String createOrUpdateRequest(DocumentDTO documentDTO) throws JsonProcessingException;
	String deleteRequest(int id) throws JsonProcessingException;
	byte[] downloadRequest(int id) throws JsonProcessingException;
}
