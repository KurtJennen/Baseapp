package be.luxuryoverdosis.framework.data.to;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name="base_query_param")
@Access(AccessType.FIELD)
@NamedQueries({
	@NamedQuery(name=QueryParam.SELECT_QUERYPARAMS_BY_QUERY, query=QueryParam.Queries.SELECT_QUERYPARAMS_BY_QUERY),
	@NamedQuery(name=QueryParam.DELETE_QUERYPARAMS_BY_QUERY, query=QueryParam.Queries.DELETE_QUERYPARAMS_BY_QUERY),
	@NamedQuery(name=QueryParam.COUNT_QUERYPARAMS_BY_QUERY, query=QueryParam.Queries.COUNT_QUERYPARAMS_BY_QUERY),
})
@Proxy(lazy=false)
public class QueryParam extends BaseTO {
	public static final String SELECT_QUERYPARAMS_BY_QUERY = "selectQueryParamsByQuery";
	public static final String DELETE_QUERYPARAMS_BY_QUERY = "deleteQueryParamsByQuery";
	public static final String COUNT_QUERYPARAMS_BY_QUERY = "countQueryParamsByQuery";
	
	@Column(name="Name")
	private String name;

	@Column(name="Operator")
	private String operator;

	@Column(name="Value")
	private String value;
	
	@Column(name="OpenBracket")
	private String openBracket;
	
	@Column(name="CloseBracket")
	private String closeBracket;
	
	@Column(name="AddAndOr")
	private String addAndOr;
	
	@ManyToOne
	@JoinColumn(name="Query_Id")
	private Query query;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
	public static final class Queries {
		public static final String SELECT_QUERYPARAMS_BY_QUERY = "from QueryParam qp "
				+ "where qp.query.id = :queryId";
		
		public static final String DELETE_QUERYPARAMS_BY_QUERY = "delete QueryParam qp "
				+ "where qp.query.id = :queryId";
		
		public static final String COUNT_QUERYPARAMS_BY_QUERY = "select count(*) "
				+ "from QueryParam qp "
				+ "where qp.query.id = :queryId";
	}
	
}
