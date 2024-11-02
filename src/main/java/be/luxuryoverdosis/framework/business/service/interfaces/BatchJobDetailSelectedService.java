package be.luxuryoverdosis.framework.business.service.interfaces;

import java.util.ArrayList;

public interface BatchJobDetailSelectedService {
	int createOrUpdate(String jobName, int[] selectedIds);
	void delete(int jobHeaderSelectedId);
	
	ArrayList<Integer> list(int batchJobHeaderSelectedId);
}
