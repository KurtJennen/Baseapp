package be.luxuryoverdosis.framework.data.dto;

public class KeycloakDTO extends BaseDTO {
	private String defaultUri;
	private String configuration;
	private String result;
	
	private String authentication;
	private String clientId;
	private String responseType;
	private String redirectUri;
	private String scope;
	
	private String token;
	private String grantType;
	private String tokenRedirectUri;
	private String code;
	private String idToken;
	
	private String refreshToken;
	
	private String userinfo;
	
	public KeycloakDTO() {
		super();
	}

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
	public String getResult() {
		return result;
	}
	public void setResult(final String result) {
		this.result = result;
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
	public String getCode() {
		return code;
	}
	public void setCode(final String code) {
		this.code = code;
	}
	public String getIdToken() {
		return idToken;
	}
	public void setIdToken(final String idToken) {
		this.idToken = idToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(final String refreshToken) {
		this.refreshToken = refreshToken;
	}
	
	public String getUserinfo() {
		return userinfo;
	}
	public void setUserinfo(final String userinfo) {
		this.userinfo = userinfo;
	}
}
