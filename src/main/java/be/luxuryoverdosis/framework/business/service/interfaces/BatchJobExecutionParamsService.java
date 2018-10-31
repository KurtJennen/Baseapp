package be.luxuryoverdosis.framework.business.service.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.to.BatchJobExecutionParams;

public interface BatchJobExecutionParamsService {
	public ArrayList<BatchJobExecutionParams> list(long jobInstanceId);
}
