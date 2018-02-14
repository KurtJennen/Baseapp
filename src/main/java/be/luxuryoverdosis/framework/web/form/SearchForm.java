package be.luxuryoverdosis.framework.web.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import be.luxuryoverdosis.framework.base.Query;
import be.luxuryoverdosis.framework.business.query.SearchCriteria;
import be.luxuryoverdosis.framework.logging.Logging;
import be.luxuryoverdosis.framework.web.BaseWebConstants;
import be.luxuryoverdosis.framework.web.message.MessageLocator;

public class SearchForm extends BaseForm {
	private static final long serialVersionUID = 1L;
	
	private SearchCriteria searchCriteria;
	
	private int defaultLines = 2;
	private String[] operators;
	private String[] parameters;
	private String[] values;
	private String[] openBrackets;
	private String[] closeBrackets;
	private String[] addAndOrs;
	private String searchName = "";
	private String queryName = "";
	private String selectQuery = "";
	private String complexQuery = Query.ZERO;
	
	private boolean isButton1Allowed = false;
	private boolean isButton2Allowed = false;
	private boolean isButton3Allowed = false;
	private boolean isButton4Allowed = false;
	private boolean isButton5Allowed = false;
	
	public int getDefaultLines() {
		return defaultLines;
	}
	public void setDefaultLines(int defaultLines) {
		this.defaultLines = defaultLines;
	}
	public String[] getOperators() {
		return operators;
	}
	public void setOperators(String[] operators) {
		this.operators = operators;
	}
	public String[] getParameters() {
		return parameters;
	}
	public void setParameters(String[] parameters) {
		this.parameters = parameters;
	}
	public String[] getValues() {
		return values;
	}
	public void setValues(String[] values) {
		this.values = values;
	}	
	public String[] getOpenBrackets() {
		return openBrackets;
	}
	public void setOpenBrackets(String[] openBrackets) {
		this.openBrackets = openBrackets;
	}
	public String[] getCloseBrackets() {
		return closeBrackets;
	}
	public void setCloseBrackets(String[] closeBrackets) {
		this.closeBrackets = closeBrackets;
	}
	public String[] getAddAndOrs() {
		return addAndOrs;
	}
	public void setAddAndOrs(String[] addAndOrs) {
		this.addAndOrs = addAndOrs;
	}
	public String getSearchName() {
		return searchName;
	}
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}	
	public String getQueryName() {
		return queryName;
	}
	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}
	public String getSelectQuery() {
		return selectQuery;
	}
	public void setSelectQuery(String selectQuery) {
		this.selectQuery = selectQuery;
	}	
	public String getComplexQuery() {
		return complexQuery;
	}
	public void setComplexQuery(String complexQuery) {
		this.complexQuery = complexQuery;
	}	
	public SearchCriteria getSearchCriteria() {
		return searchCriteria;
	}
	public void setSearchCriteria(SearchCriteria searchCriteria) {
		this.searchCriteria = searchCriteria;
	}
	
	public boolean isButton1Allowed() {
		return isButton1Allowed;
	}
	public void setButton1Allowed(boolean isButton1Allowed) {
		this.isButton1Allowed = isButton1Allowed;
	}
	public boolean isButton2Allowed() {
		return isButton2Allowed;
	}
	public void setButton2Allowed(boolean isButton2Allowed) {
		this.isButton2Allowed = isButton2Allowed;
	}
	public boolean isButton3Allowed() {
		return isButton3Allowed;
	}
	public void setButton3Allowed(boolean isButton3Allowed) {
		this.isButton3Allowed = isButton3Allowed;
	}
	public boolean isButton4Allowed() {
		return isButton4Allowed;
	}
	public void setButton4Allowed(boolean isButton4Allowed) {
		this.isButton4Allowed = isButton4Allowed;
	}
	public boolean isButton5Allowed() {
		return isButton5Allowed;
	}
	public void setButton5Allowed(boolean isButton5Allowed) {
		this.isButton5Allowed = isButton5Allowed;
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		searchCriteria = new SearchCriteria();
	}
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		Logging.info(this, "Begin Validating");
				
		ActionErrors errors = new ActionErrors();
		
		errors.add(super.validate(mapping, request));
		
		if(this.getMethod().equals(BaseWebConstants.LIST) || this.getMethod().equals(BaseWebConstants.LIST_JMESA) || this.getMethod().equals(BaseWebConstants.UPDATE_SEARCH)) {
			int aantalParameters = 0;
			int aantalOpenBrackets = 0;
			int aantalCloseBrackets = 0;
			
			for(int i = 0; i < parameters.length; i++) {
				if(!parameters[i].equals(Query.MINUS_ONE) && StringUtils.isEmpty(values[i]) && Integer.valueOf(operators[i]) < 9) {
					errors.add(Query.FIELD + i, new ActionMessage("errors.required", MessageLocator.getMessage(request, "search.value")));
				}
				if(parameters[i].equals(Query.MINUS_ONE)) {
					aantalParameters++;
				}
				if(complexQuery.equals(Query.ONE)) {
					if(i > 0 && !parameters[i].equals(Query.MINUS_ONE) && addAndOrs[i - 1].equals(Query.MINUS_ONE)) {
						errors.add(Query.FIELD + i, new ActionMessage("errors.required", MessageLocator.getMessage(request, "search.and.or")));
					}
					if(openBrackets[i].equals(Query.MINUS_ONE)) {
						aantalOpenBrackets++;
					}
					if(closeBrackets[i].equals(Query.MINUS_ONE)) {
						aantalCloseBrackets++;
					}
				}
			}
			if(aantalParameters == parameters.length) {
				errors.add(Query.FIELD + Query.ZERO, new ActionMessage("errors.required", MessageLocator.getMessage(request, "search.parameter")));
			}
			if(complexQuery.equals(Query.ONE)) {
				if(aantalOpenBrackets != aantalCloseBrackets) {
					errors.add(Query.FIELD + Query.ZERO, new ActionMessage("errors.bracket"));
				}
			}
			
			searchCriteria.setComplexQuery(complexQuery);
			searchCriteria.setAddAndOrs(addAndOrs);
			searchCriteria.setCloseBrackets(closeBrackets);
			searchCriteria.setOpenBrackets(openBrackets);
			searchCriteria.setOperators(operators);
			searchCriteria.setParameters(parameters);
			searchCriteria.setValues(values);
		}
		
		if(this.getMethod().equals(BaseWebConstants.UPDATE_SEARCH)) {
			if(StringUtils.isEmpty(queryName)) {
				errors.add("queryName", new ActionMessage("errors.required", MessageLocator.getMessage(request, "search.query.name")));
			}
		}
		
		if(errors.size() > 0) {
			request.setAttribute(BaseWebConstants.ERROR, 1);
		}
		
		Logging.info(this, "End Validating");
		
		return errors;
	}
	
}
