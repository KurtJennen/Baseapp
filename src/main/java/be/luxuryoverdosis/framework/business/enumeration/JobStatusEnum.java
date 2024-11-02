package be.luxuryoverdosis.framework.business.enumeration;

import java.util.ArrayList;
import java.util.List;

import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;

public enum JobStatusEnum {
	EXECUTED("E"),
	NOT_STARTED("N"),
	STARTED("Y");
	
	private static final List<String> JOB_STATUS_CODES = new ArrayList<String>();
	
	static {
		for (JobStatusEnum value : values()) {
			JOB_STATUS_CODES.add(value.getCode());
		}
	}
	
	private String code;
	
	public String getCode() {
		return code;
	}
	
	public String getCodeAsKey() {
		return BaseSpringServiceLocator.getMessage(this.getClass().getSimpleName() + "." + code);
	}

	JobStatusEnum(final String code) {
		this.code = code;
	}
	
	
	public static List<String> getAllCodes() {
		return JOB_STATUS_CODES;
	}
	
	public static JobStatusEnum convert(final String code) {
		for (JobStatusEnum jobStatusEnum: values()) {
			if (jobStatusEnum.getCode().equals(code)) {
				return jobStatusEnum;
			}
		}
		
		return JobStatusEnum.NOT_STARTED;
	}
	
	public static JobStatusEnum[] convert(final String[] array) {
		JobStatusEnum[] jobStatusEnum = new JobStatusEnum[array.length];
		
		for (int i = 0; i < array.length; i++) {
			jobStatusEnum[i] = convert(array[i]);
		}
		
		return jobStatusEnum;
	}
}
