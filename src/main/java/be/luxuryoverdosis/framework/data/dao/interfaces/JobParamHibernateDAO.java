package be.luxuryoverdosis.framework.data.dao.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.to.JobParam;

public interface JobParamHibernateDAO {
	JobParam createOrUpdate(JobParam jobParam);
	JobParam read(int id);
	void delete(int id);
	void deleteForJob(int jobId);
	
	ArrayList<JobParam> list(int jobId);
}
