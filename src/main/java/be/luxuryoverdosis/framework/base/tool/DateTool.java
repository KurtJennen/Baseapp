package be.luxuryoverdosis.framework.base.tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;

public class DateTool {
	public static final String UTIL_DATE_PATTERN = "dd/MM/yyyy";
	public static final String UTIL_DATE_PATTERN_CENTURY = "EE";
	public static final String UTIL_DATETIME_PATTERN = "dd/MM/yyyy hh:mm:ss";
	public static final String SQL_DATE_PATTERN = "yyyy-MM-dd";
	
	public static java.sql.Date toSqlDate(java.util.Date utilDate) {
		return new java.sql.Date(utilDate.getTime());
	}
	
	public static java.util.Date toUtilDate(java.sql.Date sqlDate) {
		return new java.util.Date(sqlDate.getTime());
	}
	
	
	
	public static String formatUtilDate(java.util.Date date) {
		return formatDate(date, UTIL_DATE_PATTERN);
	}
	
	public static String formatUtilDateTime(java.util.Date date) {
		return formatDate(date, UTIL_DATETIME_PATTERN);
	}
	
	public static String formatSqlDate(java.util.Date date) {
		return formatDate(date, SQL_DATE_PATTERN);
	}
	
	public static String formatDate(java.util.Date date, String datePattern) {
		if(date == null) {
			return StringUtils.EMPTY;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
		dateFormat.setLenient(false);
		return dateFormat.format(date);		
	}
	
	
	
	public static java.util.Date parseUtilDate(String date) throws ParseException {
		return parseDate(date, UTIL_DATE_PATTERN);
	}
	
	public static java.util.Date parseSqlDate(String date) throws ParseException {
		return parseDate(date, SQL_DATE_PATTERN);
	}
	
	public static java.util.Date parseDate(String date, String datePattern) throws ParseException {
		if(date.equals(StringUtils.EMPTY)) {
			return null;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
		dateFormat.setLenient(false);
		java.util.Date parseDate = dateFormat.parse(date);
		return parseDate;
	}
}
