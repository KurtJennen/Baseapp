package be.luxuryoverdosis.framework.data.dao.implementations;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import be.luxuryoverdosis.framework.data.dao.AbstractHibernateDaoSupport;
import be.luxuryoverdosis.framework.data.dao.interfaces.SqlHibernateDAO;
import be.luxuryoverdosis.framework.data.to.SqlTO;
import be.luxuryoverdosis.framework.logging.Logging;

@Repository
public class SqlHibernateDAOMySQLImpl extends AbstractHibernateDaoSupport implements SqlHibernateDAO {
	public SqlTO createOrUpdate(final SqlTO sqlTO) {
		Logging.info(this, "Begin createSql");
		getHibernateTemplate().saveOrUpdate(sqlTO);
		Logging.info(this, "End createSql");
		return sqlTO;
	}

	public SqlTO read(final int id) {
		Logging.info(this, "Begin readSql");
		SqlTO sqlTO = (SqlTO) getHibernateTemplate().load(SqlTO.class, id);
		Logging.info(this, "End readSql");
		return sqlTO;
	}
	
	@SuppressWarnings("unchecked")
	public SqlTO readName(final String name) {
		Logging.info(this, "Begin readNameSql");
		ArrayList<SqlTO> arrayList = (ArrayList<SqlTO>) getHibernateTemplate().find("from SqlTO r where r.name = ?", new Object[]{name});
		SqlTO sqlTO = null;
		if(!arrayList.isEmpty()) {
			sqlTO = (SqlTO)arrayList.iterator().next();
		}
		Logging.info(this, "End readNameSql");
		return sqlTO;
	}

	public void delete(final int id) {
		Logging.info(this, "Begin deleteSql");
		getHibernateTemplate().delete((SqlTO) getHibernateTemplate().load(SqlTO.class, id));
		Logging.info(this, "End deleteSql");		
	}

	@SuppressWarnings("unchecked")
	public ArrayList<SqlTO> list() {
		Logging.info(this, "Begin listSql");
		ArrayList<SqlTO> arrayList = (ArrayList<SqlTO>) getHibernateTemplate().find("from SqlTO");
		Logging.info(this, "End listSql");
		return arrayList;
	}
	
	@SuppressWarnings("unchecked")
	public long count(final String name) {
		Logging.info(this, "Begin countSql(String)");
		ArrayList<Long> arrayList = (ArrayList<Long>) getHibernateTemplate().find("select count(*) from SqlTO s where s.name = ?", new Object[]{name});
		long count = arrayList.iterator().next().longValue();
		Logging.info(this, "End countSql(String)");
		return count;
	}
	
	@SuppressWarnings("unchecked")
	public long count(final String name, final String application) {
		Logging.info(this, "Begin countSql(String, String)");
		ArrayList<Long> arrayList = (ArrayList<Long>) getHibernateTemplate().find("select count(*) from SqlTO s where s.name = ? and s.application = ?", new Object[]{name, application});
		long count = arrayList.iterator().next().longValue();
		Logging.info(this, "End countSql(String, String)");
		return count;
	}
}
