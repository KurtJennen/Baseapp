package be.luxuryoverdosis.framework.business.service.implementations;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.luxuryoverdosis.framework.business.service.interfaces.NumberService;
import be.luxuryoverdosis.framework.data.dao.interfaces.NumberHibernateDAO;
import be.luxuryoverdosis.framework.data.dto.NumberDTO;
import be.luxuryoverdosis.framework.data.factory.NumberFactory;
import be.luxuryoverdosis.framework.data.to.Number;
import be.luxuryoverdosis.framework.logging.Logging;
import be.luxuryoverdosis.framework.web.exception.ServiceException;

@Service
public class NumberServiceSpringImpl implements NumberService {
	@Resource
	private NumberHibernateDAO numberHibernateDAO;
	
	@Transactional
	public NumberDTO createOrUpdateDTO(final NumberDTO numberDTO) {
		Logging.info(this, "Begin createNumberDTO");
		
		Number number = new Number();
		if(numberDTO.getId() > 0) {
			number = this.read(numberDTO.getId());
		}
		number = NumberFactory.produceNumber(number, numberDTO);
		
		number = this.createOrUpdate(number);
		
		Logging.info(this, "End createNumberDTO");
		return this.readDTO(number.getId());
	}
	
	@Transactional(readOnly=true)
	public NumberDTO readDTO(final int id) {
		Logging.info(this, "Begin readNumberDTO");
		
		Number number = this.read(id);
		
		NumberDTO numberDTO = NumberFactory.produceNumberDTO(number);
		
		Logging.info(this, "End readNumberDTO");
		return numberDTO;
	}
	
	@Transactional
	public Number createOrUpdate(final Number number) {
		Logging.info(this, "Begin createNumber");
		if(numberHibernateDAO.count(number.getApplicationCode(), number.getYear(), number.getType(), number.getId()) > 0) {
			throw new ServiceException("exists", new String[] {"table.number"});
		}
		if(number.getApplicationCode() == null || StringUtils.isEmpty(number.getApplicationCode())) {
			throw new ServiceException("errors.required", new String[] {"security.number.application.code"});
		}
		if(number.getYear() == null || StringUtils.isEmpty(number.getYear())) {
			throw new ServiceException("errors.required", new String[] {"security.number.year"});
		}
		if(number.getType() == null || StringUtils.isEmpty(number.getType())) {
			throw new ServiceException("errors.required", new String[] {"security.number.type"});
		}
		Number result = null;
		result = numberHibernateDAO.createOrUpdate(number);
		Logging.info(this, "End createNumber");
		return result;
	}
	
	@Transactional(readOnly=true)
	public Number read(final int id) {
		Logging.info(this, "Begin readNumber");
		Number result = null;
		result = numberHibernateDAO.read(id);
		Logging.info(this, "End readNumber");
		return result;
	}
	
	@Transactional(readOnly=true)
	public Number read(final String year, final String type) {
		Logging.info(this, "Begin readNumber");
		Number result = null;
		result = numberHibernateDAO.read(year, type);
		Logging.info(this, "End readNumber");
		return result;
	}

	@Transactional
	public void delete(final int id) {
		Logging.info(this, "Begin deleteNumber");
		numberHibernateDAO.delete(id);
		Logging.info(this, "End deleteNumber");
	}

	@Transactional(readOnly=true)
	public ArrayList<Number> list() {
		Logging.info(this, "Begin listNumber");
		ArrayList<Number> arrayList = null;
		arrayList = numberHibernateDAO.list();
		Logging.info(this, "End listNumber");
		return arrayList;
	}
	
}
