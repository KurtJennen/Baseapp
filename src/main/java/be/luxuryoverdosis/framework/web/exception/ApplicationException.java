package be.luxuryoverdosis.framework.web.exception;

public class ApplicationException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private String applicationMessage;
	
	public String getApplicationMessage() {
		return applicationMessage;
	}

	public ApplicationException(final String applicationMessage) {
		super();
		this.applicationMessage = applicationMessage;
	}
}
