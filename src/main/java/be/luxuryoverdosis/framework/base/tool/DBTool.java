package be.luxuryoverdosis.framework.base.tool;

import be.luxuryoverdosis.baseapp.Constants;

public final class DBTool {
	private DBTool() {
	}
	
	public static String fixSqlFieldValue(final String value) {
		if (value == null) {
			return null;
		}		

		int length = value.length();
		StringBuffer fixedValue = new StringBuffer((int) (length * Constants.TENPROCENT));
		
		for (int i = 0; i < length; i++) {
			char c = value.charAt(i);
			if (c == '\'') {
				fixedValue.append("''");
			} else {
				fixedValue.append(c);
			}
		}
		
		return fixedValue.toString();
	}
}
