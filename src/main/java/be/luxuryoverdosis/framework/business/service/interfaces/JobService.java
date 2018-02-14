package be.luxuryoverdosis.framework.business.service.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.to.JobTO;

public interface JobService {
	public JobTO createOrUpdate(JobTO jobTO);
	public JobTO read(int id);
	public void delete(int id);
	public JobTO downloadFile(int jobInstanceId);
	
	public ArrayList<JobTO> list(String name);
	public ArrayList<JobTO> list(String name, boolean started);
	
	public void startJobs(ArrayList<JobTO> jobs);
	public void endJobs(ArrayList<JobTO> jobs);
}
