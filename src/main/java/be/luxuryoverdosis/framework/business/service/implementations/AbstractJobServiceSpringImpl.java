package be.luxuryoverdosis.framework.business.service.implementations;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import be.luxuryoverdosis.framework.BaseConstants;
import be.luxuryoverdosis.framework.business.BaseService;
import be.luxuryoverdosis.framework.business.service.interfaces.AbstractJobService;
import be.luxuryoverdosis.framework.data.dao.interfaces.JobHibernateDAO;
import be.luxuryoverdosis.framework.data.dao.interfaces.JobLogHibernateDAO;
import be.luxuryoverdosis.framework.data.dao.interfaces.JobParamHibernateDAO;
import be.luxuryoverdosis.framework.data.to.JobLog;
import be.luxuryoverdosis.framework.data.to.JobParamTO;
import be.luxuryoverdosis.framework.data.to.JobTO;
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
	abstract public void processFileJobBusiness(final JobTO jobTO, final ArrayList<JobParamTO> jobParams);
	
	public void processReadJob() {
		Logging.info(this, "Begin processReadJob");
		
		Collection<JobTO> jobs = jobHibernateDAO.listNotStarted(getJobName());
		Iterator<JobTO> jobsIterator = jobs.iterator();
		
		this.startReadJob(jobs);
		
		while(jobsIterator.hasNext()) {
			JobTO jobTO = (JobTO) jobsIterator.next();
			ArrayList<JobParamTO> jobParams = jobParamHibernateDAO.list(jobTO.getId());
			
			processFileJobBusiness(jobTO, jobParams);
		}
		
		this.endReadJob(jobs);
		
		Logging.info(this, "End processReadJob");
	}
	
	@Transactional
	private void startReadJob(final Collection<JobTO> jobs) {
		Logging.info(this, "Begin startReadJob");
		
		Iterator<JobTO> jobsIterator = jobs.iterator();
		
		while(jobsIterator.hasNext()) {
			JobTO jobTO = (JobTO) jobsIterator.next();
			
			jobTO.setStarted(new Date(Calendar.getInstance().getTimeInMillis()));
			jobTO.setStatus(BaseConstants.JOB_STATUS_STARTED);
			jobHibernateDAO.createOrUpdate(jobTO);
		}
		
		Logging.info(this, "End startReadJob");
	}
	
	@Transactional
	private void endReadJob(final Collection<JobTO> jobs) {
		Logging.info(this, "Begin endReadJob");
		
		Iterator<JobTO> jobsIterator = jobs.iterator();
		
		while(jobsIterator.hasNext()) {
			JobTO jobTO = (JobTO) jobsIterator.next();
			
			jobTO.setEnded(new Date(Calendar.getInstance().getTimeInMillis()));
			jobTO.setStatus(BaseConstants.JOB_STATUS_EXECUTED);
			jobTO.setExecuted(true);
			jobHibernateDAO.createOrUpdate(jobTO);
		}
		
		Logging.info(this, "End endReadJob");
	}
	
	public void addJobLog(final JobTO jobTO, final String record, final String output) {
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
		jobLog.setJob(jobTO);
		jobLogHibernateDAO.createOrUpdate(jobLog);
	}
}
