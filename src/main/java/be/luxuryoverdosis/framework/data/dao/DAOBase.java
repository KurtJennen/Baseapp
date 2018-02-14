package be.luxuryoverdosis.framework.data.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import be.luxuryoverdosis.framework.logging.Logging;

public class DAOBase implements DAO {
	@Resource
	private DataSource dataSource;

	public Connection getConnection() throws DAOException {
		Connection connection = null;
		
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			Logging.error(this, "BaseDAO error" + e.getMessage());
		}
		
		return connection;
	}

}