package be.luxuryoverdosis.framework.business.service.batch.user;

import javax.annotation.Resource;

import org.springframework.batch.item.ItemProcessor;

import be.luxuryoverdosis.framework.base.tool.ExceptionTool;
import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;
import be.luxuryoverdosis.framework.business.service.interfaces.JobLogService;
import be.luxuryoverdosis.framework.business.service.interfaces.JobService;
import be.luxuryoverdosis.framework.business.service.interfaces.UserService;
import be.luxuryoverdosis.framework.business.thread.ThreadManager;
import be.luxuryoverdosis.framework.data.dto.UserDTO;
import be.luxuryoverdosis.framework.data.dto.UserImportDTO;
import be.luxuryoverdosis.framework.data.factory.JobLogFactory;
import be.luxuryoverdosis.framework.data.factory.UserFactory;
import be.luxuryoverdosis.framework.data.to.Job;
import be.luxuryoverdosis.framework.data.to.JobLog;
import be.luxuryoverdosis.framework.data.to.User;
import be.luxuryoverdosis.framework.logging.Logging;

public class UserImportProcessor implements ItemProcessor<UserImportDTO, UserDTO> {
	@Resource
	private JobService jobService;
	
	@Resource
	private JobLogService jobLogService;
	
	@Resource
	private UserService userService;
	
	private int jobId;
	private String jobUser;

	public void setJobId(final long jobId) {
		this.jobId = (int) jobId;
	}
	public void setJobUser(final String jobUser) {
		this.jobUser = jobUser;
	}

	@Override
	public UserDTO process(final UserImportDTO importDTO) throws Exception {
		ThreadManager.setUserOnThread(userService.readNameDTO(jobUser));
		Job job = jobService.read(jobId);
		
		UserDTO userDTO = userService.readNameDTO(importDTO.getName());
		
		try {
			userDTO = UserFactory.produceUserDTO(userDTO, importDTO);
			
			userService.validate(UserFactory.produceUser(new User(), userDTO));
			
		} catch (Exception e) {
			String output = ExceptionTool.convertExceptionToString(e, "validate.failed", new Object[]{BaseSpringServiceLocator.getMessage("table.user")});
			
			JobLog jobLog = new JobLog();
			jobLog = JobLogFactory.produceJobLog(jobLog, job, getInput("validate.failed"), output);
			jobLogService.createOrUpdate(jobLog);
			
			Logging.error(this, output);
			
			return null;
		}
		
		return userDTO;
	}

	private String getInput(final String key) {
		return BaseSpringServiceLocator.getMessage(key, new Object[]{BaseSpringServiceLocator.getMessage("table.user")});
	}
	
}
