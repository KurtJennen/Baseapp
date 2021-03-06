package be.luxuryoverdosis.framework.data.dao.implementations;

import java.sql.Blob;
import java.util.ArrayList;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import be.luxuryoverdosis.framework.data.dao.AbstractHibernateDaoSupport;
import be.luxuryoverdosis.framework.data.dao.BaseQueryParameters;
import be.luxuryoverdosis.framework.data.dao.interfaces.JobLogHibernateDAO;
import be.luxuryoverdosis.framework.data.to.JobLog;
import be.luxuryoverdosis.framework.logging.Logging;

@Repository
public class JobLogHibernateDAOMySQLImpl extends AbstractHibernateDaoSupport implements JobLogHibernateDAO {
	public JobLog createOrUpdate(final JobLog jobLog) {
		Logging.info(this, "Begin createJobLog");
		try {
			if(jobLog.getFileData() != null) {
				Blob blob = getCurrentSession().getLobHelper().createBlob(jobLog.getFileData());
				jobLog.setFile(blob);
			}
		} catch (Exception e) {
			Logging.error(this, "Blob error" + e.getMessage());
		}
		getCurrentSession().saveOrUpdate(jobLog);
		Logging.info(this, "End createJobLog");
		return jobLog;
	}

	public JobLog read(final int id) {
		Logging.info(this, "Begin readJobLog");
		JobLog jobLog = (JobLog) getCurrentSession().load(JobLog.class, id);
		Logging.info(this, "End readJobLog");
		return jobLog;
	}

	public void delete(final int id) {
		Logging.info(this, "Begin deleteJobLog");
		getCurrentSession().delete(this.read(id));
		Logging.info(this, "End deleteJobLog");		
	}
	
	@SuppressWarnings("unchecked")
	public void deleteForJob(final int jobId) {
		Logging.info(this, "Begin deleteForJobJobLog");
		
		Query<Long> query = getCurrentSession().getNamedQuery(JobLog.DELETE_JOBLOGS_BY_JOB);
		query.setParameter(BaseQueryParameters.JOB_ID, jobId);
		query.executeUpdate();
		
		Logging.info(this, "End deleteForJobJobLog");		
	}	

	@SuppressWarnings("unchecked")
	public ArrayList<JobLog> list(final int jobId) {
		Logging.info(this, "Begin listJobLog");
		
		Query<JobLog> query = getCurrentSession().getNamedQuery(JobLog.SELECT_JOBLOGS_BY_JOB);
		query.setParameter(BaseQueryParameters.JOB_ID, jobId);
		ArrayList<JobLog> arrayList = (ArrayList<JobLog>) query.list();
		
		Logging.info(this, "End listJobLog");
		return arrayList;
	}
}
