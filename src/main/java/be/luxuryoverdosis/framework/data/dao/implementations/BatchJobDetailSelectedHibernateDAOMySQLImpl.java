package be.luxuryoverdosis.framework.data.dao.implementations;

import java.util.ArrayList;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import be.luxuryoverdosis.framework.data.dao.AbstractHibernateDaoSupport;
import be.luxuryoverdosis.framework.data.dao.BaseQueryParameters;
import be.luxuryoverdosis.framework.data.dao.interfaces.BatchJobDetailSelectedHibernateDAO;
import be.luxuryoverdosis.framework.data.to.BatchJobDetailSelected;
import be.luxuryoverdosis.framework.data.to.BatchJobParams;
import be.luxuryoverdosis.framework.logging.Logging;

@Repository
public class BatchJobDetailSelectedHibernateDAOMySQLImpl extends AbstractHibernateDaoSupport implements BatchJobDetailSelectedHibernateDAO {
	public BatchJobDetailSelected createOrUpdate(final BatchJobDetailSelected batchJobDetailSelected) {
		Logging.info(this, "Begin createBatchJobDetailSelected");
		getCurrentSession().saveOrUpdate(batchJobDetailSelected);
		Logging.info(this, "End createBatchJobDetailSelected");
		return batchJobDetailSelected;
	}
	
	@SuppressWarnings("unchecked")
	public void delete(final int jobHeaderSelectedId) {
		Logging.info(this, "Begin deleteBatchJobDetailSelected");
		
		Query<BatchJobParams> query = getCurrentSession().getNamedQuery(BatchJobDetailSelected.DELETE_BATCHJOBDETAILSELECTED_BY_BATCHJOBHEADERSELECTED);
		query.setParameter(BaseQueryParameters.JOB_HEADER_SELECTED_ID, jobHeaderSelectedId);
		query.executeUpdate();
		
		Logging.info(this, "End deleteBatchJobDetailSelected");
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Integer> list(final int jobHeaderSelectedId) {
		Logging.info(this, "Begin listBatchJobDetailSelected");
		
		Query<Integer> query = getCurrentSession().getNamedQuery(BatchJobDetailSelected.SELECT_BATCHJOBDETAILSELECTED_BY_BATCHJOBHEADERSELECTED);
		query.setParameter(BaseQueryParameters.JOB_HEADER_SELECTED_ID, jobHeaderSelectedId);
		ArrayList<Integer> arrayList = (ArrayList<Integer>) query.list();
		
		Logging.info(this, "End listBatchJobDetailSelected");
		return arrayList;
	}
}
