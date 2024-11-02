package be.luxuryoverdosis.framework.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenPayloadDTO extends BaseDTO {
	private String exp;
	private String iat;
	@JsonProperty(value = "auth_time")
	private String authTime;
	private String jti;
	private String iss;
	private String aud;
	private String sub;
	private String typ;
	private String azp;
	@JsonProperty(value = "session_state")
	private String sessionState;
	@JsonProperty(value = "at_hash")
	private String atHash;
	private String acr;
	private String sid;
	@JsonProperty(value = "email_verified")
	private String emailVerified;
	@JsonProperty(value = "realm_access")
	private Object realmAccess;
	private String name;
	@JsonProperty(value = "preferred_username")
	private String preferredUsername;
	private String myotherclaim;
	@JsonProperty(value = "given_name")
	private String givenName;
	@JsonProperty(value = "family_name")
	private String familyName;
	private String email;
	private String picture;
	
	private String myattribute;
	
	public TokenPayloadDTO() {
		super();
	}

	public String getExp() {
		return exp;
	}
	public void setExp(final String exp) {
		this.exp = exp;
	}
	public String getIat() {
		return iat;
	}
	public void setIat(final String iat) {
		this.iat = iat;
	}
	public String getAuthTime() {
		return authTime;
	}
	public void setAuthTime(final String authTime) {
		this.authTime = authTime;
	}
	public String getJti() {
		return jti;
	}
	public void setJti(final String jti) {
		this.jti = jti;
	}
	public String getIss() {
		return iss;
	}
	public void setIss(final String iss) {
		this.iss = iss;
	}
	public String getAud() {
		return aud;
	}
	public void setAud(final String aud) {
		this.aud = aud;
	}
	public String getSub() {
		return sub;
	}
	public void setSub(final String sub) {
		this.sub = sub;
	}
	public String getTyp() {
		return typ;
	}
	public void setTyp(final String typ) {
		this.typ = typ;
	}
	public String getAzp() {
		return azp;
	}
	public void setAzp(final String azp) {
		this.azp = azp;
	}
	public String getSessionState() {
		return sessionState;
	}
	public void setSessionState(final String sessionState) {
		this.sessionState = sessionState;
	}
	public String getAtHash() {
		return atHash;
	}
	public void setAtHash(final String atHash) {
		this.atHash = atHash;
	}
	public String getAcr() {
		return acr;
	}
	public void setAcr(final String acr) {
		this.acr = acr;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(final String sid) {
		this.sid = sid;
	}
	public String getEmailVerified() {
		return emailVerified;
	}
	public void setEmailVerified(final String emailVerified) {
		this.emailVerified = emailVerified;
	}
	public Object getRealmAccess() {
		return realmAccess;
	}
	public void setRealmAccess(final Object realmAccess) {
		this.realmAccess = realmAccess;
	}
	public String getName() {
		return name;
	}
	public void setName(final String name) {
		this.name = name;
	}
	public String getPreferredUsername() {
		return preferredUsername;
	}
	public void setPreferredUsername(final String preferredUsername) {
		this.preferredUsername = preferredUsername;
	}
	public String getMyotherclaim() {
		return myotherclaim;
	}
	public void setMyotherclaim(final String myotherclaim) {
		this.myotherclaim = myotherclaim;
	}
	public String getGivenName() {
		return givenName;
	}
	public void setGivenName(final String givenName) {
		this.givenName = givenName;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(final String familyName) {
		this.familyName = familyName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(final String email) {
		this.email = email;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(final String picture) {
		this.picture = picture;
	}

	public String getMyattribute() {
		return myattribute;
	}
	public void setMyattribute(final String myattribute) {
		this.myattribute = myattribute;
	}
}
