package be.luxuryoverdosis.framework.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.annotation.Resource;

import be.luxuryoverdosis.framework.BaseConstants;
import be.luxuryoverdosis.framework.data.dao.interfaces.SqlHibernateDAO;
import be.luxuryoverdosis.framework.data.dao.sql.SqlStartup;
import be.luxuryoverdosis.framework.data.to.Sql;

public class DAOBaseSql extends DAOBase {
	@Resource
	SqlHibernateDAO sqlHibernateDAO;
	
	protected Connection connection = null;
	
	public void execute() throws DAOException {
		connection = getConnection();
		
		this.execute(SqlStartup.SQL_090709a, "090709a", BaseConstants.NAME);
		this.execute(SqlStartup.SQL_090710a, "090710a", BaseConstants.NAME);
		this.execute(SqlStartup.SQL_090710b, "090710b", BaseConstants.NAME);
		this.execute(SqlStartup.SQL_090711a, "090711a", BaseConstants.NAME);
		this.execute(SqlStartup.SQL_090711b, "090711b", BaseConstants.NAME);
		this.execute(SqlStartup.SQL_090904a, "090904a", BaseConstants.NAME);
		this.execute(SqlStartup.SQL_090911a, "090911a", BaseConstants.NAME);
		this.execute(SqlStartup.SQL_090916a, "090916a", BaseConstants.NAME);
		this.execute(SqlStartup.SQL_091007a, "091007a", BaseConstants.NAME);
		this.execute(SqlStartup.SQL_091007b, "091007b", BaseConstants.NAME);
		this.execute(SqlStartup.SQL_100317a, "100317a", BaseConstants.NAME);
		this.execute(SqlStartup.SQL_120117a, "120117a", BaseConstants.NAME);
		this.execute(SqlStartup.SQL_120131a, "120131a", BaseConstants.NAME);
		this.execute(SqlStartup.SQL_120228a, "120228a", BaseConstants.NAME);
		this.execute(SqlStartup.SQL_120625b, "120625b", BaseConstants.NAME);
		//Spring Batch
		this.execute(SqlStartup.SQL_130311a, "130311a", BaseConstants.NAME);
		this.execute(SqlStartup.SQL_130311b, "130311b", BaseConstants.NAME);
		this.execute(SqlStartup.SQL_130311c, "130311c", BaseConstants.NAME);
		this.execute(SqlStartup.SQL_130311d, "130311d", BaseConstants.NAME);
		this.execute(SqlStartup.SQL_130311e, "130311e", BaseConstants.NAME);
		this.execute(SqlStartup.SQL_130311f, "130311f", BaseConstants.NAME);
		this.execute(SqlStartup.SQL_130311g, "130311g", BaseConstants.NAME);
		this.execute(SqlStartup.SQL_130311h, "130311h", BaseConstants.NAME);
		this.execute(SqlStartup.SQL_130311i, "130311i", BaseConstants.NAME);
		//SPring Batch End
		this.execute(SqlStartup.SQL_140531a, "140531a", BaseConstants.NAME);
		
		
//		try {
//			connection.close();
//		}
//		catch(SQLException ex) {
//			throw new DAOException();
//		}
	}
	
	protected void execute(String sqlStatement, String name, String application) throws DAOException {
		PreparedStatement pStatement = null;

		long count = 0;
			
		try {
			count = sqlHibernateDAO.count(name, application);
		} catch (Exception e) {
			System.out.println("SQL First Startup");
		}
		
		if(count == 0) {
			try  {
				pStatement = connection.prepareStatement(sqlStatement);
				pStatement.executeUpdate();
				
				Sql sql = new Sql();
				sql.setName(name);
				sql.setContent(sqlStatement);
				sql.setApplication(application);
				sqlHibernateDAO.createOrUpdate(sql);
				
				System.out.println("SQL Executed: " + sqlStatement);
			} catch (SQLException e) {
				System.out.println("SQL NOT Executed: " + sqlStatement + " " + e.getMessage());
				throw new DAOException();
			}
			
			finally {
				try {
					if(pStatement != null) {
						pStatement.close();
					}
				}
				catch(SQLException ex) {
					throw new DAOException();
				}
			}
		}
	}

}