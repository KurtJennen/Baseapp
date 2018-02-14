package be.luxuryoverdosis.framework.data.dto;

public class RoleDTO {
	private int id;
	private String name;
	
	public RoleDTO() {
		super();
	}
	
	public RoleDTO(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
