package be.luxuryoverdosis.framework.data.dao.implementations;

import java.util.ArrayList;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import be.luxuryoverdosis.framework.data.dao.AbstractHibernateDaoSupport;
import be.luxuryoverdosis.framework.data.dao.QueryParameters;
import be.luxuryoverdosis.framework.data.dao.interfaces.NumberHibernateDAO;
import be.luxuryoverdosis.framework.data.to.Number;
import be.luxuryoverdosis.framework.logging.Logging;

@Repository
public class NumberHibernateDAOMySQLImpl extends AbstractHibernateDaoSupport implements NumberHibernateDAO {
	public Number createOrUpdate(final Number number) {
		Logging.info(this, "Begin createNumber");
		getCurrentSession().saveOrUpdate(number);
		Logging.info(this, "End createNumber");
		return number;
	}

	public Number read(final int id) {
		Logging.info(this, "Begin readNumber");
		Number number = (Number) getCurrentSession().load(Number.class, id);
		Logging.info(this, "End readNumber");
		return number;
	}
	
	@SuppressWarnings("unchecked")
	public Number read(final String applicationCode, final String year, final String type) {
		Logging.info(this, "Begin readNumber");
		
		Query<Number> query = getCurrentSession().getNamedQuery(Number.SELECT_NUMBERS_BY_APPLICATION_CODE_AND_YEAR_AND_TYPE);
		query.setParameter(QueryParameters.APPLICATION_CODE, applicationCode);
		query.setParameter(QueryParameters.YEAR, year);
		query.setParameter(QueryParameters.TYPE, type);
		ArrayList<Number> arrayList = (ArrayList<Number>) query.list();
		
		Number number = null;
		if(!arrayList.isEmpty()) {
			number = (Number)arrayList.iterator().next();
		}
		Logging.info(this, "End readNumber");
		return number;
	}

	public void delete(final int id) {
		Logging.info(this, "Begin deleteNumber");
		getCurrentSession().delete(this.read(id));
		Logging.info(this, "End deleteNumber");		
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Number> list() {
		Logging.info(this, "Begin listNumber");
		Query<Number> query = getCurrentSession().getNamedQuery(Number.SELECT_NUMBERS);
		ArrayList<Number> arrayList = (ArrayList<Number>) query.list();
		Logging.info(this, "End listNumber");
		return arrayList;
	}
	
	@SuppressWarnings("unchecked")
	public long count(final String applicationCode, final String year, final String type, final int id) {
		Logging.info(this, "Begin countNumber");
		
		Query<Long> query = getCurrentSession().getNamedQuery(Number.COUNT_NUMBERS_BY_APPLICATION_CODE_AND_YEAR_AND_TYPE);
		query.setParameter(QueryParameters.APPLICATION_CODE, applicationCode);
		query.setParameter(QueryParameters.YEAR, year);
		query.setParameter(QueryParameters.TYPE, type);
		query.setParameter(QueryParameters.ID, id);
		ArrayList<Long> arrayList = (ArrayList<Long>) query.list();
		
		long count = arrayList.iterator().next().longValue();
		Logging.info(this, "End countNumber");
		return count;
	}
	
	@SuppressWarnings("unchecked")
	public int nextNumber(String applicationCode, String year, String type) {
        Query<Integer> query = getCurrentSession().getNamedNativeQuery(Number.SELECT_NUMBERS_BY_APPLICATION_CODE_AND_YEAR_AND_TYPE_FOR_UPDATE);
        query.setParameter(QueryParameters.APPLICATION_CODE, applicationCode);
        query.setParameter(QueryParameters.YEAR, year);
        query.setParameter(QueryParameters.TYPE, type);
        ArrayList<Integer> arrayList = (ArrayList<Integer>) query.list();
        int volgnummer = arrayList.iterator().next().intValue();
        
        volgnummer++;

        Query<Integer> queryUpdate = getCurrentSession().getNamedNativeQuery(Number.UPDATE_NUMBERS_BY_APPLICATION_CODE_AND_YEAR_AND_TYPE);
        queryUpdate.setParameter(QueryParameters.NUMBER, volgnummer);
        queryUpdate.setParameter(QueryParameters.APPLICATION_CODE, applicationCode);
        queryUpdate.setParameter(QueryParameters.YEAR, year);
        queryUpdate.setParameter(QueryParameters.TYPE, type);
        queryUpdate.executeUpdate();

        return volgnummer;
	}
}
