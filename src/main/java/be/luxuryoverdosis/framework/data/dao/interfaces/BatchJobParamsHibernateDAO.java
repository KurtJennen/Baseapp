package be.luxuryoverdosis.framework.data.dao.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.to.BatchJobParams;

public interface BatchJobParamsHibernateDAO {
	public BatchJobParams read(final long jobInstanceId, final String keyName);
	
	public ArrayList<BatchJobParams> list(long jobInstanceId);
}
