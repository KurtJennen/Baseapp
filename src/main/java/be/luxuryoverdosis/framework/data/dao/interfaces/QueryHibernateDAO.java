package be.luxuryoverdosis.framework.data.dao.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.to.Query;

public interface QueryHibernateDAO {
	Query createOrUpdate(Query query);
	Query read(int id);
	Query read(String name, String type, int userId);
	void delete(int id);
	
	ArrayList<Query> list(String type, int userId);
	
	long count(String name, String type, int userId);
}
