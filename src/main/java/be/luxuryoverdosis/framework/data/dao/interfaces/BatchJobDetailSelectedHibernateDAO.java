package be.luxuryoverdosis.framework.data.dao.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.to.BatchJobDetailSelected;

public interface BatchJobDetailSelectedHibernateDAO {
	public BatchJobDetailSelected createOrUpdate(BatchJobDetailSelected batchJobDetailSelected);
	public void delete(int jobHeaderSelectedId);
	
	public ArrayList<Integer> list(int batchJobHeaderSelectedId);
}
