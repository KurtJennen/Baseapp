package be.luxuryoverdosis.framework.web.action.security;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionRedirect;
import org.apache.struts.upload.FormFile;

import be.luxuryoverdosis.framework.BaseConstants;
import be.luxuryoverdosis.framework.base.FileContentType;
import be.luxuryoverdosis.framework.business.query.SearchSelect;
import be.luxuryoverdosis.framework.business.service.BaseSpringServiceConstants;
import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;
import be.luxuryoverdosis.framework.business.service.interfaces.BatchJobInstanceService;
import be.luxuryoverdosis.framework.business.service.interfaces.BatchService;
import be.luxuryoverdosis.framework.business.service.interfaces.SearchService;
import be.luxuryoverdosis.framework.data.dto.BatchJobInstanceDTO;
import be.luxuryoverdosis.framework.data.dto.FileDTO;
import be.luxuryoverdosis.framework.logging.Logging;
import be.luxuryoverdosis.framework.web.BaseWebConstants;
import be.luxuryoverdosis.framework.web.action.ajax.AjaxAction;
import be.luxuryoverdosis.framework.web.form.ListUserForm;
import be.luxuryoverdosis.framework.web.form.SearchUserForm;
import be.luxuryoverdosis.framework.web.message.MessageLocator;
import be.luxuryoverdosis.framework.web.sessionmanager.SessionManager;

public class ListUserAction extends AjaxAction {
	public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Search");
		Logging.info(this, "End Search Success");
		
		return (mapping.findForward(BaseWebConstants.SEARCH));
	}
		
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin List");
		ActionMessages actionMessages = new ActionMessages();
		
		setSelectedTab(form, request);
		
		String previous = request.getParameter(BaseWebConstants.PREVIOUS);
		
		if(BaseWebConstants.DELETE.equals(previous)) {
			actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("delete.success", MessageLocator.getMessage(request, "table.user")));
		}
		if(BaseWebConstants.JOB.equals(previous)) {
			actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("job.success", MessageLocator.getMessage(request, "table.user")));
		}
		actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("list.success", MessageLocator.getMessage(request, "table.user")));
		saveMessages(request, actionMessages);
		
		Logging.info(this, "End List Success");
		
		return mapping.getInputForward();
	}
	
	
	public ActionForward create(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Create");
		Logging.info(this, "End Create Success");
		
		return (mapping.findForward(BaseWebConstants.CREATE));
	}
	
	public ActionForward readExportJob(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin ReadJob");
		Logging.info(this, "End ReadJob Success");
		
		ListUserForm userForm = (ListUserForm) form;
		int jobId = userForm.getSelectedIdsExportJob()[0];
		
		return readJob(mapping, request, jobId);
	}
	
	public ActionForward readImportJob(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin ReadJob");
		Logging.info(this, "End ReadJob Success");
		
		ListUserForm userForm = (ListUserForm) form;
		int jobId = userForm.getSelectedIdsImportJob()[0];
		
		return readJob(mapping, request, jobId);
	}

	private ActionForward readJob(ActionMapping mapping, HttpServletRequest request, int jobId) {
		SessionManager.putInSession(request, BaseWebConstants.JOB_NIVEAU, BaseConstants.JOB_NIVEAU_USER);
		
        ActionRedirect actionRedirect = new ActionRedirect(mapping.findForward(BaseWebConstants.READ_JOB));
        actionRedirect.addParameter(BaseWebConstants.ID, jobId);
		
		return actionRedirect;
	}
	
	public ActionForward exportUserJob(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin ExportUserJob");
		
		ListUserForm listUserForm = (ListUserForm) form;
		
		FileDTO fileDTO = new FileDTO(null, BaseConstants.JOB_EXPORT_USER_FILENAME, 0, FileContentType.TEXT_PLAIN);
		
		getBatchService().exportUserJob(fileDTO);
		
		ActionRedirect actionRedirect = new ActionRedirect(mapping.findForward(BaseWebConstants.LIST));
		actionRedirect.addParameter(BaseWebConstants.PREVIOUS, BaseWebConstants.JOB);
		actionRedirect.addParameter(BaseWebConstants.SELECTED_TAB, listUserForm.getSelectedTab());
		
		Logging.info(this, "End ExportUserJob Success");
		
		return actionRedirect;
	}
	
	public ActionForward importUserJob(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin ImportUserJob");
		
		ListUserForm listUserForm = (ListUserForm) form;
		
		FormFile formFile = listUserForm.getFormFile();
		FileDTO fileDTO = new FileDTO(formFile.getFileData(), formFile.getFileName(), formFile.getFileSize(), formFile.getContentType());
		
		getBatchService().importUserJob(fileDTO);
		
		ActionRedirect actionRedirect = new ActionRedirect(mapping.findForward(BaseWebConstants.LIST));
		actionRedirect.addParameter(BaseWebConstants.PREVIOUS, BaseWebConstants.JOB);
		actionRedirect.addParameter(BaseWebConstants.SELECTED_TAB, listUserForm.getSelectedTab());
		
		Logging.info(this, "End ImportUserJob Success");
		
		return actionRedirect;
	}
	
	public ActionForward ajaxList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Ajax");
	
		SearchUserForm searchForm = (SearchUserForm)request.getSession().getAttribute(BaseWebConstants.SEARCH_USER_FORM);
	
		ArrayList<Object> userList = getSearchService().search(getSearchSelect(), searchForm.getSearchCriteria());
		if (userList.size() > 0) {
			super.setIds(request, userList, BaseWebConstants.USER_IDS);
			super.sendAsJson(response, userList);
		}
	
		Logging.info(this, "End Ajax Success");
	    
	    return null;
	}
	
	public ActionForward ajaxListExportJob(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Ajax");
		
		ajaxListJob(response, BaseConstants.JOB_EXPORT_USER);
		
		Logging.info(this, "End Ajax Success");
		
		return null;
	}
	
	public ActionForward ajaxListImportJob(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Ajax");
		
		ajaxListJob(response, BaseConstants.JOB_IMPORT_USER);
		
		Logging.info(this, "End Ajax Success");
		
		return null;
	}

	private void ajaxListJob(HttpServletResponse response, String jobName) throws Exception {
		ArrayList<BatchJobInstanceDTO> userListJob = getBatchJobInstanceService().list(jobName);
		if (userListJob.size() > 0) {
			super.sendAsJson(response, userListJob);
		}
	}
	
	private BatchService getBatchService() {
		return BaseSpringServiceLocator.getBean(BatchService.class);
	}
	
	private SearchService getSearchService() {
		return BaseSpringServiceLocator.getBean(SearchService.class);
	}
	
	private BatchJobInstanceService getBatchJobInstanceService() {
		return BaseSpringServiceLocator.getBean(BatchJobInstanceService.class);
	}
	
	private SearchSelect getSearchSelect() {
		return (SearchSelect)BaseSpringServiceLocator.getBean(BaseSpringServiceConstants.SEARCH_USER);
	}
}
