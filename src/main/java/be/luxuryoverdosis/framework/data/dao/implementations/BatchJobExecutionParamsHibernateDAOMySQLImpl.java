package be.luxuryoverdosis.framework.data.dao.implementations;

import java.util.ArrayList;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import be.luxuryoverdosis.framework.data.dao.AbstractHibernateDaoSupport;
import be.luxuryoverdosis.framework.data.dao.interfaces.BatchJobExecutionParamsHibernateDAO;
import be.luxuryoverdosis.framework.data.to.BatchJobExecutionParams;
import be.luxuryoverdosis.framework.logging.Logging;

@Repository
public class BatchJobExecutionParamsHibernateDAOMySQLImpl extends AbstractHibernateDaoSupport implements BatchJobExecutionParamsHibernateDAO {
	private static final String JOB_EXECUTION_ID = "jobExecutionId";
	private static final String KEY_NAME = "keyName";
	
	@SuppressWarnings("unchecked")
	public BatchJobExecutionParams read(final long jobExecutionId, final String keyName) {
		Logging.info(this, "Begin readBatchJobExecutionParams");
		
		Query<BatchJobExecutionParams> query = getCurrentSession().getNamedQuery("getAllJobExecutionParamsByJobExecutionAndKeyName");
		query.setParameter(JOB_EXECUTION_ID, jobExecutionId);
		query.setParameter(KEY_NAME, keyName);
		ArrayList<BatchJobExecutionParams> arrayList = (ArrayList<BatchJobExecutionParams>) query.list();
		
		BatchJobExecutionParams batchJobExecutionParams = null;
		if(!arrayList.isEmpty()) {
			batchJobExecutionParams = (BatchJobExecutionParams)arrayList.iterator().next();
		}
		
		Logging.info(this, "End readBatchJobExecutionParams");
		return batchJobExecutionParams;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<BatchJobExecutionParams> list(final long jobExecutionId) {
		Logging.info(this, "Begin listBatchJobExecutionParams");
		
		Query<BatchJobExecutionParams> query = getCurrentSession().getNamedQuery("getAllJobExecutionParamsByJobExecution");
		query.setParameter(JOB_EXECUTION_ID, jobExecutionId);
		ArrayList<BatchJobExecutionParams> arrayList = (ArrayList<BatchJobExecutionParams>) query.list();
		
		Logging.info(this, "End listBatchJobExecutionParams");
		return arrayList;
	}
}