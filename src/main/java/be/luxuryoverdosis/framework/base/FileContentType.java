package be.luxuryoverdosis.framework.base;

public final class FileContentType {
	private FileContentType() {
	}
	
	public static final String CHARSET = "CHARSET";

	public static final String APPLICATION_JSON = "application/json";
	public static final String APPLICATION_PDF = "application/pdf";
	public static final String APPLICATION_VND_OASIS_OPENDOCUMENT_TEXT = "application/vnd.oasis.opendocument.text";
	public static final String TEXT_PLAIN = "text/plain";
	
	public static final String REST_RESPONSE_JSON_UTF8 = FileContentType.APPLICATION_JSON + ";" + FileContentType.CHARSET + "=" + Encoding.UTF_8;
}
