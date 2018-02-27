package be.luxuryoverdosis.framework.data.to;

import java.util.Date;

public class BatchJobExecution {
	private long id;
	private long version;
	private Date createTime;
	private Date startTime;
	private Date endTime;
	private String status;
	private String exitCode;
	private String exitMessage;
	private Date lastUpdated;
	private BatchJobInstance batchJobInstance;
	
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getExitCode() {
		return exitCode;
	}
	public void setExitCode(String exitCode) {
		this.exitCode = exitCode;
	}
	public String getExitMessage() {
		return exitMessage;
	}
	public void setExitMessage(String exitMessage) {
		this.exitMessage = exitMessage;
	}
	public Date getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	public BatchJobInstance getBatchJobInstance() {
		return batchJobInstance;
	}
	public void setBatchJobInstance(BatchJobInstance batchJobInstance) {
		this.batchJobInstance = batchJobInstance;
	}
	
}
