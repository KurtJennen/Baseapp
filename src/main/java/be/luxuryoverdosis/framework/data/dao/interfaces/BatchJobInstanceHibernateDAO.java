package be.luxuryoverdosis.framework.data.dao.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.dto.BatchJobInstanceDTO;
import be.luxuryoverdosis.framework.data.to.BatchJobInstance;

public interface BatchJobInstanceHibernateDAO {
	BatchJobInstance read(long id);
	void delete(long id);
	
	ArrayList<BatchJobInstanceDTO> list(String... jobNames);
	ArrayList<BatchJobInstanceDTO> list(String keyName, long longValue, String... jobNames);
}
