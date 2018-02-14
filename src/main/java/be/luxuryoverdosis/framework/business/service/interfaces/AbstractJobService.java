package be.luxuryoverdosis.framework.business.service.interfaces;

import be.luxuryoverdosis.framework.data.to.JobTO;

public interface AbstractJobService {
	public void processReadJob();
	public void addJobLog(JobTO jobTO, String record, String output);
}
