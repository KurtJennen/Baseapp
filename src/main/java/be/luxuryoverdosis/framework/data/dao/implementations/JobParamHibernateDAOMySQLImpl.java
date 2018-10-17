package be.luxuryoverdosis.framework.data.dao.implementations;

import java.util.ArrayList;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import be.luxuryoverdosis.framework.data.dao.AbstractHibernateDaoSupport;
import be.luxuryoverdosis.framework.data.dao.interfaces.JobParamHibernateDAO;
import be.luxuryoverdosis.framework.data.to.JobParam;
import be.luxuryoverdosis.framework.logging.Logging;

@Repository
public class JobParamHibernateDAOMySQLImpl extends AbstractHibernateDaoSupport implements JobParamHibernateDAO {
	private static final String JOB_ID = "jobId";
	
	public JobParam createOrUpdate(final JobParam jobParam) {
		Logging.info(this, "Begin createJobParam");
		getCurrentSession().saveOrUpdate(jobParam);
		Logging.info(this, "End createJobParam");
		return jobParam;
	}

	public JobParam read(final int id) {
		Logging.info(this, "Begin readJobParam");
		JobParam jobParam = (JobParam) getCurrentSession().load(JobParam.class, id);
		Logging.info(this, "End readJobParam");
		return jobParam;
	}

	public void delete(final int id) {
		Logging.info(this, "Begin deleteJobParam");
		getCurrentSession().delete(this.read(id));
		Logging.info(this, "End deleteJobParam");		
	}
	
	public void deleteForJob(final int jobId) {
		Logging.info(this, "Begin deleteForJobJobParam");
		
		Query query = getCurrentSession().getNamedQuery("deleteJobParamsByJob");
		query.setInteger(JOB_ID, jobId);
		query.executeUpdate();
		
		Logging.info(this, "End deleteForJobJobParam");		
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<JobParam> list(final int jobId) {
		Logging.info(this, "Begin listJobParam");
		
		Query query = getCurrentSession().getNamedQuery("getAllJobParamsByJob");
		query.setInteger(JOB_ID, jobId);
		ArrayList<JobParam> arrayList = (ArrayList<JobParam>) query.list();
		
		Logging.info(this, "End listJobParam");
		return arrayList;
	}
}
