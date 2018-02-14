package be.luxuryoverdosis.framework.data.dto;

public class DocumentDTO {
	private int id;
	private String type;
	private FileDTO fileDTO;
	
	public DocumentDTO() {
		super();
		fileDTO = new FileDTO();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
