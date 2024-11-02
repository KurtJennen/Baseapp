package be.luxuryoverdosis.baseapp.business.service.implementations;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import be.luxuryoverdosis.baseapp.business.service.interfaces.SqlExecuterService;
import be.luxuryoverdosis.framework.business.service.implementations.AbstractSqlExecuterServiceSpringImpl;
import be.luxuryoverdosis.framework.business.service.interfaces.SqlService;
import be.luxuryoverdosis.framework.logging.Logging;

@Service
public class SqlExecuterServiceSpringImpl extends AbstractSqlExecuterServiceSpringImpl implements SqlExecuterService {
	
	public void executeSql(final SqlService sqlService) throws DataAccessException {
		Logging.info(this, "Begin executeSql");
		
		//application sql's
		
		Logging.info(this, "End executeSql");
	}
}
