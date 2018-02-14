package be.luxuryoverdosis.framework.business;

import javax.annotation.Resource;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

public class BaseService {
	@Resource
	private PlatformTransactionManager transactionManager;
	@Resource
	private TransactionTemplate transactionTemplate;
	
	public PlatformTransactionManager getTransactionManager() {
		return transactionManager;
	}
	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}
}
