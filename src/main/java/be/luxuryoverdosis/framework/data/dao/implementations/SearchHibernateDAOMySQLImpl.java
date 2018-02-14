package be.luxuryoverdosis.framework.data.dao.implementations;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import be.luxuryoverdosis.framework.data.dao.AbstractHibernateDaoSupport;
import be.luxuryoverdosis.framework.data.dao.interfaces.SearchHibernateDAO;
import be.luxuryoverdosis.framework.logging.Logging;

@Repository
public class SearchHibernateDAOMySQLImpl extends AbstractHibernateDaoSupport implements SearchHibernateDAO {
	@SuppressWarnings("unchecked")
	public ArrayList<Object> search(final String select, final Object[] objects) {
		Logging.info(this, "Begin searchSearch");
		ArrayList<Object> arrayList = (ArrayList<Object>) getHibernateTemplate().find(select, objects);
		Logging.info(this, "End searchSearch");
		return arrayList;
	}
}
