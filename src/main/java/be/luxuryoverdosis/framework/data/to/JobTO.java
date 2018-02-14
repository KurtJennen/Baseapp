package be.luxuryoverdosis.framework.data.to;

import java.sql.Blob;
import java.util.Date;

import be.luxuryoverdosis.framework.BaseConstants;
import be.luxuryoverdosis.framework.data.dto.FileDTO;

public class JobTO extends BaseTO {
	private String name;
	private String fileName;
	private Blob file;
	private byte[] fileData;
	private int fileSize;
	private String contentType;
	private boolean executed;
	private Date started;
	private Date ended;
	private String status;
	
	public JobTO() {
		super();
	}
	
	public JobTO(String name) {
		super();
		
		this.setName(name);
		this.setExecuted(false);
		this.setStatus(BaseConstants.JOB_STATUS_NOT_STARTED);
	}
	
	public JobTO(String name, FileDTO fileDTO) {
		super();
		this.setName(name);
		this.setFileData(fileDTO.getFileData());
		this.setFileName(fileDTO.getFileName());
		this.setFileSize(fileDTO.getFileSize());
		this.setContentType(fileDTO.getContentType());
		this.setExecuted(false);
		this.setStatus(BaseConstants.JOB_STATUS_NOT_STARTED);
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
