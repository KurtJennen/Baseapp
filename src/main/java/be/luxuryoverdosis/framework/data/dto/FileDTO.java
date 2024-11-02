package be.luxuryoverdosis.framework.data.dto;

public class FileDTO extends BaseDTO {
	private byte[] fileData;
	private String fileName;
	private int fileSize;
	private String contentType;
	
	public FileDTO() {
		super();
	}
	
	public FileDTO(final byte[] fileData, final String fileName, final int fileSize, final String contentType) {
		super();
		this.fileData = fileData;
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.contentType = contentType;
	}

	public byte[] getFileData() {
		return fileData;
	}
	public void setFileData(final byte[] fileData) {
		this.fileData = fileData;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(final String fileName) {
		this.fileName = fileName;
	}
	public int getFileSize() {
		return fileSize;
	}
	public void setFileSize(final int fileSize) {
		this.fileSize = fileSize;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(final String contentType) {
		this.contentType = contentType;
	}
	
}
