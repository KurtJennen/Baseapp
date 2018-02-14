package be.luxuryoverdosis.framework.data.dao.implementations;

import java.sql.SQLException;
import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import be.luxuryoverdosis.framework.data.dao.AbstractHibernateDaoSupport;
import be.luxuryoverdosis.framework.data.dao.interfaces.JobParamHibernateDAO;
import be.luxuryoverdosis.framework.data.to.JobParamTO;
import be.luxuryoverdosis.framework.logging.Logging;

@Repository
public class JobParamHibernateDAOMySQLImpl extends AbstractHibernateDaoSupport implements JobParamHibernateDAO {
	public JobParamTO createOrUpdate(final JobParamTO jobParamTO) {
		Logging.info(this, "Begin createJobParam");
		getHibernateTemplate().saveOrUpdate(jobParamTO);
		Logging.info(this, "End createJobParam");
		return jobParamTO;
	}

	public JobParamTO read(final int id) {
		Logging.info(this, "Begin readJobParam");
		JobParamTO jobParamTO = (JobParamTO) getHibernateTemplate().load(JobParamTO.class, id);
		Logging.info(this, "End readJobParam");
		return jobParamTO;
	}

	public void delete(final int id) {
		Logging.info(this, "Begin deleteJobParam");
		getHibernateTemplate().delete((JobParamTO) getHibernateTemplate().load(JobParamTO.class, id));
		Logging.info(this, "End deleteJobParam");		
	}
	
	public void deleteForJob(final int jobId) {
		Logging.info(this, "Begin deleteForJobJobParam");
		getHibernateTemplate().execute(new HibernateCallback<Integer>() {

			public Integer doInHibernate(Session session) throws HibernateException, SQLException {
				Query updateQuery = session.createQuery("delete JobParamTO jp where jp.job.id = ?");
				updateQuery.setParameter(0, jobId);
				return updateQuery.executeUpdate();
			}
			
		});
		Logging.info(this, "End deleteForJobJobParam");		
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<JobParamTO> list(final int jobId) {
		Logging.info(this, "Begin listJobParam");
		ArrayList<JobParamTO> arrayList = (ArrayList<JobParamTO>) getHibernateTemplate().find("from JobParamTO jp where jp.job.id = ?", new Object[]{jobId});
		Logging.info(this, "End listJobParam");
		return arrayList;
	}
}
