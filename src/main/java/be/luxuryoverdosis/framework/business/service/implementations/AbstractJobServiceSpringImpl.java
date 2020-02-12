package be.luxuryoverdosis.framework.business.service.implementations;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import be.luxuryoverdosis.framework.business.BaseService;
import be.luxuryoverdosis.framework.business.enumeration.JobStatusEnum;
import be.luxuryoverdosis.framework.business.service.interfaces.AbstractJobService;
import be.luxuryoverdosis.framework.data.dao.interfaces.JobHibernateDAO;
import be.luxuryoverdosis.framework.data.dao.interfaces.JobLogHibernateDAO;
import be.luxuryoverdosis.framework.data.dao.interfaces.JobParamHibernateDAO;
import be.luxuryoverdosis.framework.data.to.Job;
import be.luxuryoverdosis.framework.data.to.JobLog;
import be.luxuryoverdosis.framework.data.to.JobParam;
import be.luxuryoverdosis.framework.logging.Logging;

public abstract class AbstractJobServiceSpringImpl extends BaseService implements AbstractJobService {
	@Resource
	private JobHibernateDAO jobHibernateDAO;
	@Resource
	private JobParamHibernateDAO jobParamHibernateDAO;
	@Resource
	private JobLogHibernateDAO jobLogHibernateDAO;
	
	private static final int LENGTH = 256;
	
	abstract public String getJobName();
	abstract public void processFileJobBusiness(final Job job, final ArrayList<JobParam> jobParams);
	
	public void processReadJob() {
		Logging.info(this, "Begin processReadJob");
		
		Collection<Job> jobs = jobHibernateDAO.listNotStarted(getJobName());
		Iterator<Job> jobsIterator = jobs.iterator();
		
		this.startReadJob(jobs);
		
		while(jobsIterator.hasNext()) {
			Job job = (Job) jobsIterator.next();
			ArrayList<JobParam> jobParams = jobParamHibernateDAO.list(job.getId());
			
			processFileJobBusiness(job, jobParams);
		}
		
		this.endReadJob(jobs);
		
		Logging.info(this, "End processReadJob");
	}
	
	@Transactional
	private void startReadJob(final Collection<Job> jobs) {
		Logging.info(this, "Begin startReadJob");
		
		Iterator<Job> jobsIterator = jobs.iterator();
		
		while(jobsIterator.hasNext()) {
			Job job = (Job) jobsIterator.next();
			
			job.setStarted(new Date(Calendar.getInstance().getTimeInMillis()));
			job.setStatus(JobStatusEnum.STARTED);
			jobHibernateDAO.createOrUpdate(job);
		}
		
		Logging.info(this, "End startReadJob");
	}
	
	@Transactional
	private void endReadJob(final Collection<Job> jobs) {
		Logging.info(this, "Begin endReadJob");
		
		Iterator<Job> jobsIterator = jobs.iterator();
		
		while(jobsIterator.hasNext()) {
			Job job = (Job) jobsIterator.next();
			
			job.setEnded(new Date(Calendar.getInstance().getTimeInMillis()));
			job.setStatus(JobStatusEnum.EXECUTED);
			job.setExecuted(true);
			jobHibernateDAO.createOrUpdate(job);
		}
		
		Logging.info(this, "End endReadJob");
	}
	
	public void addJobLog(final Job job, final String record, final String output) {
		StringBuffer recordBuffer = new StringBuffer();
		StringBuffer outputBuffer = new StringBuffer();
		
		if(record != null) {
			if(record.length() > LENGTH) {
				recordBuffer.append(record.substring(0, LENGTH));
			} else {
				recordBuffer.append(record);
			}
		}
		
		if(output != null) {
			if(output.length() > LENGTH) {
				outputBuffer.append(output.substring(0, LENGTH));
			} else {
				outputBuffer.append(output);
			}
		}
		
		JobLog jobLog = new JobLog();
		jobLog.setInput(recordBuffer.toString());
		jobLog.setOutput(outputBuffer.toString());
		jobLog.setJob(job);
		jobLogHibernateDAO.createOrUpdate(jobLog);
	}
}
