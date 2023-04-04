package be.luxuryoverdosis.framework.business.service.implementations;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;

import be.luxuryoverdosis.framework.BaseConstants;
import be.luxuryoverdosis.framework.business.service.interfaces.AbstractSqlExecuterService;
import be.luxuryoverdosis.framework.business.service.interfaces.SqlService;
import be.luxuryoverdosis.framework.data.dao.sql.SqlStartup;
import be.luxuryoverdosis.framework.logging.Logging;

public abstract class AbstractSqlExecuterServiceSpringImpl implements AbstractSqlExecuterService {
	@Resource
	SqlService sqlService;
	
	abstract public void executeSql(SqlService sqlService) throws DataAccessException;
	
	public void execute() throws DataAccessException {
		Logging.info(this, "Begin execute");
		
		//framework sql's
		sqlService.execute(SqlStartup.SQL_090709a, "090709a", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_090710a, "090710a", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_090710b, "090710b", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_090711a, "090711a", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_090711b, "090711b", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_090904a, "090904a", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_090911a, "090911a", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_090916a, "090916a", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_091007a, "091007a", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_091007b, "091007b", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_100317a, "100317a", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_120117a, "120117a", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_120131a, "120131a", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_120228a, "120228a", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_120625b, "120625b", BaseConstants.NAME);
		//Spring Batch
		sqlService.execute(SqlStartup.SQL_130311a, "130311a", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_130311b, "130311b", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_130311c, "130311c", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_130311d, "130311d", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_130311e, "130311e", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_130311f, "130311f", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_130311g, "130311g", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_130311h, "130311h", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_130311i, "130311i", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_190710a, "190710a", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_190710b, "190710b", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_190710c, "190710c", BaseConstants.NAME);
		//Spring Batch End
		sqlService.execute(SqlStartup.SQL_140531a, "140531a", BaseConstants.NAME);
		//Spring Batch Upgrade
		sqlService.execute(SqlStartup.SQL_181019a, "181019a", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_181019b, "181019b", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_181019c, "181019c", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_181019d, "181019d", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_181019e, "181019e", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_181019f, "181019f", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_181019g, "181019g", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_181019h, "181019h", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_181019i, "181019i", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_181019j, "181019j", BaseConstants.NAME);
		//Spring Batch Upgrade End
		sqlService.execute(SqlStartup.SQL_210307a, "210307a", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_210307b, "210307b", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_210307c, "210307c", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_210503a, "210503a", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_210504a, "210504a", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_210525a, "210525a", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_210525b, "210525b", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_210525c, "210525c", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_221117a, "221117a", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_221117b, "221117b", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_230228a, "230228a", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_230320a, "230320a", BaseConstants.NAME);
		this.executeSql(sqlService);
		
		Logging.info(this, "Begin execute");
	}
}
