package be.luxuryoverdosis.framework.data.dao.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.to.JobLog;

public interface JobLogHibernateDAO {
	public JobLog createOrUpdate(JobLog jobLog);
	public JobLog read(int id);
	public void delete(int id);
	public void deleteForJob(int jobId);
	
	public ArrayList<JobLog> list(int jobId);
}
