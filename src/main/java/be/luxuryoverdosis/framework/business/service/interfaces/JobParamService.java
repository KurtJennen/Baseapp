package be.luxuryoverdosis.framework.business.service.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.to.JobParam;

public interface JobParamService {
	public JobParam createOrUpdate(JobParam jobParam);
	public JobParam read(int id);
	public void delete(int id);
	public void deleteForJob(int jobId);
	
	public ArrayList<JobParam> list(int jobId);
}
