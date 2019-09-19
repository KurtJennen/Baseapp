package be.luxuryoverdosis.framework.data.dao.implementations;

import java.util.ArrayList;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import be.luxuryoverdosis.framework.data.dao.AbstractHibernateDaoSupport;
import be.luxuryoverdosis.framework.data.dao.BaseQueryParameters;
import be.luxuryoverdosis.framework.data.dao.interfaces.BatchStepExecutionHibernateDAO;
import be.luxuryoverdosis.framework.data.to.BatchStepExecution;
import be.luxuryoverdosis.framework.logging.Logging;

@Repository
public class BatchStepExecutionHibernateDAOMySQLImpl extends AbstractHibernateDaoSupport implements BatchStepExecutionHibernateDAO {
	@SuppressWarnings("unchecked")
	public ArrayList<BatchStepExecution> list(long jobInstanceId) {
		Logging.info(this, "Begin listBatchStepExecution");
		
		Query<BatchStepExecution> query = getCurrentSession().getNamedQuery(BatchStepExecution.SELECT_BATCH_STEP_EXECUTIONS_BY_JOB_INSTANCE);
		query.setParameter(BaseQueryParameters.JOB_INSTANCE_ID, jobInstanceId);
		ArrayList<BatchStepExecution> arrayList = (ArrayList<BatchStepExecution>) query.list();
		
		Logging.info(this, "End listBatchStepExecution");
		return arrayList;
	}
}
