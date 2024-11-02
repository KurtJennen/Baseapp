package be.luxuryoverdosis.framework.data.dao.interfaces;

import be.luxuryoverdosis.framework.data.to.BatchJobHeaderSelected;

public interface BatchJobHeaderSelectedHibernateDAO {
	BatchJobHeaderSelected createOrUpdate(BatchJobHeaderSelected batchJobHeaderSelected);
	BatchJobHeaderSelected read(int id);
	void delete(int id);
}
