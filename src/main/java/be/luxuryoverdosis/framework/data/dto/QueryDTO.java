package be.luxuryoverdosis.framework.data.dto;

public class QueryDTO extends BaseDTO {
	private String name;
	private String type;
	public String complex;
	public String[] parameters;
	public String[] operators;
	public String[] values;
	public String[] addAndOrs;
	public String[] openBrackets;
	public String[] closeBrackets;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getComplex() {
		return complex;
	}
	public void setComplex(String complex) {
		this.complex = complex;
	}
	public String[] getParameters() {
		return parameters;
	}
	public void setParameters(String[] parameters) {
		this.parameters = parameters;
	}
	public String[] getOperators() {
		return operators;
	}
	public void setOperators(String[] operators) {
		this.operators = operators;
	}
	public String[] getValues() {
		return values;
	}
	public void setValues(String[] values) {
		this.values = values;
	}
	public String[] getAddAndOrs() {
		return addAndOrs;
	}
	public void setAddAndOrs(String[] addAndOrs) {
		this.addAndOrs = addAndOrs;
	}
	public String[] getOpenBrackets() {
		return openBrackets;
	}
	public void setOpenBrackets(String[] openBrackets) {
		this.openBrackets = openBrackets;
	}
	public String[] getCloseBrackets() {
		return closeBrackets;
	}
	public void setCloseBrackets(String[] closeBrackets) {
		this.closeBrackets = closeBrackets;
	}
	
}
