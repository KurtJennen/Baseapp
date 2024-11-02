package be.luxuryoverdosis.framework.data.dao.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.to.BatchJobDetailSelected;

public interface BatchJobDetailSelectedHibernateDAO {
	BatchJobDetailSelected createOrUpdate(BatchJobDetailSelected batchJobDetailSelected);
	void delete(int jobHeaderSelectedId);
	
	ArrayList<Integer> list(int batchJobHeaderSelectedId);
}
