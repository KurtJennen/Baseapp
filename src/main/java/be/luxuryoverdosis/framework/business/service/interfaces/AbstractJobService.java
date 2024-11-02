package be.luxuryoverdosis.framework.business.service.interfaces;

import be.luxuryoverdosis.framework.data.to.Job;

public interface AbstractJobService {
	void processReadJob();
	void addJobLog(Job job, String record, String output);
}
