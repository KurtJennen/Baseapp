package be.luxuryoverdosis.framework.base.tool;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang.StringUtils;

public final class DateTool {
	private DateTool() {
	}
	
	public static final String UTIL_DATE_NO_PATTERN = "ddMMyyyy";
	public static final String UTIL_DATE_PATTERN = "dd/MM/yyyy";
	public static final String UTIL_DATE_PATTERN_DAY_NAME = "EE";
	public static final String UTIL_DATETIME_PATTERN = "dd/MM/yyyy HH:mm:ss";
	public static final String SQL_DATE_PATTERN = "yyyy-MM-dd";
	
	public static final int YEAR = 2100;
	public static final int DAY = 1;
	
	
	public static final int NOT_VALID = -1;
	public static final int FUTURE = 0;
	public static final int PAST = 1;
	public static final int NOW = 2;
	
	
	public static java.sql.Date toSqlDate(final java.util.Date utilDate) {
		return new java.sql.Date(utilDate.getTime());
	}
	
	public static java.util.Date toUtilDate(final java.sql.Date sqlDate) {
		return new java.util.Date(sqlDate.getTime());
	}
	
	
	
	public static String formatUtilDate(final java.util.Date date) {
		return formatDate(date, UTIL_DATE_PATTERN);
	}
	
	public static String formatUtilDateTime(final java.util.Date date) {
		return formatDate(date, UTIL_DATETIME_PATTERN);
	}
	
	public static String formatSqlDate(final java.util.Date date) {
		return formatDate(date, SQL_DATE_PATTERN);
	}
	
	public static String formatDate(final java.util.Date date, final String datePattern) {
		if (date == null) {
			return StringUtils.EMPTY;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
		dateFormat.setLenient(false);
		return dateFormat.format(date);		
	}
	
	
	@Deprecated
	public static java.util.Date parseUtilDate(final String date) throws ParseException {
		return parseDate(date, UTIL_DATE_PATTERN);
	}
	
	@Deprecated
	public static java.util.Date parseSqlDate(final String date) throws ParseException {
		return parseDate(date, SQL_DATE_PATTERN);
	}
	
	@Deprecated
	public static java.util.Date parseDate(final String date, final String datePattern) throws ParseException {
		if (StringUtils.isEmpty(date)) {
			return null;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
		dateFormat.setLenient(false);
		java.util.Date parseDate = dateFormat.parse(date);
		return parseDate;
	}
	
	
	public static Timestamp parseUtilTimestamp(final String date) throws ParseException {
		return parseTimestamp(date, UTIL_DATE_PATTERN);
	}
	
	public static Timestamp parseSqlTimestamp(final String date) throws ParseException {
		return parseTimestamp(date, SQL_DATE_PATTERN);
	}
	
	public static Timestamp parseTimestamp(final String date, final String datePattern) throws ParseException {
		if (StringUtils.isEmpty(date)) {
			return null;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
		dateFormat.setLenient(false);
		java.util.Date parseDate = dateFormat.parse(date);
		return new Timestamp(parseDate.getTime());
	}
	
	
	public static Calendar getDefaultCalendar() {
		return getCalendar(YEAR, Calendar.JANUARY, DAY);
	}
	
	public static Calendar getCalendar(final int year, final int month, final int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(year, month, day);
		
		return calendar;
	}
	
	public static Calendar getCalendar(final java.util.Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.setTime(date);
		
		return calendar;
	}
	
	public static java.util.Date getDefaultDateFromCalendar() {
		return getDateFromCalendar(YEAR, Calendar.JANUARY, DAY);
	}
	
	public static java.util.Date getDateFromCalendar(final int year, final int month, final int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(year, month, day);
		
		return calendar.getTime();
	}

	public static Date getCurrentDateTimeFromCalendar() {
		Calendar today =  Calendar.getInstance();
		
		return today.getTime();
	}
	
	public static Date getCurrentDateFromCalendar() {
		Calendar today =  Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		today.set(Calendar.MILLISECOND, 0);
		
		return today.getTime();
	}
	
	
	public static boolean isDateInYear(final Date date, final String year) {
		if (date == null) {
			return true;
		}
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		if (year.equals(String.valueOf(calendar.get(Calendar.YEAR)))) {
			return true;
		}
		
		return false;
	}
	
	public static int locateDate(final String date, final String datePattern) {
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(DateTool.parseTimestamp(date, datePattern));
		} catch (Exception e) {
			return NOT_VALID;
		}
		
		if (calendar.getTimeInMillis() > getCurrentDateFromCalendar().getTime()) {
			return FUTURE;
		}
		if (calendar.getTimeInMillis() < getCurrentDateFromCalendar().getTime()) {
			return PAST;
		}
		
		return NOW;
	}
	
	public static XMLGregorianCalendar getXMLGregorianCalendar() throws DatatypeConfigurationException {
		Date now = new Date();
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(now);
		
		return DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
	}
	
}
