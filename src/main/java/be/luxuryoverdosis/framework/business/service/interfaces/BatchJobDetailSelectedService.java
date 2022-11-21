package be.luxuryoverdosis.framework.business.service.interfaces;

import java.util.ArrayList;

public interface BatchJobDetailSelectedService {
	public int createOrUpdate(String jobName, int[] selectedIds);
	public void delete(int jobHeaderSelectedId);
	
	public ArrayList<Integer> list(int batchJobHeaderSelectedId);
}
