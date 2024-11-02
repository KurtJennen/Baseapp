package be.luxuryoverdosis.framework.data.translater;

import org.apache.commons.lang.StringUtils;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;

import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;

public final class BatchJobTranslater {
	private BatchJobTranslater() {
	}
	
	public static String getBatchJobStatusTranslated(final String status) {
		String value = StringUtils.EMPTY;
		
		if (BatchStatus.ABANDONED.toString().equals(status)) {
			value = BaseSpringServiceLocator.getMessage("batchjobexecution.status.abandoned");
		}
	    if (BatchStatus.COMPLETED.toString().equals(status)) {
	    	value = BaseSpringServiceLocator.getMessage("batchjobexecution.status.completed");
		}
	    if (BatchStatus.FAILED.toString().equals(status)) {
	    	value = BaseSpringServiceLocator.getMessage("batchjobexecution.status.failed");
	    }
	    if (BatchStatus.STARTED.toString().equals(status)) {
	    	value = BaseSpringServiceLocator.getMessage("batchjobexecution.status.started");
	    }
	    if (BatchStatus.STARTING.toString().equals(status)) {
	    	value = BaseSpringServiceLocator.getMessage("batchjobexecution.status.starting");
	    }
	    if (BatchStatus.STOPPED.toString().equals(status)) {
	    	value = BaseSpringServiceLocator.getMessage("batchjobexecution.status.stopped");
	    }
	    if (BatchStatus.STOPPING.toString().equals(status)) {
	    	value = BaseSpringServiceLocator.getMessage("batchjobexecution.status.stopping");
	    }
	    if (BatchStatus.UNKNOWN.toString().equals(status)) {
	    	value = BaseSpringServiceLocator.getMessage("batchjobexecution.status.unknown");
	    }
	
	    return value;
	}
	
	public static String getBatchJobExitCodeTranslated(final String exitCode) {
		String value = StringUtils.EMPTY;
		
		if (ExitStatus.COMPLETED.getExitCode().equals(exitCode)) {
			value = BaseSpringServiceLocator.getMessage("batchjobexecution.exit.code.completed");
		}
		if (ExitStatus.EXECUTING.getExitCode().equals(exitCode)) {
			value = BaseSpringServiceLocator.getMessage("batchjobexecution.exit.code.executing");
		}
		if (ExitStatus.FAILED.getExitCode().equals(exitCode)) {
			value = BaseSpringServiceLocator.getMessage("batchjobexecution.exit.code.failed");
		}
		if (ExitStatus.NOOP.getExitCode().equals(exitCode)) {
			value = BaseSpringServiceLocator.getMessage("batchjobexecution.exit.code.noop");
		}
		if (ExitStatus.STOPPED.getExitCode().equals(exitCode)) {
			value = BaseSpringServiceLocator.getMessage("batchjobexecution.exit.code.stopped");
		}
		if (ExitStatus.UNKNOWN.getExitCode().equals(exitCode)) {
			value = BaseSpringServiceLocator.getMessage("batchjobexecution.exit.code.unknown");
		}
		
		return value;
	}
}
