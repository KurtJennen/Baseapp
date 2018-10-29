package be.luxuryoverdosis.framework.data.dao.interfaces;

import java.util.ArrayList;

import org.springframework.dao.DataAccessException;

import be.luxuryoverdosis.framework.data.to.Sql;

public interface SqlHibernateDAO {
	public Sql createOrUpdate(Sql sql);
	public Sql read(int id);
	public Sql readName(String name);
	public void delete(int id);
	
	public ArrayList<Sql> list();
	
	public long count(String name);
	public long count(String name, String application);
	
	public int countJdbc(String name, String application) throws DataAccessException;
	public void executeJdbc(String sqlStatement) throws DataAccessException;
	public void insertJdbc(String sqlStatement, String name, String application) throws DataAccessException;
}
