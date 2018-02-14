package be.luxuryoverdosis.framework.business.query;

import be.luxuryoverdosis.framework.base.Query;

public class SearchCriteria {
	private String[] operators;
	private String[] parameters;
	private String[] values;
	private String[] openBrackets;
	private String[] closeBrackets;
	private String[] addAndOrs;
	private String complexQuery = Query.ZERO;
	private String addOnSelect = "";
	
	public String[] getOperators() {
		return operators;
	}
	public void setOperators(String[] operators) {
		this.operators = operators;
	}
	public String[] getParameters() {
		return parameters;
	}
	public void setParameters(String[] parameters) {
		this.parameters = parameters;
	}
	public String[] getValues() {
		return values;
	}
	public void setValues(String[] values) {
		this.values = values;
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
	public String[] getAddAndOrs() {
		return addAndOrs;
	}
	public void setAddAndOrs(String[] addAndOrs) {
		this.addAndOrs = addAndOrs;
	}
	public String getComplexQuery() {
		return complexQuery;
	}
	public void setComplexQuery(String complexQuery) {
		this.complexQuery = complexQuery;
	}
	public String getAddOnSelect() {
		return addOnSelect;
	}
	public void setAddOnSelect(String addOnSelect) {
		this.addOnSelect = addOnSelect;
	}
	
}
