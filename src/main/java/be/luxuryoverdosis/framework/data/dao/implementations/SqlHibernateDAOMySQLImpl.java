package be.luxuryoverdosis.framework.data.dao.implementations;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import be.luxuryoverdosis.framework.data.dao.AbstractHibernateDaoSupport;
import be.luxuryoverdosis.framework.data.dao.interfaces.SqlHibernateDAO;
import be.luxuryoverdosis.framework.data.to.Sql;
import be.luxuryoverdosis.framework.logging.Logging;

@Repository
public class SqlHibernateDAOMySQLImpl extends AbstractHibernateDaoSupport implements SqlHibernateDAO {
	public Sql createOrUpdate(final Sql sql) {
		Logging.info(this, "Begin createSql");
		getHibernateTemplate().saveOrUpdate(sql);
		Logging.info(this, "End createSql");
		return sql;
	}

	public Sql read(final int id) {
		Logging.info(this, "Begin readSql");
		Sql sql = (Sql) getHibernateTemplate().load(Sql.class, id);
		Logging.info(this, "End readSql");
		return sql;
	}
	
	@SuppressWarnings("unchecked")
	public Sql readName(final String name) {
		Logging.info(this, "Begin readNameSql");
		ArrayList<Sql> arrayList = (ArrayList<Sql>) getHibernateTemplate().find("from Sql r where r.name = ?", new Object[]{name});
		Sql sql = null;
		if(!arrayList.isEmpty()) {
			sql = (Sql)arrayList.iterator().next();
		}
		Logging.info(this, "End readNameSql");
		return sql;
	}

	public void delete(final int id) {
		Logging.info(this, "Begin deleteSql");
		getHibernateTemplate().delete((Sql) getHibernateTemplate().load(Sql.class, id));
		Logging.info(this, "End deleteSql");		
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Sql> list() {
		Logging.info(this, "Begin listSql");
		ArrayList<Sql> arrayList = (ArrayList<Sql>) getHibernateTemplate().find("from Sql");
		Logging.info(this, "End listSql");
		return arrayList;
	}
	
	@SuppressWarnings("unchecked")
	public long count(final String name) {
		Logging.info(this, "Begin countSql(String)");
		ArrayList<Long> arrayList = (ArrayList<Long>) getHibernateTemplate().find("select count(*) from Sql s where s.name = ?", new Object[]{name});
		long count = arrayList.iterator().next().longValue();
		Logging.info(this, "End countSql(String)");
		return count;
	}
	
	@SuppressWarnings("unchecked")
	public long count(final String name, final String application) {
		Logging.info(this, "Begin countSql(String, String)");
		ArrayList<Long> arrayList = (ArrayList<Long>) getHibernateTemplate().find("select count(*) from Sql s where s.name = ? and s.application = ?", new Object[]{name, application});
		long count = arrayList.iterator().next().longValue();
		Logging.info(this, "End countSql(String, String)");
		return count;
	}
}
