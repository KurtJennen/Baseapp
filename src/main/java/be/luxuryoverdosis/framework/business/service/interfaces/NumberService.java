package be.luxuryoverdosis.framework.business.service.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.dto.NumberDTO;
import be.luxuryoverdosis.framework.data.to.NumberTO;

public interface NumberService {
	public NumberDTO createOrUpdateDTO(NumberDTO numberDTO);
	public NumberDTO readDTO(int id);
	
	public NumberTO createOrUpdate(NumberTO numberTO);
	public NumberTO read(int id);
	public NumberTO read(String year, String type);
	public void delete(int id);
	
	public ArrayList<NumberTO> list();
}
