package be.luxuryoverdosis.framework.data.dao.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.to.BatchJobParamsTO;

public interface BatchJobParamsHibernateDAO {
	public BatchJobParamsTO getJobParam(final long jobInstanceId, final String keyName);
	
	public ArrayList<BatchJobParamsTO> list(long jobInstanceId);
}
