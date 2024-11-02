package be.luxuryoverdosis.framework.data.dao.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.to.BatchJobExecutionParams;

public interface BatchJobExecutionParamsHibernateDAO {
	BatchJobExecutionParams read(long jobExecutionId, String keyName);
	
	ArrayList<BatchJobExecutionParams> list(long jobExecutionId);
}
