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
import org.apache.struts.actions.DispatchAction;
import org.jmesa.facade.TableFacade;
import org.jmesa.facade.TableFacadeFactory;

import be.luxuryoverdosis.framework.BaseConstants;
import be.luxuryoverdosis.framework.base.FileContentType;
import be.luxuryoverdosis.framework.base.FileType;
import be.luxuryoverdosis.framework.base.tool.ResponseTool;
import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;
import be.luxuryoverdosis.framework.business.service.interfaces.BatchJobInstanceService;
import be.luxuryoverdosis.framework.business.service.interfaces.BatchJobParamsService;
import be.luxuryoverdosis.framework.business.service.interfaces.BatchStepExecutionService;
import be.luxuryoverdosis.framework.business.service.interfaces.JobLogService;
import be.luxuryoverdosis.framework.business.service.interfaces.JobService;
import be.luxuryoverdosis.framework.data.to.BatchJobInstance;
import be.luxuryoverdosis.framework.data.to.BatchJobParams;
import be.luxuryoverdosis.framework.data.to.BatchStepExecution;
import be.luxuryoverdosis.framework.data.to.JobLog;
import be.luxuryoverdosis.framework.data.to.Job;
import be.luxuryoverdosis.framework.logging.Logging;
import be.luxuryoverdosis.framework.web.BaseWebConstants;
import be.luxuryoverdosis.framework.web.form.JobForm;
import be.luxuryoverdosis.framework.web.jmesa.BatchJobParamsJmesaTemplate;
import be.luxuryoverdosis.framework.web.jmesa.BatchJobStepExecutionJmesaTemplate;
import be.luxuryoverdosis.framework.web.jmesa.JobLogJmesaTemplate;
import be.luxuryoverdosis.framework.web.message.MessageLocator;
import be.luxuryoverdosis.framework.web.sessionmanager.SessionManager;

public class DetailJobAction extends DispatchAction {	
	
	private void storeDetailListsInSession(HttpServletRequest request, HttpServletResponse response, int id) {
		SessionManager.putInSession(request, BaseWebConstants.JOB_ID, id);
	}
	
//	public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		Logging.info(this, "Begin Search");
//		Logging.info(this, "End Search Success");
//		
//		return (mapping.findForward("search"));
//	}
//	
//	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		Logging.info(this, "Begin List");
//		Logging.info(this, "End List Success");
//		
//		return (mapping.findForward("list"));
//	}
	
	public ActionForward listJmesa(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = (Integer)SessionManager.getFromSession(request, BaseWebConstants.JOB_ID);
		
		JobForm jobForm = (JobForm) form;
		
		BatchJobInstanceService batchJobInstanceService = BaseSpringServiceLocator.getBean(BatchJobInstanceService.class);
		BatchJobInstance batchJobInstance = batchJobInstanceService.read(id);
		
		jobForm.setJobName(batchJobInstance.getJobName());
		
		BatchJobParamsService batchJobParamsService = BaseSpringServiceLocator.getBean(BatchJobParamsService.class);
		ArrayList<BatchJobParams> batchJobParamsList = new ArrayList<BatchJobParams>();
		batchJobParamsList = batchJobParamsService.list(id);
		
		//JMesa Start	
		TableFacade tableFacade = TableFacadeFactory.createTableFacade(BaseWebConstants.BATCH_JOB_PARAMS_LIST, request, response);
		BatchJobParamsJmesaTemplate batchJobParamsJmesaTemplate = new BatchJobParamsJmesaTemplate(tableFacade, batchJobParamsList, request);
		String html = batchJobParamsJmesaTemplate.render();
		if(html == null) {
			return null;
		}
        request.setAttribute(BaseWebConstants.BATCH_JOB_PARAMS_LIST, html);
		//JMesa End
        
        BatchStepExecutionService batchStepExecutionService = BaseSpringServiceLocator.getBean(BatchStepExecutionService.class);
		ArrayList<BatchStepExecution> batchStepExecutionList = new ArrayList<BatchStepExecution>();
		batchStepExecutionList = batchStepExecutionService.list(id);
		
		//JMesa Start	
		tableFacade = TableFacadeFactory.createTableFacade(BaseWebConstants.BATCH_JOB_STEP_EXECUTION_LIST, request, response);
		BatchJobStepExecutionJmesaTemplate batchJobStepExecutionJmesaTemplate = new BatchJobStepExecutionJmesaTemplate(tableFacade, batchStepExecutionList, request);
		html = batchJobStepExecutionJmesaTemplate.render();
		if(html == null) {
			return null;
		}
        request.setAttribute(BaseWebConstants.BATCH_JOB_STEP_EXECUTION_LIST, html);
		//JMesa End
        
        JobLogService jobLogService = BaseSpringServiceLocator.getBean(JobLogService.class);
        ArrayList<JobLog> jobLogList = new ArrayList<JobLog>();
        jobLogList = jobLogService.listForBatch(id);
        
        //JMesa Start	
  		tableFacade = TableFacadeFactory.createTableFacade(BaseWebConstants.JOB_LOG_LIST, request, response);
  		JobLogJmesaTemplate jobLogJmesaTemplate = new JobLogJmesaTemplate(tableFacade, jobLogList, request);
  		html = jobLogJmesaTemplate.render();
  		if(html == null) {
  			return null;
  		}
          request.setAttribute(BaseWebConstants.JOB_LOG_LIST, html);
  		//JMesa End
        
        return mapping.getInputForward();
	}
	
	public ActionForward read(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Read");
		ActionMessages actionMessages = new ActionMessages();
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		storeDetailListsInSession(request, response, id);
		
		if(listJmesa(mapping, form, request, response) == null) {
			return null;
		}
		
		actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("read.success", MessageLocator.getMessage(request, "table.job")));
		saveMessages(request, actionMessages);
		
		Logging.info(this, "End Read Success");
		
		return mapping.getInputForward();
	}
	
	public ActionForward back(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Back");
		
		ActionRedirect redirect = null;
		
		String jobNiveau = (String)SessionManager.getFromSession(request, BaseWebConstants.JOB_NIVEAU);
		if(BaseConstants.JOB_NIVEAU_USER.equals(jobNiveau)) {
			redirect = new ActionRedirect(mapping.findForward("back" + jobNiveau.charAt(0) + jobNiveau.substring(1).toLowerCase()));
		}
			
		Logging.info(this, "End Back Success");
		
		return redirect;
	}

	public void downloadFile(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		listJmesa(mapping, form, request, response);
		
		int id = (Integer)SessionManager.getFromSession(request, BaseWebConstants.JOB_ID);
		
		Logging.info(this, "Begin downloadFile");
		
		JobService jobService = BaseSpringServiceLocator.getBean(JobService.class);
		Job job = jobService.downloadFile(id);
		byte[] bytes = job.getFileData();
		
		ResponseTool.writeResponseForDownload(response, job.getFileName(), FileContentType.TEXT_PLAIN, bytes);
		
		Logging.info(this, "End downloadFile");
	}
	
	public void downloadFileLog(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		listJmesa(mapping, form, request, response);
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		Logging.info(this, "Begin downloadFileLog");
		
		JobLogService jobLogService = BaseSpringServiceLocator.getBean(JobLogService.class);
		JobLog jobLog = jobLogService.downloadFile(id);
		byte[] bytes = jobLog.getFileData();
		
		ResponseTool.writeResponseForDownload(response, BaseWebConstants.DOWNLOAD_FILE_LOG + "." + FileType.TXT, FileContentType.TEXT_PLAIN, bytes);
		
		Logging.info(this, "End downloadFileLog");
	}
}
