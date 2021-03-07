package be.luxuryoverdosis.framework.business.service.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.dto.QueryDTO;
import be.luxuryoverdosis.framework.data.to.Query;

public interface QueryService {
	public QueryDTO createOrUpdateDTO(QueryDTO queryDTO);
	public QueryDTO readDTO(int id);
	
	public Query createOrUpdate(Query query);
	public Query read(int id);
	public Query read(String name, String type, int userId);
	public String[] readNames(int id);
	public String[] readOperators(int id);
	public String[] readValues(int id);
	public String[] readAddAndOrs(int id);
	public String[] readOpenBrackets(int id);
	public String[] readCloseBrackets(int id);
	public void delete(int id);
	
	public ArrayList<Query> list(String type);
	
	public long countAndCreateOrUpdateDTO(String type, QueryDTO queryDTO);
}
