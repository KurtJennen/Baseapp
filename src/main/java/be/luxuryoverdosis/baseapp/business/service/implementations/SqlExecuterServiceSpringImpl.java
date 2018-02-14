package be.luxuryoverdosis.baseapp.business.service.implementations;

import javax.annotation.Resource;

import be.luxuryoverdosis.baseapp.business.service.interfaces.SqlExecuterService;
import be.luxuryoverdosis.baseapp.data.dao.interfaces.SqlExecuterDAO;
import be.luxuryoverdosis.framework.data.dao.DAOException;
import be.luxuryoverdosis.framework.logging.Logging;

public class SqlExecuterServiceSpringImpl implements SqlExecuterService {
	@Resource
	SqlExecuterDAO sqlExecuterDAO;
	
	public void execute() throws DAOException {
		Logging.info(this, "Begin executeSqlExecuter");
		sqlExecuterDAO.execute();
		Logging.info(this, "Begin executeSqlExecuter");
	}
}
