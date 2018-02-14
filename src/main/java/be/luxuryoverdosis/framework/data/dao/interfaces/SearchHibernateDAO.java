package be.luxuryoverdosis.framework.data.dao.interfaces;

import java.util.ArrayList;

public interface SearchHibernateDAO {
	public ArrayList<Object> search(String select, Object[] objects);
}
