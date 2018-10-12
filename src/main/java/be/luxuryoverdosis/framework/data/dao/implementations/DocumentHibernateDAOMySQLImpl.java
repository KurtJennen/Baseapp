package be.luxuryoverdosis.framework.data.dao.implementations;

import java.sql.Blob;
import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import be.luxuryoverdosis.framework.data.dao.AbstractHibernateDaoSupport;
import be.luxuryoverdosis.framework.data.dao.interfaces.DocumentHibernateDAO;
import be.luxuryoverdosis.framework.data.to.Document;
import be.luxuryoverdosis.framework.logging.Logging;

@Repository
public class DocumentHibernateDAOMySQLImpl extends AbstractHibernateDaoSupport implements DocumentHibernateDAO {
	private static final String FILE_NAME = "fileName";
	private static final String ID = "id";
	private static final String TYPE = "type";
	
	public Document createOrUpdate(final Document document) {
		Logging.info(this, "Begin createDocument");
		try {
			Session session = getSessionFactory().openSession();
			Blob blob = session.getLobHelper().createBlob(document.getFileData());
			//Blob blob = Hibernate.createBlob(document.getFileData());
			document.setFile(blob);
		} catch (Exception e) {
			Logging.error(this, "Blob error" + e.getMessage());
		}
		getCurrentSession().saveOrUpdate(document);
		Logging.info(this, "End createDocument");
		return document;
	}

	public Document read(final int id) {
		Logging.info(this, "Begin readDocument");
		Document document = (Document) getCurrentSession().load(Document.class, id);
		Logging.info(this, "End readDocument");
		return document;
	}

	public void delete(final int id) {
		Logging.info(this, "Begin deleteDocument");
		getCurrentSession().delete(this.read(id));
		Logging.info(this, "End deleteDocument");		
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Document> list() {
		Logging.info(this, "Begin listDocument");
		
		Query query = getCurrentSession().getNamedQuery("getAllDocuments");
		ArrayList<Document> arrayList = (ArrayList<Document>) query.list();
		
		Logging.info(this, "End listDocument");
		return arrayList;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Document> list(final String type) {
		Logging.info(this, "Begin listDocument");
		
		Query query = getCurrentSession().getNamedQuery("getAllDocumentsByType");
		query.setString(TYPE, type);
		ArrayList<Document> arrayList = (ArrayList<Document>) query.list();
		
		Logging.info(this, "End listDocument");
		return arrayList;
	}
	
	@SuppressWarnings("unchecked")
	public long count(final String type, final String fileName, final int id) {
		Logging.info(this, "Begin countDocument(String, String, int)");
		
		Query query = getCurrentSession().getNamedQuery("getCountDocumentsByTypeAndFilename");
		query.setString(TYPE, type);
		query.setString(FILE_NAME, fileName);
		query.setInteger(ID, id);
		ArrayList<Long> arrayList = (ArrayList<Long>) query.list();
		
		long count = arrayList.iterator().next().longValue();
		Logging.info(this, "End countDocument(String, String, int)");
		return count;
	}
	
}
