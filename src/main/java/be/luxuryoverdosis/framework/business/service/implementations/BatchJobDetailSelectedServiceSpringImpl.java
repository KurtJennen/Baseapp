package be.luxuryoverdosis.framework.business.service.implementations;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.luxuryoverdosis.framework.business.service.interfaces.BatchJobDetailSelectedService;
import be.luxuryoverdosis.framework.data.dao.interfaces.BatchJobDetailSelectedHibernateDAO;
import be.luxuryoverdosis.framework.data.dao.interfaces.BatchJobHeaderSelectedHibernateDAO;
import be.luxuryoverdosis.framework.data.to.BatchJobDetailSelected;
import be.luxuryoverdosis.framework.data.to.BatchJobHeaderSelected;
import be.luxuryoverdosis.framework.logging.Logging;

@Service
public class BatchJobDetailSelectedServiceSpringImpl implements BatchJobDetailSelectedService {
	@Resource
	private BatchJobDetailSelectedHibernateDAO batchJobDetailSelectedHibernateDAO;
	@Resource
	private BatchJobHeaderSelectedHibernateDAO batchJobHeaderSelectedHibernateDAO;
	
	@Transactional
	public int createOrUpdate(final String jobName, final int[] selectedIds) {
		Logging.info(this, "Begin createBatchJobDetailSelected");
		
		BatchJobHeaderSelected batchJobHeaderSelected = new BatchJobHeaderSelected();
		batchJobHeaderSelected.setJobName(jobName);
		
		batchJobHeaderSelectedHibernateDAO.createOrUpdate(batchJobHeaderSelected);
		
		for (int i = 0; i < selectedIds.length; i++) {
			BatchJobDetailSelected batchJobDetailSelected = new BatchJobDetailSelected();
			batchJobDetailSelected.setBatchJobHeaderSelected(batchJobHeaderSelected);
			batchJobDetailSelected.setSelectedId(selectedIds[i]);
			
			batchJobDetailSelectedHibernateDAO.createOrUpdate(batchJobDetailSelected);
		}
		
		Logging.info(this, "End createBatchJobDetailSelected");
		
		return batchJobHeaderSelected.getId();
	}
	
	@Transactional
	public void delete(final int jobHeaderSelectedId) {
		Logging.info(this, "Begin deleteBatchJobDetailSelected");
		batchJobDetailSelectedHibernateDAO.delete(jobHeaderSelectedId);
		batchJobHeaderSelectedHibernateDAO.delete(jobHeaderSelectedId);
		Logging.info(this, "End deleteBatchJobDetailSelected");
	}
	
	@Transactional(readOnly=true)
	public ArrayList<Integer> list(final int batchJobHeaderSelectedId) {
		Logging.info(this, "Begin readBatchJobDetailSelected");
		ArrayList<Integer> arrayList = null;
		arrayList = batchJobDetailSelectedHibernateDAO.list(batchJobHeaderSelectedId);
		Logging.info(this, "End readBatchJobDetailSelected");
		return arrayList;
	}

}
