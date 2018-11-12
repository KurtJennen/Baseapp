package be.luxuryoverdosis.framework.data.dao.implementations;

import java.util.ArrayList;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import be.luxuryoverdosis.framework.data.dao.AbstractHibernateDaoSupport;
import be.luxuryoverdosis.framework.data.dao.interfaces.SearchHibernateDAO;
import be.luxuryoverdosis.framework.logging.Logging;

@Repository
public class SearchHibernateDAOMySQLImpl extends AbstractHibernateDaoSupport implements SearchHibernateDAO {
	@SuppressWarnings("unchecked")
	public ArrayList<Object> search(final String select, final Object[] objects) {
		Logging.info(this, "Begin searchSearch");
		
		Query<Object> query = getCurrentSession().createQuery(select);
		for (int position = 0; position < objects.length; position++) {
			query.setParameter(position, objects[position]);
		}
		ArrayList<Object> arrayList = (ArrayList<Object>) query.list();
		
		Logging.info(this, "End searchSearch");
		return arrayList;
	}
}
