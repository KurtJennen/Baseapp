package be.luxuryoverdosis.framework.business.service.implementations;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.luxuryoverdosis.framework.business.service.interfaces.BatchStepExecutionService;
import be.luxuryoverdosis.framework.data.dao.interfaces.BatchStepExecutionHibernateDAO;
import be.luxuryoverdosis.framework.data.to.BatchStepExecution;
import be.luxuryoverdosis.framework.logging.Logging;

@Service
public class BatchStepExecutionServiceSpringImpl implements BatchStepExecutionService {
	@Resource
	private BatchStepExecutionHibernateDAO batchStepExecutionHibernateDAO;
	
	@Transactional
	public ArrayList<BatchStepExecution> list(long jobInstanceId) {
		Logging.info(this, "Begin listBatchStepExecution");
		ArrayList<BatchStepExecution> arrayList = null;
		arrayList = batchStepExecutionHibernateDAO.list(jobInstanceId);
		Logging.info(this, "End listBatchStepExecution");
		return arrayList;
	}
	
}
