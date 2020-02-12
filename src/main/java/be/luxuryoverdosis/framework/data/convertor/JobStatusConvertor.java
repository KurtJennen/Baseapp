package be.luxuryoverdosis.framework.data.convertor;

import javax.persistence.AttributeConverter;

import be.luxuryoverdosis.framework.business.enumeration.JobStatusEnum;

public class JobStatusConvertor implements AttributeConverter<JobStatusEnum, String>{

	@Override
	public String convertToDatabaseColumn(JobStatusEnum attribute) {
		return attribute.getCode();
	}

	@Override
	public JobStatusEnum convertToEntityAttribute(String attribute) {
		return JobStatusEnum.convert(attribute);
	}

}
