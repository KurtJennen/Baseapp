package be.luxuryoverdosis.framework.data.dao.implementations;

import java.util.ArrayList;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import be.luxuryoverdosis.framework.data.dao.AbstractHibernateDaoSupport;
import be.luxuryoverdosis.framework.data.dao.BaseQueryParameters;
import be.luxuryoverdosis.framework.data.dao.interfaces.BatchJobInstanceHibernateDAO;
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
	public ArrayList<BatchJobInstance> list(final String jobName) {
		Logging.info(this, "Begin listBatchJobInstance");
		
		Query<BatchJobInstance> query = getCurrentSession().getNamedQuery(BatchJobInstance.SELECT_BATCH_JOB_INSTANCES_BY_JOB_NAME);
		query.setParameter(BaseQueryParameters.JOB_NAME, jobName);
		ArrayList<BatchJobInstance> arrayList = (ArrayList<BatchJobInstance>) query.list();
		
		Logging.info(this, "End listBatchJobInstance");
		return arrayList;
	}
}
