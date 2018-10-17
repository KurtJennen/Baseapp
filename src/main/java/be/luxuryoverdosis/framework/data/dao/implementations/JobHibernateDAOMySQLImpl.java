package be.luxuryoverdosis.framework.data.dao.implementations;

import java.sql.Blob;
import java.util.ArrayList;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import be.luxuryoverdosis.framework.data.dao.AbstractHibernateDaoSupport;
import be.luxuryoverdosis.framework.data.dao.interfaces.JobHibernateDAO;
import be.luxuryoverdosis.framework.data.to.Job;
import be.luxuryoverdosis.framework.logging.Logging;

@Repository
public class JobHibernateDAOMySQLImpl extends AbstractHibernateDaoSupport implements JobHibernateDAO {
	private static final String NAME = "name";
	
	public Job createOrUpdate(final Job job) {
		Logging.info(this, "Begin createJob");
		try {
			if(job.getFileData() != null) {
				Blob blob = getCurrentSession().getLobHelper().createBlob(job.getFileData());
				job.setFile(blob);
			}
		} catch (Exception e) {
			Logging.error(this, "Blob error" + e.getMessage());
		}
		getCurrentSession().saveOrUpdate(job);
		Logging.info(this, "End createJob");
		return job;
	}

	public Job read(final int id) {
		Logging.info(this, "Begin readJob");
		Job job = (Job) getCurrentSession().load(Job.class, id);
		Logging.info(this, "End readJob");
		return job;
	}

	public void delete(final int id) {
		Logging.info(this, "Begin deleteJob");
		getCurrentSession().delete(this.read(id));
		Logging.info(this, "End deleteJob");		
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Job> list(final String name) {
		Logging.info(this, "Begin listJob");
		
		Query query = getCurrentSession().getNamedQuery("getAllJobsByName");
		query.setString(NAME, name);
		ArrayList<Job> arrayList = (ArrayList<Job>) query.list();
		
		Logging.info(this, "End listJob");
		return arrayList;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Job> listStarted(final String name) {
		Logging.info(this, "Begin listJob");
		
		Query query = getCurrentSession().getNamedQuery("getAllStartedJobsByName");
		query.setString(NAME, name);
		ArrayList<Job> arrayList = (ArrayList<Job>) query.list();
		
		Logging.info(this, "End listJob");
		return arrayList;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Job> listNotStarted(final String name) {
		Logging.info(this, "Begin listJob");
		
		Query query = getCurrentSession().getNamedQuery("getAllNotStartedJobsByName");
		query.setString(NAME, name);
		ArrayList<Job> arrayList = (ArrayList<Job>) query.list();
		
		Logging.info(this, "End listJob");
		return arrayList;
	}
}
