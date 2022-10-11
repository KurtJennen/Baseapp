package be.luxuryoverdosis.framework.base.tool;

import org.apache.commons.lang.StringUtils;

import be.luxuryoverdosis.framework.BaseConstants;



public class StringTool {
	public static String toCamelCase(final String jobNiveau) {
		String camelCase = StringUtils.EMPTY;
		
		String[] splitJobNiveau = StringUtils.split(jobNiveau, BaseConstants.UNDERSCORE);
		
		for (int i = 0; i < splitJobNiveau.length; i++) {
			camelCase += splitJobNiveau[i].charAt(0) + splitJobNiveau[i].substring(1).toLowerCase();
		}
		
		return camelCase;
	}
}
