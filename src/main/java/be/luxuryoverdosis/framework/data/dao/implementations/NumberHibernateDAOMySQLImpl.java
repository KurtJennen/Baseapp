package be.luxuryoverdosis.framework.data.dao.implementations;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import be.luxuryoverdosis.framework.data.dao.AbstractHibernateDaoSupport;
import be.luxuryoverdosis.framework.data.dao.interfaces.NumberHibernateDAO;
import be.luxuryoverdosis.framework.data.to.NumberTO;
import be.luxuryoverdosis.framework.logging.Logging;

@Repository
public class NumberHibernateDAOMySQLImpl extends AbstractHibernateDaoSupport implements NumberHibernateDAO {
	public NumberTO createOrUpdate(final NumberTO numberTO) {
		Logging.info(this, "Begin createNumber");
		getHibernateTemplate().saveOrUpdate(numberTO);
		Logging.info(this, "End createNumber");
		return numberTO;
	}

	public NumberTO read(final int id) {
		Logging.info(this, "Begin readNumber");
		NumberTO numberTO = (NumberTO) getHibernateTemplate().load(NumberTO.class, id);
		Logging.info(this, "End readNumber");
		return numberTO;
	}
	
	@SuppressWarnings("unchecked")
	public NumberTO read(final String year, final String type) {
		Logging.info(this, "Begin readNumber");
		ArrayList<NumberTO> arrayList = (ArrayList<NumberTO>) getHibernateTemplate().find("from NumberTO n where n.year = ? and n.type = ?", new Object[]{year, type});
		NumberTO numberTO = null;
		if(!arrayList.isEmpty()) {
			numberTO = (NumberTO)arrayList.iterator().next();
		}
		Logging.info(this, "End readNumber");
		return numberTO;
	}

	public void delete(final int id) {
		Logging.info(this, "Begin deleteNumber");
		getHibernateTemplate().delete((NumberTO) getHibernateTemplate().load(NumberTO.class, id));
		Logging.info(this, "End deleteNumber");		
	}

	@SuppressWarnings("unchecked")
	public ArrayList<NumberTO> list() {
		Logging.info(this, "Begin listNumber");
		ArrayList<NumberTO> arrayList = (ArrayList<NumberTO>) getHibernateTemplate().find("from NumberTO");
		Logging.info(this, "End listNumber");
		return arrayList;
	}
	
	@SuppressWarnings("unchecked")
	public long count(final String applicationCode, final String year, final String type, final int id) {
		Logging.info(this, "Begin countNumber");
		ArrayList<Long> arrayList = (ArrayList<Long>) getHibernateTemplate().find("select count(*) from NumberTO n where n.applicationCode = ? and  n.year = ? and n.type = ? and n.id <> ?", new Object[]{applicationCode, year, type, id});
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
