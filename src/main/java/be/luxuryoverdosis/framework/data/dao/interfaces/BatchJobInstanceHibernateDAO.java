package be.luxuryoverdosis.framework.data.dao.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.dto.BatchJobInstanceDTO;
import be.luxuryoverdosis.framework.data.to.BatchJobInstance;

public interface BatchJobInstanceHibernateDAO {
	public BatchJobInstance read(final long id);
	public void delete(final long id);
	
	public ArrayList<BatchJobInstanceDTO> list(String jobName);
}
