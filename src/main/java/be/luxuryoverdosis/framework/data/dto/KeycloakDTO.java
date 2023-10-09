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
	
	public KeycloakDTO() {
		super();
	}

	public String getDefaultUri() {
		return defaultUri;
	}
	public void setDefaultUri(String defaultUri) {
		this.defaultUri = defaultUri;
	}
	public String getConfiguration() {
		return configuration;
	}
	public void setConfiguration(String configuration) {
		this.configuration = configuration;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}

	public String getAuthentication() {
		return authentication;
	}
	public void setAuthentication(String authentication) {
		this.authentication = authentication;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getResponseType() {
		return responseType;
	}
	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}
	public String getRedirectUri() {
		return redirectUri;
	}
	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	
}