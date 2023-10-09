package be.luxuryoverdosis.framework.business.webservice.implementations;

import java.io.IOException;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import be.luxuryoverdosis.framework.business.webservice.interfaces.KeycloackRestServiceClient;
import be.luxuryoverdosis.framework.data.dto.KeycloakDTO;

@Service
public class KeycloackRestServiceClientImpl extends BaseRestServiceClient implements KeycloackRestServiceClient {
	@Resource(name="restTemplate")
	private RestTemplate restTemplate;
	
	private static final String CLIENT_ID = "client_id=";
	private static final String RESPONSE_TYPE = "response_type=";
	private static final String REDIRECT_URI = "redirect_uri=";
	private static final String SCOPE = "scope=";
	
	
	public String configuration(KeycloakDTO keycloakDTO) throws JsonParseException, JsonMappingException, IOException {
		StringBuffer url = new StringBuffer();
		url.append(keycloakDTO.getDefaultUri());
		url.append(keycloakDTO.getConfiguration());
		
		ResponseEntity<String> responseEntity = restTemplate.exchange(url.toString(), HttpMethod.GET, null, String.class);
		
		ObjectMapper objectMapper = new ObjectMapper();
		Object json = objectMapper.readValue(responseEntity.getBody(), Object.class);
		String result =  objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
		
		return result;
	}
	
	public String authentication(KeycloakDTO keycloakDTO) throws JsonParseException, JsonMappingException, IOException {
		StringBuffer url = new StringBuffer();
		url.append(keycloakDTO.getDefaultUri());
		url.append(keycloakDTO.getAuthentication());
		url.append(StringUtils.SPACE).append(CLIENT_ID).append(keycloakDTO.getClientId());
		url.append(StringUtils.SPACE).append(RESPONSE_TYPE).append(keycloakDTO.getResponseType());
		url.append(StringUtils.SPACE).append(REDIRECT_URI).append(keycloakDTO.getRedirectUri());
		url.append(StringUtils.SPACE).append(SCOPE).append(keycloakDTO.getScope());
		
		ResponseEntity<String> responseEntity = restTemplate.exchange(url.toString(), HttpMethod.GET, null, String.class);
		
		return responseEntity.getBody();
	}
	
}
