package be.luxuryoverdosis.framework.data.dto;

public class QueryDTO extends BaseDTO {
	private String name;
	private String type;
	private String complex;
	private String[] names;
	private String[] operators;
	private String[] values;
	private String[] addAndOrs;
	private String[] openBrackets;
	private String[] closeBrackets;
	
	public String getName() {
		return name;
	}
	public void setName(final String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(final String type) {
		this.type = type;
	}
	public String getComplex() {
		return complex;
	}
	public void setComplex(final String complex) {
		this.complex = complex;
	}
	public String[] getNames() {
		return names;
	}
	public void setNames(final String[] names) {
		this.names = names;
	}
	public String[] getOperators() {
		return operators;
	}
	public void setOperators(final String[] operators) {
		this.operators = operators;
	}
	public String[] getValues() {
		return values;
	}
	public void setValues(final String[] values) {
		this.values = values;
	}
	public String[] getAddAndOrs() {
		return addAndOrs;
	}
	public void setAddAndOrs(final String[] addAndOrs) {
		this.addAndOrs = addAndOrs;
	}
	public String[] getOpenBrackets() {
		return openBrackets;
	}
	public void setOpenBrackets(final String[] openBrackets) {
		this.openBrackets = openBrackets;
	}
	public String[] getCloseBrackets() {
		return closeBrackets;
	}
	public void setCloseBrackets(final String[] closeBrackets) {
		this.closeBrackets = closeBrackets;
	}
	
}
