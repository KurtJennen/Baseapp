package be.luxuryoverdosis.framework.business.service.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.dto.NumberDTO;
import be.luxuryoverdosis.framework.data.to.Number;

public interface NumberService {
	public NumberDTO createOrUpdateDTO(NumberDTO numberDTO);
	public NumberDTO readDTO(int id);
	
	public Number createOrUpdate(Number number);
	public Number read(int id);
	public Number read(String year, String type);
	public void delete(int id);
	
	public ArrayList<Number> list();
}
