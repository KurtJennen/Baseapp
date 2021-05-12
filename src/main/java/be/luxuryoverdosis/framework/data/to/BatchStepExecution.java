package be.luxuryoverdosis.framework.data.to;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Proxy;

import be.luxuryoverdosis.framework.base.tool.DateTool;
import be.luxuryoverdosis.framework.data.translater.BatchJobTranslater;

@Entity
@Table(name="batch_step_execution")
@Access(AccessType.FIELD)
@NamedQueries({
	@NamedQuery(name=BatchStepExecution.SELECT_BATCH_STEP_EXECUTIONS_BY_JOB_INSTANCE, query=BatchStepExecution.Queries.SELECT_BATCH_STEP_EXECUTIONS_BY_JOB_INSTANCE)
})
@NamedNativeQueries({
	@NamedNativeQuery(name=BatchStepExecution.DELETE_BATCH_STEP_EXECUTIONS_BY_JOB_INSTANCE, query=BatchStepExecution.NativeQueries.DELETE_BATCH_STEP_EXECUTIONS_BY_JOB_INSTANCE),
	@NamedNativeQuery(name=BatchStepExecution.DELETE_BATCH_STEP_EXECUTIONS_CONTEXTS_BY_JOB_INSTANCE, query=BatchStepExecution.NativeQueries.DELETE_BATCH_STEP_EXECUTIONS_CONTEXTS_BY_JOB_INSTANCE)
})
@Proxy(lazy=false)
public class BatchStepExecution {
	public static final String SELECT_BATCH_STEP_EXECUTIONS_BY_JOB_INSTANCE = "selectBatchStepExecutionsByJobInstance";
	public static final String DELETE_BATCH_STEP_EXECUTIONS_BY_JOB_INSTANCE = "deleteBatchStepExecutionsByJobInstance";
	public static final String DELETE_BATCH_STEP_EXECUTIONS_CONTEXTS_BY_JOB_INSTANCE = "deleteBatchStepExecutionsContextsByJobInstance";
	
	@Id
	@Column(name="Step_Execution_Id")
	private long id;
	
	@Column(name="Version")
	private long version;
	
	@Column(name="Step_Name")
	private String stepName;
	
	@Column(name="Start_Time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startTime;
	
	@Column(name="End_Time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endTime;
	
	@Column(name="Status")
	private String status;
	
	@Column(name="Commit_Count")
	private long commitCount;
	
	@Column(name="Read_Count")
	private long readCount;
	
	@Column(name="Filter_Count")
	private long filterCount;
	
	@Column(name="Write_Count")
	private long writeCount;
	
	@Column(name="Read_Skip_Count")
	private long readSkipCount;
	
	@Column(name="Write_Skip_Count")
	private long writeSkipCount;
	
	@Column(name="Process_Skip_Count")
	private long processSkipCount;
	
	@Column(name="Rollback_Count")
	private long rollbackCount;
	
	@Column(name="Exit_Code")
	private String exitCode;
	
	@Column(name="Exit_Message")
	private String exitMessage;
	
	@Column(name="Last_Updated")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdated;
	
	@ManyToOne
	@JoinColumn(name="Job_Execution_Id")
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
	public String getStartTimeAsString() {
		return DateTool.formatUtilDateTime(startTime);
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
	public String getEndTimeAsString() {
		return DateTool.formatUtilDateTime(endTime);
	}
	public String getStatus() {
		return status;
	}
	public String getStatusTranslated() {
		return BatchJobTranslater.getBatchJobStatusTranslated(status);
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
	public String getExitCodeTranslated() {
		return BatchJobTranslater.getBatchJobExitCodeTranslated(exitCode);
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
	public String getLastUpdatedAsString() {
		return DateTool.formatUtilDateTime(lastUpdated);
	}
	public BatchJobExecution getBatchJobExecution() {
		return batchJobExecution;
	}
	public void setBatchJobExecution(BatchJobExecution batchJobExecution) {
		this.batchJobExecution = batchJobExecution;
	}
	
	public static final class Queries {
		public static final String SELECT_BATCH_STEP_EXECUTIONS_BY_JOB_INSTANCE = "select bse "
				+ "from BatchStepExecution bse "
				+ "inner join bse.batchJobExecution bje "
				+ "inner join bje.batchJobInstance jbi "
				+ "where jbi.id = :jobInstanceId";
	}
	
	public static final class NativeQueries {
		public static final String DELETE_BATCH_STEP_EXECUTIONS_BY_JOB_INSTANCE = "delete "
				+ "from batch_step_execution "
				+ "where exists (select 1 from batch_job_execution where batch_job_execution.job_execution_id = batch_step_execution.job_execution_id and batch_job_execution.job_instance_id = :jobInstanceId)";
		
		public static final String DELETE_BATCH_STEP_EXECUTIONS_CONTEXTS_BY_JOB_INSTANCE = "delete "
				+ "from batch_step_execution_context "
				+ "where exists (select 1 from batch_step_execution where batch_step_execution.step_execution_id = batch_step_execution_context.step_execution_id "
				+ "and exists (select 1 from batch_job_execution where batch_job_execution.job_execution_id = batch_step_execution.job_execution_id and batch_job_execution.job_instance_id = :jobInstanceId))";
		
	}
}
