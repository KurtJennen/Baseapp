package be.luxuryoverdosis.framework.data.dao.implementations;

import java.util.ArrayList;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import be.luxuryoverdosis.framework.data.dao.AbstractHibernateDaoSupport;
import be.luxuryoverdosis.framework.data.dao.interfaces.BatchJobParamsHibernateDAO;
import be.luxuryoverdosis.framework.data.to.BatchJobParams;
import be.luxuryoverdosis.framework.logging.Logging;

@Repository
public class BatchJobParamsHibernateDAOMySQLImpl extends AbstractHibernateDaoSupport implements BatchJobParamsHibernateDAO {
	private static final String JOB_INSTANCE_ID = "jobInstanceId";
	private static final String KEY_NAME = "keyName";
	
	@SuppressWarnings("unchecked")
	public BatchJobParams getJobParam(final long jobInstanceId, final String keyName) {
		Logging.info(this, "Begin getJobParam");
		
		Query query = getCurrentSession().getNamedQuery("getAllJobParamsByJobInstanceAndKeyName");
		query.setLong(JOB_INSTANCE_ID, jobInstanceId);
		query.setString(KEY_NAME, keyName);
		ArrayList<BatchJobParams> arrayList = (ArrayList<BatchJobParams>) query.list();
		
		BatchJobParams batchJobParams = null;
		if(!arrayList.isEmpty()) {
			batchJobParams = (BatchJobParams)arrayList.iterator().next();
		}
		Logging.info(this, "End getJobParam");
		return batchJobParams;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<BatchJobParams> list(final long jobInstanceId) {
		Logging.info(this, "Begin listBatchJobParams");
		
		Query query = getCurrentSession().getNamedQuery("getAllJobParamsByJobInstance");
		query.setLong(JOB_INSTANCE_ID, jobInstanceId);
		ArrayList<BatchJobParams> arrayList = (ArrayList<BatchJobParams>) query.list();
		
		Logging.info(this, "End listBatchJobParams");
		return arrayList;
	}
}