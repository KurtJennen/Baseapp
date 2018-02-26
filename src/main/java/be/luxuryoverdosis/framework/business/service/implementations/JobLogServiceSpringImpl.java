package be.luxuryoverdosis.framework.business.service.implementations;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.luxuryoverdosis.framework.BaseConstants;
import be.luxuryoverdosis.framework.base.tool.BlobTool;
import be.luxuryoverdosis.framework.business.service.interfaces.JobLogService;
import be.luxuryoverdosis.framework.data.dao.interfaces.BatchJobInstanceHibernateDAO;
import be.luxuryoverdosis.framework.data.dao.interfaces.BatchJobParamsHibernateDAO;
import be.luxuryoverdosis.framework.data.dao.interfaces.JobLogHibernateDAO;
import be.luxuryoverdosis.framework.data.to.BatchJobInstance;
import be.luxuryoverdosis.framework.data.to.BatchJobParamsTO;
import be.luxuryoverdosis.framework.data.to.JobLog;
import be.luxuryoverdosis.framework.logging.Logging;

@Service
public class JobLogServiceSpringImpl implements JobLogService {
	@Resource
	private JobLogHibernateDAO jobLogHibernateDAO;
	@Resource
	private BatchJobInstanceHibernateDAO batchJobInstanceHibernateDAO;
	@Resource
	private BatchJobParamsHibernateDAO batchJobParamsHibernateDAO;
	
	
	private static final int LENGTH = 256;
	
	@Transactional
	public JobLog createOrUpdate(final JobLog jobLog) {
		Logging.info(this, "Begin createJobLog");
		JobLog result = null;
		
		if(jobLog.getInput() == null) {
			jobLog.setInput(StringUtils.EMPTY);
		}
		if(jobLog.getOutput() == null) {
			jobLog.setOutput(StringUtils.EMPTY);
		}
		if(jobLog.getInput().length() > LENGTH) {
			jobLog.setInput(jobLog.getInput().substring(0, LENGTH));
		}
		if(jobLog.getOutput().length() > LENGTH) {
			jobLog.setOutput(jobLog.getOutput().substring(0, LENGTH));
		}
		
		result = jobLogHibernateDAO.createOrUpdate(jobLog);
		Logging.info(this, "End createJobLog");
		return result;
	}
	
	@Transactional(readOnly=true)
	public JobLog read(final int id) {
		Logging.info(this, "Begin readJobLog");
		JobLog result = null;
		result = jobLogHibernateDAO.read(id);
		Logging.info(this, "End readJobLog");
		return result;
	}
	
	@Transactional
	public void delete(final int id) {
		Logging.info(this, "Begin deleteJobLog");
		jobLogHibernateDAO.delete(id);
		Logging.info(this, "End deleteJobLog");
	}
	
	@Transactional
	public void deleteForJob(final int jobId) {
		Logging.info(this, "Begin deleteForJobJobLog");
		jobLogHibernateDAO.deleteForJob(jobId);
		Logging.info(this, "End deleteForJobJobLog");
	}
	
	@Transactional(readOnly=true)
	public JobLog downloadFile(int jobLogId) {
		Logging.info(this, "Begin downloadFileLog");
		
		JobLog jobLog = null;
		
		jobLog = read(jobLogId);
		
		if(jobLog != null) {
			byte[] bytes = BlobTool.convertBlobToBytes(jobLog.getFile());
			jobLog.setFileData(bytes);
		}
		
		Logging.info(this, "End downloadFileLog");
		
		return jobLog;
	}

	@Transactional(readOnly=true)
	public ArrayList<JobLog> list(final int jobId) {
		Logging.info(this, "Begin listJobLog");
		ArrayList<JobLog> arrayList = null;
		arrayList = jobLogHibernateDAO.list(jobId);
		Logging.info(this, "End listJobLog");
		return arrayList;
	}
	
	@Transactional(readOnly=true)
	public ArrayList<JobLog> listForBatch(final int jobId) {
		Logging.info(this, "Begin listJobLog");
		ArrayList<JobLog> arrayList = null;
		
		BatchJobInstance batchJobInstance = batchJobInstanceHibernateDAO.readJobExecution(jobId);
		
		BatchJobParamsTO  batchJobParamsTO = batchJobParamsHibernateDAO.getJobParam(batchJobInstance.getId(), BaseConstants.JOB_ID);
		
		arrayList = jobLogHibernateDAO.list((int)batchJobParamsTO.getLongValue());
		Logging.info(this, "End listJobLog");
		return arrayList;
	}
	
}
