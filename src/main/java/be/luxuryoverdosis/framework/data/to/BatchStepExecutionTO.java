package be.luxuryoverdosis.framework.data.to;

import java.util.Date;

public class BatchStepExecutionTO {
	private long id;
	private long version;
	private String stepName;
	private Date startTime;
	private Date endTime;
	private String status;
	private long commitCount;
	private long readCount;
	private long filterCount;
	private long writeCount;
	private long readSkipCount;
	private long writeSkipCount;
	private long processSkipCount;
	private long rollbackCount;
	private String exitCode;
	private String exitMessage;
	private Date lastUpdated;
	private BatchJobExecution batchJobExecution;
	
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
	public String getStepName() {
		return stepName;
	}
	public void setStepName(String stepName) {
		this.stepName = stepName;
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
	public long getCommitCount() {
		return commitCount;
	}
	public void setCommitCount(long commitCount) {
		this.commitCount = commitCount;
	}
	public long getReadCount() {
		return readCount;
	}
	public void setReadCount(long readCount) {
		this.readCount = readCount;
	}
	public long getFilterCount() {
		return filterCount;
	}
	public void setFilterCount(long filterCount) {
		this.filterCount = filterCount;
	}
	public long getWriteCount() {
		return writeCount;
	}
	public void setWriteCount(long writeCount) {
		this.writeCount = writeCount;
	}
	public long getReadSkipCount() {
		return readSkipCount;
	}
	public void setReadSkipCount(long readSkipCount) {
		this.readSkipCount = readSkipCount;
	}
	public long getWriteSkipCount() {
		return writeSkipCount;
	}
	public void setWriteSkipCount(long writeSkipCount) {
		this.writeSkipCount = writeSkipCount;
	}
	public long getProcessSkipCount() {
		return processSkipCount;
	}
	public void setProcessSkipCount(long processSkipCount) {
		this.processSkipCount = processSkipCount;
	}
	public long getRollbackCount() {
		return rollbackCount;
	}
	public void setRollbackCount(long rollbackCount) {
		this.rollbackCount = rollbackCount;
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
	public BatchJobExecution getBatchJobExecution() {
		return batchJobExecution;
	}
	public void setBatchJobExecution(BatchJobExecution batchJobExecution) {
		this.batchJobExecution = batchJobExecution;
	}
	
}
