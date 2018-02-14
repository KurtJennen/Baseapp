package be.luxuryoverdosis.framework.data.dao.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.to.DocumentTO;

public interface DocumentHibernateDAO {
	public DocumentTO createOrUpdate(DocumentTO documentTO);
	public DocumentTO read(int id);
	public void delete(int id);
	
	public ArrayList<DocumentTO> list();
	public ArrayList<DocumentTO> list(String type);
	
	public long count(String type, String fileName, int id);
}
