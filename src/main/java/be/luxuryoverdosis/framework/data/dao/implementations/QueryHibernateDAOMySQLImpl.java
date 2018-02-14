package be.luxuryoverdosis.framework.data.dao.implementations;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import be.luxuryoverdosis.framework.data.dao.AbstractHibernateDaoSupport;
import be.luxuryoverdosis.framework.data.dao.interfaces.QueryHibernateDAO;
import be.luxuryoverdosis.framework.data.to.QueryTO;
import be.luxuryoverdosis.framework.logging.Logging;

@Repository
public class QueryHibernateDAOMySQLImpl extends AbstractHibernateDaoSupport implements QueryHibernateDAO {
	public QueryTO createOrUpdate(final QueryTO queryTO) {
		Logging.info(this, "Begin createQuery");
		getHibernateTemplate().saveOrUpdate(queryTO);
		Logging.info(this, "End createQuery");
		return queryTO;
	}

	public QueryTO read(final int id) {
		Logging.info(this, "Begin readQuery(id)");
		QueryTO queryTO = (QueryTO) getHibernateTemplate().load(QueryTO.class, id);
		Logging.info(this, "End readQuery(id)");
		return queryTO;
	}
	
	@SuppressWarnings("unchecked")
	public QueryTO read(final String name, final String type) {
		Logging.info(this, "Begin readQuery(name, type)");
		ArrayList<QueryTO> arrayList = (ArrayList<QueryTO>) getHibernateTemplate().find("from QueryTO q where q.name = ? and q.type = ?", new Object[]{name, type});
		QueryTO queryTO = null;
		if(!arrayList.isEmpty()) {
			queryTO = (QueryTO)arrayList.iterator().next();
		}
		Logging.info(this, "End readQuery(name, type)");
		return queryTO;
	}

	public void delete(final int id) {
		Logging.info(this, "Begin deleteQuery");
		getHibernateTemplate().delete((QueryTO) getHibernateTemplate().load(QueryTO.class, id));
		Logging.info(this, "End deleteQuery");		
	}

	@SuppressWarnings("unchecked")
	public ArrayList<QueryTO> list(final String type, final int userId) {
		Logging.info(this, "Begin listQuery(");
		ArrayList<QueryTO> arrayList = (ArrayList<QueryTO>) getHibernateTemplate().find("from QueryTO q where q.type = ? and q.user.id = ?", new Object[]{type, userId});
		Logging.info(this, "End listQuery");
		return arrayList;
	}
	
	@SuppressWarnings("unchecked")
	public long count(final String name, final String type, final int userId) {
		Logging.info(this, "Begin countQuery");
		ArrayList<Long> arrayList = (ArrayList<Long>) getHibernateTemplate().find("select count(*) from QueryTO q where q.name = ? and q.type = ? and q.user.id = ?", new Object[]{name, type, userId});
		long count = arrayList.iterator().next().longValue();
		Logging.info(this, "End countQuery");
		return count;
	}
}
