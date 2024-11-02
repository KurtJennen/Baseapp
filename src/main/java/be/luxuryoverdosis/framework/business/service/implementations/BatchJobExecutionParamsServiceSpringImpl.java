package be.luxuryoverdosis.framework.business.service.implementations;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.luxuryoverdosis.framework.business.service.interfaces.BatchJobExecutionParamsService;
import be.luxuryoverdosis.framework.data.dao.interfaces.BatchJobExecutionHibernateDAO;
import be.luxuryoverdosis.framework.data.dao.interfaces.BatchJobExecutionParamsHibernateDAO;
import be.luxuryoverdosis.framework.data.to.BatchJobExecution;
import be.luxuryoverdosis.framework.data.to.BatchJobExecutionParams;
import be.luxuryoverdosis.framework.logging.Logging;

@Service
public class BatchJobExecutionParamsServiceSpringImpl implements BatchJobExecutionParamsService {
	@Resource
	private BatchJobExecutionHibernateDAO batchJobExecutionHibernateDAO;
	@Resource
	private BatchJobExecutionParamsHibernateDAO batchJobExecutionParamsHibernateDAO;
	
	@Transactional(readOnly = true)
	public ArrayList<BatchJobExecutionParams> list(final long jobInstanceId) {
		Logging.info(this, "Begin listBatchJobParams");
		
		BatchJobExecution batchJobExecution = batchJobExecutionHibernateDAO.read(jobInstanceId);
		
		ArrayList<BatchJobExecutionParams> arrayList = null;
		arrayList = batchJobExecutionParamsHibernateDAO.list(batchJobExecution.getId());
		
		Logging.info(this, "End listBatchJobInstance");
		return arrayList;
	}
	
}
