package be.luxuryoverdosis.framework.data.dto;

import be.luxuryoverdosis.baseapp.business.enumeration.RoleNameEnum;

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
	public String getNameAsKey() {
		return RoleNameEnum.convert(name).getCodeAsKey();
	}
	public void setName(String name) {
		this.name = name;
	}
}