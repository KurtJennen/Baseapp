package be.luxuryoverdosis.framework.data.dao.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.to.Query;

public interface QueryHibernateDAO {
	public Query createOrUpdate(Query query);
	public Query read(int id);
	public Query read(String name, String type, int userId);
	public void delete(int id);
	
	public ArrayList<Query> list(String type, int userId);
	
	public long count(String name, String type, int userId);
}
