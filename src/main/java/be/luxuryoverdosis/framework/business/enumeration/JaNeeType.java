package be.luxuryoverdosis.framework.business.enumeration;

import java.util.ArrayList;
import java.util.List;

public enum JaNeeType {
	JA("J"),
	NEE("N");
	
	private static final List<String> JA_NEE_CODES = new ArrayList<String>();
	
	static {
		for(JaNeeType value : values()) {
			JA_NEE_CODES.add(value.getCode());
		}
	}
	
	private String code;
	
	public String getCode() {
		return code;
	}

	private JaNeeType (String code) {
		this.code = code;
	}
	
	public static List<String> getAllCodes() {
		return JA_NEE_CODES;
	}
	
	public static JaNeeType convert(String code) {
		for (JaNeeType jaNeeType: values()) {
			if(jaNeeType.getCode().equals(code)) {
				return jaNeeType;
			}
		}
		
		return JaNeeType.NEE;
	}
	
	public static JaNeeType[] convert(String[] array) {
		JaNeeType jaNeeType[] = new JaNeeType[array.length];
		
		for (int i = 0; i < array.length; i++) {
			jaNeeType[i] = convert(array[i]);
		}
		
		return jaNeeType;
	}
}
