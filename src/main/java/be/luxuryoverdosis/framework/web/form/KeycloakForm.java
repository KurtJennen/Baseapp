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
	private String result;
	
	private String authentication;
	private String clientId;
	private String responseType;
	private String redirectUri;
	private String scope;
	
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
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		setDefaultUri("http://localhost:8888/realms/myrealm");
		setConfiguration("/.well-known/openid-configuration");
		
		setAuthentication("/protocol/openid-connect/auth");
		setClientId("oidc-playground");
		setResponseType("code");
		setRedirectUri("http://localhost:8080/");
		setScope("openid");
	}
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		Logging.info(this, "Begin Validating");
		
		ActionErrors errors = new ActionErrors();
		
		if(this.getMethod().equals(BaseWebConstants.CONFIGURATION)) {
			errors = super.validate(mapping, request);
		}
		
		if(errors.size() > 0) {
			request.setAttribute(BaseWebConstants.ERROR, 1);
		}
			
		Logging.info(this, "End Validating");
		
		return errors;
	}
}
