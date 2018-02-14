package be.luxuryoverdosis.framework.data.dao.implementations;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import be.luxuryoverdosis.framework.data.dao.AbstractHibernateDaoSupport;
import be.luxuryoverdosis.framework.data.dao.interfaces.BatchJobParamsHibernateDAO;
import be.luxuryoverdosis.framework.data.to.BatchJobParamsTO;
import be.luxuryoverdosis.framework.logging.Logging;

@Repository
public class BatchJobParamsHibernateDAOMySQLImpl extends AbstractHibernateDaoSupport implements BatchJobParamsHibernateDAO {
	@SuppressWarnings("unchecked")
	public BatchJobParamsTO getJobParam(final long jobInstanceId, final String keyName) {
		Logging.info(this, "Begin getJobParam");
		ArrayList<BatchJobParamsTO> arrayList = (ArrayList<BatchJobParamsTO>) getHibernateTemplate().find("from BatchJobParamsTO bjp where bjp.id = ? and bjp.keyName = ?", new Object[]{jobInstanceId, keyName});
		BatchJobParamsTO batchJobParamsTO = null;
		if(!arrayList.isEmpty()) {
			batchJobParamsTO = (BatchJobParamsTO)arrayList.iterator().next();
		}
		Logging.info(this, "End getJobParam");
		return batchJobParamsTO;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<BatchJobParamsTO> list(final long jobInstanceId) {
		Logging.info(this, "Begin listBatchJobParams");
		ArrayList<BatchJobParamsTO> arrayList = (ArrayList<BatchJobParamsTO>) getHibernateTemplate().find("from BatchJobParamsTO bjp where bjp.id = ?", new Object[]{jobInstanceId});
		Logging.info(this, "End listBatchJobParams");
		return arrayList;
	}
}