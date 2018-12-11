package be.luxuryoverdosis.framework.web.action.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionRedirect;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;
import org.jmesa.facade.TableFacade;
import org.jmesa.facade.TableFacadeFactory;

import be.luxuryoverdosis.framework.BaseConstants;
import be.luxuryoverdosis.framework.base.FileContentType;
import be.luxuryoverdosis.framework.business.query.SearchSelect;
import be.luxuryoverdosis.framework.business.service.BaseSpringServiceConstants;
import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;
import be.luxuryoverdosis.framework.business.service.interfaces.BatchService;
import be.luxuryoverdosis.framework.business.service.interfaces.UserService;
import be.luxuryoverdosis.framework.data.dto.FileDTO;
import be.luxuryoverdosis.framework.data.wrapperdto.ListUserWrapperDTO;
import be.luxuryoverdosis.framework.logging.Logging;
import be.luxuryoverdosis.framework.web.BaseWebConstants;
import be.luxuryoverdosis.framework.web.form.ListUserForm;
import be.luxuryoverdosis.framework.web.form.SearchUserForm;
import be.luxuryoverdosis.framework.web.jmesa.BatchJobExecutionJmesaTemplate;
import be.luxuryoverdosis.framework.web.jmesa.UserJmesaTemplate;
import be.luxuryoverdosis.framework.web.message.MessageLocator;
import be.luxuryoverdosis.framework.web.sessionmanager.SessionManager;

public class ListUserAction extends DispatchAction {
	public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Search");
		Logging.info(this, "End Search Success");
		
		return (mapping.findForward("search"));
	}
		
	public ActionForward listJmesa(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SearchUserForm searchForm = (SearchUserForm)request.getSession().getAttribute("searchUserForm");
		
		ListUserWrapperDTO listUserWrapperDTO = getUserService().getListUserWrapperDTO(getSearchSelect(), searchForm.getSearchCriteria());
		
		//JMesa Start	
		TableFacade tableFacade = TableFacadeFactory.createTableFacade(BaseWebConstants.USER_LIST, request, response);
		UserJmesaTemplate userJmesaTemplate = new UserJmesaTemplate(tableFacade, listUserWrapperDTO.getSearchUserList(), request);
		String html = userJmesaTemplate.render();
		if(html == null) {
			return null;
		}
        request.getSession().setAttribute(BaseWebConstants.USER_LIST, html);
		//JMesa End
		
		//JMesa Start	
		tableFacade = TableFacadeFactory.createTableFacade(BaseWebConstants.USER_EXPORT_LIST, request, response);
		BatchJobExecutionJmesaTemplate batchJobExecutionJmesaTemplate = new BatchJobExecutionJmesaTemplate(tableFacade, listUserWrapperDTO.getBatchJobInstanceExportList(), request);
		html = batchJobExecutionJmesaTemplate.render();
		if(html == null) {
			return null;
		}
        request.getSession().setAttribute(BaseWebConstants.USER_EXPORT_LIST, html);
		//JMesa End
        
		//JMesa Start	
		tableFacade = TableFacadeFactory.createTableFacade(BaseWebConstants.USER_IMPORT_LIST, request, response);
		batchJobExecutionJmesaTemplate = new BatchJobExecutionJmesaTemplate(tableFacade, listUserWrapperDTO.getBatchJobInstanceImportList(), request);
		html = batchJobExecutionJmesaTemplate.render();
		if(html == null) {
			return null;
		}
        request.getSession().setAttribute(BaseWebConstants.USER_IMPORT_LIST, html);
		//JMesa End
        
        return mapping.getInputForward();
	}
	
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin List");
		ActionMessages actionMessages = new ActionMessages();
		
		String previous = request.getParameter(BaseWebConstants.PREVIOUS);
		
		if(listJmesa(mapping, form, request, response) == null) {
			return null;
		}
		
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
		
		return (mapping.findForward("create"));
	}
	
	public ActionForward read(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Read");
		Logging.info(this, "End Read Success");
		
		return (mapping.findForward("read"));
	}
	
	public ActionForward readJob(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin ReadJob");
		Logging.info(this, "End ReadJob Success");
		
		SessionManager.putInSession(request, BaseWebConstants.JOB_NIVEAU, BaseConstants.JOB_NIVEAU_USER);
		
		return (mapping.findForward("readJob"));
	}
	
	public ActionForward exportUserJob(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin ExportUserJob");
		
		FileDTO fileDTO = new FileDTO(null, BaseConstants.JOB_EXPORT_USER_FILENAME, 0, FileContentType.TEXT_PLAIN);
		
		getBatchService().exportUserJob(fileDTO);
		
		ActionRedirect actionRedirect = new ActionRedirect(mapping.findForward("list"));
		actionRedirect.addParameter(BaseWebConstants.PREVIOUS, BaseWebConstants.JOB);
		
		Logging.info(this, "End ExportUserJob Success");
		
		return actionRedirect;
	}
	
	public ActionForward importUserJob(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin ImportUserJob");
		
		ListUserForm listUserForm = (ListUserForm) form;
		
		FormFile formFile = listUserForm.getFormFile();
		FileDTO fileDTO = new FileDTO(formFile.getFileData(), formFile.getFileName(), formFile.getFileSize(), formFile.getContentType());
		
		getBatchService().importUserJob(fileDTO);
		
		
		ActionRedirect actionRedirect = new ActionRedirect(mapping.findForward("list"));
		actionRedirect.addParameter(BaseWebConstants.PREVIOUS, BaseWebConstants.JOB);
		
		Logging.info(this, "End ImportUserJob Success");
		
		return actionRedirect;
	}
	
	private UserService getUserService() {
		return BaseSpringServiceLocator.getBean(UserService.class);
	}
	
	private BatchService getBatchService() {
		return BaseSpringServiceLocator.getBean(BatchService.class);
	}
	
	private SearchSelect getSearchSelect() {
		return (SearchSelect)BaseSpringServiceLocator.getBean(BaseSpringServiceConstants.SEARCH_USER);
	}
}
