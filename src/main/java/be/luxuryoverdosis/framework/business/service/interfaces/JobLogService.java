package be.luxuryoverdosis.framework.business.service.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.to.JobLogTO;

public interface JobLogService {
	public JobLogTO createOrUpdate(JobLogTO jobLogTO);
	public JobLogTO read(int id);
	public void delete(int id);
	public void deleteForJob(int jobId);
	public JobLogTO downloadFile(int jobLogId);
	
	public ArrayList<JobLogTO> list(int jobId);
	public ArrayList<JobLogTO> listForBatch(int jobId);
}
