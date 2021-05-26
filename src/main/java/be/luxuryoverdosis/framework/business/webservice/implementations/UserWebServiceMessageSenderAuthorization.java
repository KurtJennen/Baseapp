package be.luxuryoverdosis.framework.business.webservice.implementations;

import java.io.IOException;
import java.net.HttpURLConnection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ws.transport.http.HttpUrlConnectionMessageSender;

import be.luxuryoverdosis.framework.BaseConstants;
import be.luxuryoverdosis.framework.base.tool.RequestTool;

@Service
public class UserWebServiceMessageSenderAuthorization extends HttpUrlConnectionMessageSender {
	@Value("${ws.user.user}")
	private String user;
	@Value("${ws.user.password}")
	private String password;

	@Override
	protected void prepareConnection(HttpURLConnection connection) throws IOException {
		String authorization = RequestTool.initializeRequestForWebservice(user, password);
		
		connection.setRequestProperty(BaseConstants.AUTHORIZATION, authorization);
		
		super.prepareConnection(connection);
	}

}
