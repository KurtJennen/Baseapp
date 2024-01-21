package be.luxuryoverdosis.framework.business.webservice.implementations;

import java.io.IOException;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import be.luxuryoverdosis.baseapp.Constants;
import be.luxuryoverdosis.framework.business.encryption.Encryption;
import be.luxuryoverdosis.framework.business.webservice.interfaces.KeycloackRestServiceClient;
import be.luxuryoverdosis.framework.data.dto.KeycloakDTO;
import be.luxuryoverdosis.framework.data.dto.TokenDTO;
import be.luxuryoverdosis.framework.data.dto.TokenHeaderDTO;
import be.luxuryoverdosis.framework.data.dto.TokenPayloadDTO;

@Service
public class KeycloackRestServiceClientImpl extends BaseRestServiceClient implements KeycloackRestServiceClient {
	@Resource(name="restTemplate")
	private RestTemplate restTemplate;
	
	private static final String CLIENT_ID = "client_id=";
	private static final String RESPONSE_TYPE = "response_type=";
	private static final String REDIRECT_URI = "redirect_uri=";
	private static final String SCOPE = "scope=";
	
	private static final String GRANT_TYPE = "grant_type=";
	private static final String CODE = "code=";
	
	private static final String REFRESH_TOKEN = "refresh_token=";
	
	private static final String AUTHORIZATION_TOKEN = "Authorization: Bearer ";
	
	public String configuration(KeycloakDTO keycloakDTO) throws JsonParseException, JsonMappingException, IOException {
		StringBuffer url = new StringBuffer();
		url.append(keycloakDTO.getDefaultUri());
		url.append(keycloakDTO.getConfiguration());
		
		ResponseEntity<String> responseEntity = restTemplate.exchange(url.toString(), HttpMethod.GET, null, String.class);
		
		ObjectMapper objectMapper = new ObjectMapper();
		Object json = objectMapper.readValue(responseEntity.getBody(), Object.class);
		String result = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
		
		return result;
	}
	
	public String authenticationUrl(KeycloakDTO keycloakDTO) {
		StringBuffer url = new StringBuffer();
		url.append(keycloakDTO.getDefaultUri());
		url.append(keycloakDTO.getAuthentication());
		url.append(Constants.QUESTION);
		url.append(CLIENT_ID).append(keycloakDTO.getClientId()).append(Constants.AMPERSAND);
		url.append(RESPONSE_TYPE).append(keycloakDTO.getResponseType()).append(Constants.AMPERSAND);
		url.append(REDIRECT_URI).append(keycloakDTO.getRedirectUri()).append(Constants.AMPERSAND);
		url.append(SCOPE).append(keycloakDTO.getScope());
		
		return url.toString();
	}
	
	public TokenDTO tokenUrl(KeycloakDTO keycloakDTO) throws JsonParseException, JsonMappingException, IOException {
		StringBuffer url = new StringBuffer();
		url.append(keycloakDTO.getDefaultUri());
		url.append(keycloakDTO.getToken());
		//url.append(Constants.QUESTION);
		
		StringBuffer body = new StringBuffer();
		body.append(GRANT_TYPE).append(keycloakDTO.getGrantType()).append(Constants.AMPERSAND);
		body.append(CODE).append(keycloakDTO.getCode()).append(Constants.AMPERSAND);
		body.append(CLIENT_ID).append(keycloakDTO.getClientId()).append(Constants.AMPERSAND);
		body.append(REDIRECT_URI).append(keycloakDTO.getTokenRedirectUri());
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		ResponseEntity<String> responseEntity = restTemplate.exchange(url.toString(), HttpMethod.POST, new HttpEntity<>(body.toString(), httpHeaders), String.class);
		
		TokenDTO tokenDTO = convertJsonToObject(responseEntity.getBody(), TokenDTO.class);
		tokenDTO.setTokenResult(prettyPrintJsonString(tokenDTO));
		
		String[] idTokens = StringUtils.split(tokenDTO.getId_token(), Constants.POINT);
		TokenHeaderDTO tokenHeaderDTO = convertJsonToObject(Encryption.decode(idTokens[0]), TokenHeaderDTO.class);
		tokenDTO.setIdTokenHeader(prettyPrintJsonString(tokenHeaderDTO));
		
		TokenPayloadDTO tokenPayloadDTO = convertJsonToObject(Encryption.decode(idTokens[1]), TokenPayloadDTO.class);
		tokenDTO.setIdTokenPayload(prettyPrintJsonString(tokenPayloadDTO));

		tokenDTO.setIdTokenSignature(idTokens[2]);
		
		return tokenDTO;
	}
	
	public TokenDTO refresh(KeycloakDTO keycloakDTO) throws JsonParseException, JsonMappingException, IOException {
		StringBuffer url = new StringBuffer();
		url.append(keycloakDTO.getDefaultUri());
		url.append(keycloakDTO.getToken());
		
		StringBuffer body = new StringBuffer();
		body.append(GRANT_TYPE).append("refresh_token").append(Constants.AMPERSAND);
		body.append(REFRESH_TOKEN).append(keycloakDTO.getRefreshToken()).append(Constants.AMPERSAND);
		body.append(CLIENT_ID).append(keycloakDTO.getClientId()).append(Constants.AMPERSAND);
		body.append(SCOPE).append(keycloakDTO.getScope());
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		ResponseEntity<String> responseEntity = restTemplate.exchange(url.toString(), HttpMethod.POST, new HttpEntity<>(body.toString(), httpHeaders), String.class);
		
		TokenDTO tokenDTO = convertJsonToObject(responseEntity.getBody(), TokenDTO.class);
		tokenDTO.setTokenResult(prettyPrintJsonString(tokenDTO));
		
		return tokenDTO;
		
	}
	
	public TokenDTO userinfo(KeycloakDTO keycloakDTO) throws JsonParseException, JsonMappingException, IOException {
		StringBuffer url = new StringBuffer();
		url.append(keycloakDTO.getDefaultUri());
		url.append(keycloakDTO.getUserinfo());
		
		StringBuffer body = new StringBuffer();
		body.append(AUTHORIZATION_TOKEN).append(keycloakDTO.getIdToken()).append(Constants.AMPERSAND);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		ResponseEntity<String> responseEntity = restTemplate.exchange(url.toString(), HttpMethod.POST, new HttpEntity<>(body.toString(), httpHeaders), String.class);
		
		TokenDTO tokenDTO = convertJsonToObject(responseEntity.getBody(), TokenDTO.class);
		tokenDTO.setTokenResult(prettyPrintJsonString(tokenDTO));
		
		return tokenDTO;
	}

	private <T> T convertJsonToObject(String body, Class<T> clazz) throws IOException, JsonParseException, JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		
		return objectMapper.readValue(body, clazz);
	}
	
	private <T> String prettyPrintJsonString(T object) throws IOException, JsonParseException, JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		
		String result = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
		
		return result;
	}
	
}
