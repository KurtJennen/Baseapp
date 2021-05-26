package be.luxuryoverdosis.framework.business.enumeration;

import java.util.ArrayList;
import java.util.List;

import be.luxuryoverdosis.baseapp.business.service.SpringServiceLocator;

public enum DocumentTypeEnum {
	USER("USER");
	
	private static final List<String> DOCUMENT_TYPE_CODES = new ArrayList<String>();
	
	static {
		for(DocumentTypeEnum value : values()) {
			DOCUMENT_TYPE_CODES.add(value.getCode());
		}
	}
	
	private String code;
	
	public String getCode() {
		return code;
	}
	
	public String getCodeAsKey() {
		return SpringServiceLocator.getMessage(this.getClass().getSimpleName() + "." + code);
	}
	
	private DocumentTypeEnum (String code) {
		this.code = code;
	}
	
	public static List<String> getAllCodes() {
		return DOCUMENT_TYPE_CODES;
	}
	
	public static DocumentTypeEnum convert(String code) {
		for (DocumentTypeEnum documentTypeEnum: values()) {
			if(documentTypeEnum.getCode().equals(code)) {
				return documentTypeEnum;
			}
		}
		
		return DocumentTypeEnum.USER;
	}
	
	public static DocumentTypeEnum[] convert(String[] array) {
		DocumentTypeEnum documentTypeEnum[] = new DocumentTypeEnum[array.length];
		
		for (int i = 0; i < array.length; i++) {
			documentTypeEnum[i] = convert(array[i]);
		}
		
		return documentTypeEnum;
	}
}
