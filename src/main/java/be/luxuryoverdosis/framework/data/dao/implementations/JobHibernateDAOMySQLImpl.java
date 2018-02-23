package be.luxuryoverdosis.framework.data.dao.implementations;

import java.sql.Blob;
import java.util.ArrayList;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import be.luxuryoverdosis.framework.data.dao.AbstractHibernateDaoSupport;
import be.luxuryoverdosis.framework.data.dao.interfaces.JobHibernateDAO;
import be.luxuryoverdosis.framework.data.to.Job;
import be.luxuryoverdosis.framework.logging.Logging;

@Repository
public class JobHibernateDAOMySQLImpl extends AbstractHibernateDaoSupport implements JobHibernateDAO {
	public Job createOrUpdate(final Job job) {
		Logging.info(this, "Begin createJob");
		try {
			Session session = getSessionFactory().openSession();
			Blob blob = session.getLobHelper().createBlob(job.getFileData());
			//Blob blob = Hibernate.createBlob(job.getFileData());
			job.setFile(blob);
		} catch (Exception e) {
			Logging.error(this, "Blob error" + e.getMessage());
		}
		getHibernateTemplate().saveOrUpdate(job);
		Logging.info(this, "End createJob");
		return job;
	}

	public Job read(final int id) {
		Logging.info(this, "Begin readJob");
		Job job = (Job) getHibernateTemplate().load(Job.class, id);
		Logging.info(this, "End readJob");
		return job;
	}

	public void delete(final int id) {
		Logging.info(this, "Begin deleteJob");
		getHibernateTemplate().delete((Job) getHibernateTemplate().load(Job.class, id));
		Logging.info(this, "End deleteJob");		
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Job> list(final String name) {
		Logging.info(this, "Begin listJob");
		ArrayList<Job> arrayList = (ArrayList<Job>) getHibernateTemplate().find("from Job j where j.name = ? order by j.ended asc", new Object[]{name});
		Logging.info(this, "End listJob");
		return arrayList;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Job> listStarted(final String name) {
		Logging.info(this, "Begin listJob");
		ArrayList<Job> arrayList = null;
		arrayList = (ArrayList<Job>) getHibernateTemplate().find("from Job j where j.name = ? and j.started is not null", new Object[]{name});	 
		Logging.info(this, "End listJob");
		return arrayList;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Job> listNotStarted(final String name) {
		Logging.info(this, "Begin listJob");
		ArrayList<Job> arrayList = null;
		arrayList = (ArrayList<Job>) getHibernateTemplate().find("from Job j where j.name = ? and j.started is null", new Object[]{name});
		Logging.info(this, "End listJob");
		return arrayList;
	}
}
