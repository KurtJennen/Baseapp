package be.luxuryoverdosis.framework.business.service.implementations;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.luxuryoverdosis.framework.base.Query;
import be.luxuryoverdosis.framework.base.tool.DateTool;
import be.luxuryoverdosis.framework.business.query.SearchCriteria;
import be.luxuryoverdosis.framework.business.query.SearchParameter;
import be.luxuryoverdosis.framework.business.query.SearchSelect;
import be.luxuryoverdosis.framework.business.service.interfaces.SearchService;
import be.luxuryoverdosis.framework.data.dao.interfaces.SearchHibernateDAO;
import be.luxuryoverdosis.framework.logging.Logging;
import be.luxuryoverdosis.framework.web.exception.ServiceException;

@Service
public class SearchServiceSpringImpl implements SearchService {
	@Resource
	private SearchHibernateDAO searchHibernateDAO;
	
	@Transactional(readOnly=true)
	public ArrayList<Object> search(final SearchSelect searchSelect, final SearchCriteria searchCriteria) {
		Logging.info(this, "Begin searchSearch");
		
		String select = constructSelect(searchSelect, searchCriteria); 
		Object[] objects = constructObjects(searchSelect, searchCriteria);
		ArrayList<Object> arrayList = null;
		
		arrayList = searchHibernateDAO.search(select, objects);
		
		Logging.info(this, "End searchSearch");
		return arrayList;
	}
	
	private String constructSelect(final SearchSelect searchSelect, final SearchCriteria searchCriteria) {
		String[] operators = searchCriteria.getOperators();
		String[] parameters = searchCriteria.getParameters();
		String[] openBrackets = searchCriteria.getOpenBrackets();
		String[] closeBrackets = searchCriteria.getCloseBrackets();
		String[] addAndOrs = searchCriteria.getAddAndOrs();
		String complexQuery = Query.ZERO;
		String addOnSelect = searchCriteria.getAddOnSelect();
		
		StringBuffer search = new StringBuffer();
		
		search.append(searchSelect.getSelect());
		search.append(Query.SPACE);
		if(addOnSelect != null && !StringUtils.isEmpty(addOnSelect)) {
			search.append(addOnSelect);
			search.append(Query.SPACE);
		}
		
		if(parameters != null) {
			int fromPos = searchSelect.getSelect().lastIndexOf(Query.FROM);
			String from = searchSelect.getSelect().substring(fromPos);
			
			if(!from.contains(Query.WHERE) && !addOnSelect.contains(Query.WHERE)) {
				search.append(Query.WHERE);
			} else {
				search.append(Query.AND);
			}
			search.append(Query.SPACE);
			
			for(int i = 0; i < parameters.length; i++) {
				if(!parameters[i].equals(Query.MINUS_ONE)) {
					SearchParameter searchParameter = searchSelect.getSearchParameter(Integer.valueOf(parameters[i]));
					
					if(i > 0) {
						if(complexQuery.equals(Query.ONE)) {
							search.append(Query.DEFAULT_ADD[Integer.valueOf(addAndOrs[i - 1])]);
						} else {
							search.append(Query.AND);
						}
						search.append(Query.SPACE);
					}
					
					if(complexQuery.equals(Query.ONE)) {
						if(!openBrackets[i].equals(Query.MINUS_ONE)) {
							search.append(Query.OPEN_BRACKET);
							search.append(Query.SPACE);
						}
					}
					
					search.append(searchParameter.getName());
					search.append(Query.SPACE);
					if(Integer.valueOf(operators[i]) < 6) {
						search.append(Query.DEFAULT_OPERATORS[Integer.valueOf(operators[i])]);
					} 
					if(Integer.valueOf(operators[i]) >= 6 && Integer.valueOf(operators[i]) <= 8) {
						search.append(Query.LIKE);
					}
					if(Integer.valueOf(operators[i]) == 9) {
						search.append(Query.IS_NULL);
					}
					if(Integer.valueOf(operators[i]) == 10) {
						search.append(Query.IS_NOT_NULL);
					}
					search.append(Query.SPACE);
					
					if(Integer.valueOf(operators[i]) < 9) {
						search.append(Query.QUESTION);
						search.append(Query.SPACE);
					}
					
					if(complexQuery.equals(Query.ONE)) {
						if(!closeBrackets[i].equals(Query.MINUS_ONE)) {
							search.append(Query.CLOSE_BRACKET);
							search.append(Query.SPACE);
						}
					}
				}
			}
		}
		
		search.append(searchSelect.getOrderby());
		
		return search.toString();
	}
	
	private Object[] constructObjects(final SearchSelect searchSelect, final SearchCriteria searchCriteria) {
		String[] operators = searchCriteria.getOperators();
		String[] parameters = searchCriteria.getParameters();
		String[] values = searchCriteria.getValues();
		
		Object[] objects = null;
		
		if(parameters != null) {
			int teller = 0;
			
			for(int i = 0; i < parameters.length; i++) {
				if(!parameters[i].equals(Query.MINUS_ONE) && Integer.valueOf(operators[i]) < 9) {
					teller++;
				}
			}
			
			objects = new Object[teller];
			
			teller = 0;
			
			for(int i = 0; i < parameters.length; i++) {
				if(!parameters[i].equals(Query.MINUS_ONE) && Integer.valueOf(operators[i]) < 9) {
					SearchParameter searchParameter = searchSelect.getSearchParameter(Integer.valueOf(parameters[i]));
					
					if(searchParameter.getType() == null || searchParameter.getType().equals("java.lang.String")) {
						if(Integer.valueOf(operators[i]) >= 6 && Integer.valueOf(operators[i]) <= 8) {
							if(Integer.valueOf(operators[i]) == 6) {
								objects[teller] = values[i] + Query.PROCENT;
							}
							if(Integer.valueOf(operators[i]) == 7) {
								objects[teller] =  Query.PROCENT + values[i];
							}
							if(Integer.valueOf(operators[i]) == 8) {
								objects[teller] = Query.PROCENT + values[i] + Query.PROCENT;
							}
						} else {
							objects[teller] = values[i];
						}
					} else if (searchParameter.getType().equals("java.lang.Integer")) {
						try {
							objects[teller] = new Integer(values[i]);
						} catch (Exception e) {
							throw new ServiceException("errors.integer", new String[] {searchParameter.getKey()});
						}
					} else if (searchParameter.getType().equals("java.lang.Long")) {
						try {
							objects[teller] = new Long(values[i]);
						} catch (Exception e) {
							throw new ServiceException("errors.long", new String[] {searchParameter.getKey()});
						}
					} else if (searchParameter.getType().equals("java.lang.BigDecimal")) {
						try {
							objects[teller] = new BigDecimal(values[i]);
						} catch (Exception e) {
							throw new ServiceException("errors.double", new String[] {searchParameter.getKey()});
						}
					} else if (searchParameter.getType().equals("java.util.Date")) {
						try {
							objects[teller] = DateTool.parseUtilDate(values[i]);
						} catch (Exception e) {
							throw new ServiceException("errors.date", new String[] {searchParameter.getKey()});
						}
						
					}
					teller++;
				}
			}
		}
		
		return objects;
	}
}
