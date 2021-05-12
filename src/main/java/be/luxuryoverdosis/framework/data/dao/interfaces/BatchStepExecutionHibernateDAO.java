package be.luxuryoverdosis.framework.data.dao.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.to.BatchStepExecution;

public interface BatchStepExecutionHibernateDAO {
	public ArrayList<BatchStepExecution> list(long jobInstanceId);
	public void delete(long jobInstanceId);
	public void deleteContexts(long jobInstanceId);
}
