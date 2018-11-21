package be.luxuryoverdosis.framework.data.to;

import java.sql.Blob;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name="base_job_log")
@Access(AccessType.FIELD)
@NamedQueries({
	@NamedQuery(name=JobLog.SELECT_JOBLOGS_BY_JOB, query=JobLog.Queries.SELECT_JOBLOGS_BY_JOB),
	@NamedQuery(name=JobLog.DELETE_JOBLOGS_BY_JOB, query=JobLog.Queries.DELETE_JOBLOGS_BY_JOB)
})
@Proxy(lazy=false)
public class JobLog extends BaseTO {
	public static final String SELECT_JOBLOGS_BY_JOB = "selectJobLogsByJob";
	public static final String DELETE_JOBLOGS_BY_JOB = "deleteJobLogsByJob";
	
	@Column(name="Input")
	private String input;
	
	@Column(name="Output")
	private String output;
	
	@Column(name="File")
	private Blob file;
	
	@Transient
	private byte[] fileData;
	
	@ManyToOne
	@JoinColumn(name="Job_Id")
	private Job job;
	
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	public String getOutput() {
		return output;
	}
	public void setOutput(String output) {
		this.output = output;
	}
	public Blob getFile() {
		return file;
	}
	public void setFile(Blob file) {
		this.file = file;
	}
	public byte[] getFileData() {
		return fileData;
	}
	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}
	
	public static final class Queries {
		public static final String SELECT_JOBLOGS_BY_JOB = "from JobLog jl "
				+ "where jl.job.id = :jobId";
		
		public static final String DELETE_JOBLOGS_BY_JOB = "delete JobLog jl "
				+ "where jl.job.id = :jobId";
	}
}
