package be.luxuryoverdosis.framework.web.action.search;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import be.luxuryoverdosis.framework.base.Query;
import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;
import be.luxuryoverdosis.framework.business.service.interfaces.QueryService;
import be.luxuryoverdosis.framework.data.dto.QueryDTO;
import be.luxuryoverdosis.framework.logging.Logging;
import be.luxuryoverdosis.framework.web.action.ajaxaction.AjaxAction;
import be.luxuryoverdosis.framework.web.form.SearchForm;
import be.luxuryoverdosis.framework.web.message.MessageLocator;

public abstract class SearchAction extends AjaxAction {
	public abstract String getSearchService();
	
	public ActionForward createLine(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin CreateLine");
		ActionMessages actionMessages = new ActionMessages();
		
		SearchForm searchForm = (SearchForm) form;
		searchForm.setDefaultLines(searchForm.getDefaultLines() + 1);
		
		actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("create.success", MessageLocator.getMessage(request, "table.queryparam")));
		saveMessages(request, actionMessages);
		
		Logging.info(this, "End CreateLine Success");
		
		return mapping.getInputForward();
	}
	
	public ActionForward deleteLine(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin DeleteLine");
		ActionMessages actionMessages = new ActionMessages();
		
		SearchForm searchForm = (SearchForm) form;
		searchForm.setDefaultLines(searchForm.getDefaultLines() - 1);
		
		actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("delete.success", MessageLocator.getMessage(request, "table.queryparam")));
		saveMessages(request, actionMessages);
		
		Logging.info(this, "End DeleteLine Success");
		
		return mapping.getInputForward();
	}
	
	public ActionForward complexLine(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin ComplexLine");
		ActionMessages actionMessages = new ActionMessages();
		
		SearchForm searchForm = (SearchForm) form;
		
		if(Query.ZERO.equals(searchForm.getComplexQuery())) {
			searchForm.setComplexQuery(Query.ONE);
			actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("complex.success", MessageLocator.getMessage(request, "table.query")));
		} else {
			searchForm.setComplexQuery(Query.ZERO);
			actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("complex.success", MessageLocator.getMessage(request, "table.query")));
		}
		
		saveMessages(request, actionMessages);
		
		Logging.info(this, "End ComplexLine Success");
		
		return mapping.getInputForward();
	}
	
	public ActionForward updateSearch(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Update");
		ActionMessages actionMessages = new ActionMessages();
		
		SearchForm searchForm = (SearchForm) form;
		
		//QueryService queryService = (QueryService)SpringServiceLocator.getBean(SpringServiceConstants.QUERY_SERVICE);
		QueryService queryService = BaseSpringServiceLocator.getBean(QueryService.class);
		
		QueryDTO queryDTO = new QueryDTO();
		queryDTO.setName(searchForm.getQueryName());
		queryDTO.setType(this.getSearchService());
		queryDTO.setComplex(searchForm.getComplexQuery());
		queryDTO.setParameters(searchForm.getSearchCriteria().getParameters());
		queryDTO.setOperators(searchForm.getSearchCriteria().getOperators());
		queryDTO.setValues(searchForm.getSearchCriteria().getValues());
		queryDTO.setAddAndOrs(searchForm.getSearchCriteria().getAddAndOrs());
		queryDTO.setOpenBrackets(searchForm.getSearchCriteria().getOpenBrackets());
		queryDTO.setCloseBrackets(searchForm.getSearchCriteria().getCloseBrackets());
		
		long count = queryService.countAndCreateOrUpdateDTO(searchForm.getQueryName(), this.getSearchService(), queryDTO);
		if(count == 0) {
			actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("save.success", MessageLocator.getMessage(request, "table.query")));
		} else {
			actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("update.success", MessageLocator.getMessage(request, "table.query")));
		}
		
		saveMessages(request, actionMessages);
		
		searchForm.setSelectQuery(String.valueOf(queryDTO.getId()));
		
		Logging.info(this, "End Update Success");
		
		return mapping.getInputForward();
	}
	
	public ActionForward readSearch(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Read");
		ActionMessages actionMessages = new ActionMessages();
		
		SearchForm searchForm = (SearchForm) form;
		
		if(!Query.MINUS_ONE.equals(searchForm.getSelectQuery())) {
			//QueryService queryService = (QueryService)SpringServiceLocator.getBean(SpringServiceConstants.QUERY_SERVICE);
			QueryService queryService = BaseSpringServiceLocator.getBean(QueryService.class);
			
			Integer id = Integer.valueOf(searchForm.getSelectQuery());
			
			QueryDTO queryDTO = queryService.readDTO(id);
			
			searchForm.setComplexQuery(queryDTO.getComplex());
			searchForm.setParameters(queryDTO.getParameters());
			searchForm.setOperators(queryDTO.getOperators());
			searchForm.setValues(queryDTO.getValues());
			searchForm.setAddAndOrs(queryDTO.getAddAndOrs());
			searchForm.setOpenBrackets(queryDTO.getOpenBrackets());
			searchForm.setCloseBrackets(queryDTO.getCloseBrackets());
			searchForm.setDefaultLines(searchForm.getParameters().length);
		}
		
		actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("read.success", MessageLocator.getMessage(request, "table.query")));
		saveMessages(request, actionMessages);
		
		Logging.info(this, "End Read Success");
		
		return mapping.getInputForward();
	}
	
	public ActionForward deleteSearch(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Delete");
		ActionMessages actionMessages = new ActionMessages();
		
		SearchForm searchForm = (SearchForm) form;
		
		if(!Query.MINUS_ONE.equals(searchForm.getSelectQuery())) {
			//QueryService queryService = (QueryService)SpringServiceLocator.getBean(SpringServiceConstants.QUERY_SERVICE);
			QueryService queryService = BaseSpringServiceLocator.getBean(QueryService.class);
			queryService.delete(Integer.valueOf(searchForm.getSelectQuery()));
		}
		
		actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("delete.success", MessageLocator.getMessage(request, "table.query")));
		saveMessages(request, actionMessages);
		
		Logging.info(this, "End Delete Success");
		
		return mapping.getInputForward();
	}
	
	public ActionForward resetSearch(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Reset");
		ActionMessages actionMessages = new ActionMessages();
		
		SearchForm searchForm = (SearchForm) form;
		searchForm.setOperators(null);
		searchForm.setParameters(null);
		searchForm.setValues(null);
		searchForm.setOpenBrackets(null);
		searchForm.setCloseBrackets(null);
		searchForm.setAddAndOrs(null);
		searchForm.setSearchName("");
		searchForm.setQueryName("");
		searchForm.setSelectQuery("");
		searchForm.setComplexQuery(Query.ZERO);
		searchForm.setDefaultLines(2);
		
		actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("reset.success", MessageLocator.getMessage(request, "table.query")));
		saveMessages(request, actionMessages);
		
		Logging.info(this, "End Reset Success");
		
		return mapping.getInputForward();
	}
	
}
