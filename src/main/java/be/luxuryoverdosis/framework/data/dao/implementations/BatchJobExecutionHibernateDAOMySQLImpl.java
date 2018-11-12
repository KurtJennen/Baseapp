package be.luxuryoverdosis.framework.data.dao.implementations;

import java.util.ArrayList;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import be.luxuryoverdosis.framework.data.dao.AbstractHibernateDaoSupport;
import be.luxuryoverdosis.framework.data.dao.interfaces.BatchJobExecutionHibernateDAO;
import be.luxuryoverdosis.framework.data.to.BatchJobExecution;
import be.luxuryoverdosis.framework.logging.Logging;

@Repository
public class BatchJobExecutionHibernateDAOMySQLImpl extends AbstractHibernateDaoSupport implements BatchJobExecutionHibernateDAO {
	private static final String JOB_INSTANCE_ID = "jobInstanceId";
	
	@SuppressWarnings("unchecked")
	public BatchJobExecution read(final long jobInstanceId) {
		Logging.info(this, "Begin readBatchJobExecution");
		
		Query<BatchJobExecution> query = getCurrentSession().getNamedQuery("getAllBatchJobExecutionByJobInstance");
		query.setParameter(JOB_INSTANCE_ID, jobInstanceId);
		ArrayList<BatchJobExecution> arrayList = (ArrayList<BatchJobExecution>) query.list();
		
		BatchJobExecution batchJobExecution = null;
		if(!arrayList.isEmpty()) {
			batchJobExecution = (BatchJobExecution)arrayList.iterator().next();
		}
		
		Logging.info(this, "End readBatchJobExecution");
		return batchJobExecution;
	}
}