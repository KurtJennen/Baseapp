package be.luxuryoverdosis.framework.business.service.implementations;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.luxuryoverdosis.framework.business.service.interfaces.QueryParamService;
import be.luxuryoverdosis.framework.data.dao.interfaces.QueryParamHibernateDAO;
import be.luxuryoverdosis.framework.data.to.QueryParamTO;
import be.luxuryoverdosis.framework.logging.Logging;

@Service
public class QueryParamServiceSpringImpl implements QueryParamService {
	@Resource
	private QueryParamHibernateDAO queryParamHibernateDAO;
	
	@Transactional
	public QueryParamTO createOrUpdate(final QueryParamTO queryParamTO) {
		Logging.info(this, "Begin createQueryParam");
		QueryParamTO result = null;
		result = queryParamHibernateDAO.createOrUpdate(queryParamTO);	
		Logging.info(this, "End createQueryParam");
		return result;
	}
	
	@Transactional(readOnly=true)
	public QueryParamTO read(final int id) {
		Logging.info(this, "Begin readQueryParam");
		QueryParamTO result = null;
		result = queryParamHibernateDAO.read(id);
		Logging.info(this, "End readQueryParam");
		return result;
	}

	@Transactional
	public void delete(final int id) {
		Logging.info(this, "Begin deleteQueryParam");
		queryParamHibernateDAO.delete(id);
		Logging.info(this, "End deleteQueryParam");
	}
	
	@Transactional
	public void deleteForQuery(final int queryId) {
		Logging.info(this, "Begin deleteForQueryQueryParam");
		queryParamHibernateDAO.deleteForQuery(queryId);
		Logging.info(this, "End deleteForQueryQueryParam");
	}

	@Transactional(readOnly=true)
	public ArrayList<QueryParamTO> list(final int queryId) {
		Logging.info(this, "Begin listQueryParam");
		ArrayList<QueryParamTO> arrayList = null;
		arrayList = queryParamHibernateDAO.list(queryId);
		Logging.info(this, "End listQueryParam");
		return arrayList;
	}
	
	@Transactional(readOnly=true)
	public long count(final int queryId) {
		Logging.info(this, "Begin countQueryParam");
		Long countQueryParam = new Long(0);
		countQueryParam = queryParamHibernateDAO.count(queryId);
		Logging.info(this, "End countQueryParam");
		return countQueryParam.longValue();
	}
}
