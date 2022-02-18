package be.luxuryoverdosis.framework.data.dao.implementations;

import java.util.ArrayList;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import be.luxuryoverdosis.framework.data.dao.AbstractHibernateDaoSupport;
import be.luxuryoverdosis.framework.data.dao.interfaces.SearchHibernateDAO;
import be.luxuryoverdosis.framework.data.dto.SearchDTO;
import be.luxuryoverdosis.framework.logging.Logging;

@Repository
public class SearchHibernateDAOMySQLImpl extends AbstractHibernateDaoSupport implements SearchHibernateDAO {
	@SuppressWarnings("unchecked")
	public ArrayList<Object> search(final String select, final ArrayList<SearchDTO> searchDTOs) {
		Logging.info(this, "Begin searchSearch");
		
		Query<Object> query = getCurrentSession().createQuery(select);
		
		if(searchDTOs != null) {
			for (SearchDTO searchDTO : searchDTOs) {
				query.setParameter(searchDTO.getParameterName(), searchDTO.getObject());
			}
		}
		
		ArrayList<Object> arrayList = (ArrayList<Object>) query.list();
		
		Logging.info(this, "End searchSearch");
		return arrayList;
	}
}
