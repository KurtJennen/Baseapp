package be.luxuryoverdosis.framework.business.service.implementations;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.luxuryoverdosis.framework.business.service.interfaces.BatchJobInstanceService;
import be.luxuryoverdosis.framework.data.dao.interfaces.BatchJobInstanceHibernateDAO;
import be.luxuryoverdosis.framework.data.to.BatchJobInstance;
import be.luxuryoverdosis.framework.logging.Logging;

@Service
public class BatchJobInstanceServiceSpringImpl implements BatchJobInstanceService {
	@Resource
	private BatchJobInstanceHibernateDAO batchJobInstanceHibernateDAO;
	
	@Transactional(readOnly = true)
	public BatchJobInstance read(final long id) {
		Logging.info(this, "Begin readBatchJobInstance");
		BatchJobInstance batchJobInstance = batchJobInstanceHibernateDAO.read(id);
		Logging.info(this, "End readBatchJobInstance");
		return batchJobInstance;
	}
	
	@Transactional
	public ArrayList<BatchJobInstance> list(String jobName) {
		Logging.info(this, "Begin listBatchJobInstance");
		ArrayList<BatchJobInstance> arrayList = null;
		arrayList = batchJobInstanceHibernateDAO.list(jobName);
		Logging.info(this, "End listBatchJobInstance");
		return arrayList;
	}
	
}
