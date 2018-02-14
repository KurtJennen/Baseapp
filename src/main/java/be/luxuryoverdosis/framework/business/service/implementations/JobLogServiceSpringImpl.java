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
import be.luxuryoverdosis.framework.data.to.BatchJobInstanceTO;
import be.luxuryoverdosis.framework.data.to.BatchJobParamsTO;
import be.luxuryoverdosis.framework.data.to.JobLogTO;
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
	public JobLogTO createOrUpdate(final JobLogTO jobLogTO) {
		Logging.info(this, "Begin createJobLog");
		JobLogTO result = null;
		
		if(jobLogTO.getInput() == null) {
			jobLogTO.setInput(StringUtils.EMPTY);
		}
		if(jobLogTO.getOutput() == null) {
			jobLogTO.setOutput(StringUtils.EMPTY);
		}
		if(jobLogTO.getInput().length() > LENGTH) {
			jobLogTO.setInput(jobLogTO.getInput().substring(0, LENGTH));
		}
		if(jobLogTO.getOutput().length() > LENGTH) {
			jobLogTO.setOutput(jobLogTO.getOutput().substring(0, LENGTH));
		}
		
		result = jobLogHibernateDAO.createOrUpdate(jobLogTO);
		Logging.info(this, "End createJobLog");
		return result;
	}
	
	@Transactional(readOnly=true)
	public JobLogTO read(final int id) {
		Logging.info(this, "Begin readJobLog");
		JobLogTO result = null;
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
	public JobLogTO downloadFile(int jobLogId) {
		Logging.info(this, "Begin downloadFileLog");
		
		JobLogTO jobLogTO = null;
		
		jobLogTO = read(jobLogId);
		
		if(jobLogTO != null) {
			byte[] bytes = BlobTool.convertBlobToBytes(jobLogTO.getFile());
			jobLogTO.setFileData(bytes);
		}
		
		Logging.info(this, "End downloadFileLog");
		
		return jobLogTO;
	}

	@Transactional(readOnly=true)
	public ArrayList<JobLogTO> list(final int jobId) {
		Logging.info(this, "Begin listJobLog");
		ArrayList<JobLogTO> arrayList = null;
		arrayList = jobLogHibernateDAO.list(jobId);
		Logging.info(this, "End listJobLog");
		return arrayList;
	}
	
	@Transactional(readOnly=true)
	public ArrayList<JobLogTO> listForBatch(final int jobId) {
		Logging.info(this, "Begin listJobLog");
		ArrayList<JobLogTO> arrayList = null;
		
		BatchJobInstanceTO batchJobInstanceTO = batchJobInstanceHibernateDAO.readJobExecution(jobId);
		
		BatchJobParamsTO  batchJobParamsTO = batchJobParamsHibernateDAO.getJobParam(batchJobInstanceTO.getId(), BaseConstants.JOB_ID);
		
		arrayList = jobLogHibernateDAO.list((int)batchJobParamsTO.getLongValue());
		Logging.info(this, "End listJobLog");
		return arrayList;
	}
	
}
