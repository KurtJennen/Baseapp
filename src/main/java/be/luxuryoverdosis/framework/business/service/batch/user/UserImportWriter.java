package be.luxuryoverdosis.framework.business.service.batch.user;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.batch.item.database.HibernateItemWriter;
import org.springframework.orm.hibernate3.HibernateOperations;

import be.luxuryoverdosis.framework.base.tool.ExceptionTool;
import be.luxuryoverdosis.framework.business.encryption.Encryption;
import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;
import be.luxuryoverdosis.framework.business.service.interfaces.JobLogService;
import be.luxuryoverdosis.framework.business.service.interfaces.JobService;
import be.luxuryoverdosis.framework.business.service.interfaces.UserService;
import be.luxuryoverdosis.framework.data.dto.UserDTO;
import be.luxuryoverdosis.framework.data.factory.JobLogFactory;
import be.luxuryoverdosis.framework.data.factory.UserFactory;
import be.luxuryoverdosis.framework.data.to.JobLog;
import be.luxuryoverdosis.framework.data.to.Job;
import be.luxuryoverdosis.framework.data.to.UserTO;
import be.luxuryoverdosis.framework.logging.Logging;

public class UserImportWriter extends HibernateItemWriter<UserDTO> {
	@Resource
	private JobService jobService;
	
	@Resource
	private JobLogService jobLogService;
	
	@Resource
	private UserService userService;
	
	private int jobId;

	public void setJobId(long jobId) {
		this.jobId = (int)jobId;
	}

	@Override
	protected void doWrite(HibernateOperations hibernateOperations, List<? extends UserDTO> user) {
		Job job = jobService.read(jobId);
		try {
			
			for (UserDTO userDTO : user) {
				UserTO userTO = userService.readName(userDTO.getName());
				
				if(userTO == null) {
					userDTO.setPassword(Encryption.decode(userDTO.getPassword()));
					
					userTO = new UserTO();
					UserFactory.produceUser(userTO, userDTO);
					
					userService.createOrUpdateDTO(userDTO);
					
					JobLog jobLog = new JobLog();
					jobLog = JobLogFactory.produceJobLog(jobLog, job, getInput("import.success"), getOutput(userDTO));
					jobLogService.createOrUpdate(jobLog);
				} else {
					JobLog jobLog = new JobLog();
					jobLog = JobLogFactory.produceJobLog(jobLog, job, getInput("import.failed") + ":" + getInput("exists"), getOutput(userDTO));
					jobLogService.createOrUpdate(jobLog);
				}
			}
		} catch (Exception e) {
			String output = ExceptionTool.convertExceptionToString(e, "import.failed", new Object[]{BaseSpringServiceLocator.getMessage("table.user")});
			
			JobLog jobLog = new JobLog();
			jobLog = JobLogFactory.produceJobLog(jobLog, job, getInput("import.failed"), output);
			jobLogService.createOrUpdate(jobLog);
			
			Logging.error(this, output);
		}
	}

	private String getInput(String key) {
		return BaseSpringServiceLocator.getMessage(key,  new Object[]{BaseSpringServiceLocator.getMessage("table.user")});
	}

	private String getOutput(UserDTO userDTO) {
		return userDTO.getName() + " " + userDTO.getUserName();
	}

	

}