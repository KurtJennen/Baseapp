package be.luxuryoverdosis.framework.data.to;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "batch_job_instance")
@Access(AccessType.FIELD)
@NamedQueries({
	@NamedQuery(name = BatchJobInstance.SELECT_BATCH_JOB_INSTANCES_BY_JOB_NAME, query = BatchJobInstance.Queries.SELECT_BATCH_JOB_INSTANCES_BY_JOB_NAME),
	@NamedQuery(name = BatchJobInstance.SELECT_BATCH_JOB_INSTANCES_BY_JOB_NAME_AND_JOB_PARAMETER, query = BatchJobInstance.Queries.SELECT_BATCH_JOB_INSTANCES_BY_JOB_NAME_AND_JOB_PARAMETER),
	@NamedQuery(name = BatchJobInstance.DELETE_BATCH_JOB_INSTANCES_BY_ID, query = BatchJobInstance.Queries.DELETE_BATCH_JOB_INSTANCES_BY_ID)
})
@Proxy(lazy = false)
public class BatchJobInstance {
	public static final String SELECT_BATCH_JOB_INSTANCES_BY_JOB_NAME = "selectBatchJobInstancesByJobName";
	public static final String SELECT_BATCH_JOB_INSTANCES_BY_JOB_NAME_AND_JOB_PARAMETER = "selectBatchJobInstancesByJobNameAndJobParameter";
	public static final String DELETE_BATCH_JOB_INSTANCES_BY_ID = "deleteBatchJobInstancesById";
	
	@Id
	@Column(name = "Job_Instance_Id")
	private long id;
	
	@Column(name = "Version")
	private long version;
	
	@Column(name = "Job_Name")
	private String jobName;
	
	@Column(name = "Job_Key")
	private String jobKey;
	
	public long getId() {
		return id;
	}
	public void setId(final long id) {
		this.id = id;
	}
	public long getVersion() {
		return version;
	}
	public void setVersion(final long version) {
		this.version = version;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(final String jobName) {
		this.jobName = jobName;
	}
	public String getJobKey() {
		return jobKey;
	}
	public void setJobKey(final String jobKey) {
		this.jobKey = jobKey;
	}
	
	public static final class Queries {
		private static final String SELECT_BATCH_JOB_INSTANCES = "select new be.luxuryoverdosis.framework.data.dto.BatchJobInstanceDTO( "
				+ "bji.id, "
				+ "bji.jobName, "
				+ "bje.id, "
				+ "bje.version, "
				+ "bje.createTime, "
				+ "bje.startTime, "
				+ "bje.endTime, "
				+ "bje.status, "
				+ "bje.exitCode, "
				+ "bje.exitMessage, "
				+ "bje.lastUpdated "
				+ ") ";
		
		public static final String SELECT_BATCH_JOB_INSTANCES_BY_JOB_NAME = SELECT_BATCH_JOB_INSTANCES
				+ "from BatchJobExecution bje "
				+ "inner join bje.batchJobInstance bji "
				+ "where bji.jobName in (:jobNames) "
				+ "order by bje.createTime desc";
		
		public static final String SELECT_BATCH_JOB_INSTANCES_BY_JOB_NAME_AND_JOB_PARAMETER = SELECT_BATCH_JOB_INSTANCES
				+ "from BatchJobExecutionParams bjep "
				+ "inner join bjep.batchJobExecution bje "
				+ "inner join bje.batchJobInstance bji "
				+ "where bji.jobName in (:jobNames) "
				+ "and bjep.keyName = :keyName "
				+ "and bjep.longValue = :longValue "
				+ "order by bje.createTime desc";
		
		public static final String DELETE_BATCH_JOB_INSTANCES_BY_ID = "delete "
				+ "from BatchJobInstance bji "
				+ "where bji.id = :id ";
	}
}
