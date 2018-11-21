package be.luxuryoverdosis.framework.data.dto;

public class DocumentDTO extends BaseDTO {
	private String type;
	private FileDTO fileDTO;
	
	public DocumentDTO() {
		super();
		fileDTO = new FileDTO();
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public FileDTO getFileDTO() {
		return fileDTO;
	}
	public void setFileDTO(FileDTO fileDTO) {
		this.fileDTO = fileDTO;
	}
}
