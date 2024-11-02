package be.luxuryoverdosis.framework.business.service.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.dto.NumberDTO;
import be.luxuryoverdosis.framework.data.to.Number;

public interface NumberService {
	NumberDTO createOrUpdateDTO(NumberDTO numberDTO);
	NumberDTO readDTO(int id);
	
	Number createOrUpdate(Number number);
	Number read(int id);
	Number read(String applicationCode, String year, String type);
	void delete(int id);
	
	ArrayList<Number> list();
}
