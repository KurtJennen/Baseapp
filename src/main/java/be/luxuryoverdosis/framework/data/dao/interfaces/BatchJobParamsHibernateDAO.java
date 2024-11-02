package be.luxuryoverdosis.framework.data.dao.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.to.BatchJobParams;

public interface BatchJobParamsHibernateDAO {
	BatchJobParams read(long jobInstanceId, String keyName);
	
	ArrayList<BatchJobParams> list(long jobInstanceId);
}
