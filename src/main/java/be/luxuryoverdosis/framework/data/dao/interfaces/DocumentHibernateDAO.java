package be.luxuryoverdosis.framework.data.dao.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.dto.DocumentDTO;
import be.luxuryoverdosis.framework.data.to.Document;

public interface DocumentHibernateDAO {
	Document createOrUpdate(Document document);
	Document read(int id);
	void delete(int id);
	
	ArrayList<Document> list(String type);
	ArrayList<DocumentDTO> listDTO();
	
	long count(String type, String fileName, int id);
}
