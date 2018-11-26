package be.luxuryoverdosis.framework.data.convertor;

import javax.persistence.AttributeConverter;

import be.luxuryoverdosis.framework.business.enumeration.JaNeeType;

public class JaNeeConvertor implements AttributeConverter<JaNeeType, String>{

	@Override
	public String convertToDatabaseColumn(JaNeeType attribute) {
		return attribute.getCode();
	}

	@Override
	public JaNeeType convertToEntityAttribute(String attribute) {
		return JaNeeType.convert(attribute);
	}

}
