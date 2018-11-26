package be.luxuryoverdosis.framework.data.convertor;

import javax.persistence.AttributeConverter;

import be.luxuryoverdosis.framework.business.enumeration.JobStatusType;

public class JobStatusConvertor implements AttributeConverter<JobStatusType, String>{

	@Override
	public String convertToDatabaseColumn(JobStatusType attribute) {
		return attribute.getCode();
	}

	@Override
	public JobStatusType convertToEntityAttribute(String attribute) {
		return JobStatusType.convert(attribute);
	}

}
