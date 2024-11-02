package be.luxuryoverdosis.framework.business.service.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.to.JobParam;

public interface JobParamService {
	JobParam createOrUpdate(JobParam jobParam);
	JobParam read(int id);
	void delete(int id);
	void deleteForJob(int jobId);
	
	ArrayList<JobParam> list(int jobId);
}
