package be.luxuryoverdosis.framework.base;

public final class SearchQuery {
	private SearchQuery() {
	}
	
	public static final String FIELD = "FIELD";
	
	public static final String SPACE = " ";
	
	public static final String QUESTION = "?";
	
	public static final String OPEN_BRACKET = "(";
	
	public static final String CLOSE_BRACKET = ")";
	
	public static final String SELECT = "SELECT";
	
	public static final String FROM = "FROM";
	
	public static final String WHERE = "WHERE";
	
	public static final String AND = "AND";
	
	public static final String OR = "OR";
	
	public static final String INNER_JOIN = "INNER JOIN";
	
	public static final String LEFT_OUTER_JOIN = "LEFT OUTER JOIN";
	
	public static final String LIKE = "LIKE";
	
	public static final String IS_NULL = "IS NULL";
	
	public static final String IS_NOT_NULL = "IS NOT NULL";
	
	public static final String PROCENT = "%";
	
	public static final String ZERO = "0";
	
	public static final String ONE = "1";
	
	public static final String MINUS_ONE = "-1";
	
	public static final String[] DEFAULT_OPERATORS = {"=", ">", "<", ">=", "<=", "<>", "%x", "x%", "%x%", "null", "notnull"};
	
	public static final String[] DEFAULT_ADD = {AND, OR};	
	
	public static final String[] DEFAULT_OPEN = {OPEN_BRACKET};
	
	public static final String[] DEFAULT_CLOSE = {CLOSE_BRACKET};
}
