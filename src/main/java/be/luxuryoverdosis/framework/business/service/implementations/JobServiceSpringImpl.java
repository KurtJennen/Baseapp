package be.luxuryoverdosis.framework.business.service.implementations;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.luxuryoverdosis.Constants;
import be.luxuryoverdosis.framework.base.tool.BlobTool;
import be.luxuryoverdosis.framework.business.service.interfaces.JobService;
import be.luxuryoverdosis.framework.data.dao.interfaces.BatchJobParamsHibernateDAO;
import be.luxuryoverdosis.framework.data.dao.interfaces.JobHibernateDAO;
import be.luxuryoverdosis.framework.data.dao.interfaces.JobLogHibernateDAO;
import be.luxuryoverdosis.framework.data.dao.interfaces.JobParamHibernateDAO;
import be.luxuryoverdosis.framework.data.to.BatchJobParamsTO;
import be.luxuryoverdosis.framework.data.to.JobTO;
import be.luxuryoverdosis.framework.logging.Logging;

@Service
public class JobServiceSpringImpl implements JobService {
	@Resource
	private JobHibernateDAO jobHibernateDAO;
	@Resource
	private JobLogHibernateDAO jobLogHibernateDAO;
	@Resource
	private JobParamHibernateDAO jobParamHibernateDAO;
	@Resource
	private BatchJobParamsHibernateDAO batchJobParamsHibernateDAO;
	
	@Transactional
	public JobTO createOrUpdate(final JobTO jobTO) {
		Logging.info(this, "Begin createJob");
		JobTO result = null;
		result = jobHibernateDAO.createOrUpdate(jobTO);
		Logging.info(this, "End createJob");
		return result;
	}
	
	@Transactional(readOnly=true)
	public JobTO read(final int id) {
		Logging.info(this, "Begin readJob");
		JobTO result = null;
		result = jobHibernateDAO.read(id);
		Logging.info(this, "End readJob");
		return result;
	}
	
	@Transactional
	public void delete(final int id) {
		Logging.info(this, "Begin deleteJob");
		
		jobParamHibernateDAO.deleteForJob(id);
		jobLogHibernateDAO.deleteForJob(id);		
		jobHibernateDAO.delete(id);
		
		Logging.info(this, "End deleteJob");
	}
	
	@Transactional(readOnly=true)
	public JobTO downloadFile(int jobInstanceId){
		Logging.info(this, "Begin downloadFileJob");
		
		JobTO jobTO = null;
		
		BatchJobParamsTO batchJobParamsTO = batchJobParamsHibernateDAO.getJobParam(jobInstanceId, "jobId");
		
		if(batchJobParamsTO != null) {
			jobTO = jobHibernateDAO.read((int)batchJobParamsTO.getLongValue());
			byte[] bytes = BlobTool.convertBlobToBytes(jobTO.getFile());
			jobTO.setFileData(bytes);
		}
		
		Logging.info(this, "End downloadFileJob");
		
		return jobTO;
	}

	@Transactional(readOnly=true)
	public ArrayList<JobTO> list(final String name) {
		Logging.info(this, "Begin listJob");
		ArrayList<JobTO> arrayList = null;
		arrayList = jobHibernateDAO.list(name);
		Logging.info(this, "End listJob");
		return arrayList;
	}
	
	@Transactional(readOnly=true)
	public ArrayList<JobTO> list(final String name, final boolean started) {
		Logging.info(this, "Begin listJob");
		ArrayList<JobTO> arrayList = null;
		if(started) {
			arrayList = jobHibernateDAO.listStarted(name);
		} else {
			arrayList = jobHibernateDAO.listNotStarted(name);
		}
		
		Logging.info(this, "End listJob");
		return arrayList;
	}
	
	@Transactional
	public void startJobs(final ArrayList<JobTO> jobs) {
		Logging.info(this, "Begin startReadJob");
		
		Iterator<JobTO> jobsIterator = jobs.iterator();
		
		while(jobsIterator.hasNext()) {
			JobTO jobTO = (JobTO) jobsIterator.next();
			
			jobTO.setStarted(new Date(Calendar.getInstance().getTimeInMillis()));
			jobTO.setStatus(Constants.JOB_STATUS_STARTED);
			jobHibernateDAO.createOrUpdate(jobTO);
		}
		
		Logging.info(this, "End startReadJob");
	}
	
	@Transactional
	public void endJobs(final ArrayList<JobTO> jobs) {
		Logging.info(this, "Begin endReadJob");
		
		Iterator<JobTO> jobsIterator = jobs.iterator();
		
		while(jobsIterator.hasNext()) {
			JobTO jobTO = (JobTO) jobsIterator.next();
			
			jobTO.setEnded(new Date(Calendar.getInstance().getTimeInMillis()));
			jobTO.setStatus(Constants.JOB_STATUS_EXECUTED);
			jobTO.setExecuted(true);
			jobHibernateDAO.createOrUpdate(jobTO);
		}
		
		Logging.info(this, "End endReadJob");
	}
}
