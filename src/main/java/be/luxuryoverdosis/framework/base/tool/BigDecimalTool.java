package be.luxuryoverdosis.framework.base.tool;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import org.apache.commons.validator.routines.BigDecimalValidator;

public class BigDecimalTool {
	public static final String BIGDECIMAL_FILTER_PATTERN = "#0.00";
	public static final String BIGDECIMAL_PATTERN = "###,##0.00";
	public static final String BIGDECIMAL_PATTERN_DOUBLE = "###,##0.0000";
	public static String ZERO = "0,00";
	public static String ZERO_DOUBLE = "0,0000";
	public static int FRACTION = 2;
	public static int FRACTION_DOUBLE = 4;
	
	public static String toString(BigDecimal decimal, int fraction) {
		String convertedDecimal = "";
		
		Locale locale = Locale.getDefault();
		
		NumberFormat formatter = NumberFormat.getNumberInstance(locale);

        formatter.setMinimumFractionDigits(fraction);
        formatter.setMaximumFractionDigits(fraction);
        formatter.setGroupingUsed(true);
        
        if(decimal != null) {
        	convertedDecimal = formatter.format(decimal);
        }
        
        return convertedDecimal;
	}
	
	public static String toString(BigDecimal decimal) {
		return toString(decimal, FRACTION);
	}
	
	public static String toStringDouble(BigDecimal decimal) {
		return toString(decimal, FRACTION_DOUBLE);
	}
	
	public static BigDecimal toBigDecimal(String string, int fraction) {
		BigDecimal convertedDecimal = BigDecimal.ZERO;
		
		Locale locale = Locale.getDefault();
		
        if(string != null) {
        	convertedDecimal = new BigDecimalValidator().validate(string, locale).setScale(fraction);
        }
        
        return convertedDecimal;
	}
	
	public static BigDecimal toBigDecimal(String string) {
		return toBigDecimal(string, FRACTION);
	}
	
	public static BigDecimal toBigDecimalDouble(String string) {
		return toBigDecimal(string, FRACTION_DOUBLE);
	}
}
