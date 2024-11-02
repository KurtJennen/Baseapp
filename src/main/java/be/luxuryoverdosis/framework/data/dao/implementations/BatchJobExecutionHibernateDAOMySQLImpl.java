package be.luxuryoverdosis.framework.data.dao.implementations;

import java.util.ArrayList;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import be.luxuryoverdosis.framework.data.dao.AbstractHibernateDaoSupport;
import be.luxuryoverdosis.framework.data.dao.BaseQueryParameters;
import be.luxuryoverdosis.framework.data.dao.interfaces.BatchJobExecutionHibernateDAO;
import be.luxuryoverdosis.framework.data.to.BatchJobExecution;
import be.luxuryoverdosis.framework.logging.Logging;

@Repository
public class BatchJobExecutionHibernateDAOMySQLImpl extends AbstractHibernateDaoSupport implements BatchJobExecutionHibernateDAO {
	@SuppressWarnings("unchecked")
	public BatchJobExecution read(final long jobInstanceId) {
		Logging.info(this, "Begin readBatchJobExecution");
		
		Query<BatchJobExecution> query = getCurrentSession().getNamedQuery(BatchJobExecution.SELECT_BATCH_JOB_EXECUTIONS_BY_JOB_INSTANCE);
		query.setParameter(BaseQueryParameters.JOB_INSTANCE_ID, jobInstanceId);
		ArrayList<BatchJobExecution> arrayList = (ArrayList<BatchJobExecution>) query.list();
		
		BatchJobExecution batchJobExecution = null;
		if (!arrayList.isEmpty()) {
			batchJobExecution = (BatchJobExecution) arrayList.iterator().next();
		}
		
		Logging.info(this, "End readBatchJobExecution");
		return batchJobExecution;
	}
	
	@SuppressWarnings("unchecked")
	public void delete(final long jobInstanceId) {
		Logging.info(this, "Begin deleteBatchJobExecution");
		
		Query<Long> query = getCurrentSession().getNamedQuery(BatchJobExecution.DELETE_BATCH_JOB_EXECUTIONS_BY_JOB_INSTANCE);
		query.setParameter(BaseQueryParameters.JOB_INSTANCE_ID, jobInstanceId);
		query.executeUpdate();
		
		Logging.info(this, "End deleteBatchJobExecution");
	}
	
	@SuppressWarnings("unchecked")
	public void deleteContexts(final long jobInstanceId) {
		Logging.info(this, "Begin deleteBatchJobExecutionContexts");
		
		Query<Long> query = getCurrentSession().getNamedNativeQuery(BatchJobExecution.DELETE_BATCH_JOB_EXECUTION_CONTEXTS_BY_JOB_INSTANCE);
		query.setParameter(BaseQueryParameters.JOB_INSTANCE_ID, jobInstanceId);
		query.executeUpdate();
		
		Logging.info(this, "End deleteBatchJobExecutionContexts");
	}
	
	@SuppressWarnings("unchecked")
	public void deleteParams(final long jobInstanceId) {
		Logging.info(this, "Begin deleteBatchJobExecutionParams");
		
		Query<Long> query = getCurrentSession().getNamedNativeQuery(BatchJobExecution.DELETE_BATCH_JOB_EXECUTION_PARAMS_BY_JOB_INSTANCE);
		query.setParameter(BaseQueryParameters.JOB_INSTANCE_ID, jobInstanceId);
		query.executeUpdate();
		
		Logging.info(this, "End deleteBatchJobExecutionParams");
	}
}
