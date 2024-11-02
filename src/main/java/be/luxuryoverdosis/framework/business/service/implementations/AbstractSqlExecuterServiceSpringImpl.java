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
	private SqlService sqlService;
	
	public abstract void executeSql(SqlService sqlService) throws DataAccessException;
	
	public void execute() throws DataAccessException {
		Logging.info(this, "Begin execute");
		
		//framework sql's
		sqlService.execute(SqlStartup.SQL_090709A, "090709a", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_090710A, "090710a", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_090710B, "090710b", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_090711A, "090711a", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_090711B, "090711b", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_090904A, "090904a", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_090911A, "090911a", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_090916A, "090916a", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_091007A, "091007a", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_091007B, "091007b", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_100317A, "100317a", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_120117A, "120117a", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_120131A, "120131a", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_120228A, "120228a", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_120625B, "120625b", BaseConstants.NAME);
		//Spring Batch
		sqlService.execute(SqlStartup.SQL_130311A, "130311a", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_130311B, "130311b", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_130311C, "130311c", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_130311D, "130311d", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_130311E, "130311e", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_130311F, "130311f", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_130311G, "130311g", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_130311H, "130311h", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_130311I, "130311i", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_190710A, "190710a", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_190710B, "190710b", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_190710C, "190710c", BaseConstants.NAME);
		//Spring Batch End
		sqlService.execute(SqlStartup.SQL_140531A, "140531a", BaseConstants.NAME);
		//Spring Batch Upgrade
		sqlService.execute(SqlStartup.SQL_181019A, "181019a", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_181019B, "181019b", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_181019C, "181019c", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_181019D, "181019d", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_181019E, "181019e", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_181019F, "181019f", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_181019G, "181019g", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_181019H, "181019h", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_181019I, "181019i", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_181019J, "181019j", BaseConstants.NAME);
		//Spring Batch Upgrade End
		sqlService.execute(SqlStartup.SQL_210307A, "210307a", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_210307B, "210307b", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_210307C, "210307c", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_210503A, "210503a", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_210504A, "210504a", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_210525A, "210525a", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_210525B, "210525b", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_210525C, "210525c", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_221117A, "221117a", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_221117B, "221117b", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_230228A, "230228a", BaseConstants.NAME);
		sqlService.execute(SqlStartup.SQL_230320A, "230320a", BaseConstants.NAME);
		this.executeSql(sqlService);
		
		Logging.info(this, "Begin execute");
	}
}
