package be.luxuryoverdosis.framework.data.to;

public class QueryParam extends BaseTO {
	private String parameter;
	private String operator;
	private String value;
	private String openBracket;
	private String closeBracket;
	private String addAndOr;
	private Query query;
	
	public String getParameter() {
		return parameter;
	}
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getOpenBracket() {
		return openBracket;
	}
	public void setOpenBracket(String openBracket) {
		this.openBracket = openBracket;
	}
	public String getCloseBracket() {
		return closeBracket;
	}
	public void setCloseBracket(String closeBracket) {
		this.closeBracket = closeBracket;
	}
	public String getAddAndOr() {
		return addAndOr;
	}
	public void setAddAndOr(String addAndOr) {
		this.addAndOr = addAndOr;
	}
	public Query getQuery() {
		return query;
	}
	public void setQuery(Query query) {
		this.query = query;
	}
	
}
