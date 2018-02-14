package be.luxuryoverdosis.framework.data.factory;

import be.luxuryoverdosis.framework.data.dto.NumberDTO;
import be.luxuryoverdosis.framework.data.to.NumberTO;

public class NumberFactory {
	private NumberFactory() {
	}
	
	public static NumberDTO produceNumberDTO(final NumberTO numberTO) {
		NumberDTO numberDTO = new NumberDTO();
		numberDTO.setId(numberTO.getId());
		numberDTO.setApplicationCode(numberTO.getApplicationCode());
		numberDTO.setYear(numberTO.getYear());
		numberDTO.setNumber(numberTO.getNumber());
		numberDTO.setType(numberTO.getType());
		
		return numberDTO;
	}
	
	public static NumberTO produceNumber(NumberTO numberTO, final NumberDTO numberDTO) {
		if(numberTO == null) {
			numberTO = new NumberTO();
		}
		numberTO.setId(numberDTO.getId());
		numberTO.setApplicationCode(numberDTO.getApplicationCode());
		numberTO.setYear(numberDTO.getYear());
		numberTO.setNumber(numberDTO.getNumber());
		numberTO.setType(numberDTO.getType());
		
		return numberTO;
	}
}
