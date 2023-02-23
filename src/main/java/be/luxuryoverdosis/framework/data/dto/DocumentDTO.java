package be.luxuryoverdosis.framework.data.dto;

public class DocumentDTO extends BaseDTO {
	private String type;
	private byte[] fileData;
	private String fileName;
	private int fileSize;
	private String contentType;
	
	public DocumentDTO() {
		super();
	}
	
	public DocumentDTO(int id, String type, String fileName, int fileSize, String contentType) {
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
