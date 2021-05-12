package be.luxuryoverdosis.framework.data.dao.interfaces;

import be.luxuryoverdosis.framework.data.to.BatchJobExecution;

public interface BatchJobExecutionHibernateDAO {
	public BatchJobExecution read(long jobInstanceId);
	public void delete(long jobInstanceId);
	public void deleteContexts(long jobInstanceId);
	public void deleteParams(long jobInstanceId);
}
