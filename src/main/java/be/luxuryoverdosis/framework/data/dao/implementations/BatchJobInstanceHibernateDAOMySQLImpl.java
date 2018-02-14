package be.luxuryoverdosis.framework.data.dao.implementations;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import be.luxuryoverdosis.framework.data.dao.AbstractHibernateDaoSupport;
import be.luxuryoverdosis.framework.data.dao.interfaces.BatchJobInstanceHibernateDAO;
import be.luxuryoverdosis.framework.data.to.BatchJobInstanceTO;
import be.luxuryoverdosis.framework.logging.Logging;

@Repository
public class BatchJobInstanceHibernateDAOMySQLImpl extends AbstractHibernateDaoSupport implements BatchJobInstanceHibernateDAO {
	
	public BatchJobInstanceTO read(final long id) {
		Logging.info(this, "Begin readBatchJobInstance");
		BatchJobInstanceTO batchJobInstanceTO = (BatchJobInstanceTO) getHibernateTemplate().load(BatchJobInstanceTO.class, id);
		Logging.info(this, "End readBatchJobInstance");
		return batchJobInstanceTO;
	}
	
	private static final String readJobExecutionHql = "SELECT bji " +
			"from BatchJobExecutionTO bje " +
			"inner join bje.batchJobInstance bji " +
			"where bje.id = ? " +
			"order by bje.createTime desc";
	
	@SuppressWarnings("unchecked")
	public BatchJobInstanceTO readJobExecution(final long jobExecutionId) {
		Logging.info(this, "Begin readBatchJobInstance");
		ArrayList<BatchJobInstanceTO> arrayList = (ArrayList<BatchJobInstanceTO>) getHibernateTemplate().find(readJobExecutionHql, new Object[]{jobExecutionId});
		BatchJobInstanceTO batchJobInstanceTO = null;
		if(!arrayList.isEmpty()) {
			batchJobInstanceTO = (BatchJobInstanceTO)arrayList.iterator().next();
		}
		Logging.info(this, "End readBatchJobInstance");
		return batchJobInstanceTO;
	}
	
	
	private static final String listHql = "SELECT new be.luxuryoverdosis.framework.data.to.BatchJobInstanceTO(" +
		"bji.id, " +
		"bji.jobName, " +
		"bje.id, " +
		"bje.version, " +
		"bje.createTime, " +
		"bje.startTime, " +
		"bje.endTime, " +
		"bje.status, " +
		"bje.exitCode, " +
		"bje.exitMessage, " +
		"bje.lastUpdated " +
		") " +
		"from BatchJobExecutionTO bje " +
		"inner join bje.batchJobInstance bji " +
		"where bji.jobName = ? " +
		"order by bje.createTime desc";
	
	@SuppressWarnings("unchecked")
	public ArrayList<BatchJobInstanceTO> list(final String jobName) {
		Logging.info(this, "Begin listBatchJobInstance");
		ArrayList<BatchJobInstanceTO> arrayList = (ArrayList<BatchJobInstanceTO>) getHibernateTemplate().find(listHql, new Object[]{jobName});
		Logging.info(this, "End listBatchJobInstance");
		return arrayList;
	}
}
