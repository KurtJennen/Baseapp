package be.luxuryoverdosis.framework.data.to;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name="base_job_param")
@Access(AccessType.FIELD)
@NamedQueries({
	@NamedQuery(name=JobParam.SELECT_JOBPARAMS_BY_JOB, query=JobParam.Queries.SELECT_JOBPARAMS_BY_JOB),
	@NamedQuery(name=JobParam.DELETE_JOBPARAMS_BY_JOB, query=JobParam.Queries.DELETE_JOBPARAMS_BY_JOB)
})
@Proxy(lazy=false)
public class JobParam extends BaseTO {
	public static final String SELECT_JOBPARAMS_BY_JOB = "selectJobParamsByJob";
	public static final String DELETE_JOBPARAMS_BY_JOB = "deleteJobParamsByJob";
	
	@Column(name="Name")
	private String name;
	
	@Column(name="Value")
	private String value;
	
	@ManyToOne
	@JoinColumn(name="Job_Id")
	private Job job;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}
	
	public static final class Queries {
		public static final String SELECT_JOBPARAMS_BY_JOB = "from JobParam jp "
				+ "where jp.job.id = :jobId";
		
		public static final String DELETE_JOBPARAMS_BY_JOB = "delete JobParam jp "
				+ "where jp.job.id = :jobId";
	}
	
}
