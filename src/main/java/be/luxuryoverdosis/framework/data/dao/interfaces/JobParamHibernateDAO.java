package be.luxuryoverdosis.framework.data.dao.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.to.JobParamTO;

public interface JobParamHibernateDAO {
	public JobParamTO createOrUpdate(JobParamTO jobParamTO);
	public JobParamTO read(int id);
	public void delete(int id);
	public void deleteForJob(int jobId);
	
	public ArrayList<JobParamTO> list(int jobId);
}
