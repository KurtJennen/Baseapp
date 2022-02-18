package be.luxuryoverdosis.framework.data.dto;

public class SearchDTO extends BaseDTO {
	private Object object;
	private String parameterName;
	
	public SearchDTO() {
		super();
	}

	public SearchDTO(Object object, String parameterName) {
		super();
		this.object = object;
		this.parameterName = parameterName;
	}

	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	public String getParameterName() {
		return parameterName;
	}
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	
}