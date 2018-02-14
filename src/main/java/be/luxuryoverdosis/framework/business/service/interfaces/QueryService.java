package be.luxuryoverdosis.framework.business.service.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.dto.QueryDTO;
import be.luxuryoverdosis.framework.data.to.QueryTO;

public interface QueryService {
	public QueryDTO createOrUpdateDTO(QueryDTO queryDTO);
	public QueryDTO readDTO(int id);
	
	public QueryTO createOrUpdate(QueryTO queryTO);
	public QueryTO read(int id);
	public QueryTO read(String name, String type);
	public String[] readParameters(int id);
	public String[] readOperators(int id);
	public String[] readValues(int id);
	public String[] readAddAndOrs(int id);
	public String[] readOpenBrackets(int id);
	public String[] readCloseBrackets(int id);
	public void delete(int id);
	
	public ArrayList<QueryTO> list(String type);
	
	public long countAndCreateOrUpdateDTO(String name, String type, QueryDTO queryDTO);
}
