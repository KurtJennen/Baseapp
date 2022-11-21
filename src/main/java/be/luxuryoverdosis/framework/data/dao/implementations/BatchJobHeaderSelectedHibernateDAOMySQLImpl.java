package be.luxuryoverdosis.framework.data.dao.implementations;

import org.springframework.stereotype.Repository;

import be.luxuryoverdosis.framework.data.dao.AbstractHibernateDaoSupport;
import be.luxuryoverdosis.framework.data.dao.interfaces.BatchJobHeaderSelectedHibernateDAO;
import be.luxuryoverdosis.framework.data.to.BatchJobHeaderSelected;
import be.luxuryoverdosis.framework.logging.Logging;

@Repository
public class BatchJobHeaderSelectedHibernateDAOMySQLImpl extends AbstractHibernateDaoSupport implements BatchJobHeaderSelectedHibernateDAO {
	public BatchJobHeaderSelected createOrUpdate(final BatchJobHeaderSelected batchJobHeaderSelected) {
		Logging.info(this, "Begin createBatchJobHeaderSelected");
		getCurrentSession().saveOrUpdate(batchJobHeaderSelected);
		Logging.info(this, "End createBatchJobHeaderSelected");
		return batchJobHeaderSelected;
	}

	public BatchJobHeaderSelected read(final int id) {
		Logging.info(this, "Begin readBatchJobHeaderSelected");
		BatchJobHeaderSelected batchJobHeaderSelected = (BatchJobHeaderSelected) getCurrentSession().load(BatchJobHeaderSelected.class, id);
		Logging.info(this, "End readBatchJobHeaderSelected");
		return batchJobHeaderSelected;
	}
	
	public void delete(final int id) {
		Logging.info(this, "Begin deleteBatchJobHeaderSelected");
		getCurrentSession().delete(this.read(id));
		Logging.info(this, "End deleteBatchJobHeaderSelected");		
	}
}