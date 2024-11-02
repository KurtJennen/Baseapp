package be.luxuryoverdosis.framework.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenDTO extends BaseDTO {
	@JsonProperty(value = "access_token")
	private String accessToken;
	@JsonProperty(value = "expires_in")
	private String expiresIn;
	@JsonProperty(value = "refresh_expires_in")
	private String refreshExpiresIn;
	@JsonProperty(value = "refresh_token")
	private String refreshToken;
	@JsonProperty(value = "token_type")
	private String tokenType;
	@JsonProperty(value = "id_token")
	private String idToken;
	@JsonProperty(value = "session_state")
	private String sessionState;
	@JsonProperty(value = "scope")
	private String scope;
	@JsonProperty(value = "not-before-policy")
	private String notBeforePolicy;
	
	private String tokenResult;
	private String idTokenHeader;
	private String idTokenPayload;
	private String idTokenSignature;
	
	
	public TokenDTO() {
		super();
	}

	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(final String accessToken) {
		this.accessToken = accessToken;
	}
	public String getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(final String expiresIn) {
		this.expiresIn = expiresIn;
	}
	public String getRefreshExpiresIn() {
		return refreshExpiresIn;
	}
	public void setRefreshExpiresIn(final String refreshExpiresIn) {
		this.refreshExpiresIn = refreshExpiresIn;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(final String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public String getTokenType() {
		return tokenType;
	}
	public void setTokenType(final String tokenType) {
		this.tokenType = tokenType;
	}
	public String getIdToken() {
		return idToken;
	}
	public void setIdToken(final String idToken) {
		this.idToken = idToken;
	}
	public String getSessionState() {
		return sessionState;
	}
	public void setSessionState(final String sessionState) {
		this.sessionState = sessionState;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(final String scope) {
		this.scope = scope;
	}
	public String getNotBeforePolicy() {
		return notBeforePolicy;
	}
	public void setNotBeforePolicy(final String notBeforePolicy) {
		this.notBeforePolicy = notBeforePolicy;
	}

	public String getTokenResult() {
		return tokenResult;
	}
	public void setTokenResult(final String tokenResult) {
		this.tokenResult = tokenResult;
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
}
