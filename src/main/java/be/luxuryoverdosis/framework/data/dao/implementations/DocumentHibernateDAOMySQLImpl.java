package be.luxuryoverdosis.framework.data.dao.implementations;

import java.sql.Blob;
import java.util.ArrayList;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import be.luxuryoverdosis.framework.data.dao.AbstractHibernateDaoSupport;
import be.luxuryoverdosis.framework.data.dao.interfaces.DocumentHibernateDAO;
import be.luxuryoverdosis.framework.data.to.DocumentTO;
import be.luxuryoverdosis.framework.logging.Logging;

@Repository
public class DocumentHibernateDAOMySQLImpl extends AbstractHibernateDaoSupport implements DocumentHibernateDAO {
	public DocumentTO createOrUpdate(final DocumentTO documentTO) {
		Logging.info(this, "Begin createDocument");
		try {
			Session session = getSessionFactory().openSession();
			Blob blob = session.getLobHelper().createBlob(documentTO.getFileData());
			//Blob blob = Hibernate.createBlob(documentTO.getFileData());
			documentTO.setFile(blob);
		} catch (Exception e) {
			Logging.error(this, "Blob error" + e.getMessage());
		}
		getHibernateTemplate().saveOrUpdate(documentTO);
		Logging.info(this, "End createDocument");
		return documentTO;
	}

	public DocumentTO read(final int id) {
		Logging.info(this, "Begin readDocument");
		DocumentTO documentTO = (DocumentTO) getHibernateTemplate().load(DocumentTO.class, id);
		Logging.info(this, "End readDocument");
		return documentTO;
	}

	public void delete(final int id) {
		Logging.info(this, "Begin deleteDocument");
		getHibernateTemplate().delete((DocumentTO) getHibernateTemplate().load(DocumentTO.class, id));
		Logging.info(this, "End deleteDocument");		
	}
	
	private static final String listHql = "SELECT new be.luxuryoverdosis.framework.data.to.DocumentTO(" +
		"d.id, " +
		"d.type, " +
		"d.fileName, " +
		"d.fileSize, " +
		"d.contentType " +
		") " +
		"from DocumentTO d ";

	@SuppressWarnings("unchecked")
	public ArrayList<DocumentTO> list() {
		Logging.info(this, "Begin listDocument");
		ArrayList<DocumentTO> arrayList = (ArrayList<DocumentTO>) getHibernateTemplate().find(listHql);
		Logging.info(this, "End listDocument");
		return arrayList;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<DocumentTO> list(final String type) {
		Logging.info(this, "Begin listDocument");
		ArrayList<DocumentTO> arrayList = (ArrayList<DocumentTO>) getHibernateTemplate().find("from DocumentTO d where d.type = ? order by d.fileName asc", new Object[]{type});
		Logging.info(this, "End listDocument");
		return arrayList;
	}
	
	@SuppressWarnings("unchecked")
	public long count(final String type, final String fileName, final int id) {
		Logging.info(this, "Begin countDocument(String, String, int)");
		ArrayList<Long> arrayList = (ArrayList<Long>) getHibernateTemplate().find("select count(*) from DocumentTO d where d.type = ? and d.fileName = ? and d.id <> ?", new Object[]{type, fileName, id});
		long count = arrayList.iterator().next().longValue();
		Logging.info(this, "End countDocument(String, String, int)");
		return count;
	}
	
}
