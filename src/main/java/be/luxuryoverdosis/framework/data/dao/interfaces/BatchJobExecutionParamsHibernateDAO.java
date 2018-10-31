package be.luxuryoverdosis.framework.data.dao.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.to.BatchJobExecutionParams;

public interface BatchJobExecutionParamsHibernateDAO {
	public BatchJobExecutionParams read(final long jobExecutionId, final String keyName);
	
	public ArrayList<BatchJobExecutionParams> list(long jobExecutionId);
}
