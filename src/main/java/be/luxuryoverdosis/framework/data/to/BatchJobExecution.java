package be.luxuryoverdosis.framework.data.to;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Proxy;

import be.luxuryoverdosis.framework.data.translater.BatchJobTranslater;

@Entity
@Table(name="batch_job_execution")
@Access(AccessType.FIELD)
@NamedQueries({
	@NamedQuery(name=BatchJobExecution.SELECT_BATCH_JOB_EXECUTIONS_BY_JOB_INSTANCE, query=BatchJobExecution.Queries.SELECT_BATCH_JOB_EXECUTIONS_BY_JOB_INSTANCE)
})
@Proxy(lazy=false)
public class BatchJobExecution {
	public static final String SELECT_BATCH_JOB_EXECUTIONS_BY_JOB_INSTANCE = "selectBatchJobExecutionsByJobInstance";
	
	@Id
	@Column(name="Job_Execution_Id")
	private long id;
	
	@Column(name="Version")
	private long version;
	
	@Column(name="Create_Time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	@Column(name="Start_Time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startTime;
	
	@Column(name="End_Time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endTime;
	
	@Column(name="Status")
	private String status;
	
	@Column(name="Exit_Code")
	private String exitCode;
	
	@Column(name="Exit_Message")
	private String exitMessage;
	
	@Column(name="Last_Updated")
	private Date lastUpdated;
	
	@ManyToOne
	@JoinColumn(name="Job_Instance_Id")
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
	public String getStatusTranslated() {
		return BatchJobTranslater.getBatchJobStatusTranslated(status);
	}
	public void setStatus(String status) {
		this.status = status;
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
	public BatchJobInstance getBatchJobInstance() {
		return batchJobInstance;
	}
	public void setBatchJobInstance(BatchJobInstance batchJobInstance) {
		this.batchJobInstance = batchJobInstance;
	}
	
	public static final class Queries {
		public static final String SELECT_BATCH_JOB_EXECUTIONS_BY_JOB_INSTANCE = "select bje "
				+ "from BatchJobExecution bje "
				+ "inner join bje.batchJobInstance bji "
				+ "where bji.id = :jobInstanceId "
				+ "order by bje.createTime desc";
	}
}
