package be.luxuryoverdosis.framework.business.service.interfaces;

import java.util.ArrayList;

import org.springframework.dao.DataAccessException;

import be.luxuryoverdosis.framework.data.to.Sql;

public interface SqlService {
	Sql createOrUpdate(Sql sql);
	Sql read(int id);
	Sql readName(String name);
	void delete(int id);
	
	ArrayList<Sql> list();
	
	long count(String name);
	long count(String name, String application);
	
	void execute(String sqlStatement, String name, String application) throws DataAccessException;
}
