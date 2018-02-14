package be.luxuryoverdosis.framework.business.service.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.to.BatchJobInstanceTO;

public interface BatchJobInstanceService {
	public BatchJobInstanceTO read(long id);
	
	public ArrayList<BatchJobInstanceTO> list(String jobName);
}
