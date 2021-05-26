package be.luxuryoverdosis.framework.business.service.batch.user;

import java.io.ByteArrayOutputStream;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.batch.item.database.HibernateItemWriter;

import be.luxuryoverdosis.framework.BaseConstants;
import be.luxuryoverdosis.framework.base.FileContentType;
import be.luxuryoverdosis.framework.base.tool.BlobTool;
import be.luxuryoverdosis.framework.base.tool.ExceptionTool;
import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;
import be.luxuryoverdosis.framework.business.service.interfaces.JobLogService;
import be.luxuryoverdosis.framework.business.service.interfaces.JobService;
import be.luxuryoverdosis.framework.data.factory.JobLogFactory;
import be.luxuryoverdosis.framework.data.to.Job;
import be.luxuryoverdosis.framework.data.to.JobLog;
import be.luxuryoverdosis.framework.data.to.User;
import be.luxuryoverdosis.framework.logging.Logging;

public class UserExportWriter extends HibernateItemWriter<User> {
	@Resource
	private JobService jobService;
	
	@Resource
	private JobLogService jobLogService;
	
	private int jobId;

	public void setJobId(long jobId) {
		this.jobId = (int)jobId;
	}

	protected void doWrite(SessionFactory sessionFactory, List<? extends User> users) {
		Job job = jobService.read(jobId);
		try {
			StringBuffer exportBuffer = new StringBuffer();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			if(job.getFile() != null) {
				byte[] bytes = BlobTool.convertBlobToBytes(job.getFile());
				baos.write(bytes);
			}
			
			for (User user : users) {
				exportBuffer.append(user.getName()).append(BaseConstants.PIPE);
				exportBuffer.append(user.getUserName()).append(BaseConstants.PIPE);
				exportBuffer.append(user.getEncryptedPassword()).append(BaseConstants.PIPE);
				exportBuffer.append(user.getEmail()).append(BaseConstants.PIPE);
				exportBuffer.append(user.getDateExpiration()).append(BaseConstants.PIPE);
//				exportBuffer.append(user.getRole().getName());
				exportBuffer.append(BaseConstants.CARRIAGE_RETURN);
				
				baos.write(exportBuffer.toString().getBytes(), 0, exportBuffer.length());
				
				JobLog jobLog = new JobLog();
				jobLog = JobLogFactory.produceJobLog(jobLog, job, getInput("export.success"), getOutput(user));
				jobLogService.createOrUpdate(jobLog);
			}
			
			job.setFileData(baos.toString().getBytes());
			job.setFileName(BaseConstants.JOB_EXPORT_USER_FILENAME);
			job.setFileSize(baos.size());
			job.setContentType(FileContentType.TEXT_PLAIN);
			
			jobService.createOrUpdate(job);
			
		} catch (Exception e) {
			String output = ExceptionTool.convertExceptionToString(e, "export.failed", new Object[]{BaseSpringServiceLocator.getMessage("table.user")});
			
			JobLog jobLog = new JobLog();
			jobLog = JobLogFactory.produceJobLog(jobLog, job, getInput("export.failed"), output);
			jobLogService.createOrUpdate(jobLog);
			
			Logging.error(this, output);
		}
	}

	private String getInput(String key) {
		return BaseSpringServiceLocator.getMessage(key,  new Object[]{BaseSpringServiceLocator.getMessage("table.user")});
	}

	private String getOutput(User user) {
		return user.getName() + BaseConstants.SPACE + user.getUserName();
	}

	

}