package be.luxuryoverdosis.baseapp.data.dao.implementations;

import java.sql.SQLException;

import be.luxuryoverdosis.baseapp.data.dao.interfaces.SqlExecuterDAO;
import be.luxuryoverdosis.framework.data.dao.DAOBaseSql;
import be.luxuryoverdosis.framework.data.dao.DAOException;
import be.luxuryoverdosis.framework.logging.Logging;

public class SqlExecuterDAOMySQLImpl extends DAOBaseSql implements SqlExecuterDAO {
	public void execute() throws DAOException {
		Logging.info(this, "Begin executeSqlExecuter");
		super.execute();
		
		//Version_1_0_0
		
		try {
			connection.close();
		}
		catch(SQLException ex) {
			throw new DAOException();
		}
		
		Logging.info(this, "End executeSqlExecuter");
	}
}
