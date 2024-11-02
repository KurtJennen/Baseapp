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
	
	public BatchJobInstanceDTO(final long id, final String jobName, final long batchJobExecutionId, final long batchJobExecutionVersion, final Date batchJobExecutionCreateTime, final Date batchJobExecutionStartTime, 
			final Date batchJobExecutionEndTime, final String batchJobExecutionStatus, final String batchJobExecutionExitCode, final String batchJobExecutionExitMessage, final Date batchJobExecutionLastUpdated) {
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
	public void setId(final long id) {
		this.id = id;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(final String jobName) {
		this.jobName = jobName;
	}
	public long getBatchJobExecutionId() {
		return batchJobExecutionId;
	}
	public void setBatchJobExecutionId(final long batchJobExecutionId) {
		this.batchJobExecutionId = batchJobExecutionId;
	}
	public long getBatchJobExecutionVersion() {
		return batchJobExecutionVersion;
	}
	public void setBatchJobExecutionVersion(final long batchJobExecutionVersion) {
		this.batchJobExecutionVersion = batchJobExecutionVersion;
	}
	public Date getBatchJobExecutionCreateTime() {
		return batchJobExecutionCreateTime;
	}
	public void setBatchJobExecutionCreateTime(final Date batchJobExecutionCreateTime) {
		this.batchJobExecutionCreateTime = batchJobExecutionCreateTime;
	}
	public String getBatchJobExecutionCreateTimeAsString() {
		return DateTool.formatUtilDateTime(batchJobExecutionCreateTime);
	}
	public Date getBatchJobExecutionStartTime() {
		return batchJobExecutionStartTime;
	}
	public void setBatchJobExecutionStartTime(final Date batchJobExecutionStartTime) {
		this.batchJobExecutionStartTime = batchJobExecutionStartTime;
	}
	public String getBatchJobExecutionStartTimeAsString() {
		return DateTool.formatUtilDateTime(batchJobExecutionStartTime);
	}
	public Date getBatchJobExecutionEndTime() {
		return batchJobExecutionEndTime;
	}
	public void setBatchJobExecutionEndTime(final Date batchJobExecutionEndTime) {
		this.batchJobExecutionEndTime = batchJobExecutionEndTime;
	}
	public String getBatchJobExecutionEndTimeAsString() {
		return DateTool.formatUtilDateTime(batchJobExecutionEndTime);
	}
	public String getBatchJobExecutionStatus() {
		return batchJobExecutionStatus;
	}
	public String getBatchJobExecutionStatusTranslated() {
		return BatchJobTranslater.getBatchJobStatusTranslated(batchJobExecutionStatus);
	}
	public void setBatchJobExecutionStatus(final String batchJobExecutionStatus) {
		this.batchJobExecutionStatus = batchJobExecutionStatus;
	}
	public String getBatchJobExecutionExitCode() {
		return batchJobExecutionExitCode;
	}
	public String getBatchJobExecutionExitCodeTranslated() {
		return BatchJobTranslater.getBatchJobExitCodeTranslated(batchJobExecutionExitCode);
	}
	public void setBatchJobExecutionExitCode(final String batchJobExecutionExitCode) {
		this.batchJobExecutionExitCode = batchJobExecutionExitCode;
	}
	public String getBatchJobExecutionExitMessage() {
		return batchJobExecutionExitMessage;
	}
	public void setBatchJobExecutionExitMessage(final String batchJobExecutionExitMessage) {
		this.batchJobExecutionExitMessage = batchJobExecutionExitMessage;
	}
	public Date getBatchJobExecutionLastUpdated() {
		return batchJobExecutionLastUpdated;
	}
	public void setBatchJobExecutionLastUpdated(final Date batchJobExecutionLastUpdated) {
		this.batchJobExecutionLastUpdated = batchJobExecutionLastUpdated;
	}
	public String getBatchJobExecutionLastUpdatedAsString() {
		return DateTool.formatUtilDateTime(batchJobExecutionLastUpdated);
	}
}
