package be.luxuryoverdosis.framework.data.dao.implementations;

import java.util.ArrayList;

import org.hibernate.query.Query;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import be.luxuryoverdosis.framework.data.dao.AbstractHibernateDaoSupport;
import be.luxuryoverdosis.framework.data.dao.QueryParameters;
import be.luxuryoverdosis.framework.data.dao.interfaces.SqlHibernateDAO;
import be.luxuryoverdosis.framework.data.to.Sql;
import be.luxuryoverdosis.framework.logging.Logging;

@Repository
public class SqlHibernateDAOMySQLImpl extends AbstractHibernateDaoSupport implements SqlHibernateDAO {
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
		
		Query<Sql> query = getCurrentSession().getNamedQuery(Sql.SELECT_SQLS_BY_NAME);
		query.setParameter(QueryParameters.NAME, name);
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
		Query<Sql> query = getCurrentSession().getNamedQuery(Sql.SELECT_SQLS);
		ArrayList<Sql> arrayList = (ArrayList<Sql>) query.list();
		Logging.info(this, "End listSql");
		return arrayList;
	}
	
	@SuppressWarnings("unchecked")
	public long count(final String name) {
		Logging.info(this, "Begin countSql(String)");
		
		Query<Long> query = getCurrentSession().getNamedQuery(Sql.COUNT_SQLS_BY_NAME);
		query.setParameter(QueryParameters.NAME, name);
		ArrayList<Long> arrayList = (ArrayList<Long>) query.list();
		long count = arrayList.iterator().next().longValue();
		
		Logging.info(this, "End countSql(String)");
		return count;
	}
	
	@SuppressWarnings("unchecked")
	public long count(final String name, final String application) {
		Logging.info(this, "Begin countSql(String, String)");
		
		Query<Long> query = getCurrentSession().getNamedQuery(Sql.COUNT_SQLS_BY_NAME_AND_APPLICATION);
		query.setParameter(QueryParameters.NAME, name);
		query.setParameter(QueryParameters.APPLICATION, application);
		ArrayList<Long> arrayList = (ArrayList<Long>) query.list();
		long count = arrayList.iterator().next().longValue();
		
		Logging.info(this, "End countSql(String, String)");
		return count;
	}
	
	public int countJdbc(final String name, final String application) throws DataAccessException {
		Logging.info(this, "Begin countJdbc");
		final Object[] paramsSelect = new Object[] {name, application};
        int count = getJdbcTemplate().queryForObject(Sql.COUNT_BASE_SQL, paramsSelect, Integer.class);
		Logging.info(this, "End countJdbc");
		return count;
	}
	
	public void executeJdbc(final String sqlStatement) throws DataAccessException {
		Logging.info(this, "Begin executeJdbc");
		getJdbcTemplate().execute(sqlStatement);
		Logging.info(this, "End executeJdbc");
	}
	
	public void insertJdbc(final String sqlStatement, final String name, final String application) throws DataAccessException {
		Logging.info(this, "Begin insertJdbc");
		final Object[] paramsInsert = new Object[] {name, sqlStatement, application};
		getJdbcTemplate().update(Sql.INSERT_BASE_SQL, paramsInsert);
		Logging.info(this, "End insertJdbc");
	}
}
