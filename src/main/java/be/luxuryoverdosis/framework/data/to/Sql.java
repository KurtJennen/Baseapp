package be.luxuryoverdosis.framework.data.to;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "base_sql")
@Access(AccessType.FIELD)
@NamedQueries({
	@NamedQuery(name = Sql.SELECT_SQLS, query = Sql.Queries.SELECT_SQLS),
	@NamedQuery(name = Sql.SELECT_SQLS_BY_NAME, query = Sql.Queries.SELECT_SQLS_BY_NAME),
	@NamedQuery(name = Sql.COUNT_SQLS_BY_NAME, query = Sql.Queries.COUNT_SQLS_BY_NAME),
	@NamedQuery(name = Sql.COUNT_SQLS_BY_NAME_AND_APPLICATION, query = Sql.Queries.COUNT_SQLS_BY_NAME_AND_APPLICATION)
})
public class Sql extends BaseTO {
	public static final String SELECT_SQLS = "selectSqls";
	public static final String SELECT_SQLS_BY_NAME = "selectSqlsByName";
	public static final String COUNT_SQLS_BY_NAME = "countSqlsByName";
	public static final String COUNT_SQLS_BY_NAME_AND_APPLICATION = "countSqlsByNameAndApplication";
	
	public static final String COUNT_BASE_SQL = "select count(*) from base_sql where name = ? and app = ?";
	public static final String INSERT_BASE_SQL = "insert into base_sql (Version, UserAdd, UserUpdate, DateAdd, DateUpdate, Name, Content, App) values (0, 'root', 'root', now(), now(), ?, ?, ?)";
	
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "Content")
	private String content;
	
	@Column(name = "App")
	private String application;
	
	public String getName() {
		return name;
	}
	public void setName(final String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(final String content) {
		this.content = content;
	}
	public String getApplication() {
		return application;
	}
	public void setApplication(final String application) {
		this.application = application;
	}
					
	public static final class Queries {
		public static final String SELECT_SQLS = "from Sql s";
		
		public static final String SELECT_SQLS_BY_NAME = "from Sql s "
				+ "where s.name = :name";
		
		public static final String COUNT_SQLS_BY_NAME = "select count(*) "
				+ "from Sql s "
				+ "where s.name = :name";
		
		public static final String COUNT_SQLS_BY_NAME_AND_APPLICATION = "select count(*) "
				+ "from Sql s "
				+ "where s.name = :name "
				+ "and s.application = :application";
	}
}
