package be.luxuryoverdosis.framework.data.dao.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.to.BatchStepExecutionTO;

public interface BatchStepExecutionHibernateDAO {
	public ArrayList<BatchStepExecutionTO> list(long jobInstanceId);
}
