package be.luxuryoverdosis.framework.business.enumeration;

import java.util.ArrayList;
import java.util.List;

public enum JobStatusType {
	EXECUTED("E"),
	NOT_STARTED("N"),
	STARTED("Y");
	
	private static final List<String> JOB_STATUS_CODES = new ArrayList<String>();
	
	static {
		for(JobStatusType value : values()) {
			JOB_STATUS_CODES.add(value.getCode());
		}
	}
	
	private String code;
	
	public String getCode() {
		return code;
	}

	private JobStatusType (String code) {
		this.code = code;
	}
	
	
	public static List<String> getAllCodes() {
		return JOB_STATUS_CODES;
	}
	
	public static JobStatusType convert(String code) {
		for (JobStatusType jobStatusType: values()) {
			if(jobStatusType.getCode().equals(code)) {
				return jobStatusType;
			}
		}
		
		return JobStatusType.NOT_STARTED;
	}
	
	public static JobStatusType[] convert(String[] array) {
		JobStatusType jobStatusType[] = new JobStatusType[array.length];
		
		for (int i = 0; i < array.length; i++) {
			jobStatusType[i] = convert(array[i]);
		}
		
		return jobStatusType;
	}
}
