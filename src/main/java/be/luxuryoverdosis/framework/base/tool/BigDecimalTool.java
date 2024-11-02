package be.luxuryoverdosis.framework.base.tool;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.routines.BigDecimalValidator;

public final class BigDecimalTool {
	private BigDecimalTool() {
	}
	
	public static final String BIGDECIMAL_FILTER_PATTERN = "#0.00";
	public static final String BIGDECIMAL_PATTERN = "###,##0.00";
	public static final String BIGDECIMAL_PATTERN_DOUBLE = "###,##0.0000";
	public static final String ZERO = "0,00";
	public static final String ZERO_DOUBLE = "0,0000";
	public static final int FRACTION = 2;
	public static final int FRACTION_DOUBLE = 4;
	
	public static String toString(final BigDecimal decimal, final int fraction, final boolean grouping) {
		String convertedDecimal = "";
		
		Locale locale = Locale.getDefault();
		
		NumberFormat formatter = NumberFormat.getNumberInstance(locale);

        formatter.setMinimumFractionDigits(fraction);
        formatter.setMaximumFractionDigits(fraction);
        formatter.setGroupingUsed(grouping);
        
        if (decimal != null) {
        	convertedDecimal = formatter.format(decimal);
        }
        
        return convertedDecimal;
	}
	
	public static String toString(final BigDecimal decimal) {
		return toString(decimal, FRACTION, true);
	}
	
	public static String toStringDouble(final BigDecimal decimal) {
		return toString(decimal, FRACTION_DOUBLE, true);
	}
	
	public static BigDecimal toBigDecimal(final String string, final int fraction) {
		BigDecimal convertedDecimal = BigDecimal.ZERO;
		
		Locale locale = Locale.getDefault();
		
        if (string != null && StringUtils.isNotEmpty(string)) {
        	BigDecimal validatedDecimal = new BigDecimalValidator().validate(string, locale);
        	
        	if (validatedDecimal != null) {
        		convertedDecimal = new BigDecimalValidator().validate(string, locale).setScale(fraction, RoundingMode.HALF_UP);
        	}
        }
        
        return convertedDecimal;
	}
	
	public static BigDecimal toBigDecimal(final String string) {
		return toBigDecimal(string, FRACTION);
	}
	
	public static BigDecimal toBigDecimalDouble(final String string) {
		return toBigDecimal(string, FRACTION_DOUBLE);
	}
}
