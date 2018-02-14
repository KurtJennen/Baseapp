package be.luxuryoverdosis.framework.business.service.implementations;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.luxuryoverdosis.framework.business.service.interfaces.BatchJobParamsService;
import be.luxuryoverdosis.framework.data.dao.interfaces.BatchJobParamsHibernateDAO;
import be.luxuryoverdosis.framework.data.to.BatchJobParamsTO;
import be.luxuryoverdosis.framework.logging.Logging;

@Service
public class BatchJobParamsServiceSpringImpl implements BatchJobParamsService {
	@Resource
	private BatchJobParamsHibernateDAO batchJobParamsHibernateDAO;
	
	@Transactional(readOnly = true)
	public ArrayList<BatchJobParamsTO> list(long jobInstanceId) {
		Logging.info(this, "Begin listBatchJobParams");
		ArrayList<BatchJobParamsTO> arrayList = null;
		arrayList = batchJobParamsHibernateDAO.list(jobInstanceId);
		Logging.info(this, "End listBatchJobInstance");
		return arrayList;
	}
	
}
