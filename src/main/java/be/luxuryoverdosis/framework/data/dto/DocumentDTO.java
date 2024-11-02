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
	
	public DocumentDTO(final int id, final String type, final String fileName, final int fileSize, final String contentType) {
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
	public void setType(final String type) {
		this.type = type;
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
