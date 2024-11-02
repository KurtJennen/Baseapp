package be.luxuryoverdosis.framework.business.enumeration;

import java.util.ArrayList;
import java.util.List;

import be.luxuryoverdosis.baseapp.business.service.SpringServiceLocator;

public enum RoleNameEnum {
	BEHEERDER(1, "Beheerder", 1),
	UITGEBREIDEGEBRUIKER(1, "UitgebreideGebruiker", 2),
	NORMALEGEBRUIKER(1, "NormaleGebruiker", 3);
	
	private static final List<String> ROLE_CODES = new ArrayList<String>();
	
	static {
		for (RoleNameEnum value : values()) {
			ROLE_CODES.add(value.getCode());
		}
	}
	
	private int type;
	private String code;
	private int rang;
	
	public int getType() {
		return type;
	}
	public String getCode() {
		return code;
	}
	public int getRang() {
		return rang;
	}

	public String getCodeAsKey() {
		return SpringServiceLocator.getMessage(this.getClass().getSimpleName() + "." + code);
	}
	
	RoleNameEnum(final int type, final String code, final  int rang) {
		this.type = type;
		this.code = code;
		this.rang = rang;
	}
	
	public static List<String> getAllCodes() {
		return ROLE_CODES;
	}
	
	public static RoleNameEnum convert(final String code) {
		for (RoleNameEnum roleEnum: values()) {
			if (roleEnum.getCode().equals(code)) {
				return roleEnum;
			}
		}
		
		return RoleNameEnum.NORMALEGEBRUIKER;
	}
	
	public static RoleNameEnum[] convert(final String[] array) {
		RoleNameEnum[] roleEnum = new RoleNameEnum[array.length];
		
		for (int i = 0; i < array.length; i++) {
			roleEnum[i] = convert(array[i]);
		}
		
		return roleEnum;
	}
}
