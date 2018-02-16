package be.luxuryoverdosis.framework.data.dao.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.to.Number;

public interface NumberHibernateDAO {
	public Number createOrUpdate(Number number);
	public Number read(int id);
	public Number read(String year, String type);
	public void delete(int id);
	
	public ArrayList<Number> list();
	
	public long count(String applicationCode, String year, String type, int id);
	
	public int nextNumber(String applicationCode, String year, String type);
}
