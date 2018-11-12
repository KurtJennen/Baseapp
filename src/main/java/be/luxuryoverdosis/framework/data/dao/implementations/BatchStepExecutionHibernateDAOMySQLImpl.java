package be.luxuryoverdosis.framework.data.dao.implementations;

import java.util.ArrayList;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import be.luxuryoverdosis.framework.data.dao.AbstractHibernateDaoSupport;
import be.luxuryoverdosis.framework.data.dao.interfaces.BatchStepExecutionHibernateDAO;
import be.luxuryoverdosis.framework.data.to.BatchStepExecution;
import be.luxuryoverdosis.framework.logging.Logging;

@Repository
public class BatchStepExecutionHibernateDAOMySQLImpl extends AbstractHibernateDaoSupport implements BatchStepExecutionHibernateDAO {
	private static final String JOB_INSTANCE_ID = "jobInstanceId";
	
	@SuppressWarnings("unchecked")
	public ArrayList<BatchStepExecution> list(long jobInstanceId) {
		Logging.info(this, "Begin listBatchStepExecution");
		
		Query<BatchStepExecution> query = getCurrentSession().getNamedQuery("getAllBatchStepExecutionByJobInstance");
		query.setParameter(JOB_INSTANCE_ID, jobInstanceId);
		ArrayList<BatchStepExecution> arrayList = (ArrayList<BatchStepExecution>) query.list();
		
		Logging.info(this, "End listBatchStepExecution");
		return arrayList;
	}
}
