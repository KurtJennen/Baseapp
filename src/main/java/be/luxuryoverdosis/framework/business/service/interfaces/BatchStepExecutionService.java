package be.luxuryoverdosis.framework.business.service.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.to.BatchStepExecution;

public interface BatchStepExecutionService {
	ArrayList<BatchStepExecution> list(long jobInstanceId);
}
