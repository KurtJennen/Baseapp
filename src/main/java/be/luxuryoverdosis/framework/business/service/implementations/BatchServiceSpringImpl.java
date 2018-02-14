package be.luxuryoverdosis.framework.business.service.implementations;

import javax.annotation.Resource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.stereotype.Service;

import be.luxuryoverdosis.Constants;
import be.luxuryoverdosis.framework.business.service.interfaces.BatchService;
import be.luxuryoverdosis.framework.data.dao.interfaces.JobHibernateDAO;
import be.luxuryoverdosis.framework.data.dto.FileDTO;
import be.luxuryoverdosis.framework.data.to.JobTO;
import be.luxuryoverdosis.framework.logging.Logging;

@Service
public class BatchServiceSpringImpl implements BatchService {
	@Resource(name = "jobLauncher")
	JobLauncher jobLauncher;
	
	@Resource(name = "jobLauncherAsync")
	JobLauncher jobLauncherAsync;
	
	@Resource(name = "userExportJob")
	Job userExportJob;
	
	@Resource(name = "userImportJob")
	Job userImportJob;
	
	@Resource
	JobHibernateDAO jobHibernateDAO;
	
	public void exportUserJob(FileDTO fileDTO) throws Exception {
		Logging.info(this, "Begin exportUserJob");
		
		//Job
		JobTO jobTO = new JobTO(Constants.JOB_EXPORT_USER, fileDTO);
		jobTO = jobHibernateDAO.createOrUpdate(jobTO);
		
		//JobParameters
		JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
		jobParametersBuilder.addLong(Constants.JOB_ID, Long.valueOf(jobTO.getId()));
		
		//Run
		jobLauncherAsync.run(userExportJob, jobParametersBuilder.toJobParameters());
		Logging.info(this, "End exportUserJob");
	}
	
	public void importUserJob(FileDTO fileDTO) throws Exception {
		Logging.info(this, "Begin importUserJob");
		
		//Job
		JobTO jobTO = new JobTO(Constants.JOB_IMPORT_USER, fileDTO);
		jobTO = jobHibernateDAO.createOrUpdate(jobTO);
		
		//JobParameters
		JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
		jobParametersBuilder.addLong(Constants.JOB_ID, Long.valueOf(jobTO.getId()));
		
		//Run
		jobLauncherAsync.run(userImportJob, jobParametersBuilder.toJobParameters());
		Logging.info(this, "End importUserJob");
	}
}
