package be.luxuryoverdosis.framework.data.dao.implementations;

import java.sql.Blob;
import java.util.ArrayList;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import be.luxuryoverdosis.framework.data.dao.AbstractHibernateDaoSupport;
import be.luxuryoverdosis.framework.data.dao.interfaces.DocumentHibernateDAO;
import be.luxuryoverdosis.framework.data.to.Document;
import be.luxuryoverdosis.framework.logging.Logging;

@Repository
public class DocumentHibernateDAOMySQLImpl extends AbstractHibernateDaoSupport implements DocumentHibernateDAO {
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
		getHibernateTemplate().saveOrUpdate(document);
		Logging.info(this, "End createDocument");
		return document;
	}

	public Document read(final int id) {
		Logging.info(this, "Begin readDocument");
		Document document = (Document) getHibernateTemplate().load(Document.class, id);
		Logging.info(this, "End readDocument");
		return document;
	}

	public void delete(final int id) {
		Logging.info(this, "Begin deleteDocument");
		getHibernateTemplate().delete((Document) getHibernateTemplate().load(Document.class, id));
		Logging.info(this, "End deleteDocument");		
	}
	
	private static final String listHql = "SELECT new be.luxuryoverdosis.framework.data.to.Document(" +
		"d.id, " +
		"d.type, " +
		"d.fileName, " +
		"d.fileSize, " +
		"d.contentType " +
		") " +
		"from Document d ";

	@SuppressWarnings("unchecked")
	public ArrayList<Document> list() {
		Logging.info(this, "Begin listDocument");
		ArrayList<Document> arrayList = (ArrayList<Document>) getHibernateTemplate().find(listHql);
		Logging.info(this, "End listDocument");
		return arrayList;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Document> list(final String type) {
		Logging.info(this, "Begin listDocument");
		ArrayList<Document> arrayList = (ArrayList<Document>) getHibernateTemplate().find("from Document d where d.type = ? order by d.fileName asc", new Object[]{type});
		Logging.info(this, "End listDocument");
		return arrayList;
	}
	
	@SuppressWarnings("unchecked")
	public long count(final String type, final String fileName, final int id) {
		Logging.info(this, "Begin countDocument(String, String, int)");
		ArrayList<Long> arrayList = (ArrayList<Long>) getHibernateTemplate().find("select count(*) from Document d where d.type = ? and d.fileName = ? and d.id <> ?", new Object[]{type, fileName, id});
		long count = arrayList.iterator().next().longValue();
		Logging.info(this, "End countDocument(String, String, int)");
		return count;
	}
	
}
