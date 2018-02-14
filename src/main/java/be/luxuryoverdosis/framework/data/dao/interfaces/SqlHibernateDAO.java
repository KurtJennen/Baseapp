package be.luxuryoverdosis.framework.data.dao.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.to.SqlTO;

public interface SqlHibernateDAO {
	public SqlTO createOrUpdate(SqlTO sqlTO);
	public SqlTO read(int id);
	public SqlTO readName(String name);
	public void delete(int id);
	
	public ArrayList<SqlTO> list();
	
	public long count(String name);
	public long count(String name, String application);
}
