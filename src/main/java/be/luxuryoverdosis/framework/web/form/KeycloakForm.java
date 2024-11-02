package be.luxuryoverdosis.framework.web.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import be.luxuryoverdosis.framework.logging.Logging;
import be.luxuryoverdosis.framework.web.BaseWebConstants;

public class KeycloakForm extends BaseForm {
	private static final long serialVersionUID = 1L;
	
	private String defaultUri;
	private String configuration;
	private String configurationResult;
	
	private String authentication;
	private String clientId;
	private String responseType;
	private String redirectUri;
	private String scope;
	private String authenticationResult;
	
	private String token;
	private String grantType;
	private String tokenRedirectUri;
	private String tokenResult;
	
	private String refreshToken;
	private String idToken;
	private String idTokenHeader;
	private String idTokenPayload;
	private String idTokenSignature;
	
	private String refreshResult;
	
	private String userinfo;
	private String userinfoResult;
	
	public String getDefaultUri() {
		return defaultUri;
	}
	public void setDefaultUri(final String defaultUri) {
		this.defaultUri = defaultUri;
	}
	public String getConfiguration() {
		return configuration;
	}
	public void setConfiguration(final String configuration) {
		this.configuration = configuration;
	}
	public String getConfigurationResult() {
		return configurationResult;
	}
	public void setConfigurationResult(final String configurationResult) {
		this.configurationResult = configurationResult;
	}
	
	public String getAuthentication() {
		return authentication;
	}
	public void setAuthentication(final String authentication) {
		this.authentication = authentication;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(final String clientId) {
		this.clientId = clientId;
	}
	public String getResponseType() {
		return responseType;
	}
	public void setResponseType(final String responseType) {
		this.responseType = responseType;
	}
	public String getRedirectUri() {
		return redirectUri;
	}
	public void setRedirectUri(final String redirectUri) {
		this.redirectUri = redirectUri;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(final String scope) {
		this.scope = scope;
	}
	public String getAuthenticationResult() {
		return authenticationResult;
	}
	public void setAuthenticationResult(final String authenticationResult) {
		this.authenticationResult = authenticationResult;
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(final String token) {
		this.token = token;
	}
	public String getGrantType() {
		return grantType;
	}
	public void setGrantType(final String grantType) {
		this.grantType = grantType;
	}
	public String getTokenRedirectUri() {
		return tokenRedirectUri;
	}
	public void setTokenRedirectUri(final String tokenRedirectUri) {
		this.tokenRedirectUri = tokenRedirectUri;
	}
	public String getTokenResult() {
		return tokenResult;
	}
	public void setTokenResult(final String tokenResult) {
		this.tokenResult = tokenResult;
	}
	
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(final String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public String getIdToken() {
		return idToken;
	}
	public void setIdToken(final String idToken) {
		this.idToken = idToken;
	}
	public String getIdTokenHeader() {
		return idTokenHeader;
	}
	public void setIdTokenHeader(final String idTokenHeader) {
		this.idTokenHeader = idTokenHeader;
	}
	public String getIdTokenPayload() {
		return idTokenPayload;
	}
	public void setIdTokenPayload(final String idTokenPayload) {
		this.idTokenPayload = idTokenPayload;
	}
	public String getIdTokenSignature() {
		return idTokenSignature;
	}
	public void setIdTokenSignature(final String idTokenSignature) {
		this.idTokenSignature = idTokenSignature;
	}
	
	public String getRefreshResult() {
		return refreshResult;
	}
	public void setRefreshResult(final String refreshResult) {
		this.refreshResult = refreshResult;
	}
	
	public String getUserinfo() {
		return userinfo;
	}
	public void setUserinfo(final String userinfo) {
		this.userinfo = userinfo;
	}
	public String getUserinfoResult() {
		return userinfoResult;
	}
	public void setUserinfoResult(final String userinfoResult) {
		this.userinfoResult = userinfoResult;
	}
	
	public void reset(final ActionMapping mapping, final HttpServletRequest request) {
		super.reset(mapping, request);
		setDefaultUri("http://localhost:8888/realms/myrealm");
		setConfiguration("/.well-known/openid-configuration");
		
		setAuthentication("/protocol/openid-connect/auth");
		setClientId("baseapp");
		setResponseType("code");
		setRedirectUri("http://localhost:8080/Baseapp/keycloak.do?method=afterAuthentication");
		setScope("openid");
		
		setToken("/protocol/openid-connect/token");
		setGrantType("authorization_code");
		setTokenRedirectUri("http://localhost:8080/Baseapp/keycloak.do?method=afterAuthentication");
		
		setUserinfo("/protocol/openid-connect/userinfo");
	}
	
	public ActionErrors validate(final ActionMapping mapping, final HttpServletRequest request) {
		Logging.info(this, "Begin Validating");
		
		ActionErrors errors = new ActionErrors();
		
		if (this.getMethod().equals(BaseWebConstants.CONFIGURATION)) {
			errors = super.validate(mapping, request);
		}
		
		if (errors.size() > 0) {
			request.setAttribute(BaseWebConstants.ERROR, 1);
		}
			
		Logging.info(this, "End Validating");
		
		return errors;
	}
}
