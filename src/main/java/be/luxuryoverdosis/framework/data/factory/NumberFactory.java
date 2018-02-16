package be.luxuryoverdosis.framework.data.factory;

import be.luxuryoverdosis.framework.data.dto.NumberDTO;
import be.luxuryoverdosis.framework.data.to.Number;

public class NumberFactory {
	private NumberFactory() {
	}
	
	public static NumberDTO produceNumberDTO(final Number number) {
		NumberDTO numberDTO = new NumberDTO();
		numberDTO.setId(number.getId());
		numberDTO.setApplicationCode(number.getApplicationCode());
		numberDTO.setYear(number.getYear());
		numberDTO.setNumber(number.getNumber());
		numberDTO.setType(number.getType());
		
		return numberDTO;
	}
	
	public static Number produceNumber(Number number, final NumberDTO numberDTO) {
		if(number == null) {
			number = new Number();
		}
		number.setId(numberDTO.getId());
		number.setApplicationCode(numberDTO.getApplicationCode());
		number.setYear(numberDTO.getYear());
		number.setNumber(numberDTO.getNumber());
		number.setType(numberDTO.getType());
		
		return number;
	}
}
