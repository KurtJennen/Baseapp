package be.luxuryoverdosis.framework.business.webservice.implementations;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import be.luxuryoverdosis.framework.base.tool.RequestTool;

public class BaseRestServiceClient {
	public HttpHeaders getHttpHeaders(String user, String password) {
		String authorization = RequestTool.initializeRequestForWebservice(user, password);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		if(StringUtils.isNotEmpty(authorization)) {
			httpHeaders.set(HttpHeaders.AUTHORIZATION, authorization);
		}
		httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		
		return httpHeaders;
	}
	
}
