package be.luxuryoverdosis.framework.web.exception;

import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;

public class ServiceException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private String key;
	private String[] values;
	
	public String getKey() {
		return key;
	}
	public String[] getValues() {
		return values;
	}
	
	public ServiceException(final String key) {
		super();
		this.key = key;
		this.values = null;
	}

	public ServiceException(final String key, final String[] values) {
		super();
		this.key = key;
		this.values = values;
	}
	
	public String getMessage() {
		if (values == null) {
			return BaseSpringServiceLocator.getMessage(key);
		} else {
			String[] translatedValues = new String[values.length];
			for (int i = 0; i < values.length; i++) {
				translatedValues[i] = BaseSpringServiceLocator.getMessage(values[i]);
			}
			
			return BaseSpringServiceLocator.getMessage(key, translatedValues);
		}
	}
}
