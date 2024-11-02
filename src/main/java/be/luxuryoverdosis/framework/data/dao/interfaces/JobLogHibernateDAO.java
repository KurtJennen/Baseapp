package be.luxuryoverdosis.framework.data.dao.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.to.JobLog;

public interface JobLogHibernateDAO {
	JobLog createOrUpdate(JobLog jobLog);
	JobLog read(int id);
	void delete(int id);
	void deleteForJob(int jobId);
	
	ArrayList<JobLog> list(int jobId);
}
