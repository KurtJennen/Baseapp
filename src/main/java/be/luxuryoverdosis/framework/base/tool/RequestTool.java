package be.luxuryoverdosis.framework.base.tool;

import org.apache.commons.lang.StringUtils;

import be.luxuryoverdosis.framework.BaseConstants;
import be.luxuryoverdosis.framework.business.encryption.Encryption;

public final class RequestTool {
	private RequestTool() {
	}

	public static String initializeRequestForWebservice(final String user, final String password) {
		if (StringUtils.isEmpty(user) && StringUtils.isEmpty(password)) {
			return StringUtils.EMPTY;
		}
		
		String[] arrayCredentials = new String[]{user, password};
		String encodedCredentials = Encryption.encode(StringUtils.join(arrayCredentials, BaseConstants.DOUBLEPOINT));
		
		String[] arrayAuthorization = new String[]{BaseConstants.BASIC, encodedCredentials};
		String authorization = StringUtils.join(arrayAuthorization, BaseConstants.SPACE);
		
		return authorization;
	}
	
}
