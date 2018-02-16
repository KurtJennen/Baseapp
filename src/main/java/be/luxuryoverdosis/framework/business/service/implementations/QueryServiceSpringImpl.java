package be.luxuryoverdosis.framework.business.service.implementations;

import java.util.ArrayList;
import java.util.Iterator;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.luxuryoverdosis.framework.base.SearchQuery;
import be.luxuryoverdosis.framework.business.service.interfaces.QueryService;
import be.luxuryoverdosis.framework.business.thread.ThreadManager;
import be.luxuryoverdosis.framework.data.dao.interfaces.QueryHibernateDAO;
import be.luxuryoverdosis.framework.data.dao.interfaces.QueryParamHibernateDAO;
import be.luxuryoverdosis.framework.data.dto.QueryDTO;
import be.luxuryoverdosis.framework.data.to.QueryParamTO;
import be.luxuryoverdosis.framework.data.to.Query;
import be.luxuryoverdosis.framework.data.to.UserTO;
import be.luxuryoverdosis.framework.logging.Logging;

@Service
public class QueryServiceSpringImpl implements QueryService {
	@Resource
	private QueryHibernateDAO queryHibernateDAO;
	@Resource
	private QueryParamHibernateDAO queryParamHibernateDAO;
	
	@Transactional
	public QueryDTO createOrUpdateDTO(final QueryDTO queryDTO) {
		Logging.info(this, "Begin createQueryDTO");
		
		UserTO userTO = ThreadManager.getUserFromThread();
		
		Query query = this.read(queryDTO.getName(), queryDTO.getType());
		if(query == null) {
			query = new Query();
		}
		query.setName(queryDTO.getName());
		query.setType(queryDTO.getType());
		query.setComplex(queryDTO.getComplex());
		query.setUser(userTO);
		
		query = this.createOrUpdate(query);
		
		queryParamHibernateDAO.deleteForQuery(query.getId());
		
		for(int i = 0; i < queryDTO.getParameters().length; i++) {
			if(!queryDTO.getParameters()[i].equals(SearchQuery.MINUS_ONE)) {
				QueryParamTO queryParamTO = new QueryParamTO();
				queryParamTO.setQuery(query);
				queryParamTO.setParameter(queryDTO.getParameters()[i]);
				queryParamTO.setOperator(queryDTO.getOperators()[i]);
				queryParamTO.setValue(queryDTO.getValues()[i]);
				if(queryDTO.getComplex().equals(SearchQuery.ONE)) {
					queryParamTO.setOpenBracket(queryDTO.getOpenBrackets()[i]);
					queryParamTO.setCloseBracket(queryDTO.getCloseBrackets()[i]);
					if(i > 0) {
						queryParamTO.setAddAndOr(queryDTO.getAddAndOrs()[i - 1]);
					}					
				}
				queryParamHibernateDAO.createOrUpdate(queryParamTO);
			}
		}
		
		Logging.info(this, "End createQueryDTO");		
		return this.readDTO(query.getId());
	}
	
	@Transactional(readOnly=true)
	public QueryDTO readDTO(final int id) {
		Logging.info(this, "Begin readQueryDTO");
		
		Query query = this.read(id);
		
		QueryDTO queryDTO = new QueryDTO();
		queryDTO.setComplex(query.getComplex());
		queryDTO.setParameters(this.readParameters(id));
		queryDTO.setOperators(this.readOperators(id));
		queryDTO.setValues(this.readValues(id));
		
		if(SearchQuery.ZERO.equals(query.getComplex())) {
			queryDTO.setAddAndOrs(null);
			queryDTO.setOpenBrackets(null);
			queryDTO.setCloseBrackets(null);
		} else {
			queryDTO.setAddAndOrs(this.readAddAndOrs(id));
			queryDTO.setOpenBrackets(this.readOpenBrackets(id));
			queryDTO.setCloseBrackets(this.readCloseBrackets(id));
		}
		
		Logging.info(this, "End readQueryDTO");
		return queryDTO;
	}
	
	@Transactional
	public Query createOrUpdate(final Query query) {
		Logging.info(this, "Begin createQuery(Query)");
		Query result = null;
		result = queryHibernateDAO.createOrUpdate(query);	
		Logging.info(this, "End createQuery(Query)");
		return result;
	}
	
	@Transactional(readOnly=true)
	public Query read(final int id) {
		Logging.info(this, "Begin readQuery");
		Query result = null;
		result = queryHibernateDAO.read(id);
		Logging.info(this, "End readQuery");
		return result;
	}
	
	@Transactional(readOnly=true)
	public Query read(final String name, final String type) {
		Logging.info(this, "Begin readQuery");
		Query result = null;
		result = queryHibernateDAO.read(name, type);
		Logging.info(this, "End readQuery");
		return result;
	}
	
	@Transactional(readOnly=true)
	public String[] readParameters(final int id) {
		Logging.info(this, "Begin readParametersQuery");
		
		ArrayList<QueryParamTO> queryParameters = queryParamHibernateDAO.list(id);
		Iterator<QueryParamTO> iterator = queryParameters.iterator();
		
		String[] parameters = new String[queryParameters.size()];
		int teller = 0;
		
		while(iterator.hasNext()) {
			QueryParamTO queryParamTO = (QueryParamTO)iterator.next();
			parameters[teller] = queryParamTO.getParameter();
			teller++;
		}
		
		Logging.info(this, "End readParametersQuery");
		return parameters;
	}
	
	@Transactional(readOnly=true)
	public String[] readOperators(final int id) {
		Logging.info(this, "Begin readOperatorsQuery");
		
		ArrayList<QueryParamTO> queryParameters = queryParamHibernateDAO.list(id);
		Iterator<QueryParamTO> iterator = queryParameters.iterator();
		
		String[] operators = new String[queryParameters.size()];
		int teller = 0;
		
		while(iterator.hasNext()) {
			QueryParamTO queryParamTO = (QueryParamTO)iterator.next();
			operators[teller] = queryParamTO.getOperator();
			teller++;
		}
		
		Logging.info(this, "End readOperatorsQuery");
		return operators;
	}
	
	@Transactional(readOnly=true)
	public String[] readValues(final int id) {
		Logging.info(this, "Begin readValuesQuery");
		
		ArrayList<QueryParamTO> queryParameters = queryParamHibernateDAO.list(id);
		Iterator<QueryParamTO> iterator = queryParameters.iterator();
		
		String[] values = new String[queryParameters.size()];
		int teller = 0;
		
		while(iterator.hasNext()) {
			QueryParamTO queryParamTO = (QueryParamTO)iterator.next();
			values[teller] = queryParamTO.getValue();
			teller++;
		}
		
		Logging.info(this, "End readValuesQuery");
		return values;
	}
	
	@Transactional(readOnly=true)
	public String[] readAddAndOrs(final int id) {
		Logging.info(this, "Begin readAddAndOrsQuery");
		
		ArrayList<QueryParamTO> queryParameters = queryParamHibernateDAO.list(id);
		Iterator<QueryParamTO> iterator = queryParameters.iterator();
		
		String[] addAndOrs = new String[queryParameters.size()];
		int teller = -1;
		
		while(iterator.hasNext()) {
			QueryParamTO queryParamTO = (QueryParamTO)iterator.next();
			if(teller >= 0) {
				addAndOrs[teller] = queryParamTO.getAddAndOr();
			}
			teller++;
		}
		
		Logging.info(this, "End readAddAndOrsQuery");
		return addAndOrs;
	}
	
	@Transactional(readOnly=true)
	public String[] readOpenBrackets(final int id) {
		Logging.info(this, "Begin readOpenBracketsQuery");
		
		ArrayList<QueryParamTO> queryParameters = queryParamHibernateDAO.list(id);
		Iterator<QueryParamTO> iterator = queryParameters.iterator();
		
		String[] openBrackets = new String[queryParameters.size()];
		int teller = 0;
		
		while(iterator.hasNext()) {
			QueryParamTO queryParamTO = (QueryParamTO)iterator.next();
			openBrackets[teller] = queryParamTO.getOpenBracket();
			teller++;
		}
		
		Logging.info(this, "End readOpenBracketsQuery");
		return openBrackets;
	}
	
	@Transactional(readOnly=true)
	public String[] readCloseBrackets(final int id) {
		Logging.info(this, "Begin readCloseBracketsQuery");
		
		ArrayList<QueryParamTO> queryParameters = queryParamHibernateDAO.list(id);
		Iterator<QueryParamTO> iterator = queryParameters.iterator();
		
		String[] closeBrackets = new String[queryParameters.size()];
		int teller = 0;
		
		while(iterator.hasNext()) {
			QueryParamTO queryParamTO = (QueryParamTO)iterator.next();
			closeBrackets[teller] = queryParamTO.getCloseBracket();
			teller++;
		}
		
		Logging.info(this, "End readCloseBracketsQuery");
		return closeBrackets;
	}
	
	@Transactional
	public void delete(final int id) {
		Logging.info(this, "Begin deleteQuery");
		
		queryParamHibernateDAO.deleteForQuery(id);
		queryHibernateDAO.delete(id);
				
		Logging.info(this, "End deleteQuery");
	}

	@Transactional(readOnly=true)
	public ArrayList<Query> list(final String type) {
		Logging.info(this, "Begin listQuery");
		
		UserTO userTO = ThreadManager.getUserFromThread();
		
		ArrayList<Query> arrayList = null;
		arrayList = queryHibernateDAO.list(type, userTO.getId());
		Logging.info(this, "End listQuery");
		return arrayList;
	}
	
	@Transactional
	public long countAndCreateOrUpdateDTO(final String name, final String type, final QueryDTO queryDTO) {
		Logging.info(this, "Begin countQuery");
		
		UserTO userTO = ThreadManager.getUserFromThread();
		
		Long countQuery = new Long(0);
		countQuery = queryHibernateDAO.count(name, type, userTO.getId());
		
		this.createOrUpdateDTO(queryDTO);
		
		Logging.info(this, "End countQuery");
		return countQuery.longValue();
	}
}
