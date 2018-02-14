package be.luxuryoverdosis.framework.data.dao.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.to.BatchJobInstanceTO;

public interface BatchJobInstanceHibernateDAO {
	public BatchJobInstanceTO read(final long id);
	public BatchJobInstanceTO readJobExecution(final long jobExecutionId);
	
	public ArrayList<BatchJobInstanceTO> list(String jobName);
}
