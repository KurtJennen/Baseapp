package be.luxuryoverdosis.framework.data.factory;

import be.luxuryoverdosis.framework.data.to.JobLogTO;
import be.luxuryoverdosis.framework.data.to.JobTO;

public class JobLogFactory {
	private JobLogFactory() {
	}
	
	public static JobLogTO produceJobLog(JobLogTO jobLogTO, final JobTO jobTO, final String input, final String output) {
		if(jobLogTO == null) {
			jobLogTO = new JobLogTO();
		}
		jobLogTO.setInput(input);
		jobLogTO.setOutput(output);
		jobLogTO.setFileData(output.getBytes());
		jobLogTO.setJob(jobTO);
		
		return jobLogTO;
	}
}
