package be.luxuryoverdosis.framework.data.dao.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.to.QueryTO;

public interface QueryHibernateDAO {
	public QueryTO createOrUpdate(QueryTO queryTO);
	public QueryTO read(int id);
	public QueryTO read(String name, String type);
	public void delete(int id);
	
	public ArrayList<QueryTO> list(String type, int userId);
	
	public long count(String name, String type, int userId);
}
