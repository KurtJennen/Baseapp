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
import be.luxuryoverdosis.framework.data.to.JobLogTO;
import be.luxuryoverdosis.framework.logging.Logging;

@Repository
public class JobLogHibernateDAOMySQLImpl extends AbstractHibernateDaoSupport implements JobLogHibernateDAO {
	public JobLogTO createOrUpdate(final JobLogTO jobLogTO) {
		Logging.info(this, "Begin createJobLog");
		try {
			Session session = getSessionFactory().openSession();
			Blob blob = session.getLobHelper().createBlob(jobLogTO.getFileData());
			//Blob blob = Hibernate.createBlob(jobLogTO.getFileData());
			jobLogTO.setFile(blob);
		} catch (Exception e) {
			Logging.error(this, "Blob error" + e.getMessage());
		}
		getHibernateTemplate().saveOrUpdate(jobLogTO);
		Logging.info(this, "End createJobLog");
		return jobLogTO;
	}

	public JobLogTO read(final int id) {
		Logging.info(this, "Begin readJobLog");
		JobLogTO jobLogTO = (JobLogTO) getHibernateTemplate().load(JobLogTO.class, id);
		Logging.info(this, "End readJobLog");
		return jobLogTO;
	}

	public void delete(final int id) {
		Logging.info(this, "Begin deleteJobLog");
		getHibernateTemplate().delete((JobLogTO) getHibernateTemplate().load(JobLogTO.class, id));
		Logging.info(this, "End deleteJobLog");		
	}
	
	public void deleteForJob(final int jobId) {
		Logging.info(this, "Begin deleteForJobJobLog");
		getHibernateTemplate().execute(new HibernateCallback<Integer>() {

			public Integer doInHibernate(Session session) throws HibernateException, SQLException {
				org.hibernate.Query updateQuery = session.createQuery("delete JobLogTO jl where jl.job.id = ?");
				updateQuery.setParameter(0, jobId);
				return updateQuery.executeUpdate();
			}
			
		});
		Logging.info(this, "End deleteForJobJobLog");		
	}	

	@SuppressWarnings("unchecked")
	public ArrayList<JobLogTO> list(final int jobId) {
		Logging.info(this, "Begin listJobLog");
		ArrayList<JobLogTO> arrayList = (ArrayList<JobLogTO>) getHibernateTemplate().find("from JobLogTO jl where jl.job.id = ?", new Object[]{jobId});
		Logging.info(this, "End listJobLog");
		return arrayList;
	}
}
