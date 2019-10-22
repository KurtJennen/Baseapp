package be.luxuryoverdosis.framework.data.dto;

import java.util.Date;

import be.luxuryoverdosis.framework.base.tool.DateTool;
import be.luxuryoverdosis.framework.data.translater.BatchJobTranslater;

public class BatchJobInstanceDTO {
	private long id;
	private String jobName;
	private long batchJobExecutionId;
	private long batchJobExecutionVersion;
	private Date batchJobExecutionCreateTime;
	private Date batchJobExecutionStartTime;
	private Date batchJobExecutionEndTime;
	private String batchJobExecutionStatus;
	private String batchJobExecutionExitCode;
	private String batchJobExecutionExitMessage;
	private Date batchJobExecutionLastUpdated;
	
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
		this.batchJobExecutionStartTime = batchJobExecutionStartTime;
		this.batchJobExecutionEndTime = batchJobExecutionEndTime;
		this.batchJobExecutionStatus = batchJobExecutionStatus;
		this.batchJobExecutionExitCode = batchJobExecutionExitCode;
		this.batchJobExecutionExitMessage = batchJobExecutionExitMessage;
		this.batchJobExecutionLastUpdated = batchJobExecutionLastUpdated;
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
		return DateTool.formatUtilDateTime(batchJobExecutionCreateTime);
	}
	public Date getBatchJobExecutionStartTime() {
		return batchJobExecutionStartTime;
	}
	public void setBatchJobExecutionStartTime(Date batchJobExecutionStartTime) {
		this.batchJobExecutionStartTime = batchJobExecutionStartTime;
	}
	public String getBatchJobExecutionStartTimeAsString() {
		return DateTool.formatUtilDateTime(batchJobExecutionStartTime);
	}
	public Date getBatchJobExecutionEndTime() {
		return batchJobExecutionEndTime;
	}
	public void setBatchJobExecutionEndTime(Date batchJobExecutionEndTime) {
		this.batchJobExecutionEndTime = batchJobExecutionEndTime;
	}
	public String getBatchJobExecutionEndTimeAsString() {
		return DateTool.formatUtilDateTime(batchJobExecutionEndTime);
	}
	public String getBatchJobExecutionStatus() {
		return batchJobExecutionStatus;
	}
	public void setBatchJobExecutionStatus(String batchJobExecutionStatus) {
		this.batchJobExecutionStatus = batchJobExecutionStatus;
	}
	public String getBatchJobExecutionStatusTranslated() {
		return BatchJobTranslater.getTranslatedBatchJobStatus(batchJobExecutionStatus);
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
		return DateTool.formatUtilDateTime(batchJobExecutionLastUpdated);
	}
}
