package be.luxuryoverdosis.framework.data.dto;

public class NumberDTO extends BaseDTO {
	private String applicationCode;
	private String year;
	private int number;
	private String type;
	
	public String getApplicationCode() {
		return applicationCode;
	}
	public void setApplicationCode(final String applicationCode) {
		this.applicationCode = applicationCode;
	}
	public String getYear() {
		return year;
	}
	public void setYear(final String year) {
		this.year = year;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(final int number) {
		this.number = number;
	}
	public String getType() {
		return type;
	}
	public void setType(final String type) {
		this.type = type;
	}
	
}
