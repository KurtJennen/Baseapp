package be.luxuryoverdosis.framework.data.dto;

public class FileDTO extends BaseDTO {
	private byte[] fileData;
	private String fileName;
	private int fileSize;
	private String contentType;
	
	public FileDTO() {
		super();
	}
	
	public FileDTO(byte[] fileData, String fileName, int fileSize, String contentType) {
		super();
		this.fileData = fileData;
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.contentType = contentType;
	}

	public byte[] getFileData() {
		return fileData;
	}
	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
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
