package be.luxuryoverdosis.framework.data.dao.implementations;

import java.util.ArrayList;

import org.hibernate.Query;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import be.luxuryoverdosis.framework.data.dao.AbstractHibernateDaoSupport;
import be.luxuryoverdosis.framework.data.dao.interfaces.SqlHibernateDAO;
import be.luxuryoverdosis.framework.data.to.Sql;
import be.luxuryoverdosis.framework.logging.Logging;

@Repository
public class SqlHibernateDAOMySQLImpl extends AbstractHibernateDaoSupport implements SqlHibernateDAO {
	private static final String APPLICATION = "application";
	private static final String NAME = "name";
	
	public Sql createOrUpdate(final Sql sql) {
		Logging.info(this, "Begin createSql");
		getCurrentSession().saveOrUpdate(sql);
		Logging.info(this, "End createSql");
		return sql;
	}

	public Sql read(final int id) {
		Logging.info(this, "Begin readSql");
		Sql sql = (Sql) getCurrentSession().load(Sql.class, id);
		Logging.info(this, "End readSql");
		return sql;
	}
	
	@SuppressWarnings("unchecked")
	public Sql readName(final String name) {
		Logging.info(this, "Begin readNameSql");
		
		Query query = getCurrentSession().getNamedQuery("getAllSqlsByName");
		query.setString(NAME, name);
		ArrayList<Sql> arrayList = (ArrayList<Sql>) query.list();
		
		Sql sql = null;
		if(!arrayList.isEmpty()) {
			sql = (Sql)arrayList.iterator().next();
		}
		Logging.info(this, "End readNameSql");
		return sql;
	}

	public void delete(final int id) {
		Logging.info(this, "Begin deleteSql");
		getCurrentSession().delete(this.read(id));
		Logging.info(this, "End deleteSql");		
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Sql> list() {
		Logging.info(this, "Begin listSql");
		Query query = getCurrentSession().getNamedQuery("getAllSqls");
		ArrayList<Sql> arrayList = (ArrayList<Sql>) query.list();
		Logging.info(this, "End listSql");
		return arrayList;
	}
	
	@SuppressWarnings("unchecked")
	public long count(final String name) {
		Logging.info(this, "Begin countSql(String)");
		Query query = getCurrentSession().getNamedQuery("getCountSqlsByName");
		query.setString(NAME, name);
		ArrayList<Long> arrayList = (ArrayList<Long>) query.list();
		long count = arrayList.iterator().next().longValue();
		Logging.info(this, "End countSql(String)");
		return count;
	}
	
	@SuppressWarnings("unchecked")
	public long count(final String name, final String application) {
		Logging.info(this, "Begin countSql(String, String)");
		Query query = getCurrentSession().getNamedQuery("getCountSqlsByNameANdApplication");
		query.setString(NAME, name);
		query.setString(APPLICATION, application);
		ArrayList<Long> arrayList = (ArrayList<Long>) query.list();
		long count = arrayList.iterator().next().longValue();
		Logging.info(this, "End countSql(String, String)");
		return count;
	}
	
	public void execute(final String sqlStatement) throws DataAccessException {
		Logging.info(this, "Begin countSql(String, String)");
		getJdbcTemplate().execute(sqlStatement);
		Logging.info(this, "End countSql(String, String)");
	}
}
