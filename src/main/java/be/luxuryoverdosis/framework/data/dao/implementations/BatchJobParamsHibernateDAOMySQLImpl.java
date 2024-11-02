package be.luxuryoverdosis.framework.data.dao.implementations;

import java.util.ArrayList;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import be.luxuryoverdosis.framework.data.dao.AbstractHibernateDaoSupport;
import be.luxuryoverdosis.framework.data.dao.BaseQueryParameters;
import be.luxuryoverdosis.framework.data.dao.interfaces.BatchJobParamsHibernateDAO;
import be.luxuryoverdosis.framework.data.to.BatchJobParams;
import be.luxuryoverdosis.framework.logging.Logging;

@Repository
public class BatchJobParamsHibernateDAOMySQLImpl extends AbstractHibernateDaoSupport implements BatchJobParamsHibernateDAO {
	@SuppressWarnings("unchecked")
	public BatchJobParams read(final long jobInstanceId, final String keyName) {
		Logging.info(this, "Begin readBatchJobParams");
		
		Query<BatchJobParams> query = getCurrentSession().getNamedQuery(BatchJobParams.SELECT_BATCH_JOB_PARAMS_BY_JOB_INSTANCE_AND_KEY_NAME);
		query.setParameter(BaseQueryParameters.JOB_INSTANCE_ID, jobInstanceId);
		query.setParameter(BaseQueryParameters.KEY_NAME, keyName);
		ArrayList<BatchJobParams> arrayList = (ArrayList<BatchJobParams>) query.list();
		
		BatchJobParams batchJobParams = null;
		if (!arrayList.isEmpty()) {
			batchJobParams = (BatchJobParams) arrayList.iterator().next();
		}
		Logging.info(this, "End readBatchJobParams");
		return batchJobParams;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<BatchJobParams> list(final long jobInstanceId) {
		Logging.info(this, "Begin listBatchJobParams");
		
		Query<BatchJobParams> query = getCurrentSession().getNamedQuery(BatchJobParams.SELECT_BATCH_JOB_PARAMS_BY_JOB_INSTANCE);
		query.setParameter(BaseQueryParameters.JOB_INSTANCE_ID, jobInstanceId);
		ArrayList<BatchJobParams> arrayList = (ArrayList<BatchJobParams>) query.list();
		
		Logging.info(this, "End listBatchJobParams");
		return arrayList;
	}
}
