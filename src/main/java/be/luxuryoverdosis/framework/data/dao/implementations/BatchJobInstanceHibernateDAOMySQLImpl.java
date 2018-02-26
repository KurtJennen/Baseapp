package be.luxuryoverdosis.framework.data.dao.implementations;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import be.luxuryoverdosis.framework.data.dao.AbstractHibernateDaoSupport;
import be.luxuryoverdosis.framework.data.dao.interfaces.BatchJobInstanceHibernateDAO;
import be.luxuryoverdosis.framework.data.to.BatchJobInstance;
import be.luxuryoverdosis.framework.logging.Logging;

@Repository
public class BatchJobInstanceHibernateDAOMySQLImpl extends AbstractHibernateDaoSupport implements BatchJobInstanceHibernateDAO {
	
	public BatchJobInstance read(final long id) {
		Logging.info(this, "Begin readBatchJobInstance");
		BatchJobInstance batchJobInstance = (BatchJobInstance) getHibernateTemplate().load(BatchJobInstance.class, id);
		Logging.info(this, "End readBatchJobInstance");
		return batchJobInstance;
	}
	
	private static final String readJobExecutionHql = "SELECT bji " +
			"from BatchJobExecution bje " +
			"inner join bje.batchJobInstance bji " +
			"where bje.id = ? " +
			"order by bje.createTime desc";
	
	@SuppressWarnings("unchecked")
	public BatchJobInstance readJobExecution(final long jobExecutionId) {
		Logging.info(this, "Begin readBatchJobInstance");
		ArrayList<BatchJobInstance> arrayList = (ArrayList<BatchJobInstance>) getHibernateTemplate().find(readJobExecutionHql, new Object[]{jobExecutionId});
		BatchJobInstance batchJobInstance = null;
		if(!arrayList.isEmpty()) {
			batchJobInstance = (BatchJobInstance)arrayList.iterator().next();
		}
		Logging.info(this, "End readBatchJobInstance");
		return batchJobInstance;
	}
	
	
	private static final String listHql = "SELECT new be.luxuryoverdosis.framework.data.to.BatchJobInstance(" +
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
		"from BatchJobExecution bje " +
		"inner join bje.batchJobInstance bji " +
		"where bji.jobName = ? " +
		"order by bje.createTime desc";
	
	@SuppressWarnings("unchecked")
	public ArrayList<BatchJobInstance> list(final String jobName) {
		Logging.info(this, "Begin listBatchJobInstance");
		ArrayList<BatchJobInstance> arrayList = (ArrayList<BatchJobInstance>) getHibernateTemplate().find(listHql, new Object[]{jobName});
		Logging.info(this, "End listBatchJobInstance");
		return arrayList;
	}
}
