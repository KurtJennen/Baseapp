package be.luxuryoverdosis.framework.data.dao.implementations;

import java.util.ArrayList;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import be.luxuryoverdosis.framework.data.dao.AbstractHibernateDaoSupport;
import be.luxuryoverdosis.framework.data.dao.QueryParameters;
import be.luxuryoverdosis.framework.data.dao.interfaces.QueryParamHibernateDAO;
import be.luxuryoverdosis.framework.data.to.QueryParam;
import be.luxuryoverdosis.framework.logging.Logging;

@Repository
public class QueryParamHibernateDAOMySQLImpl extends AbstractHibernateDaoSupport implements QueryParamHibernateDAO {
	public QueryParam createOrUpdate(final QueryParam queryParam) {
		Logging.info(this, "Begin createQueryParam");
		getCurrentSession().saveOrUpdate(queryParam);
		Logging.info(this, "End createQueryParam");
		return queryParam;
	}

	public QueryParam read(final int id) {
		Logging.info(this, "Begin readQueryParam");
		QueryParam queryParam = (QueryParam) getCurrentSession().load(QueryParam.class, id);
		Logging.info(this, "End readQueryParam");
		return queryParam;
	}

	public void delete(final int id) {
		Logging.info(this, "Begin deleteQueryParam");
		getCurrentSession().delete(this.read(id));
		Logging.info(this, "End deleteQueryParam");		
	}
	
	@SuppressWarnings("unchecked")
	public void deleteForQuery(final int queryId) {
		Logging.info(this, "Begin deleteForQueryQueryParam");

		Query<Long> query = getCurrentSession().getNamedQuery(QueryParam.DELETE_QUERYPARAMS_BY_QUERY);
		query.setParameter(QueryParameters.QUERY_ID, queryId);
		query.executeUpdate();
		
		Logging.info(this, "End deleteForQueryQueryParam");		
	}

	@SuppressWarnings("unchecked")
	public ArrayList<QueryParam> list(final int queryId) {
		Logging.info(this, "Begin listQueryParam");
		
		Query<QueryParam> query = getCurrentSession().getNamedQuery(QueryParam.SELECT_QUERYPARAMS_BY_QUERY);
		query.setParameter(QueryParameters.QUERY_ID, queryId);
		ArrayList<QueryParam> arrayList = (ArrayList<QueryParam>) query.list();
		
		Logging.info(this, "End listQueryParam");
		return arrayList;
	}
	
	@SuppressWarnings("unchecked")
	public long count(final int queryId) {
		Logging.info(this, "Begin countQueryParam");
		
		Query<Long> query = getCurrentSession().getNamedQuery(QueryParam.COUNT_QUERYPARAMS_BY_QUERY);
		query.setParameter(QueryParameters.QUERY_ID, queryId);
		ArrayList<Long> arrayList = (ArrayList<Long>) query.list();
		long count = arrayList.iterator().next().longValue();
		
		Logging.info(this, "End countQueryParam");
		return count;
	}
}
