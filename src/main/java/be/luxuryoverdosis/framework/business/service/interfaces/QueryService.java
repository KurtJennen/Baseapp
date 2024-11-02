package be.luxuryoverdosis.framework.business.service.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.dto.QueryDTO;
import be.luxuryoverdosis.framework.data.to.Query;

public interface QueryService {
	QueryDTO createOrUpdateDTO(QueryDTO queryDTO);
	QueryDTO readDTO(int id);
	
	Query createOrUpdate(Query query);
	Query read(int id);
	Query read(String name, String type, int userId);
	String[] readNames(int id);
	String[] readOperators(int id);
	String[] readValues(int id);
	String[] readAddAndOrs(int id);
	String[] readOpenBrackets(int id);
	String[] readCloseBrackets(int id);
	void delete(int id);
	
	ArrayList<Query> list(String type);
	
	long countAndCreateOrUpdateDTO(String type, QueryDTO queryDTO);
}
