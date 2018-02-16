package be.luxuryoverdosis.framework.data.dao.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.to.Document;

public interface DocumentHibernateDAO {
	public Document createOrUpdate(Document document);
	public Document read(int id);
	public void delete(int id);
	
	public ArrayList<Document> list();
	public ArrayList<Document> list(String type);
	
	public long count(String type, String fileName, int id);
}
