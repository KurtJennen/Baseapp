package be.luxuryoverdosis.framework.data.dao.implementations;

import java.util.ArrayList;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import be.luxuryoverdosis.framework.data.dao.AbstractHibernateDaoSupport;
import be.luxuryoverdosis.framework.data.dao.interfaces.NumberHibernateDAO;
import be.luxuryoverdosis.framework.data.to.Number;
import be.luxuryoverdosis.framework.logging.Logging;

@Repository
public class NumberHibernateDAOMySQLImpl extends AbstractHibernateDaoSupport implements NumberHibernateDAO {
	private static final String APPLICATION_CODE = "applicationCode";
	private static final String ID = "id";
	private static final String TYPE = "type";
	private static final String YEAR = "year";
	
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
		
		Query query = getCurrentSession().getNamedQuery("getNumberByYearAndType");
		query.setString(APPLICATION_CODE, applicationCode);
		query.setString(YEAR, year);
		query.setString(TYPE, type);
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
		
		Query query = getCurrentSession().getNamedQuery("getAllNumbers");
		ArrayList<Number> arrayList = (ArrayList<Number>) query.list();
		
		Logging.info(this, "End listNumber");
		return arrayList;
	}
	
	@SuppressWarnings("unchecked")
	public long count(final String applicationCode, final String year, final String type, final int id) {
		Logging.info(this, "Begin countNumber");
		
		Query query = getCurrentSession().getNamedQuery("getCountByApplicationCodeAndYearAndType");
		query.setString(APPLICATION_CODE, applicationCode);
		query.setString(YEAR, year);
		query.setString(TYPE, type);
		query.setInteger(ID, id);
		ArrayList<Long> arrayList = (ArrayList<Long>) query.list();
		
		long count = arrayList.iterator().next().longValue();
		Logging.info(this, "End countNumber");
		return count;
	}
	
	public int nextNumber(String applicationCode, String year, String type) {
        final Object[] paramsSelect = new Object[] {applicationCode, year, type};
        int volgnummer = getJdbcTemplate().queryForObject("select number from base_number where appcode = ? and year = ? and type = ? for update", paramsSelect, Integer.class);

        volgnummer++;

        final Object[] paramsUpdate = new Object[] {volgnummer, applicationCode, year, type };
        getJdbcTemplate().update("update base_number set number = ? where appcode = ? and year = ? and type = ?", paramsUpdate);

        return volgnummer;
	}
}
