package be.luxuryoverdosis.framework;

import be.luxuryoverdosis.framework.base.FileType;

public class BaseConstants {
	protected BaseConstants() {
	}
	
	//Application Base
	public static final String NAME = "BASEAPP";
	
	public static final String TEMP_MAP = "c:\\temp\\";
	
	public static final String AUTHORIZATION = "Authorization";
	public static final String BASIC = "Basic";
	
	public static final String ZERO = "0";
	public static final String SPACE = " ";
	public static final String SLASH = "/";
	public static final String PIPE = "|";
	public static final String POINT = ".";
	public static final String UNDERSCORE = "_";
	public static final String DOUBLEPOINT = ":";
	public static final String AMPERSAND = "&";
	public static final String QUESTION = "?";
	public static final String CARRIAGE_RETURN = "\r\n";
	
	public static final String MAIL_FROM = "baseapp@skynet.be";
	public static final String MAIL_DELIMITER_DOUBLEPOINT = " : ";
	public static final String MAIL_DELIMITER_BR = "<BR>";
	
	public static final String JOB_NIVEAU_USER = "USER";
	
	public static final String JOB_ID = "jobId";
	public static final String JOB_EXPORT_USER = "userExportJob";
	public static final String JOB_EXPORT_USER_FILENAME = "userExport.txt";
	public static final String JOB_HEADERSELECTED_ID = "jobHeaderSelectedId";
	public static final String JOB_IMPORT_USER = "userImportJob";
	public static final String JOB_USER = "jobUser";
	public static final String JOB_USER_ID = "jobUserId";
	public static final String JOB_USER_ROOT = "root";
	
	public static final String REPORT_PATH = "/files/reports/";
	public static final String REPORT_USERS = "Users";
	public static final String REPORT_USERS_PATH = REPORT_PATH + REPORT_USERS + FileType.JRXML;
	public static final String FILE_USERS = REPORT_USERS + FileType.PDF;
	
	public static final int NUL = 0;
	public static final int EEN = 1;
	public static final int TWEE = 2;
	public static final int DRIE = 3;
	public static final int VIER = 4;
	public static final int VIJF = 5;
	public static final int ZES = 6;
	public static final int ZEVEN = 7;
	public static final int ACHT = 8;
	public static final int NEGEN = 9;
	public static final int TIEN = 10;
	public static final int VEERTIEN = 14;
	public static final int VIJFTIEN = 15;
	public static final int ZESTIG = 60;
	public static final int TACHTIG = 80;
	public static final int VIERHONDERDRIEENVEERTIG = 443;
	public static final int DUIZEND = 1000;
	public static final int DUIZENDVIERENTWINTIG = 1024;
	
	public static final double TENPROCENT = 1.1;
}
