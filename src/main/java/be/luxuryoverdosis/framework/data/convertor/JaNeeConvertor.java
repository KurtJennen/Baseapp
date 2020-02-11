package be.luxuryoverdosis.framework.data.convertor;

import javax.persistence.AttributeConverter;

import be.luxuryoverdosis.framework.business.enumeration.JaNeeEnum;

public class JaNeeConvertor implements AttributeConverter<JaNeeEnum, String>{

	@Override
	public String convertToDatabaseColumn(JaNeeEnum attribute) {
		return attribute.getCode();
	}

	@Override
	public JaNeeEnum convertToEntityAttribute(String attribute) {
		return JaNeeEnum.convert(attribute);
	}

}
