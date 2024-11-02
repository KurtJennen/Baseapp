package be.luxuryoverdosis.framework.data.dao.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.to.Number;

public interface NumberHibernateDAO {
	Number createOrUpdate(Number number);
	Number read(int id);
	Number read(String applicationCode, String year, String type);
	void delete(int id);
	
	ArrayList<Number> list();
	
	long count(String applicationCode, String year, String type, int id);
	
	int nextNumber(String applicationCode, String year, String type);
}
