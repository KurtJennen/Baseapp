package be.luxuryoverdosis.framework.business.service.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.to.JobLog;

public interface JobLogService {
	JobLog createOrUpdate(JobLog jobLog);
	JobLog read(int id);
	void delete(int id);
	void deleteForJob(int jobId);
	JobLog downloadFile(int jobLogId);
	
	ArrayList<JobLog> list(int jobId);
	ArrayList<JobLog> listForBatch(int jobId);
}
