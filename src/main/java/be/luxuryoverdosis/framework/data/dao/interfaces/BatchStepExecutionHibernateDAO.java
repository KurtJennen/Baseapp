package be.luxuryoverdosis.framework.data.dao.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.to.BatchStepExecution;

public interface BatchStepExecutionHibernateDAO {
	ArrayList<BatchStepExecution> list(long jobInstanceId);
	void delete(long jobInstanceId);
	void deleteContexts(long jobInstanceId);
}
