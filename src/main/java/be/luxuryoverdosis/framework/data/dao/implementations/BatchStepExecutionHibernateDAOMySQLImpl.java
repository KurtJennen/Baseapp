package be.luxuryoverdosis.framework.data.dao.implementations;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import be.luxuryoverdosis.framework.data.dao.AbstractHibernateDaoSupport;
import be.luxuryoverdosis.framework.data.dao.interfaces.BatchStepExecutionHibernateDAO;
import be.luxuryoverdosis.framework.data.to.BatchStepExecution;
import be.luxuryoverdosis.framework.logging.Logging;

@Repository
public class BatchStepExecutionHibernateDAOMySQLImpl extends AbstractHibernateDaoSupport implements BatchStepExecutionHibernateDAO {
	
	private static final String listHql = "select bse " +
		"from BatchStepExecution bse " +
		"inner join bse.batchJobExecution bje " +
		"inner join bje.batchJobInstance jbi " +
		"where jbi.id = ? ";
	
	@SuppressWarnings("unchecked")
	public ArrayList<BatchStepExecution> list(long jobInstanceId) {
		Logging.info(this, "Begin listBatchJobStepExecution");
		ArrayList<BatchStepExecution> arrayList = (ArrayList<BatchStepExecution>) getHibernateTemplate().find(listHql, new Object[]{jobInstanceId});
		Logging.info(this, "End listBatchJobStepExecution");
		return arrayList;
	}
}
