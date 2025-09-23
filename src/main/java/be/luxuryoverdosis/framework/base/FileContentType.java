package be.luxuryoverdosis.framework.base;

import org.springframework.http.MediaType;

public class FileContentType {
	public static final String CHARSET = "CHARSET";

	public static final String APPLICATION_VND_OASIS_OPENDOCUMENT_TEXT = "application/vnd.oasis.opendocument.text";
	
	public static final String REST_RESPONSE_JSON_UTF8 = MediaType.APPLICATION_JSON_VALUE + ";" + FileContentType.CHARSET + "=" + Encoding.UTF_8;
}
