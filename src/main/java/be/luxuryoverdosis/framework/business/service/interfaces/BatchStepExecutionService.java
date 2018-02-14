package be.luxuryoverdosis.framework.business.service.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.to.BatchStepExecutionTO;

public interface BatchStepExecutionService {
	public ArrayList<BatchStepExecutionTO> list(long jobInstanceId);
}
