package be.luxuryoverdosis.framework.data.translater;

import org.apache.commons.lang.StringUtils;
import org.springframework.batch.core.ExitStatus;

import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;

public class BatchJobTranslater {
	public static String getTranslatedBatchJobStatus(String status) {
	    
	    if(ExitStatus.COMPLETED.getExitCode().equals(status)) {
	    	status = BaseSpringServiceLocator.getMessage("batchjobexecution.status.completed");
		}
	    if(ExitStatus.EXECUTING.getExitCode().equals(status)) {
	    	status = BaseSpringServiceLocator.getMessage("batchjobexecution.status.executing");
	    }
	    if(ExitStatus.FAILED.getExitCode().equals(status)) {
	    	status = BaseSpringServiceLocator.getMessage("batchjobexecution.status.failed");
	    }
	    if(ExitStatus.NOOP.getExitCode().equals(status)) {
	    	status = BaseSpringServiceLocator.getMessage("batchjobexecution.status.noop");
	    }
	    if(ExitStatus.STOPPED.getExitCode().equals(status)) {
	    	status = BaseSpringServiceLocator.getMessage("batchjobexecution.status.stopped");
	    }
	    if(ExitStatus.UNKNOWN.getExitCode().equals(status)) {
	    	status = BaseSpringServiceLocator.getMessage("batchjobexecution.status.unknown");
	    }
	
	
	    return StringUtils.EMPTY;
	}
}
