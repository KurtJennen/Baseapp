package be.luxuryoverdosis.framework.business.webservice.implementations;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import be.luxuryoverdosis.framework.business.webservice.interfaces.UserRestServiceClient;
import be.luxuryoverdosis.framework.data.dto.UserDTO;
import be.luxuryoverdosis.framework.data.restwrapperdto.RestWrapperDTO;

@Service
public class UserRestServiceClientImpl extends BaseRestServiceClient implements UserRestServiceClient {
	@Resource(name="restTemplate")
	private RestTemplate restTemplate;
	@Value("${rest.user.defaultUri}")
	private String defaultUri;
	@Value("${rest.user.user}")
	private String user;
	@Value("${rest.user.password}")
	private String password;
	
	private static final String USER = "/user";
	private static final String READ_USER_REQUEST = USER + "/readRequestName";
//	private static final String READ_USER_REQUEST = USER + "/readRequestName?name={name}&password={password}";
	private static final String READ_ALL_USERS_REQUEST = USER + "/readAllRequest";
	private static final String CREATE_OR_UPDATE_USER_REQUEST = USER + "/createOrUpdateRequest";
//	private static final String DELETE_USER_REQUEST = USER + "/deleteRequest?id={id}";
	private static final String DELETE_USER_REQUEST = USER + "/deleteRequest";
	
	public RestWrapperDTO<UserDTO> readUserRequest(final String name, final String encryptedPassword) {
		HttpHeaders httpHeaders = getHttpHeaders(user, password);
		
//		HashMap<String, String> params = new HashMap<String, String>();
//		params.put("name", name);
//		params.put("password", encryptedPassword);
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(defaultUri + READ_USER_REQUEST).queryParam("name", name).queryParam("password", password);
		
//		ResponseEntity<RestWrapperDTO<UserDTO>> responseEntity = restTemplate.exchange(defaultUri + READ_USER_REQUEST, HttpMethod.GET, new HttpEntity<>(httpHeaders), new ParameterizedTypeReference<RestWrapperDTO<UserDTO>>(){}, params);
		ResponseEntity<RestWrapperDTO<UserDTO>> responseEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity<>(httpHeaders), new ParameterizedTypeReference<RestWrapperDTO<UserDTO>>(){});
		
		return responseEntity.getBody();
	}
	
	public RestWrapperDTO<UserDTO> readAllUsersRequest() {
		HttpHeaders httpHeaders = getHttpHeaders(user, password);
		ResponseEntity<RestWrapperDTO<UserDTO>> responseEntity = restTemplate.exchange(defaultUri + READ_ALL_USERS_REQUEST, HttpMethod.GET, new HttpEntity<>(httpHeaders), new ParameterizedTypeReference<RestWrapperDTO<UserDTO>>(){});
		return responseEntity.getBody();
	}
	
	public RestWrapperDTO<UserDTO> createOrUpdateUserRequest(String name, String userName, String encryptedPassword, String email, String[] roleNames) {
		HttpHeaders httpHeaders = getHttpHeaders(user, password);
		
		UserDTO body = new UserDTO();
		body.setName(name);
		body.setUserName(userName);
		body.setEncryptedPassword(encryptedPassword);
		body.setEmail(email);
		ArrayList<String> roles = body.getRoles();
		for (int i = 0; i < roleNames.length; i++) {
			roles.add(roleNames[i]);
		}
		body.setRoles(roles);
		
		ResponseEntity<RestWrapperDTO<UserDTO>> responseEntity = restTemplate.exchange(defaultUri + CREATE_OR_UPDATE_USER_REQUEST, HttpMethod.POST, new HttpEntity<>(body, httpHeaders), new ParameterizedTypeReference<RestWrapperDTO<UserDTO>>(){});
		return responseEntity.getBody();
	}
	
	public RestWrapperDTO<UserDTO> deleteUserRequest(int id) {
		HttpHeaders httpHeaders = getHttpHeaders(user, password);
		
//		HashMap<String, Integer> params = new HashMap<String, Integer>();
//		params.put("id", id);
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(defaultUri + DELETE_USER_REQUEST).queryParam("id", id);
		
//		ResponseEntity<RestWrapperDTO<UserDTO>> responseEntity = restTemplate.exchange(defaultUri + DELETE_USER_REQUEST, HttpMethod.DELETE, new HttpEntity<>(httpHeaders), new ParameterizedTypeReference<RestWrapperDTO<UserDTO>>(){}, params);
		ResponseEntity<RestWrapperDTO<UserDTO>> responseEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.DELETE, new HttpEntity<>(httpHeaders), new ParameterizedTypeReference<RestWrapperDTO<UserDTO>>(){});
		return responseEntity.getBody();
	}
	
}
