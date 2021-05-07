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
import be.luxuryoverdosis.framework.data.dao.interfaces.UserHibernateDAO;
import be.luxuryoverdosis.framework.data.dto.QueryDTO;
import be.luxuryoverdosis.framework.data.dto.UserDTO;
import be.luxuryoverdosis.framework.data.to.QueryParam;
import be.luxuryoverdosis.framework.data.to.Query;
import be.luxuryoverdosis.framework.logging.Logging;

@Service
public class QueryServiceSpringImpl implements QueryService {
	@Resource
	private QueryHibernateDAO queryHibernateDAO;
	@Resource
	private QueryParamHibernateDAO queryParamHibernateDAO;
	@Resource
	private UserHibernateDAO userHibernateDAO;
	
	@Transactional
	public QueryDTO createOrUpdateDTO(final QueryDTO queryDTO) {
		Logging.info(this, "Begin createQueryDTO");
		
		UserDTO userDTO = ThreadManager.getUserFromThread();
		
		Query query = this.read(queryDTO.getName(), queryDTO.getType(), userDTO.getId());
		if(query == null) {
			query = new Query();
		}
		query.setName(queryDTO.getName());
		query.setType(queryDTO.getType());
		query.setComplex(queryDTO.getComplex());
		query.setUser(userHibernateDAO.read(userDTO.getId()));
		
		query = this.createOrUpdate(query);
		
		queryParamHibernateDAO.deleteForQuery(query.getId());
		
		for(int i = 0; i < queryDTO.getNames().length; i++) {
			if(!queryDTO.getNames()[i].equals(SearchQuery.MINUS_ONE)) {
				QueryParam queryParam = new QueryParam();
				queryParam.setQuery(query);
				queryParam.setName(queryDTO.getNames()[i]);
				queryParam.setOperator(queryDTO.getOperators()[i]);
				queryParam.setValue(queryDTO.getValues()[i]);
				if(queryDTO.getComplex().equals(SearchQuery.ONE)) {
					queryParam.setOpenBracket(queryDTO.getOpenBrackets()[i]);
					queryParam.setCloseBracket(queryDTO.getCloseBrackets()[i]);
					if(i > 0) {
						queryParam.setAddAndOr(queryDTO.getAddAndOrs()[i - 1]);
					}					
				}
				queryParamHibernateDAO.createOrUpdate(queryParam);
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
		queryDTO.setNames(this.readNames(id));
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
	public Query read(final String name, final String type, final int userId) {
		Logging.info(this, "Begin readQuery");
		Query result = null;
		result = queryHibernateDAO.read(name, type, userId);
		Logging.info(this, "End readQuery");
		return result;
	}
	
	@Transactional(readOnly=true)
	public String[] readNames(final int id) {
		Logging.info(this, "Begin readNamesQuery");
		
		ArrayList<QueryParam> queryParameters = queryParamHibernateDAO.list(id);
		Iterator<QueryParam> iterator = queryParameters.iterator();
		
		String[] names = new String[queryParameters.size()];
		int teller = 0;
		
		while(iterator.hasNext()) {
			QueryParam queryParam = (QueryParam)iterator.next();
			names[teller] = queryParam.getName();
			teller++;
		}
		
		Logging.info(this, "End readNamesQuery");
		return names;
	}
	
	@Transactional(readOnly=true)
	public String[] readOperators(final int id) {
		Logging.info(this, "Begin readOperatorsQuery");
		
		ArrayList<QueryParam> queryParameters = queryParamHibernateDAO.list(id);
		Iterator<QueryParam> iterator = queryParameters.iterator();
		
		String[] operators = new String[queryParameters.size()];
		int teller = 0;
		
		while(iterator.hasNext()) {
			QueryParam queryParam = (QueryParam)iterator.next();
			operators[teller] = queryParam.getOperator();
			teller++;
		}
		
		Logging.info(this, "End readOperatorsQuery");
		return operators;
	}
	
	@Transactional(readOnly=true)
	public String[] readValues(final int id) {
		Logging.info(this, "Begin readValuesQuery");
		
		ArrayList<QueryParam> queryParameters = queryParamHibernateDAO.list(id);
		Iterator<QueryParam> iterator = queryParameters.iterator();
		
		String[] values = new String[queryParameters.size()];
		int teller = 0;
		
		while(iterator.hasNext()) {
			QueryParam queryParam = (QueryParam)iterator.next();
			values[teller] = queryParam.getValue();
			teller++;
		}
		
		Logging.info(this, "End readValuesQuery");
		return values;
	}
	
	@Transactional(readOnly=true)
	public String[] readAddAndOrs(final int id) {
		Logging.info(this, "Begin readAddAndOrsQuery");
		
		ArrayList<QueryParam> queryParameters = queryParamHibernateDAO.list(id);
		Iterator<QueryParam> iterator = queryParameters.iterator();
		
		String[] addAndOrs = new String[queryParameters.size()];
		int teller = -1;
		
		while(iterator.hasNext()) {
			QueryParam queryParam = (QueryParam)iterator.next();
			if(teller >= 0) {
				addAndOrs[teller] = queryParam.getAddAndOr();
			}
			teller++;
		}
		
		Logging.info(this, "End readAddAndOrsQuery");
		return addAndOrs;
	}
	
	@Transactional(readOnly=true)
	public String[] readOpenBrackets(final int id) {
		Logging.info(this, "Begin readOpenBracketsQuery");
		
		ArrayList<QueryParam> queryParameters = queryParamHibernateDAO.list(id);
		Iterator<QueryParam> iterator = queryParameters.iterator();
		
		String[] openBrackets = new String[queryParameters.size()];
		int teller = 0;
		
		while(iterator.hasNext()) {
			QueryParam queryParam = (QueryParam)iterator.next();
			openBrackets[teller] = queryParam.getOpenBracket();
			teller++;
		}
		
		Logging.info(this, "End readOpenBracketsQuery");
		return openBrackets;
	}
	
	@Transactional(readOnly=true)
	public String[] readCloseBrackets(final int id) {
		Logging.info(this, "Begin readCloseBracketsQuery");
		
		ArrayList<QueryParam> queryParameters = queryParamHibernateDAO.list(id);
		Iterator<QueryParam> iterator = queryParameters.iterator();
		
		String[] closeBrackets = new String[queryParameters.size()];
		int teller = 0;
		
		while(iterator.hasNext()) {
			QueryParam queryParam = (QueryParam)iterator.next();
			closeBrackets[teller] = queryParam.getCloseBracket();
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
		
		UserDTO user = ThreadManager.getUserFromThread();
		
		ArrayList<Query> arrayList = null;
		arrayList = queryHibernateDAO.list(type, user.getId());
		Logging.info(this, "End listQuery");
		return arrayList;
	}
	
	@Transactional
	public long countAndCreateOrUpdateDTO(final String type, final QueryDTO queryDTO) {
		Logging.info(this, "Begin countQuery");
		
		UserDTO user = ThreadManager.getUserFromThread();
		
		Long countQuery = new Long(0);
		countQuery = queryHibernateDAO.count(queryDTO.getName(), type, user.getId());
		
		this.createOrUpdateDTO(queryDTO);
		
		Logging.info(this, "End countQuery");
		return countQuery.longValue();
	}
}
