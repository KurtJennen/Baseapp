package be.luxuryoverdosis.framework.data.factory;

import be.luxuryoverdosis.framework.data.to.JobLog;
import be.luxuryoverdosis.framework.data.to.JobTO;

public class JobLogFactory {
	private JobLogFactory() {
	}
	
	public static JobLog produceJobLog(JobLog jobLog, final JobTO jobTO, final String input, final String output) {
		if(jobLog == null) {
			jobLog = new JobLog();
		}
		jobLog.setInput(input);
		jobLog.setOutput(output);
		jobLog.setFileData(output.getBytes());
		jobLog.setJob(jobTO);
		
		return jobLog;
	}
}
