package be.luxuryoverdosis.framework.business.enumeration;

import java.util.ArrayList;
import java.util.List;

import be.luxuryoverdosis.baseapp.business.service.SpringServiceLocator;

public enum JaNeeEnum {
	JA("J"),
	NEE("N");
	
	private static final List<String> JA_NEE_CODES = new ArrayList<String>();
	
	static {
		for(JaNeeEnum value : values()) {
			JA_NEE_CODES.add(value.getCode());
		}
	}
	
	private String code;
	
	public String getCode() {
		return code;
	}
	
	public String getCodeAsKey() {
		return SpringServiceLocator.getMessage(this.getClass().getSimpleName() + "." + code);
	}
	
	private JaNeeEnum (String code) {
		this.code = code;
	}
	
	public static List<String> getAllCodes() {
		return JA_NEE_CODES;
	}
	
	public static JaNeeEnum convert(String code) {
		for (JaNeeEnum jaNeeEnum: values()) {
			if(jaNeeEnum.getCode().equals(code)) {
				return jaNeeEnum;
			}
		}
		
		return JaNeeEnum.NEE;
	}
	
	public static JaNeeEnum[] convert(String[] array) {
		JaNeeEnum jaNeeEnum[] = new JaNeeEnum[array.length];
		
		for (int i = 0; i < array.length; i++) {
			jaNeeEnum[i] = convert(array[i]);
		}
		
		return jaNeeEnum;
	}
}
