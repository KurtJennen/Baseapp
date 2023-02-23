package be.luxuryoverdosis.framework.data.dao.implementations;

import java.sql.Blob;
import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import be.luxuryoverdosis.framework.data.dao.AbstractHibernateDaoSupport;
import be.luxuryoverdosis.framework.data.dao.BaseQueryParameters;
import be.luxuryoverdosis.framework.data.dao.interfaces.DocumentHibernateDAO;
import be.luxuryoverdosis.framework.data.dto.DocumentDTO;
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
	public ArrayList<Document> list(final String type) {
		Logging.info(this, "Begin listDocument");
		
		Query<Document> query = getCurrentSession().getNamedQuery(Document.SELECT_DOCUMENTS_BY_TYPE);
		query.setParameter(BaseQueryParameters.TYPE, type);
		ArrayList<Document> arrayList = (ArrayList<Document>) query.list();
		
		Logging.info(this, "End listDocument");
		return arrayList;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<DocumentDTO> listDTO() {
		Logging.info(this, "Begin listDocument");
		
		Query<DocumentDTO> query = getCurrentSession().getNamedQuery(Document.SELECT_DOCUMENTS_DTO);
		ArrayList<DocumentDTO> arrayList = (ArrayList<DocumentDTO>) query.list();
		
		Logging.info(this, "End listDocument");
		return arrayList;
	}
	
	@SuppressWarnings("unchecked")
	public long count(final String type, final String fileName, final int id) {
		Logging.info(this, "Begin countDocument(String, String, int)");
		
		Query<Long> query = getCurrentSession().getNamedQuery(Document.COUNT_DOCUMENTS_BY_TYPE_AND_FILENAME);
		query.setParameter(BaseQueryParameters.TYPE, type);
		query.setParameter(BaseQueryParameters.FILE_NAME, fileName);
		query.setParameter(BaseQueryParameters.ID, id);
		ArrayList<Long> arrayList = (ArrayList<Long>) query.list();
		
		long count = arrayList.iterator().next().longValue();
		Logging.info(this, "End countDocument(String, String, int)");
		return count;
	}
	
}
