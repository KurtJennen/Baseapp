package be.luxuryoverdosis.framework.web.enumeration;

import java.util.ArrayList;
import java.util.List;

import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;

public enum ButtonTypeEnum {
	CONFIRM("C"),
	DEFAULT("DE"),
	DIALOG("DI");
	
	private static final List<String> BUTTON_TYPE_CODES = new ArrayList<String>();
	
	static {
		for (ButtonTypeEnum value : values()) {
			BUTTON_TYPE_CODES.add(value.getCode());
		}
	}
	
	private String code;
	
	public String getCode() {
		return code;
	}
	
	public String getCodeAsKey() {
		return BaseSpringServiceLocator.getMessage(this.getClass().getSimpleName() + "." + code);
	}
	
	ButtonTypeEnum(final String code) {
		this.code = code;
	}
	
	public static List<String> getAllCodes() {
		return BUTTON_TYPE_CODES;
	}
	
	public static ButtonTypeEnum convert(final String code) {
		for (ButtonTypeEnum buttonTypeEnum: values()) {
			if (buttonTypeEnum.getCode().equals(code)) {
				return buttonTypeEnum;
			}
		}
		
		return ButtonTypeEnum.DEFAULT;
	}
	
	public static ButtonTypeEnum[] convert(final String[] array) {
		ButtonTypeEnum[] buttonTypeEnum = new ButtonTypeEnum[array.length];
		
		for (int i = 0; i < array.length; i++) {
			buttonTypeEnum[i] = convert(array[i]);
		}
		
		return buttonTypeEnum;
	}
}
