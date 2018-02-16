package be.luxuryoverdosis.framework.business.service.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.to.JobLog;

public interface JobLogService {
	public JobLog createOrUpdate(JobLog jobLog);
	public JobLog read(int id);
	public void delete(int id);
	public void deleteForJob(int jobId);
	public JobLog downloadFile(int jobLogId);
	
	public ArrayList<JobLog> list(int jobId);
	public ArrayList<JobLog> listForBatch(int jobId);
}
