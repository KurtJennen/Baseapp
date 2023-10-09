package be.luxuryoverdosis.framework.web.action.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;
import be.luxuryoverdosis.framework.business.webservice.interfaces.KeycloackRestServiceClient;
import be.luxuryoverdosis.framework.data.dto.KeycloakDTO;
import be.luxuryoverdosis.framework.logging.Logging;
import be.luxuryoverdosis.framework.web.action.ajaxaction.AjaxAction;
import be.luxuryoverdosis.framework.web.form.KeycloakForm;

public class KeycloakAction extends AjaxAction {
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin List");
		Logging.info(this, "End List Success");
		
		return mapping.getInputForward();
	}
	
	public ActionForward configuration(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin List");
		
		KeycloakForm keycloakForm = (KeycloakForm) form;
		
		KeycloakDTO keycloakDTO = new KeycloakDTO();
		keycloakDTO.setDefaultUri(keycloakForm.getDefaultUri());
		keycloakDTO.setConfiguration(keycloakForm.getConfiguration());
		
		keycloakForm.setResult(getKeycloackRestServiceClient().configuration(keycloakDTO));
		
		Logging.info(this, "End List Success");
		
		return mapping.getInputForward();
	}
	
	public ActionForward authentication(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin List");
		
		KeycloakForm keycloakForm = (KeycloakForm) form;
		
		KeycloakDTO keycloakDTO = new KeycloakDTO();
		keycloakDTO.setDefaultUri(keycloakForm.getDefaultUri());
		keycloakDTO.setAuthentication(keycloakForm.getAuthentication());
		keycloakDTO.setClientId(keycloakForm.getClientId());
		keycloakDTO.setResponseType(keycloakForm.getResponseType());
		keycloakDTO.setRedirectUri(keycloakForm.getRedirectUri());
		keycloakDTO.setScope(keycloakForm.getScope());
		
		keycloakForm.setResult(getKeycloackRestServiceClient().authentication(keycloakDTO));
		
		Logging.info(this, "End List Success");
		
		return mapping.getInputForward();
	}
	
	private KeycloackRestServiceClient getKeycloackRestServiceClient() {
		return BaseSpringServiceLocator.getBean(KeycloackRestServiceClient.class);
	}
}
