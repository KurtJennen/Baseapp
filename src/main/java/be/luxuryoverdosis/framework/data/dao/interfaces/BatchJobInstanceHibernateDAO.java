package be.luxuryoverdosis.framework.data.dao.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.to.BatchJobInstance;

public interface BatchJobInstanceHibernateDAO {
	public BatchJobInstance read(final long id);
	public BatchJobInstance readJobExecution(final long jobExecutionId);
	
	public ArrayList<BatchJobInstance> list(String jobName);
}
