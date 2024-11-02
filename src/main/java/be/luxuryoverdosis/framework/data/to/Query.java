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
@Table(name = "base_query")
@Access(AccessType.FIELD)
@NamedQueries({
		@NamedQuery(name = Query.SELECT_QUERIES_BY_NAME_AND_TYPE_AND_USER, query = Query.Queries.SELECT_QUERIES_BY_NAME_AND_TYPE_AND_USER),
		@NamedQuery(name = Query.SELECT_QUERIES_BY_TYPE_AND_USER, query = Query.Queries.SELECT_QUERIES_BY_TYPE_AND_USER),
		@NamedQuery(name = Query.COUNT_QUERIES_BY_NAME_AND_TYPE_AND_USER, query = Query.Queries.COUNT_QUERIES_BY_NAME_AND_TYPE_AND_USER), })
@Proxy(lazy = false)
public class Query extends BaseTO {
	public static final String SELECT_QUERIES_BY_NAME_AND_TYPE_AND_USER = "selectQueriesByNameAndTypeAndUser";
	public static final String SELECT_QUERIES_BY_TYPE_AND_USER = "selectQueriesByTypeAndUser";
	public static final String COUNT_QUERIES_BY_NAME_AND_TYPE_AND_USER = "countQueriesByNameAndTypeAndUser";

	@Column(name = "Name")
	private String name;

	@Column(name = "Type")
	private String type;

	@Column(name = "Complex")
	private String complex;

	@ManyToOne
	@JoinColumn(name = "User_Id")
	private User user;

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

	public User getUser() {
		return user;
	}

	public void setUser(final User user) {
		this.user = user;
	}

	public static final class Queries {
		public static final String SELECT_QUERIES_BY_NAME_AND_TYPE_AND_USER = "from Query q " + "where q.name = :name "
				+ "and q.type = :type " + "and q.user.id = :userId";

		public static final String SELECT_QUERIES_BY_TYPE_AND_USER = "from Query q " + "where q.type = :type "
				+ "and q.user.id = :userId";

		public static final String COUNT_QUERIES_BY_NAME_AND_TYPE_AND_USER = "select count(*) " + "from Query q "
				+ "where q.name = :name " + "and q.type = :type " + "and q.user.id = :userId";
	}
}
