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

import be.luxuryoverdosis.framework.base.FileContentType;
import be.luxuryoverdosis.framework.base.FileType;
import be.luxuryoverdosis.framework.base.tool.ResponseTool;
import be.luxuryoverdosis.framework.base.tool.StringTool;
import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;
import be.luxuryoverdosis.framework.business.service.interfaces.BatchJobExecutionParamsService;
import be.luxuryoverdosis.framework.business.service.interfaces.BatchJobInstanceService;
import be.luxuryoverdosis.framework.business.service.interfaces.BatchJobParamsService;
import be.luxuryoverdosis.framework.business.service.interfaces.BatchStepExecutionService;
import be.luxuryoverdosis.framework.business.service.interfaces.JobLogService;
import be.luxuryoverdosis.framework.business.service.interfaces.JobService;
import be.luxuryoverdosis.framework.data.to.BatchJobExecutionParams;
import be.luxuryoverdosis.framework.data.to.BatchJobInstance;
import be.luxuryoverdosis.framework.data.to.BatchJobParams;
import be.luxuryoverdosis.framework.data.to.BatchStepExecution;
import be.luxuryoverdosis.framework.data.to.Job;
import be.luxuryoverdosis.framework.data.to.JobLog;
import be.luxuryoverdosis.framework.logging.Logging;
import be.luxuryoverdosis.framework.web.BaseWebConstants;
import be.luxuryoverdosis.framework.web.action.ajaxaction.AjaxAction;
import be.luxuryoverdosis.framework.web.form.JobForm;
import be.luxuryoverdosis.framework.web.message.MessageLocator;
import be.luxuryoverdosis.framework.web.sessionmanager.SessionManager;

public class DetailJobAction extends AjaxAction {	
	
	private void storeDetailListsInSession(HttpServletRequest request, HttpServletResponse response, int jobInstanceId) {
		SessionManager.putInSession(request, BaseWebConstants.JOB_ID, jobInstanceId);
	}
	
	public ActionForward read(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Read");
		
		ActionMessages actionMessages = new ActionMessages();
		
		int jobInstanceId = Integer.parseInt(request.getParameter(BaseWebConstants.ID));
		storeDetailListsInSession(request, response, jobInstanceId);
		
		BatchJobInstance batchJobInstance = getBatchJobInstanceService().read(jobInstanceId);
		JobForm jobForm = (JobForm)form;
		jobForm.setJobName(batchJobInstance.getJobName());
		
		actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("read.success", MessageLocator.getMessage(request, "table.job")));
		saveMessages(request, actionMessages);
		
		Logging.info(this, "End Read Success");
		
		return mapping.getInputForward();
	}
	
	public ActionForward back(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Back");
		
		String jobNiveau = (String)SessionManager.getFromSession(request, BaseWebConstants.JOB_NIVEAU);
		
		ActionRedirect redirect = new ActionRedirect(mapping.findForward(BaseWebConstants.BACK + StringTool.toCamelCase(jobNiveau)));
			
		Logging.info(this, "End Back Success");
		
		return redirect;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin delete");
		
		int jobInstanceId = (Integer)SessionManager.getFromSession(request, BaseWebConstants.JOB_ID);
		
		getJobService().delete(new int[] {jobInstanceId});
		
		Logging.info(this, "End delete");
		
		return back(mapping, form, request, response);
	}
	
	public void downloadFile(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin downloadFile");
		
		int jobInstanceId = (Integer)SessionManager.getFromSession(request, BaseWebConstants.JOB_ID);
		
		Job job = getJobService().downloadFile(jobInstanceId);
		byte[] bytes = job.getFileData();
		
		ResponseTool.writeResponseForDownload(response, job.getFileName(), FileContentType.TEXT_PLAIN, bytes);
		
		Logging.info(this, "End downloadFile");
	}
	
	public void downloadFileLog(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin downloadFileLog");

		int jobLogId = Integer.parseInt(request.getParameter(BaseWebConstants.ID));

		JobLog jobLog = getJobLogService().downloadFile(jobLogId);
		byte[] bytes = jobLog.getFileData();
		
		ResponseTool.writeResponseForDownload(response, BaseWebConstants.DOWNLOAD_FILE_LOG + FileType.TXT, FileContentType.TEXT_PLAIN, bytes);
		
		Logging.info(this, "End downloadFileLog");
	}
	
	public ActionForward ajaxBatchJobParamsList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Logging.info(this, "Begin Ajax");
        
        int jobInstanceId = (Integer)SessionManager.getFromSession(request, BaseWebConstants.JOB_ID);
        
        ArrayList<BatchJobParams> batchJobParamsList = getBatchJobParamsService().list(jobInstanceId);
        if (batchJobParamsList.size() > 0) {
            super.sendAsJson(response, batchJobParamsList);
        }
        
        Logging.info(this, "End Ajax Success");
        
        return null;
    }
	
	public ActionForward ajaxBatchJobExecutionParamsList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Ajax");
		
		int jobInstanceId = (Integer)SessionManager.getFromSession(request, BaseWebConstants.JOB_ID);
		
		ArrayList<BatchJobExecutionParams> batchJobExecutionParamsList = getBatchJobExecutionParamsService().list(jobInstanceId);
		if (batchJobExecutionParamsList.size() > 0) {
			super.sendAsJson(response, batchJobExecutionParamsList);
		}
		
		Logging.info(this, "End Ajax Success");
		
		return null;
	}
	
	public ActionForward ajaxBatchJobStepExecutionList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Ajax");
		
		int jobInstanceId = (Integer)SessionManager.getFromSession(request, BaseWebConstants.JOB_ID);
		
		ArrayList<BatchStepExecution> batchJobStepExecutionList = getBatchStepExecutionService().list(jobInstanceId);
		if (batchJobStepExecutionList.size() > 0) {
			super.sendAsJson(response, batchJobStepExecutionList);
		}
		
		Logging.info(this, "End Ajax Success");
		
		return null;
	}
	
	public ActionForward ajaxJobLogList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logging.info(this, "Begin Ajax");
		
		int jobInstanceId = (Integer)SessionManager.getFromSession(request, BaseWebConstants.JOB_ID);
		
		ArrayList<JobLog> joblogList = getJobLogService().listForBatch(jobInstanceId);
		if (joblogList.size() > 0) {
			super.sendAsJson(response, joblogList);
		}
		
		Logging.info(this, "End Ajax Success");
		
		return null;
	}
	
	private JobService getJobService() {
		return BaseSpringServiceLocator.getBean(JobService.class);
	}
	
	private JobLogService getJobLogService() {
		return BaseSpringServiceLocator.getBean(JobLogService.class);
	}
	
	private BatchJobInstanceService getBatchJobInstanceService() {
		return BaseSpringServiceLocator.getBean(BatchJobInstanceService.class);
	}
	
	private BatchJobParamsService getBatchJobParamsService() {
		return BaseSpringServiceLocator.getBean(BatchJobParamsService.class);
	}
	
	private BatchJobExecutionParamsService getBatchJobExecutionParamsService() {
		return BaseSpringServiceLocator.getBean(BatchJobExecutionParamsService.class);
	}
	
	private BatchStepExecutionService getBatchStepExecutionService() {
		return BaseSpringServiceLocator.getBean(BatchStepExecutionService.class);
	}
}
