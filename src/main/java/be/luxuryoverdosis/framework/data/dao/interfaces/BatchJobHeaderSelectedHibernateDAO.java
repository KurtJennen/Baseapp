package be.luxuryoverdosis.framework.data.dao.interfaces;

import be.luxuryoverdosis.framework.data.to.BatchJobHeaderSelected;

public interface BatchJobHeaderSelectedHibernateDAO {
	public BatchJobHeaderSelected createOrUpdate(BatchJobHeaderSelected batchJobHeaderSelected);
	public BatchJobHeaderSelected read(int id);
	public void delete(int id);
}
