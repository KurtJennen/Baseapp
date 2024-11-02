package be.luxuryoverdosis.framework.business.service.implementations;

import javax.annotation.Resource;

import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.stereotype.Service;

import be.luxuryoverdosis.framework.BaseConstants;
import be.luxuryoverdosis.framework.business.service.interfaces.BatchService;
import be.luxuryoverdosis.framework.business.service.interfaces.JobService;
import be.luxuryoverdosis.framework.business.thread.ThreadManager;
import be.luxuryoverdosis.framework.data.dto.FileDTO;
import be.luxuryoverdosis.framework.data.to.Job;
import be.luxuryoverdosis.framework.logging.Logging;

@Service
public class BatchServiceSpringImpl implements BatchService {
	@Resource(name = "jobLauncher")
	private JobLauncher jobLauncher;
	
	@Resource(name = "jobLauncherAsync")
	private JobLauncher jobLauncherAsync;
	
	@Resource(name = "userExportJob")
	private org.springframework.batch.core.Job userExportJob;
	
	@Resource(name = "userImportJob")
	private org.springframework.batch.core.Job userImportJob;
	
	@Resource
	private JobService jobService;
	
	public void exportUserJob(final FileDTO fileDTO) throws Exception {
		Logging.info(this, "Begin exportUserJob");
		
		//Job
		Job job = new Job(BaseConstants.JOB_EXPORT_USER, fileDTO);
		job = jobService.createOrUpdate(job);
		
		//JobParameters
		JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
		jobParametersBuilder.addLong(BaseConstants.JOB_ID, Long.valueOf(job.getId()));
		jobParametersBuilder.addString(BaseConstants.JOB_USER, ThreadManager.getUserFromThread().getName());
		
		//Run
		jobLauncherAsync.run(userExportJob, jobParametersBuilder.toJobParameters());
		Logging.info(this, "End exportUserJob");
	}
	
	public void importUserJob(final FileDTO fileDTO) throws Exception {
		Logging.info(this, "Begin importUserJob");
		
		//Job
		Job job = new Job(BaseConstants.JOB_IMPORT_USER, fileDTO);
		job = jobService.createOrUpdate(job);
		
		//JobParameters
		JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
		jobParametersBuilder.addLong(BaseConstants.JOB_ID, Long.valueOf(job.getId()));
		jobParametersBuilder.addString(BaseConstants.JOB_USER, ThreadManager.getUserFromThread().getName());
		
		//Run
		jobLauncherAsync.run(userImportJob, jobParametersBuilder.toJobParameters());
		Logging.info(this, "End importUserJob");
	}
}
