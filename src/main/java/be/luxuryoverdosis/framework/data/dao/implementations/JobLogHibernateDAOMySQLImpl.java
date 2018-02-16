package be.luxuryoverdosis.framework.data.dao.implementations;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import be.luxuryoverdosis.framework.data.dao.AbstractHibernateDaoSupport;
import be.luxuryoverdosis.framework.data.dao.interfaces.JobLogHibernateDAO;
import be.luxuryoverdosis.framework.data.to.JobLog;
import be.luxuryoverdosis.framework.logging.Logging;

@Repository
public class JobLogHibernateDAOMySQLImpl extends AbstractHibernateDaoSupport implements JobLogHibernateDAO {
	public JobLog createOrUpdate(final JobLog jobLog) {
		Logging.info(this, "Begin createJobLog");
		try {
			Session session = getSessionFactory().openSession();
			Blob blob = session.getLobHelper().createBlob(jobLog.getFileData());
			//Blob blob = Hibernate.createBlob(jobLog.getFileData());
			jobLog.setFile(blob);
		} catch (Exception e) {
			Logging.error(this, "Blob error" + e.getMessage());
		}
		getHibernateTemplate().saveOrUpdate(jobLog);
		Logging.info(this, "End createJobLog");
		return jobLog;
	}

	public JobLog read(final int id) {
		Logging.info(this, "Begin readJobLog");
		JobLog jobLog = (JobLog) getHibernateTemplate().load(JobLog.class, id);
		Logging.info(this, "End readJobLog");
		return jobLog;
	}

	public void delete(final int id) {
		Logging.info(this, "Begin deleteJobLog");
		getHibernateTemplate().delete((JobLog) getHibernateTemplate().load(JobLog.class, id));
		Logging.info(this, "End deleteJobLog");		
	}
	
	public void deleteForJob(final int jobId) {
		Logging.info(this, "Begin deleteForJobJobLog");
		getHibernateTemplate().execute(new HibernateCallback<Integer>() {

			public Integer doInHibernate(Session session) throws HibernateException, SQLException {
				org.hibernate.Query updateQuery = session.createQuery("delete JobLog jl where jl.job.id = ?");
				updateQuery.setParameter(0, jobId);
				return updateQuery.executeUpdate();
			}
			
		});
		Logging.info(this, "End deleteForJobJobLog");		
	}	

	@SuppressWarnings("unchecked")
	public ArrayList<JobLog> list(final int jobId) {
		Logging.info(this, "Begin listJobLog");
		ArrayList<JobLog> arrayList = (ArrayList<JobLog>) getHibernateTemplate().find("from JobLog jl where jl.job.id = ?", new Object[]{jobId});
		Logging.info(this, "End listJobLog");
		return arrayList;
	}
}
