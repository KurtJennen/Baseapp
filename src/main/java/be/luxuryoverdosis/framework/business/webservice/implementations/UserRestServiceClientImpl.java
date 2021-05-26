package be.luxuryoverdosis.framework.business.webservice.implementations;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import be.luxuryoverdosis.framework.business.webservice.interfaces.UserRestServiceClient;
import be.luxuryoverdosis.framework.data.restwrapperdto.UserRestWrapperDTO;

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
	private static final String READ_USER_REQUEST = USER + "/readUserRequest";
	private static final String READ_ALL_USERS_REQUEST = USER + "/readAllUsersRequest";
	private static final String CREATE_OR_UPDATE_USER_REQUEST = USER + "/createOrUpdateUserRequest";
	private static final String DELETE_USER_REQUEST = USER + "/deleteUserRequest";
	
	public UserRestWrapperDTO readUserRequest(final String name) {
		HttpHeaders httpHeaders = getHttpHeaders(user, password);
		httpHeaders.set("name", name);
		
		ResponseEntity<UserRestWrapperDTO> responseEntity = restTemplate.exchange(defaultUri + READ_USER_REQUEST, HttpMethod.GET, new HttpEntity<>(httpHeaders), UserRestWrapperDTO.class);
		
		return responseEntity.getBody();
	}
	
	public UserRestWrapperDTO readAllUsersRequest() {
		HttpHeaders httpHeaders = getHttpHeaders(user, password);
		ResponseEntity<UserRestWrapperDTO> responseEntity = restTemplate.exchange(defaultUri + READ_ALL_USERS_REQUEST, HttpMethod.GET, new HttpEntity<>(httpHeaders), UserRestWrapperDTO.class);
		return responseEntity.getBody();
	}
	
	public UserRestWrapperDTO createOrUpdateUserRequest(String name, String userName, String encryptedPassword, String email, String roleNames) {
		HttpHeaders httpHeaders = getHttpHeaders(user, password);
		httpHeaders.set("name", name);
		httpHeaders.set("userName", userName);
		httpHeaders.set("encryptedPassword", encryptedPassword);
		httpHeaders.set("email", email);
		httpHeaders.set("roleNames", roleNames);
		ResponseEntity<UserRestWrapperDTO> responseEntity = restTemplate.exchange(defaultUri + CREATE_OR_UPDATE_USER_REQUEST, HttpMethod.POST, new HttpEntity<>(httpHeaders), UserRestWrapperDTO.class);
		return responseEntity.getBody();
	}
	
	public UserRestWrapperDTO deleteUserRequest(String name) {
		HttpHeaders httpHeaders = getHttpHeaders(user, password);
		httpHeaders.set("name", name);
		ResponseEntity<UserRestWrapperDTO> responseEntity = restTemplate.exchange(defaultUri + DELETE_USER_REQUEST, HttpMethod.DELETE, new HttpEntity<>(httpHeaders), UserRestWrapperDTO.class);
		return responseEntity.getBody();
	}
	
}
