package be.luxuryoverdosis.framework.data.dao.interfaces;

import java.util.ArrayList;

import org.springframework.dao.DataAccessException;

import be.luxuryoverdosis.framework.data.to.Sql;

public interface SqlHibernateDAO {
	Sql createOrUpdate(Sql sql);
	Sql read(int id);
	Sql readName(String name);
	void delete(int id);
	
	ArrayList<Sql> list();
	
	long count(String name);
	long count(String name, String application);
	
	int countJdbc(String name, String application) throws DataAccessException;
	void executeJdbc(String sqlStatement) throws DataAccessException;
	void insertJdbc(String sqlStatement, String name, String application) throws DataAccessException;
}
