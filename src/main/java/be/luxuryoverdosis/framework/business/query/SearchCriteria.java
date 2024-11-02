package be.luxuryoverdosis.framework.business.query;

import be.luxuryoverdosis.framework.base.SearchQuery;

public class SearchCriteria {
	private String[] operators;
	private String[] names;
	private String[] values;
	private String[] openBrackets;
	private String[] closeBrackets;
	private String[] addAndOrs;
	private String complexQuery = SearchQuery.ZERO;
	private String addOnSelect = "";
	
	public String[] getOperators() {
		return operators;
	}
	public void setOperators(final String[] operators) {
		this.operators = operators;
	}
	public String[] getNames() {
		return names;
	}
	public void setNames(final String[] names) {
		this.names = names;
	}
	public String[] getValues() {
		return values;
	}
	public void setValues(final String[] values) {
		this.values = values;
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
	public String[] getAddAndOrs() {
		return addAndOrs;
	}
	public void setAddAndOrs(final String[] addAndOrs) {
		this.addAndOrs = addAndOrs;
	}
	public String getComplexQuery() {
		return complexQuery;
	}
	public void setComplexQuery(final String complexQuery) {
		this.complexQuery = complexQuery;
	}
	public String getAddOnSelect() {
		return addOnSelect;
	}
	public void setAddOnSelect(final String addOnSelect) {
		this.addOnSelect = addOnSelect;
	}
	
}
