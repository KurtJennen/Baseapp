package be.luxuryoverdosis.framework.business.service.implementations;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.luxuryoverdosis.framework.BaseConstants;
import be.luxuryoverdosis.framework.base.tool.BlobTool;
import be.luxuryoverdosis.framework.business.enumeration.JobStatusType;
import be.luxuryoverdosis.framework.business.service.interfaces.JobLogService;
import be.luxuryoverdosis.framework.business.service.interfaces.JobService;
import be.luxuryoverdosis.framework.data.dao.interfaces.BatchJobExecutionHibernateDAO;
import be.luxuryoverdosis.framework.data.dao.interfaces.BatchJobExecutionParamsHibernateDAO;
import be.luxuryoverdosis.framework.data.dao.interfaces.BatchJobInstanceHibernateDAO;
import be.luxuryoverdosis.framework.data.dao.interfaces.BatchJobParamsHibernateDAO;
import be.luxuryoverdosis.framework.data.dao.interfaces.BatchStepExecutionHibernateDAO;
import be.luxuryoverdosis.framework.data.dao.interfaces.JobHibernateDAO;
import be.luxuryoverdosis.framework.data.dao.interfaces.JobParamHibernateDAO;
import be.luxuryoverdosis.framework.data.to.BatchJobExecution;
import be.luxuryoverdosis.framework.data.to.BatchJobExecutionParams;
import be.luxuryoverdosis.framework.data.to.BatchJobInstance;
import be.luxuryoverdosis.framework.data.to.BatchJobParams;
import be.luxuryoverdosis.framework.data.to.Job;
import be.luxuryoverdosis.framework.data.wrapperdto.DetailJobWrapperDTO;
import be.luxuryoverdosis.framework.logging.Logging;

@Service
public class JobServiceSpringImpl implements JobService {
	@Resource
	private JobHibernateDAO jobHibernateDAO;
	@Resource
	private JobLogService jobLogService;
	@Resource
	private BatchJobInstanceHibernateDAO batchJobInstanceHibernateDAO;
	@Resource
	private BatchJobExecutionHibernateDAO batchJobExecutionHibernateDAO;
	@Resource
	private BatchStepExecutionHibernateDAO batchStepExecutionHibernateDAO;
	@Resource
	private JobParamHibernateDAO jobParamHibernateDAO;
	@Resource
	private BatchJobParamsHibernateDAO batchJobParamsHibernateDAO;
	@Resource
	private BatchJobExecutionParamsHibernateDAO batchJobExecutionParamsHibernateDAO;
	
	@Transactional
	public Job createOrUpdate(final Job job) {
		Logging.info(this, "Begin createJob");
		Job result = null;
		result = jobHibernateDAO.createOrUpdate(job);
		Logging.info(this, "End createJob");
		return result;
	}
	
	@Transactional(readOnly=true)
	public Job read(final int id) {
		Logging.info(this, "Begin readJob");
		Job result = null;
		result = jobHibernateDAO.read(id);
		Logging.info(this, "End readJob");
		return result;
	}
	
	@Transactional
	public void delete(final int id) {
		Logging.info(this, "Begin deleteJob");
		
		jobParamHibernateDAO.deleteForJob(id);
		jobLogService.deleteForJob(id);		
		jobHibernateDAO.delete(id);
		
		Logging.info(this, "End deleteJob");
	}
	
	@Transactional(readOnly=true)
	public Job downloadFile(int jobInstanceId){
		Logging.info(this, "Begin downloadFileJob");
		
		Job job = null;
		
		BatchJobParams batchJobParams = batchJobParamsHibernateDAO.read(jobInstanceId, BaseConstants.JOB_ID);
		
		BatchJobExecution batchJobExecution = batchJobExecutionHibernateDAO.read(jobInstanceId);
		BatchJobExecutionParams batchJobExecutionParams = batchJobExecutionParamsHibernateDAO.read(batchJobExecution.getId(), BaseConstants.JOB_ID);
		
		if(batchJobParams != null) {
			job = jobHibernateDAO.read((int)batchJobParams.getLongValue());
			byte[] bytes = BlobTool.convertBlobToBytes(job.getFile());
			job.setFileData(bytes);
		}
		
		if(batchJobExecutionParams != null) {
			job = jobHibernateDAO.read((int)batchJobExecutionParams.getLongValue());
			byte[] bytes = BlobTool.convertBlobToBytes(job.getFile());
			job.setFileData(bytes);
		}
		
		Logging.info(this, "End downloadFileJob");
		
		return job;
	}

	@Transactional(readOnly=true)
	public ArrayList<Job> list(final String name) {
		Logging.info(this, "Begin listJob");
		ArrayList<Job> arrayList = null;
		arrayList = jobHibernateDAO.list(name);
		Logging.info(this, "End listJob");
		return arrayList;
	}
	
	@Transactional(readOnly=true)
	public ArrayList<Job> list(final String name, final boolean started) {
		Logging.info(this, "Begin listJob");
		ArrayList<Job> arrayList = null;
		if(started) {
			arrayList = jobHibernateDAO.listStarted(name);
		} else {
			arrayList = jobHibernateDAO.listNotStarted(name);
		}
		
		Logging.info(this, "End listJob");
		return arrayList;
	}
	
	@Transactional(readOnly=true)
	public DetailJobWrapperDTO getDetailJobWrapperDTO(final int jobInstanceId) {
		Logging.info(this, "Begin getDetailJobWrapperDTO(int)");
		DetailJobWrapperDTO detailJobWrapperDTO = new DetailJobWrapperDTO();
		
		BatchJobInstance batchJobInstance = batchJobInstanceHibernateDAO.read(jobInstanceId);
		detailJobWrapperDTO.setJobName(batchJobInstance.getJobName());
		
		detailJobWrapperDTO.setBatchJobParamsList(batchJobParamsHibernateDAO.list(jobInstanceId));
		detailJobWrapperDTO.setBatchJobExecutionParamsList(batchJobExecutionParamsHibernateDAO.list(jobInstanceId));
		detailJobWrapperDTO.setBatchStepExecutionList(batchStepExecutionHibernateDAO.list(jobInstanceId));
		detailJobWrapperDTO.setJobLogList(jobLogService.listForBatch(jobInstanceId));
		
		Logging.info(this, "End getDetailJobWrapperDTO(int)");
		return detailJobWrapperDTO;
	};
	
	@Transactional
	public void startJobs(final ArrayList<Job> jobs) {
		Logging.info(this, "Begin startReadJob");
		
		Iterator<Job> jobsIterator = jobs.iterator();
		
		while(jobsIterator.hasNext()) {
			Job job = (Job) jobsIterator.next();
			
			job.setStarted(new Date(Calendar.getInstance().getTimeInMillis()));
			job.setStatus(JobStatusType.STARTED);
			jobHibernateDAO.createOrUpdate(job);
		}
		
		Logging.info(this, "End startReadJob");
	}
	
	@Transactional
	public void endJobs(final ArrayList<Job> jobs) {
		Logging.info(this, "Begin endReadJob");
		
		Iterator<Job> jobsIterator = jobs.iterator();
		
		while(jobsIterator.hasNext()) {
			Job job = (Job) jobsIterator.next();
			
			job.setEnded(new Date(Calendar.getInstance().getTimeInMillis()));
			job.setStatus(JobStatusType.EXECUTED);
			job.setExecuted(true);
			jobHibernateDAO.createOrUpdate(job);
		}
		
		Logging.info(this, "End endReadJob");
	}
}
