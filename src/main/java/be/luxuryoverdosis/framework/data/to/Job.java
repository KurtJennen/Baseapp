package be.luxuryoverdosis.framework.data.to;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Proxy;

import be.luxuryoverdosis.framework.business.enumeration.JobStatusType;
import be.luxuryoverdosis.framework.data.convertor.JobStatusConvertor;
import be.luxuryoverdosis.framework.data.dto.FileDTO;

@Entity
@Table(name="base_job")
@Access(AccessType.FIELD)
@NamedQueries({
	@NamedQuery(name=Job.SELECT_JOBS_BY_NAME, query=Job.Queries.SELECT_JOBS_BY_NAME),
	@NamedQuery(name=Job.SELECT_JOBS_STARTED_BY_NAME, query=Job.Queries.SELECT_JOBS_STARTED_BY_NAME),
	@NamedQuery(name=Job.SELECT_JOBS_NOT_STARTED_BY_NAME, query=Job.Queries.SELECT_JOBS_NOT_STARTED_BY_NAME)
})
@Proxy(lazy=false)
public class Job extends BaseTO {
	public static final String SELECT_JOBS_BY_NAME = "selectJobsByName";
	public static final String SELECT_JOBS_STARTED_BY_NAME = "selectJobsStartedByName";
	public static final String SELECT_JOBS_NOT_STARTED_BY_NAME = "selectJobsNotStartedByName";
	
	@Column(name="Name")
	private String name;
	
	@Column(name="Filename")
	private String fileName;
	
	@Column(name="File")
	private Blob file;
	
	@Transient
	private byte[] fileData;
	
	@Column(name="Filesize")
	private int fileSize;
	
	@Column(name="Contenttype")
	private String contentType;
	
	@Column(name="Executed")
	private boolean executed;
	
	@Column(name="Started")
	@Temporal(TemporalType.TIMESTAMP)
	private Date started;
	
	@Column(name="Ended")
	@Temporal(TemporalType.TIMESTAMP)
	private Date ended;
	
	@Column(name="Status")
	@Convert(converter=JobStatusConvertor.class)
	private JobStatusType status;
	
	public Job() {
		super();
	}
	
	public Job(String name) {
		super();
		
		this.setName(name);
		this.setExecuted(false);
		this.setStatus(JobStatusType.NOT_STARTED);
	}
	
	public Job(String name, FileDTO fileDTO) {
		super();
		this.setName(name);
		this.setFileData(fileDTO.getFileData());
		this.setFileName(fileDTO.getFileName());
		this.setFileSize(fileDTO.getFileSize());
		this.setContentType(fileDTO.getContentType());
		this.setExecuted(false);
		this.setStatus(JobStatusType.NOT_STARTED);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
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
	public int getFileSize() {
		return fileSize;
	}
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public boolean isExecuted() {
		return executed;
	}
	public void setExecuted(boolean executed) {
		this.executed = executed;
	}
	public Date getStarted() {
		return started;
	}
	public void setStarted(Date started) {
		this.started = started;
	}
	public Date getEnded() {
		return ended;
	}
	public void setEnded(Date ended) {
		this.ended = ended;
	}
	public JobStatusType getStatus() {
		return status;
	}
	public void setStatus(JobStatusType status) {
		this.status = status;
	}
	
	public static final class Queries {
		public static final String SELECT_JOBS_BY_NAME = "from Job j "
				+ " where j.name = :name "
				+ "order by j.ended asc";
		
		public static final String SELECT_JOBS_STARTED_BY_NAME = "from Job j "
				+ "where j.name = :name "
				+ "and j.started is not null";
		
		public static final String SELECT_JOBS_NOT_STARTED_BY_NAME = "from Job j "
				+ "where j.name = :name "
				+ "and j.started is null";
	}
	
}
