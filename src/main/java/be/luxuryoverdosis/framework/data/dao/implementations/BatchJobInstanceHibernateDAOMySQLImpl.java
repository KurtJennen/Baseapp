package be.luxuryoverdosis.framework.data.dao.implementations;

import java.util.ArrayList;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import be.luxuryoverdosis.framework.data.dao.AbstractHibernateDaoSupport;
import be.luxuryoverdosis.framework.data.dao.BaseQueryParameters;
import be.luxuryoverdosis.framework.data.dao.interfaces.BatchJobInstanceHibernateDAO;
import be.luxuryoverdosis.framework.data.dto.BatchJobInstanceDTO;
import be.luxuryoverdosis.framework.data.to.BatchJobInstance;
import be.luxuryoverdosis.framework.logging.Logging;

@Repository
public class BatchJobInstanceHibernateDAOMySQLImpl extends AbstractHibernateDaoSupport implements BatchJobInstanceHibernateDAO {
	public BatchJobInstance read(final long id) {
		Logging.info(this, "Begin readBatchJobInstance");
		BatchJobInstance batchJobInstance = (BatchJobInstance) getCurrentSession().load(BatchJobInstance.class, id);
		Logging.info(this, "End readBatchJobInstance");
		return batchJobInstance;
	}
	
	@SuppressWarnings("unchecked")
	public void delete(final long id) {
		Logging.info(this, "Begin deleteBatchJobInstance");
		
		Query<Long> query = getCurrentSession().getNamedQuery(BatchJobInstance.DELETE_BATCH_JOB_INSTANCES_BY_ID);
		query.setParameter(BaseQueryParameters.ID, id);
		query.executeUpdate();
		
		Logging.info(this, "End deleteBatchJobInstance");
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<BatchJobInstanceDTO> list(final String... jobNames) {
		Logging.info(this, "Begin listBatchJobInstance");
		
		Query<BatchJobInstanceDTO> query = getCurrentSession().getNamedQuery(BatchJobInstance.SELECT_BATCH_JOB_INSTANCES_BY_JOB_NAME);
		query.setParameterList(BaseQueryParameters.JOB_NAMES, jobNames);
		ArrayList<BatchJobInstanceDTO> arrayList = (ArrayList<BatchJobInstanceDTO>) query.list();
		
		Logging.info(this, "End listBatchJobInstance");
		return arrayList;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<BatchJobInstanceDTO> list(final String keyName, final long longValue, final String... jobNames) {
		Logging.info(this, "Begin listBatchJobInstance");
		
		Query<BatchJobInstanceDTO> query = getCurrentSession().getNamedQuery(BatchJobInstance.SELECT_BATCH_JOB_INSTANCES_BY_JOB_NAME_AND_JOB_PARAMETER);
		query.setParameter(BaseQueryParameters.KEY_NAME, keyName);
		query.setParameter(BaseQueryParameters.LONG_VALUE, longValue);
		query.setParameterList(BaseQueryParameters.JOB_NAMES, jobNames);
		ArrayList<BatchJobInstanceDTO> arrayList = (ArrayList<BatchJobInstanceDTO>) query.list();
		
		Logging.info(this, "End listBatchJobInstance");
		return arrayList;
	}
}
