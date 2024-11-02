package be.luxuryoverdosis.framework.data.convertor;

import javax.persistence.AttributeConverter;

import be.luxuryoverdosis.framework.business.enumeration.JaNeeEnum;

public class JaNeeConvertor implements AttributeConverter<JaNeeEnum, String> {

	@Override
	public String convertToDatabaseColumn(final JaNeeEnum attribute) {
		return attribute.getCode();
	}

	@Override
	public JaNeeEnum convertToEntityAttribute(final String attribute) {
		return JaNeeEnum.convert(attribute);
	}

}
