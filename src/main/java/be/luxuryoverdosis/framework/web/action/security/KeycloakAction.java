package be.luxuryoverdosis.framework.web.action.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;
import be.luxuryoverdosis.framework.business.webservice.interfaces.KeycloackRestServiceClient;
import be.luxuryoverdosis.framework.data.dto.KeycloakDTO;
import be.luxuryoverdosis.framework.data.dto.TokenDTO;
import be.luxuryoverdosis.framework.logging.Logging;
import be.luxuryoverdosis.framework.web.action.ajaxaction.AjaxAction;
import be.luxuryoverdosis.framework.web.form.KeycloakForm;

public class KeycloakAction extends AjaxAction {
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin List");
		
		KeycloakForm keycloakForm = (KeycloakForm) form;
		
		fillDTO(keycloakForm);
		
		Logging.info(this, "End List Success");
		
		return mapping.getInputForward();
	}
	
	public ActionForward configuration(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Configuration");
		
		KeycloakForm keycloakForm = (KeycloakForm) form;
		
		KeycloakDTO keycloakDTO = new KeycloakDTO();
		keycloakDTO.setDefaultUri(keycloakForm.getDefaultUri());
		keycloakDTO.setConfiguration(keycloakForm.getConfiguration());
		
		keycloakForm.setConfigurationResult(getKeycloackRestServiceClient().configuration(keycloakDTO));
		
		Logging.info(this, "End Configuration Success");
		
		return mapping.getInputForward();
	}
	
	public ActionForward authentication(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Authentication");
		
		KeycloakForm keycloakForm = (KeycloakForm) form;
		
		KeycloakDTO keycloakDTO = fillDTO(keycloakForm);
		
		Logging.info(this, "End Authentication Success");
		
		return new ActionForward(getKeycloackRestServiceClient().authenticationUrl(keycloakDTO), true);
	}
	
	public ActionForward afterAuthentication(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin AfterAuthentication");
		
		KeycloakForm keycloakForm = (KeycloakForm) form;
		
		keycloakForm.setAuthenticationResult(request.getParameter("code"));
		
		Logging.info(this, "End AfterAuthentication Success");
		
		return mapping.getInputForward();
	}
	
	public ActionForward token(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Token");
		
		KeycloakForm keycloakForm = (KeycloakForm) form;
		
		TokenDTO tokenDTO = getKeycloackRestServiceClient().tokenUrl(fillDTO(keycloakForm));
		
		keycloakForm.setTokenResult(tokenDTO.getTokenResult());
		keycloakForm.setRefreshToken(tokenDTO.getRefresh_token());
		keycloakForm.setIdToken(tokenDTO.getId_token());
		keycloakForm.setIdTokenHeader(tokenDTO.getIdTokenHeader());
		keycloakForm.setIdTokenPayload(tokenDTO.getIdTokenPayload());
		keycloakForm.setIdTokenSignature(tokenDTO.getIdTokenSignature());
		
		Logging.info(this, "End Token Success");
		
		return mapping.getInputForward();
	}
	
	public ActionForward refresh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Refresh");
		
		KeycloakForm keycloakForm = (KeycloakForm) form;
		
		TokenDTO tokenDTO = getKeycloackRestServiceClient().refresh(fillDTO(keycloakForm));
		
		keycloakForm.setRefreshResult(tokenDTO.getTokenResult());
		
		Logging.info(this, "End Refresh Success");
		
		return mapping.getInputForward();
	}
	
	public ActionForward userinfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Userinfo");
		
		KeycloakForm keycloakForm = (KeycloakForm) form;
		
		TokenDTO tokenDTO = getKeycloackRestServiceClient().userinfo(fillDTO(keycloakForm));
		
		keycloakForm.setUserinfoResult(tokenDTO.getTokenResult());
		
		Logging.info(this, "End Userinfo Success");
		
		return mapping.getInputForward();
	}
	
	private KeycloakDTO fillDTO(KeycloakForm keycloakForm) throws JsonParseException, JsonMappingException, IOException {
		KeycloakDTO keycloakDTO = new KeycloakDTO();
		keycloakDTO.setDefaultUri(keycloakForm.getDefaultUri());
		
		keycloakDTO.setAuthentication(keycloakForm.getAuthentication());
		keycloakDTO.setClientId(keycloakForm.getClientId());
		keycloakDTO.setResponseType(keycloakForm.getResponseType());
		keycloakDTO.setRedirectUri(keycloakForm.getRedirectUri());
		keycloakDTO.setScope(keycloakForm.getScope());
		
		keycloakDTO.setToken(keycloakForm.getToken());
		keycloakDTO.setGrantType(keycloakForm.getGrantType());
		keycloakDTO.setCode(keycloakForm.getAuthenticationResult());
		keycloakDTO.setTokenRedirectUri(keycloakForm.getTokenRedirectUri());
		keycloakDTO.setIdToken(keycloakForm.getIdToken());
		
		keycloakDTO.setRefreshToken(keycloakForm.getRefreshToken());
		
		return keycloakDTO;
	}
	
	private KeycloackRestServiceClient getKeycloackRestServiceClient() {
		return BaseSpringServiceLocator.getBean(KeycloackRestServiceClient.class);
	}
}
