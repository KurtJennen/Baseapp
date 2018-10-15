package be.luxuryoverdosis.framework.data.dao.implementations;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import be.luxuryoverdosis.framework.data.dao.AbstractHibernateDaoSupport;
import be.luxuryoverdosis.framework.data.dao.interfaces.QueryHibernateDAO;
import be.luxuryoverdosis.framework.data.to.Query;
import be.luxuryoverdosis.framework.logging.Logging;

@Repository
public class QueryHibernateDAOMySQLImpl extends AbstractHibernateDaoSupport implements QueryHibernateDAO {
	private static final String TYPE = "type";
	private static final String NAME = "name";
	private static final String USER_ID = "userId";
	
	public Query createOrUpdate(final Query query) {
		Logging.info(this, "Begin createQuery");
		getCurrentSession().saveOrUpdate(query);
		Logging.info(this, "End createQuery");
		return query;
	}

	public Query read(final int id) {
		Logging.info(this, "Begin readQuery(id)");
		Query query = (Query) getCurrentSession().load(Query.class, id);
		Logging.info(this, "End readQuery(id)");
		return query;
	}
	
	@SuppressWarnings("unchecked")
	public Query read(final String name, final String type) {
		Logging.info(this, "Begin readQuery(name, type)");
		
		org.hibernate.Query hibernateQuery = getCurrentSession().getNamedQuery("getAllQueriesByNameAndType");
		hibernateQuery.setString(NAME, name);
		hibernateQuery.setString(TYPE, type);
		ArrayList<Query> arrayList = (ArrayList<Query>) hibernateQuery.list();
		
		Query query = null;
		if(!arrayList.isEmpty()) {
			query = (Query)arrayList.iterator().next();
		}
		Logging.info(this, "End readQuery(name, type)");
		return query;
	}

	public void delete(final int id) {
		Logging.info(this, "Begin deleteQuery");
		getCurrentSession().delete(this.read(id));
		Logging.info(this, "End deleteQuery");		
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Query> list(final String type, final int userId) {
		Logging.info(this, "Begin listQuery");
		
		org.hibernate.Query hibernateQuery = getCurrentSession().getNamedQuery("getAllQueriesByTypeAndUser");
		hibernateQuery.setString(TYPE, type);
		hibernateQuery.setInteger(USER_ID, userId);
		ArrayList<Query> arrayList = (ArrayList<Query>) hibernateQuery.list();
		
		Logging.info(this, "End listQuery");
		return arrayList;
	}
	
	@SuppressWarnings("unchecked")
	public long count(final String name, final String type, final int userId) {
		Logging.info(this, "Begin countQuery");
		
		org.hibernate.Query hibernateQuery = getCurrentSession().getNamedQuery("getCountQueriesByNameAndTypeAndUser");
		hibernateQuery.setString(NAME, name);
		hibernateQuery.setString(TYPE, type);
		hibernateQuery.setInteger(USER_ID, userId);
		ArrayList<Long> arrayList = (ArrayList<Long>) hibernateQuery.list();
		long count = arrayList.iterator().next().longValue();
		
		Logging.info(this, "End countQuery");
		return count;
	}
}
