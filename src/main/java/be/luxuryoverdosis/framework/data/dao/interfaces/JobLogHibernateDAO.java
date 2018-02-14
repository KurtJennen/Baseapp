package be.luxuryoverdosis.framework.data.dao.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.to.JobLogTO;

public interface JobLogHibernateDAO {
	public JobLogTO createOrUpdate(JobLogTO jobLogTO);
	public JobLogTO read(int id);
	public void delete(int id);
	public void deleteForJob(int jobId);
	
	public ArrayList<JobLogTO> list(int jobId);
}
