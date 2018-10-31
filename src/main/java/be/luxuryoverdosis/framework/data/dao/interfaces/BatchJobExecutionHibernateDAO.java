package be.luxuryoverdosis.framework.data.dao.interfaces;

import be.luxuryoverdosis.framework.data.to.BatchJobExecution;

public interface BatchJobExecutionHibernateDAO {
	public BatchJobExecution read(long jobInstanceId);
}
