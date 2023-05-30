package be.luxuryoverdosis.framework.base.tool;

import org.apache.commons.lang.StringUtils;

public class IntegerTool {
	public static final String INTEGER_PATTERN = "#";
	
	public static int toInteger(String string) {
		int convertedInteger = 0;
		
		if(string != null && StringUtils.isNotEmpty(string)) {
			convertedInteger = Integer.parseInt(string);
		}
		
		return convertedInteger;
	}

}
