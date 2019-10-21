package be.luxuryoverdosis.framework.data.dto;

import java.util.Date;

import org.springframework.batch.core.ExitStatus;

import be.luxuryoverdosis.framework.base.tool.DateTool;
import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;

public class BatchJobInstanceDTO {
	private long id;
	private String jobName;
	private long batchJobExecutionId;
	private long batchJobExecutionVersion;
	private Date batchJobExecutionCreateTime;
	private String batchJobExecutionCreateTimeAsString;
	private Date batchJobExecutionStartTime;
	private String batchJobExecutionStartTimeAsString;
	private Date batchJobExecutionEndTime;
	private String batchJobExecutionEndTimeAsString;
	private String batchJobExecutionStatus;
	private String batchJobExecutionStatusTranslated;
	private String batchJobExecutionExitCode;
	private String batchJobExecutionExitMessage;
	private Date batchJobExecutionLastUpdated;
	private String batchJobExecutionLastUpdatedAsString;
	
	public BatchJobInstanceDTO() {
		super();
	}
	
	public BatchJobInstanceDTO(long id, String jobName, long batchJobExecutionId, long batchJobExecutionVersion, Date batchJobExecutionCreateTime, Date batchJobExecutionStartTime, 
			Date batchJobExecutionEndTime, String batchJobExecutionStatus, String batchJobExecutionExitCode, String batchJobExecutionExitMessage, Date batchJobExecutionLastUpdated) {
		super();
		setId(id);
		this.jobName = jobName;
		this.batchJobExecutionId = batchJobExecutionId;
		this.batchJobExecutionVersion = batchJobExecutionVersion;
		this.batchJobExecutionCreateTime = batchJobExecutionCreateTime;
		this.batchJobExecutionCreateTimeAsString = DateTool.formatUtilDateTime(batchJobExecutionCreateTime);
		this.batchJobExecutionStartTime = batchJobExecutionStartTime;
		this.batchJobExecutionStartTimeAsString = DateTool.formatUtilDateTime(batchJobExecutionStartTime);
		this.batchJobExecutionEndTime = batchJobExecutionEndTime;
		this.batchJobExecutionEndTimeAsString = DateTool.formatUtilDateTime(batchJobExecutionEndTime);
		this.batchJobExecutionStatus = batchJobExecutionStatus;
		this.batchJobExecutionStatusTranslated = this.getBatchJobExecutionExitCodeTranslated();
		this.batchJobExecutionExitCode = batchJobExecutionExitCode;
		this.batchJobExecutionExitMessage = batchJobExecutionExitMessage;
		this.batchJobExecutionLastUpdated = batchJobExecutionLastUpdated;
		this.batchJobExecutionLastUpdatedAsString = DateTool.formatUtilDateTime(batchJobExecutionLastUpdated);
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public long getBatchJobExecutionId() {
		return batchJobExecutionId;
	}
	public void setBatchJobExecutionId(long batchJobExecutionId) {
		this.batchJobExecutionId = batchJobExecutionId;
	}
	public long getBatchJobExecutionVersion() {
		return batchJobExecutionVersion;
	}
	public void setBatchJobExecutionVersion(long batchJobExecutionVersion) {
		this.batchJobExecutionVersion = batchJobExecutionVersion;
	}
	public Date getBatchJobExecutionCreateTime() {
		return batchJobExecutionCreateTime;
	}
	public void setBatchJobExecutionCreateTime(Date batchJobExecutionCreateTime) {
		this.batchJobExecutionCreateTime = batchJobExecutionCreateTime;
	}
	public String getBatchJobExecutionCreateTimeAsString() {
		return batchJobExecutionCreateTimeAsString;
	}
	public void setBatchJobExecutionCreateTimeAsString(String batchJobExecutionCreateTimeAsString) {
		this.batchJobExecutionCreateTimeAsString = batchJobExecutionCreateTimeAsString;
	}
	public Date getBatchJobExecutionStartTime() {
		return batchJobExecutionStartTime;
	}
	public void setBatchJobExecutionStartTime(Date batchJobExecutionStartTime) {
		this.batchJobExecutionStartTime = batchJobExecutionStartTime;
	}
	public String getBatchJobExecutionStartTimeAsString() {
		return batchJobExecutionStartTimeAsString;
	}
	public void setBatchJobExecutionStartTimeAsString(String batchJobExecutionStartTimeAsString) {
		this.batchJobExecutionStartTimeAsString = batchJobExecutionStartTimeAsString;
	}
	public Date getBatchJobExecutionEndTime() {
		return batchJobExecutionEndTime;
	}
	public void setBatchJobExecutionEndTime(Date batchJobExecutionEndTime) {
		this.batchJobExecutionEndTime = batchJobExecutionEndTime;
	}
	public String getBatchJobExecutionEndTimeAsString() {
		return batchJobExecutionEndTimeAsString;
	}
	public void setBatchJobExecutionEndTimeAsString(String batchJobExecutionEndTimeAsString) {
		this.batchJobExecutionEndTimeAsString = batchJobExecutionEndTimeAsString;
	}
	public String getBatchJobExecutionStatus() {
		return batchJobExecutionStatus;
	}
	public void setBatchJobExecutionStatus(String batchJobExecutionStatus) {
		this.batchJobExecutionStatus = batchJobExecutionStatus;
	}
	public String getBatchJobExecutionStatusTranslated() {
		return batchJobExecutionStatusTranslated;
	}
	public void setBatchJobExecutionStatusTranslated(String batchJobExecutionStatusTranslated) {
		this.batchJobExecutionStatusTranslated = batchJobExecutionStatusTranslated;
	}
	public String getBatchJobExecutionExitCode() {
		return batchJobExecutionExitCode;
	}
	public void setBatchJobExecutionExitCode(String batchJobExecutionExitCode) {
		this.batchJobExecutionExitCode = batchJobExecutionExitCode;
	}
	public String getBatchJobExecutionExitMessage() {
		return batchJobExecutionExitMessage;
	}
	public void setBatchJobExecutionExitMessage(String batchJobExecutionExitMessage) {
		this.batchJobExecutionExitMessage = batchJobExecutionExitMessage;
	}
	public Date getBatchJobExecutionLastUpdated() {
		return batchJobExecutionLastUpdated;
	}
	public void setBatchJobExecutionLastUpdated(Date batchJobExecutionLastUpdated) {
		this.batchJobExecutionLastUpdated = batchJobExecutionLastUpdated;
	}
	public String getBatchJobExecutionLastUpdatedAsString() {
		return batchJobExecutionLastUpdatedAsString;
	}
	public void setBatchJobExecutionLastUpdatedAsString(String batchJobExecutionLastUpdatedAsString) {
		this.batchJobExecutionLastUpdatedAsString = batchJobExecutionLastUpdatedAsString;
	}

	public String getBatchJobExecutionExitCodeTranslated() {
		String status = null;
	    
	    if(ExitStatus.COMPLETED.getExitCode().equals(this.batchJobExecutionStatus)) {
	    	status = BaseSpringServiceLocator.getMessage("batchjobexecution.status.completed");
		}
	    if(ExitStatus.EXECUTING.getExitCode().equals(this.batchJobExecutionStatus)) {
	    	status = BaseSpringServiceLocator.getMessage("batchjobexecution.status.executing");
	    }
	    if(ExitStatus.FAILED.getExitCode().equals(this.batchJobExecutionStatus)) {
	    	status = BaseSpringServiceLocator.getMessage("batchjobexecution.status.failed");
	    }
	    if(ExitStatus.NOOP.getExitCode().equals(this.batchJobExecutionStatus)) {
	    	status = BaseSpringServiceLocator.getMessage("batchjobexecution.status.noop");
	    }
	    if(ExitStatus.STOPPED.getExitCode().equals(this.batchJobExecutionStatus)) {
	    	status = BaseSpringServiceLocator.getMessage("batchjobexecution.status.stopped");
	    }
	    if(ExitStatus.UNKNOWN.getExitCode().equals(this.batchJobExecutionStatus)) {
	    	status = BaseSpringServiceLocator.getMessage("batchjobexecution.status.unknown");
	    }
	
	
	    return status;
	}
}
