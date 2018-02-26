package be.luxuryoverdosis.framework.business.service.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.to.BatchJobInstance;

public interface BatchJobInstanceService {
	public BatchJobInstance read(long id);
	
	public ArrayList<BatchJobInstance> list(String jobName);
}
