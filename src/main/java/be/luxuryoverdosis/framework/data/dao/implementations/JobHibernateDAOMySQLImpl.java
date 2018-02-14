package be.luxuryoverdosis.framework.data.dao.implementations;

import java.sql.Blob;
import java.util.ArrayList;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import be.luxuryoverdosis.framework.data.dao.AbstractHibernateDaoSupport;
import be.luxuryoverdosis.framework.data.dao.interfaces.JobHibernateDAO;
import be.luxuryoverdosis.framework.data.to.JobTO;
import be.luxuryoverdosis.framework.logging.Logging;

@Repository
public class JobHibernateDAOMySQLImpl extends AbstractHibernateDaoSupport implements JobHibernateDAO {
	public JobTO createOrUpdate(final JobTO jobTO) {
		Logging.info(this, "Begin createJob");
		try {
			Session session = getSessionFactory().openSession();
			Blob blob = session.getLobHelper().createBlob(jobTO.getFileData());
			//Blob blob = Hibernate.createBlob(jobTO.getFileData());
			jobTO.setFile(blob);
		} catch (Exception e) {
			Logging.error(this, "Blob error" + e.getMessage());
		}
		getHibernateTemplate().saveOrUpdate(jobTO);
		Logging.info(this, "End createJob");
		return jobTO;
	}

	public JobTO read(final int id) {
		Logging.info(this, "Begin readJob");
		JobTO jobTO = (JobTO) getHibernateTemplate().load(JobTO.class, id);
		Logging.info(this, "End readJob");
		return jobTO;
	}

	public void delete(final int id) {
		Logging.info(this, "Begin deleteJob");
		getHibernateTemplate().delete((JobTO) getHibernateTemplate().load(JobTO.class, id));
		Logging.info(this, "End deleteJob");		
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<JobTO> list(final String name) {
		Logging.info(this, "Begin listJob");
		ArrayList<JobTO> arrayList = (ArrayList<JobTO>) getHibernateTemplate().find("from JobTO j where j.name = ? order by j.ended asc", new Object[]{name});
		Logging.info(this, "End listJob");
		return arrayList;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<JobTO> listStarted(final String name) {
		Logging.info(this, "Begin listJob");
		ArrayList<JobTO> arrayList = null;
		arrayList = (ArrayList<JobTO>) getHibernateTemplate().find("from JobTO j where j.name = ? and j.started is not null", new Object[]{name});	 
		Logging.info(this, "End listJob");
		return arrayList;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<JobTO> listNotStarted(final String name) {
		Logging.info(this, "Begin listJob");
		ArrayList<JobTO> arrayList = null;
		arrayList = (ArrayList<JobTO>) getHibernateTemplate().find("from JobTO j where j.name = ? and j.started is null", new Object[]{name});
		Logging.info(this, "End listJob");
		return arrayList;
	}
}
