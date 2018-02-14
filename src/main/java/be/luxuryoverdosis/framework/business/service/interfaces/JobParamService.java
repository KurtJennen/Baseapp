package be.luxuryoverdosis.framework.business.service.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.to.JobParamTO;

public interface JobParamService {
	public JobParamTO createOrUpdate(JobParamTO jobParamTO);
	public JobParamTO read(int id);
	public void delete(int id);
	public void deleteForJob(int jobId);
	
	public ArrayList<JobParamTO> list(int jobId);
}
