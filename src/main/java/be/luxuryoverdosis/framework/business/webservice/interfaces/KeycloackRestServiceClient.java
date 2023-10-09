package be.luxuryoverdosis.framework.business.webservice.interfaces;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import be.luxuryoverdosis.framework.data.dto.KeycloakDTO;

public interface KeycloackRestServiceClient {
	public String configuration(KeycloakDTO keycloakDTO) throws JsonParseException, JsonMappingException, IOException;
	public String authentication(KeycloakDTO keycloakDTO) throws JsonParseException, JsonMappingException, IOException;
}
