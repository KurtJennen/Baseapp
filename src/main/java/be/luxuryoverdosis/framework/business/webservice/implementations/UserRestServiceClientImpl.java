package be.luxuryoverdosis.framework.business.webservice.implementations;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
	private static final String READ_USER_REQUEST = USER + "/readUserRequestName";
	private static final String READ_ALL_USERS_REQUEST = USER + "/readAllUsersRequest";
	private static final String CREATE_OR_UPDATE_USER_REQUEST = USER + "/createOrUpdateUserRequest";
	private static final String DELETE_USER_REQUEST = USER + "/deleteUserRequest";
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public RestWrapperDTO<UserDTO> readUserRequest(final String name) {
		HttpHeaders httpHeaders = getHttpHeaders(user, password);
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("name", name);
		
		ResponseEntity<RestWrapperDTO> responseEntity = restTemplate.exchange(defaultUri + READ_USER_REQUEST, HttpMethod.GET, new HttpEntity<>(httpHeaders), RestWrapperDTO.class);
		
		return responseEntity.getBody();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public RestWrapperDTO<UserDTO> readAllUsersRequest() {
		HttpHeaders httpHeaders = getHttpHeaders(user, password);
		ResponseEntity<RestWrapperDTO> responseEntity = restTemplate.exchange(defaultUri + READ_ALL_USERS_REQUEST, HttpMethod.GET, new HttpEntity<>(httpHeaders), RestWrapperDTO.class);
		return responseEntity.getBody();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public RestWrapperDTO<UserDTO> createOrUpdateUserRequest(String name, String userName, String encryptedPassword, String email, String roleNames) {
		HttpHeaders httpHeaders = getHttpHeaders(user, password);
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("name", name);
		params.put("userName", userName);
		params.put("encryptedPassword", encryptedPassword);
		params.put("email", email);
		params.put("roleNames", roleNames);
		
		ResponseEntity<RestWrapperDTO> responseEntity = restTemplate.exchange(defaultUri + CREATE_OR_UPDATE_USER_REQUEST, HttpMethod.POST, new HttpEntity<>(httpHeaders), RestWrapperDTO.class);
		return responseEntity.getBody();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public RestWrapperDTO<UserDTO> deleteUserRequest(int id) {
		HttpHeaders httpHeaders = getHttpHeaders(user, password);
		
		HashMap<String, Integer> params = new HashMap<String, Integer>();
		params.put("id", id);
		
		ResponseEntity<RestWrapperDTO> responseEntity = restTemplate.exchange(defaultUri + DELETE_USER_REQUEST, HttpMethod.DELETE, new HttpEntity<>(httpHeaders), RestWrapperDTO.class);
		return responseEntity.getBody();
	}
	
}
