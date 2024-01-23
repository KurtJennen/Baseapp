package be.luxuryoverdosis.framework.business.webservice.interfaces;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import be.luxuryoverdosis.framework.data.dto.KeycloakDTO;
import be.luxuryoverdosis.framework.data.dto.TokenDTO;

public interface KeycloackRestServiceClient {
	public String configuration(KeycloakDTO keycloakDTO) throws JsonParseException, JsonMappingException, IOException;
	public String authenticationUrl(KeycloakDTO keycloakDTO);
	public TokenDTO tokenUrl(KeycloakDTO keycloakDTO) throws JsonParseException, JsonMappingException, IOException;
	public TokenDTO refresh(KeycloakDTO keycloakDTO) throws JsonParseException, JsonMappingException, IOException;
	public TokenDTO userinfo(KeycloakDTO keycloakDTO) throws JsonParseException, JsonMappingException, IOException;
}
