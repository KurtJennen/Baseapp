package be.luxuryoverdosis.framework.data.to;

import java.util.Date;

public class BatchJobInstance {
	private long id;
	private long version;
	private String jobName;
	private String jobKey;
	
	long batchJobExecutionId;
	long batchJobExecutionVersion;
	Date batchJobExecutionCreateTime;
	Date batchJobExecutionStartTime; 
	Date batchJobExecutionEndTime;
	String batchJobExecutionStatus;
	String batchJobExecutionExitCode;
	String batchJobExecutionExitMessage;
	Date batchJobExecutionLastUpdated;
	
	public BatchJobInstance() {
	}
	
	public BatchJobInstance(long id, String jobName, long batchJobExecutionId, long batchJobExecutionVersion, Date batchJobExecutionCreateTime, Date batchJobExecutionStartTime, 
			Date batchJobExecutionEndTime, String batchJobExecutionStatus, String batchJobExecutionExitCode, String batchJobExecutionExitMessage, Date batchJobExecutionLastUpdated) {
		super();
		this.id = id;
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
	public long getVersion() {
		return version;
	}
	public void setVersion(long version) {
		this.version = version;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getJobKey() {
		return jobKey;
	}
	public void setJobKey(String jobKey) {
		this.jobKey = jobKey;
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
	public Date getBatchJobExecutionStartTime() {
		return batchJobExecutionStartTime;
	}
	public void setBatchJobExecutionStartTime(Date batchJobExecutionStartTime) {
		this.batchJobExecutionStartTime = batchJobExecutionStartTime;
	}
	public Date getBatchJobExecutionEndTime() {
		return batchJobExecutionEndTime;
	}
	public void setBatchJobExecutionEndTime(Date batchJobExecutionEndTime) {
		this.batchJobExecutionEndTime = batchJobExecutionEndTime;
	}
	public String getBatchJobExecutionStatus() {
		return batchJobExecutionStatus;
	}
	public void setBatchJobExecutionStatus(String batchJobExecutionStatus) {
		this.batchJobExecutionStatus = batchJobExecutionStatus;
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
	
}
