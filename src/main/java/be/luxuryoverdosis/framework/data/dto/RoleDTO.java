package be.luxuryoverdosis.framework.data.dto;


public class RoleDTO extends BaseDTO {
	private String name;
	
	public RoleDTO() {
		super();
	}
	
	public RoleDTO(final int id, final String name) {
		super();
		setId(id);
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(final String name) {
		this.name = name;
	}
}
