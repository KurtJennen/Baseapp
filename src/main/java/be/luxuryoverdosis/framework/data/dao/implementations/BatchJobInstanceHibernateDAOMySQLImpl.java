package be.luxuryoverdosis.framework.data.dao.implementations;

import java.util.ArrayList;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import be.luxuryoverdosis.framework.data.dao.AbstractHibernateDaoSupport;
import be.luxuryoverdosis.framework.data.dao.interfaces.BatchJobInstanceHibernateDAO;
import be.luxuryoverdosis.framework.data.to.BatchJobInstance;
import be.luxuryoverdosis.framework.logging.Logging;

@Repository
public class BatchJobInstanceHibernateDAOMySQLImpl extends AbstractHibernateDaoSupport implements BatchJobInstanceHibernateDAO {
	private static final String JOB_EXECUTION_ID = "jobExecutionId";
	private static final String JOB_NAME = "jobName";
	
	public BatchJobInstance read(final long id) {
		Logging.info(this, "Begin readBatchJobInstance");
		BatchJobInstance batchJobInstance = (BatchJobInstance) getCurrentSession().load(BatchJobInstance.class, id);
		Logging.info(this, "End readBatchJobInstance");
		return batchJobInstance;
	}
	
	@SuppressWarnings("unchecked")
	public BatchJobInstance readJobExecution(final long jobExecutionId) {
		Logging.info(this, "Begin readBatchJobInstance");
		
		Query query = getCurrentSession().getNamedQuery("getAllBatchJobInstanceByJobExecution");
		query.setLong(JOB_EXECUTION_ID, jobExecutionId);
		ArrayList<BatchJobInstance> arrayList = (ArrayList<BatchJobInstance>) query.list();
		
		BatchJobInstance batchJobInstance = null;
		if(!arrayList.isEmpty()) {
			batchJobInstance = (BatchJobInstance)arrayList.iterator().next();
		}
		Logging.info(this, "End readBatchJobInstance");
		return batchJobInstance;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<BatchJobInstance> list(final String jobName) {
		Logging.info(this, "Begin listBatchJobInstance");
		
		Query query = getCurrentSession().getNamedQuery("getAllBatchJobInstanceByJobName");
		query.setString(JOB_NAME, jobName);
		ArrayList<BatchJobInstance> arrayList = (ArrayList<BatchJobInstance>) query.list();
		
		Logging.info(this, "End listBatchJobInstance");
		return arrayList;
	}
}
