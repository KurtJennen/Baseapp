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
import be.luxuryoverdosis.framework.business.service.interfaces.JobService;
import be.luxuryoverdosis.framework.business.service.interfaces.SearchService;
import be.luxuryoverdosis.framework.data.dto.BatchJobInstanceDTO;
import be.luxuryoverdosis.framework.data.dto.FileDTO;
import be.luxuryoverdosis.framework.logging.Logging;
import be.luxuryoverdosis.framework.web.BaseWebConstants;
import be.luxuryoverdosis.framework.web.action.ajaxaction.AjaxAction;
import be.luxuryoverdosis.framework.web.form.ListUserForm;
import be.luxuryoverdosis.framework.web.form.SearchUserForm;
import be.luxuryoverdosis.framework.web.message.MessageLocator;
import be.luxuryoverdosis.framework.web.sessionmanager.SessionManager;

public class ListUserAction extends AjaxAction {
	public ActionForward search(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Search");
		Logging.info(this, "End Search Success");
		
		return (mapping.findForward(BaseWebConstants.SEARCH));
	}
		
	public ActionForward list(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin List");
		ActionMessages actionMessages = new ActionMessages();
		
		setSelectedTab(form, request);
		
		String previous = request.getParameter(BaseWebConstants.PREVIOUS);
		
		if (BaseWebConstants.DELETE.equals(previous)) {
			actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("delete.success", MessageLocator.getMessage(request, "table.user")));
		}
		if (BaseWebConstants.JOB.equals(previous)) {
			actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("job.success", MessageLocator.getMessage(request, "table.user")));
		}
		actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("list.success", MessageLocator.getMessage(request, "table.user")));
		saveMessages(request, actionMessages);
		
		Logging.info(this, "End List Success");
		
		return mapping.getInputForward();
	}
	
	
	public ActionForward create(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Create");
		Logging.info(this, "End Create Success");
		
		return (mapping.findForward(BaseWebConstants.CREATE));
	}
	
	public ActionForward readExportUserJob(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin ReadExportUserJob");
		Logging.info(this, "End ReadExportUserJob Success");
		
		ListUserForm userForm = (ListUserForm) form;
		int jobId = userForm.getSelectedIdsExportUserJob()[0];
		
		return readJob(mapping, request, jobId);
	}
	
	public ActionForward readImportUserJob(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin ReadImportUserJob");
		Logging.info(this, "End ReadImportUserJob Success");
		
		ListUserForm userForm = (ListUserForm) form;
		int jobId = userForm.getSelectedIdsImportUserJob()[0];
		
		return readJob(mapping, request, jobId);
	}

	private ActionForward readJob(final ActionMapping mapping, final HttpServletRequest request, final int jobId) {
		SessionManager.putInSession(request, BaseWebConstants.JOB_NIVEAU, BaseConstants.JOB_NIVEAU_USER);
		
        ActionRedirect actionRedirect = new ActionRedirect(mapping.findForward(BaseWebConstants.READ_JOB));
        actionRedirect.addParameter(BaseWebConstants.ID, jobId);
		
		return actionRedirect;
	}
	
	public ActionForward deleteExportUserJob(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin DeleteExportUserJob");
		
		ListUserForm userForm = (ListUserForm) form;
		getJobService().delete(userForm.getSelectedIdsExportUserJob());
		
		Logging.info(this, "End DeleteExportUserJob");
		
		return mapping.getInputForward();
	}
	
	public ActionForward deleteImportUserJob(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin DeleteImportUserJob");
		
		ListUserForm userForm = (ListUserForm) form;
		getJobService().delete(userForm.getSelectedIdsImportUserJob());
		
		Logging.info(this, "End DeleteImportUserJob");
		
		return mapping.getInputForward();
	}
	
	public ActionForward createExportUserJob(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin CreateExportUserJob");
		
		ListUserForm listUserForm = (ListUserForm) form;
		
		FileDTO fileDTO = new FileDTO(null, BaseConstants.JOB_EXPORT_USER_FILENAME, 0, FileContentType.TEXT_PLAIN);
		
		getBatchService().exportUserJob(fileDTO);
		
		ActionRedirect actionRedirect = new ActionRedirect(mapping.findForward(BaseWebConstants.LIST));
		actionRedirect.addParameter(BaseWebConstants.PREVIOUS, BaseWebConstants.JOB);
		actionRedirect.addParameter(BaseWebConstants.SELECTED_TAB, listUserForm.getSelectedTab());
		
		Logging.info(this, "End CreateExportUserJob Success");
		
		return actionRedirect;
	}
	
	public ActionForward createImportUserJob(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin CreateImportUserJob");
		
		ListUserForm listUserForm = (ListUserForm) form;
		
		FormFile formFile = listUserForm.getFormFile();
		FileDTO fileDTO = new FileDTO(formFile.getFileData(), formFile.getFileName(), formFile.getFileSize(), formFile.getContentType());
		
		getBatchService().importUserJob(fileDTO);
		
		ActionRedirect actionRedirect = new ActionRedirect(mapping.findForward(BaseWebConstants.LIST));
		actionRedirect.addParameter(BaseWebConstants.PREVIOUS, BaseWebConstants.JOB);
		actionRedirect.addParameter(BaseWebConstants.SELECTED_TAB, listUserForm.getSelectedTab());
		
		Logging.info(this, "End CreateImportUserJob Success");
		
		return actionRedirect;
	}
	
	public ActionForward ajaxList(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Ajax");
	
		SearchUserForm searchForm = (SearchUserForm) request.getSession().getAttribute(BaseWebConstants.SEARCH_USER_FORM);
	
		ArrayList<Object> userList = getSearchService().search(getSearchSelect(), searchForm.getSearchCriteria());
		if (userList.size() > 0) {
			super.setIds(request, userList, BaseWebConstants.USER_IDS);
			super.sendAsJson(response, userList);
		}
	
		Logging.info(this, "End Ajax Success");
	    
	    return null;
	}
	
	public ActionForward ajaxListExportUserJob(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Ajax");
		
		ajaxListJob(response, BaseConstants.JOB_EXPORT_USER);
		
		Logging.info(this, "End Ajax Success");
		
		return null;
	}
	
	public ActionForward ajaxListImportUserJob(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Ajax");
		
		ajaxListJob(response, BaseConstants.JOB_IMPORT_USER);
		
		Logging.info(this, "End Ajax Success");
		
		return null;
	}

	private void ajaxListJob(final HttpServletResponse response, final String jobName) throws Exception {
		ArrayList<BatchJobInstanceDTO> userListJob = getBatchJobInstanceService().list(jobName);
		if (userListJob.size() > 0) {
			super.sendAsJson(response, userListJob);
		}
	}
	
	private JobService getJobService() {
		return BaseSpringServiceLocator.getBean(JobService.class);
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
		return (SearchSelect) BaseSpringServiceLocator.getBean(BaseSpringServiceConstants.SEARCH_USER);
	}
}
