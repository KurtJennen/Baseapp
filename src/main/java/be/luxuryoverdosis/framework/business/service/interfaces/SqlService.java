package be.luxuryoverdosis.framework.business.service.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.to.SqlTO;

public interface SqlService {
	public SqlTO createOrUpdate(SqlTO sqlTO);
	public SqlTO read(int id);
	public SqlTO readName(String name);
	public void delete(int id);
	
	public ArrayList<SqlTO> list();
	
	public long count(String name);
	public long count(String name, String application);
}
