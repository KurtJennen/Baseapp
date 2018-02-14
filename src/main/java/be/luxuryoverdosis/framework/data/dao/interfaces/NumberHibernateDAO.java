package be.luxuryoverdosis.framework.data.dao.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.to.NumberTO;

public interface NumberHibernateDAO {
	public NumberTO createOrUpdate(NumberTO numberTO);
	public NumberTO read(int id);
	public NumberTO read(String year, String type);
	public void delete(int id);
	
	public ArrayList<NumberTO> list();
	
	public long count(String applicationCode, String year, String type, int id);
	
	public int nextNumber(String applicationCode, String year, String type);
}
