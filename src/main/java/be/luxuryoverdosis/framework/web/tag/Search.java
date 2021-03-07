package be.luxuryoverdosis.framework.web.tag;

import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import be.luxuryoverdosis.framework.base.SearchQuery;
import be.luxuryoverdosis.framework.business.query.SearchParameter;
import be.luxuryoverdosis.framework.business.query.SearchSelect;
import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;
import be.luxuryoverdosis.framework.business.service.interfaces.QueryService;
import be.luxuryoverdosis.framework.data.to.Query;
import be.luxuryoverdosis.framework.web.form.SearchForm;
import be.luxuryoverdosis.framework.web.message.MessageLocator;

public class Search implements Tag {
	PageContext pageContext;
	String searchService;
	String searchName;
		
	public void setPageContext(PageContext p) {
		pageContext = p;
	}
	
	public void setParent(Tag t) {
	}
	
	public void release() {
	}
	
	public Tag getParent() {
		return null;
	}

	public void setSearchService(String searchService) {
		this.searchService = searchService;
	}
	
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
			
			SearchForm searchForm = (SearchForm)request.getSession().getAttribute(searchName);
			
			if(!searchName.equals(searchForm.getSearchName())) {
				searchForm.setDefaultLines(2);
			}
			
			ArrayList<Query> queries = getQueryService().list(searchService);
			
			//hidden
			out.print("<input type=\"hidden\" name=\"searchName\" value=\"" + searchName + "\" />");
			out.print("<input type=\"hidden\" name=\"complexQuery\" value=\"" + searchForm.getComplexQuery() + "\" />");
			
			//buttons
			out.print("<button onclick=\"javascript:doAction('createLine');\" title=\"" + MessageLocator.getMessage(request, "button.create") + "\">");
			out.print("<img src=\"images/zoom_in.png\"/>");
			out.println("</button>");
			out.print("<button onclick=\"javascript:doAction('deleteLine');\" title=\"" + MessageLocator.getMessage(request, "button.delete") + "\">");
			out.print("<img src=\"images/zoom_out.png\"/>");
			out.println("</button>");
			out.print("<button onclick=\"javascript:doAction('complexLine');\" title=\"" + MessageLocator.getMessage(request, "button.complex") + "\">");
			out.print("<img src=\"images/table_gear.png\"/>");
			out.println("</button>");
			out.print("<button onclick=\"javascript:doAction('resetSearch');\" title=\"" + MessageLocator.getMessage(request, "button.reset") + "\">");
			out.print("<img src=\"images/table_lightning.png\"/>");
			out.println("</button>");
					
			//table1
			out.print("<table>");
			out.print("<tr>");
			
			out.print("<td>");
			out.print("<input type=\"text\" name=\"queryName\" value=\"" + searchForm.getQueryName() + "\">");
			out.print("</td>");
			
			out.print("<td>");
			out.print("<button onclick=\"javascript:doAction('updateSearch');\" title=\"" + MessageLocator.getMessage(request, "button.update") + "\">");
			out.print("<img src=\"images/table_save.png\"/>");
			out.println("</button>");
			out.print("</td>");
			
//			if(actionErrors != null) {
//				Iterator errorsIterator = actionErrors.get("queryName");
//				
//				if(errorsIterator.hasNext()) {
//					ActionMessage actionMessage = (ActionMessage)errorsIterator.next();
//					out.print("<td class=\"error\">");
//					out.print(MessageLocator.getMessage(request, actionMessage.getKey(), (String)actionMessage.getValues()[0]));
//					out.print("</td>");
//				}
//			}
			
			out.print("<td>");
			out.print("<select name=\"selectQuery\">");
			out.print("<option value=\"" + SearchQuery.MINUS_ONE + "\">" + MessageLocator.getMessage(request, "select") + "</option>");
			
			Iterator<Query> queriesIterator = queries.iterator();
			
			while(queriesIterator.hasNext()) {
				Query query = (Query)queriesIterator.next();
				if(searchForm.getSelectQuery() != null && searchForm.getSelectQuery().equals(String.valueOf(query.getId()))) {
					out.print("<option value=\"" + query.getId() + "\" selected=\"selected\">" + query.getName() + "</option>");
				} else {
					out.print("<option value=\"" + query.getId() + "\">" + query.getName() + "</option>");
				}
			}
			out.print("</select>");
			out.print("</td>");
			
			out.print("<td>");
			out.print("<button onclick=\"javascript:doAction('readSearch');\" title=\"" + MessageLocator.getMessage(request, "button.read") + "\">");
			out.print("<img src=\"images/table_refresh.png\"/>");
			out.println("</button>");
			out.print("</td>");
			
			out.print("<td>");
			out.print("<button onclick=\"javascript:doAction('deleteSearch');\" title=\"" + MessageLocator.getMessage(request, "button.delete") + "\">");
			out.print("<img src=\"images/table_delete.png\"/>");
			out.println("</button>");
			out.print("</td>");
			
			out.print("</tr>");
			out.print("</table>");
			
			//table2
			out.print("<table>");
			
			for(int i = 0; i < searchForm.getDefaultLines(); i++) {
				out.print("<tr>");
				
				if(searchForm.getComplexQuery().equals(SearchQuery.ONE)) {
					out.print("<td>");
					if(i > 0) {
						out.print("<select name=\"addAndOrs\">");
						out.print("<option value=\"" + SearchQuery.MINUS_ONE + "\">" + MessageLocator.getMessage(request, "select") + "</option>");
						for(int j = 0; j < SearchQuery.DEFAULT_ADD.length; j++) {
							if(searchForm.getAddAndOrs() != null && i - 1 < searchForm.getAddAndOrs().length && searchForm.getAddAndOrs()[i - 1].equals(String.valueOf(j)) && searchName.equals(searchForm.getSearchName())) {
								out.print("<option value=\"" + j + "\" selected=\"selected\">" + MessageLocator.getMessage(request, "search." + SearchQuery.DEFAULT_ADD[j].toLowerCase()) + "</option>");
							} else {
								out.print("<option value=\"" + j + "\">" + MessageLocator.getMessage(request, "search." + SearchQuery.DEFAULT_ADD[j].toLowerCase()) + "</option>");
							}
						}
						out.print("</select>");
					}
					out.print("</td>");
					
					out.print("<td>");
					out.print("<select name=\"openBrackets\">");
					out.print("<option value=\"" + SearchQuery.MINUS_ONE + "\">" + MessageLocator.getMessage(request, "select") + "</option>");
					for(int j = 0; j < SearchQuery.DEFAULT_OPEN.length; j++) {
						if(searchForm.getOpenBrackets() != null && i < searchForm.getOpenBrackets().length && searchForm.getOpenBrackets()[i].equals(String.valueOf(j)) && searchName.equals(searchForm.getSearchName())) {
							out.print("<option value=\"" + j + "\" selected=\"selected\">" + SearchQuery.DEFAULT_OPEN[j] + "</option>");
						} else {
							out.print("<option value=\"" + j + "\">" + SearchQuery.DEFAULT_OPEN[j] + "</option>");
						}
					}
					out.print("</select>");
					out.print("</td>");
				}
				
				out.print("<td>");
				out.print("<select name=\"names\">");
				out.print("<option value=\"" + SearchQuery.MINUS_ONE + "\">" + MessageLocator.getMessage(request, "select") + "</option>");
				for(int j = 0; j < getSearchSelect().getSearchParameters().length; j++) {
					SearchParameter searchParameter = getSearchSelect().getSearchParameter(j);
					if(searchForm.getNames() != null && i < searchForm.getNames().length && searchForm.getNames()[i].equals(String.valueOf(searchParameter.getName())) && searchName.equals(searchForm.getSearchName())) {
						out.print("<option value=\"" + searchParameter.getName() + "\" selected=\"selected\">" + MessageLocator.getMessage(request, searchParameter.getKey()) + "</option>");
					} else {
						out.print("<option value=\"" + searchParameter.getName() + "\">" + MessageLocator.getMessage(request, searchParameter.getKey()) + "</option>");
					}
						
				}
				out.print("</select>");
				out.print("</td>");
				
				out.print("<td>");
				out.print("<select name=\"operators\">");
				for(int j = 0; j < SearchQuery.DEFAULT_OPERATORS.length; j++) {
					if(searchForm.getOperators() != null && i < searchForm.getNames().length && searchForm.getOperators()[i].equals(String.valueOf(j)) && searchName.equals(searchForm.getSearchName())) {
						if(j < 6) {
							out.print("<option value=\"" + j + "\" selected=\"selected\">" + SearchQuery.DEFAULT_OPERATORS[j] + "</option>");
						} else {
							out.print("<option value=\"" + j + "\" selected=\"selected\">" + MessageLocator.getMessage(request, "search." + SearchQuery.DEFAULT_OPERATORS[j]) + "</option>");
						}
						
					} else {
						if(j < 6) {
							out.print("<option value=\"" + j + "\">" + SearchQuery.DEFAULT_OPERATORS[j] + "</option>");
						} else {
							out.print("<option value=\"" + j + "\">" + MessageLocator.getMessage(request, "search." + SearchQuery.DEFAULT_OPERATORS[j]) + "</option>");
						}
					}
				}
				out.print("</select>");
				out.print("</td>");
				
				out.print("<td>");
				if(searchForm.getValues() != null && i < searchForm.getValues().length && searchName.equals(searchForm.getSearchName())) {
					out.print("<input type=\"text\" name=\"values\" value=\"" + searchForm.getValues()[i] + "\" />");
				} else {
					out.print("<input type=\"text\" name=\"values\">");
				}
				out.print("</td>");
				
				if(searchForm.getComplexQuery().equals(SearchQuery.ONE)) {					
					out.print("<td>");
					out.print("<select name=\"closeBrackets\">");
					out.print("<option value=\"" + SearchQuery.MINUS_ONE + "\">" + MessageLocator.getMessage(request, "select") + "</option>");
					for(int j = 0; j < SearchQuery.DEFAULT_CLOSE.length; j++) {
						if(searchForm.getCloseBrackets() != null && i < searchForm.getCloseBrackets().length && searchForm.getCloseBrackets()[i].equals(String.valueOf(j)) && searchName.equals(searchForm.getSearchName())) {
							out.print("<option value=\"" + j + "\" selected=\"selected\">" + SearchQuery.DEFAULT_CLOSE[j] + "</option>");
						} else {
							out.print("<option value=\"" + j + "\">" + SearchQuery.DEFAULT_CLOSE[j] + "</option>");
						}
					}
					out.print("</select>");
					out.print("</td>");
				}
				
//				if(actionErrors != null) {
//					Iterator iterator = actionErrors.get(Query.FIELD + i);
//					
//					if(iterator.hasNext()) {
//						ActionMessage actionMessage = (ActionMessage)iterator.next();
//						out.print("<td class=\"error\">");
//						if(actionMessage.getValues() == null) {
//							out.print(MessageLocator.getMessage(request, actionMessage.getKey()));
//						} else {
//							out.print(MessageLocator.getMessage(request, actionMessage.getKey(), (String)actionMessage.getValues()[0]));
//						}
//						
//						out.print("</td>");
//					}
//				}
				
				out.print("</tr>");
			}
			
			out.print("</table>");
		}
		catch (Exception e) {
		}
		return EVAL_BODY_INCLUDE;
	}

	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
	
	private QueryService getQueryService() {
		return BaseSpringServiceLocator.getBean(QueryService.class);
	}
	
	private SearchSelect getSearchSelect() {
		return (SearchSelect)BaseSpringServiceLocator.getBean(searchService);
	}
	
}
