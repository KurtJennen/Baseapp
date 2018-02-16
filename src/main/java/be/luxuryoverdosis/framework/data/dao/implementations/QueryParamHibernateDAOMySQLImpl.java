package be.luxuryoverdosis.framework.data.dao.implementations;

import java.sql.SQLException;
import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import be.luxuryoverdosis.framework.data.dao.AbstractHibernateDaoSupport;
import be.luxuryoverdosis.framework.data.dao.interfaces.QueryParamHibernateDAO;
import be.luxuryoverdosis.framework.data.to.QueryParam;
import be.luxuryoverdosis.framework.logging.Logging;

@Repository
public class QueryParamHibernateDAOMySQLImpl extends AbstractHibernateDaoSupport implements QueryParamHibernateDAO {
	public QueryParam createOrUpdate(final QueryParam queryParam) {
		Logging.info(this, "Begin createQueryParam");
		getHibernateTemplate().saveOrUpdate(queryParam);
		Logging.info(this, "End createQueryParam");
		return queryParam;
	}

	public QueryParam read(final int id) {
		Logging.info(this, "Begin readQueryParam");
		QueryParam queryParam = (QueryParam) getHibernateTemplate().load(QueryParam.class, id);
		Logging.info(this, "End readQueryParam");
		return queryParam;
	}

	public void delete(final int id) {
		Logging.info(this, "Begin deleteQueryParam");
		getHibernateTemplate().delete((QueryParam) getHibernateTemplate().load(QueryParam.class, id));
		Logging.info(this, "End deleteQueryParam");		
	}
	
	public void deleteForQuery(final int queryId) {
		Logging.info(this, "Begin deleteForQueryQueryParam");
		getHibernateTemplate().execute(new HibernateCallback<Integer>() {

			public Integer doInHibernate(Session session) throws HibernateException, SQLException {
				Query updateQuery = session.createQuery("delete QueryParam qp where qp.query.id = ?");
				updateQuery.setParameter(0, queryId);
				return updateQuery.executeUpdate();
			}
			
		});
		Logging.info(this, "End deleteForQueryQueryParam");		
	}

	@SuppressWarnings("unchecked")
	public ArrayList<QueryParam> list(final int queryParamId) {
		Logging.info(this, "Begin listQueryParam");
		ArrayList<QueryParam> arrayList = (ArrayList<QueryParam>) getHibernateTemplate().find("from QueryParam qp where qp.query.id = ?", new Object[]{queryParamId});
		Logging.info(this, "End listQueryParam");
		return arrayList;
	}
	
	@SuppressWarnings("unchecked")
	public long count(final int queryParamId) {
		Logging.info(this, "Begin countQueryParam");
		ArrayList<Long> arrayList = (ArrayList<Long>) getHibernateTemplate().find("select count(*) from QueryParam qp where qp.query.id = ?", new Object[]{queryParamId});
		long count = arrayList.iterator().next().longValue();
		Logging.info(this, "End countQueryParam");
		return count;
	}
}
