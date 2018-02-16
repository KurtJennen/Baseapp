package be.luxuryoverdosis.framework.data.to;

import java.sql.Blob;

public class DocumentTO extends BaseTO {
	private String type;
	private String fileName;
	private Blob file;
	private byte[] fileData;
	private int fileSize;
	private String contentType;
	
	public DocumentTO() {
		super();
	}
	
	public DocumentTO(int id, String type, String fileName, int fileSize, String contentType) {
		super();
		setId(id);
		this.type = type;
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.contentType = contentType;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	
}
