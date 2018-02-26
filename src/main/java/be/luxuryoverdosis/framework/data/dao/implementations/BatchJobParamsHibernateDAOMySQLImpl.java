package be.luxuryoverdosis.framework.data.dao.implementations;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import be.luxuryoverdosis.framework.data.dao.AbstractHibernateDaoSupport;
import be.luxuryoverdosis.framework.data.dao.interfaces.BatchJobParamsHibernateDAO;
import be.luxuryoverdosis.framework.data.to.BatchJobParams;
import be.luxuryoverdosis.framework.logging.Logging;

@Repository
public class BatchJobParamsHibernateDAOMySQLImpl extends AbstractHibernateDaoSupport implements BatchJobParamsHibernateDAO {
	@SuppressWarnings("unchecked")
	public BatchJobParams getJobParam(final long jobInstanceId, final String keyName) {
		Logging.info(this, "Begin getJobParam");
		ArrayList<BatchJobParams> arrayList = (ArrayList<BatchJobParams>) getHibernateTemplate().find("from BatchJobParams bjp where bjp.id = ? and bjp.keyName = ?", new Object[]{jobInstanceId, keyName});
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
		ArrayList<BatchJobParams> arrayList = (ArrayList<BatchJobParams>) getHibernateTemplate().find("from BatchJobParams bjp where bjp.id = ?", new Object[]{jobInstanceId});
		Logging.info(this, "End listBatchJobParams");
		return arrayList;
	}
}