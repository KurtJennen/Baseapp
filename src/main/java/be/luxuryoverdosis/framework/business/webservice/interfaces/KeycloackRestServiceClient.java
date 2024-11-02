package be.luxuryoverdosis.framework.business.webservice.interfaces;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import be.luxuryoverdosis.framework.data.dto.KeycloakDTO;
import be.luxuryoverdosis.framework.data.dto.TokenDTO;

public interface KeycloackRestServiceClient {
	String configuration(KeycloakDTO keycloakDTO) throws JsonParseException, JsonMappingException, IOException;
	String authenticationUrl(KeycloakDTO keycloakDTO);
	TokenDTO tokenUrl(KeycloakDTO keycloakDTO) throws JsonParseException, JsonMappingException, IOException;
	TokenDTO refresh(KeycloakDTO keycloakDTO) throws JsonParseException, JsonMappingException, IOException;
	TokenDTO userinfo(KeycloakDTO keycloakDTO) throws JsonParseException, JsonMappingException, IOException;
}
