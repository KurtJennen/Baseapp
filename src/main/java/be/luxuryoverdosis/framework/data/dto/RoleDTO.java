package be.luxuryoverdosis.framework.data.dto;

public class RoleDTO extends BaseDTO {
	private String name;
	
	public RoleDTO() {
		super();
	}
	
	public RoleDTO(int id, String name) {
		super();
		setId(id);
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
