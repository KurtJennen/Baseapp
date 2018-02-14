package be.luxuryoverdosis.framework.business.service.implementations;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.luxuryoverdosis.framework.business.service.interfaces.SqlService;
import be.luxuryoverdosis.framework.data.dao.interfaces.SqlHibernateDAO;
import be.luxuryoverdosis.framework.data.to.SqlTO;
import be.luxuryoverdosis.framework.logging.Logging;

@Service
public class SqlServiceSpringImpl implements SqlService {
	@Resource
	private SqlHibernateDAO sqlHibernateDAO;
	
	@Transactional
	public SqlTO createOrUpdate(final SqlTO sqlTO) {
		Logging.info(this, "Begin createSql");
		SqlTO result = null;
		result = sqlHibernateDAO.createOrUpdate(sqlTO);
		Logging.info(this, "End createSql");
		return result;
	}
	
	@Transactional(readOnly=true)
	public SqlTO read(final int id) {
		Logging.info(this, "Begin readSql");
		SqlTO result = null;
		result = sqlHibernateDAO.read(id);
		Logging.info(this, "End readSql");
		return result;
	}
	
	@Transactional(readOnly=true)
	public SqlTO readName(final String name) {
		Logging.info(this, "Begin readNameSql");
		SqlTO result = null;
		result = sqlHibernateDAO.readName(name);
		Logging.info(this, "End readNameSql");
		return result;
	}

	@Transactional
	public void delete(final int id) {
		Logging.info(this, "Begin deleteSql");
		sqlHibernateDAO.delete(id);
		Logging.info(this, "End deleteSql");
	}

	@Transactional(readOnly=true)
	public ArrayList<SqlTO> list() {
		Logging.info(this, "Begin listSql");
		ArrayList<SqlTO> arrayList = null;
		arrayList = sqlHibernateDAO.list();
		Logging.info(this, "End listSql");
		return arrayList;
	}
	
	@Transactional(readOnly=true)
	public long count(final String name) {
		Logging.info(this, "Begin countSql(String)");
		Long countSql = new Long(0); 
		countSql = sqlHibernateDAO.count(name);
		Logging.info(this, "End countSql(String)");
		return countSql.longValue();
	}
	
	@Transactional(readOnly=true)
	public long count(final String name, final String application) {
		Logging.info(this, "Begin countSql(String, String)");
		Long countSql = new Long(0); 
		countSql = sqlHibernateDAO.count(name, application);
		Logging.info(this, "End countSql(String, String)");
		return countSql.longValue();
	}
}
