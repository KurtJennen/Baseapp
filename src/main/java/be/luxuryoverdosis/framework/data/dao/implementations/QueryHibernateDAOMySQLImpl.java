package be.luxuryoverdosis.framework.data.dao.implementations;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import be.luxuryoverdosis.framework.data.dao.AbstractHibernateDaoSupport;
import be.luxuryoverdosis.framework.data.dao.interfaces.QueryHibernateDAO;
import be.luxuryoverdosis.framework.data.to.Query;
import be.luxuryoverdosis.framework.logging.Logging;

@Repository
public class QueryHibernateDAOMySQLImpl extends AbstractHibernateDaoSupport implements QueryHibernateDAO {
	public Query createOrUpdate(final Query query) {
		Logging.info(this, "Begin createQuery");
		getHibernateTemplate().saveOrUpdate(query);
		Logging.info(this, "End createQuery");
		return query;
	}

	public Query read(final int id) {
		Logging.info(this, "Begin readQuery(id)");
		Query query = (Query) getHibernateTemplate().load(Query.class, id);
		Logging.info(this, "End readQuery(id)");
		return query;
	}
	
	@SuppressWarnings("unchecked")
	public Query read(final String name, final String type) {
		Logging.info(this, "Begin readQuery(name, type)");
		ArrayList<Query> arrayList = (ArrayList<Query>) getHibernateTemplate().find("from Query q where q.name = ? and q.type = ?", new Object[]{name, type});
		Query query = null;
		if(!arrayList.isEmpty()) {
			query = (Query)arrayList.iterator().next();
		}
		Logging.info(this, "End readQuery(name, type)");
		return query;
	}

	public void delete(final int id) {
		Logging.info(this, "Begin deleteQuery");
		getHibernateTemplate().delete((Query) getHibernateTemplate().load(Query.class, id));
		Logging.info(this, "End deleteQuery");		
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Query> list(final String type, final int userId) {
		Logging.info(this, "Begin listQuery(");
		ArrayList<Query> arrayList = (ArrayList<Query>) getHibernateTemplate().find("from Query q where q.type = ? and q.user.id = ?", new Object[]{type, userId});
		Logging.info(this, "End listQuery");
		return arrayList;
	}
	
	@SuppressWarnings("unchecked")
	public long count(final String name, final String type, final int userId) {
		Logging.info(this, "Begin countQuery");
		ArrayList<Long> arrayList = (ArrayList<Long>) getHibernateTemplate().find("select count(*) from Query q where q.name = ? and q.type = ? and q.user.id = ?", new Object[]{name, type, userId});
		long count = arrayList.iterator().next().longValue();
		Logging.info(this, "End countQuery");
		return count;
	}
}
