package be.luxuryoverdosis.framework.business.service.batch.user;

import java.io.ByteArrayOutputStream;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.batch.item.database.HibernateItemWriter;
import org.springframework.orm.hibernate3.HibernateOperations;

import be.luxuryoverdosis.framework.BaseConstants;
import be.luxuryoverdosis.framework.base.FileContentType;
import be.luxuryoverdosis.framework.base.tool.BlobTool;
import be.luxuryoverdosis.framework.base.tool.ExceptionTool;
import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;
import be.luxuryoverdosis.framework.business.service.interfaces.JobLogService;
import be.luxuryoverdosis.framework.business.service.interfaces.JobService;
import be.luxuryoverdosis.framework.data.factory.JobLogFactory;
import be.luxuryoverdosis.framework.data.to.JobLog;
import be.luxuryoverdosis.framework.data.to.JobTO;
import be.luxuryoverdosis.framework.data.to.UserTO;
import be.luxuryoverdosis.framework.logging.Logging;

public class UserExportWriter extends HibernateItemWriter<UserTO> {
	@Resource
	private JobService jobService;
	
	@Resource
	private JobLogService jobLogService;
	
	private int jobId;

	public void setJobId(long jobId) {
		this.jobId = (int)jobId;
	}

	@Override
	protected void doWrite(HibernateOperations hibernateOperations, List<? extends UserTO> user) {
		JobTO jobTO = jobService.read(jobId);
		try {
			StringBuffer exportBuffer = new StringBuffer();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			if(jobTO.getFile() != null) {
				byte[] bytes = BlobTool.convertBlobToBytes(jobTO.getFile());
				baos.write(bytes);
			}
			
			for (UserTO userTO : user) {
				exportBuffer.append(userTO.getName()).append(BaseConstants.PIPE);
				exportBuffer.append(userTO.getUserName()).append(BaseConstants.PIPE);
				exportBuffer.append(userTO.getEncryptedPassword()).append(BaseConstants.PIPE);
				exportBuffer.append(userTO.getEmail()).append(BaseConstants.PIPE);
				exportBuffer.append(userTO.getDateExpiration()).append(BaseConstants.PIPE);
				exportBuffer.append(userTO.getRole().getName());
				exportBuffer.append(BaseConstants.CARRIAGE_RETURN);
				
				baos.write(exportBuffer.toString().getBytes(), 0, exportBuffer.length());
				
				JobLog jobLog = new JobLog();
				jobLog = JobLogFactory.produceJobLog(jobLog, jobTO, getInput("export.success"), getOutput(userTO));
				jobLogService.createOrUpdate(jobLog);
			}
			
			jobTO.setFileData(baos.toString().getBytes());
			jobTO.setFileName(BaseConstants.JOB_EXPORT_USER_FILENAME);
			jobTO.setFileSize(baos.size());
			jobTO.setContentType(FileContentType.TEXT_PLAIN);
			
			jobService.createOrUpdate(jobTO);
			
		} catch (Exception e) {
			String output = ExceptionTool.convertExceptionToString(e, "export.failed", new Object[]{BaseSpringServiceLocator.getMessage("table.user")});
			
			JobLog jobLog = new JobLog();
			jobLog = JobLogFactory.produceJobLog(jobLog, jobTO, getInput("export.failed"), output);
			jobLogService.createOrUpdate(jobLog);
			
			Logging.error(this, output);
		}
	}

	private String getInput(String key) {
		return BaseSpringServiceLocator.getMessage(key,  new Object[]{BaseSpringServiceLocator.getMessage("table.user")});
	}

	private String getOutput(UserTO userTO) {
		return userTO.getName() + " " + userTO.getUserName();
	}

	

}