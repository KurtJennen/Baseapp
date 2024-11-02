package be.luxuryoverdosis.framework.data.dao.interfaces;

import be.luxuryoverdosis.framework.data.to.BatchJobExecution;

public interface BatchJobExecutionHibernateDAO {
	BatchJobExecution read(long jobInstanceId);
	void delete(long jobInstanceId);
	void deleteContexts(long jobInstanceId);
	void deleteParams(long jobInstanceId);
}
