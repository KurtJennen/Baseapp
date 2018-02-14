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
import be.luxuryoverdosis.framework.data.to.NumberTO;
import be.luxuryoverdosis.framework.logging.Logging;
import be.luxuryoverdosis.framework.web.exception.ServiceException;

@Service
public class NumberServiceSpringImpl implements NumberService {
	@Resource
	private NumberHibernateDAO numberHibernateDAO;
	
	@Transactional
	public NumberDTO createOrUpdateDTO(final NumberDTO numberDTO) {
		Logging.info(this, "Begin createNumberDTO");
		
		NumberTO numberTO = new NumberTO();
		if(numberDTO.getId() > 0) {
			numberTO = this.read(numberDTO.getId());
		}
		numberTO = NumberFactory.produceNumber(numberTO, numberDTO);
		
		numberTO = this.createOrUpdate(numberTO);
		
		Logging.info(this, "End createNumberDTO");
		return this.readDTO(numberTO.getId());
	}
	
	@Transactional(readOnly=true)
	public NumberDTO readDTO(final int id) {
		Logging.info(this, "Begin readNumberDTO");
		
		NumberTO numberTO = this.read(id);
		
		NumberDTO numberDTO = NumberFactory.produceNumberDTO(numberTO);
		
		Logging.info(this, "End readNumberDTO");
		return numberDTO;
	}
	
	@Transactional
	public NumberTO createOrUpdate(final NumberTO numberTO) {
		Logging.info(this, "Begin createNumber");
		if(numberHibernateDAO.count(numberTO.getApplicationCode(), numberTO.getYear(), numberTO.getType(), numberTO.getId()) > 0) {
			throw new ServiceException("exists", new String[] {"table.number"});
		}
		if(numberTO.getApplicationCode() == null || StringUtils.isEmpty(numberTO.getApplicationCode())) {
			throw new ServiceException("errors.required", new String[] {"security.number.application.code"});
		}
		if(numberTO.getYear() == null || StringUtils.isEmpty(numberTO.getYear())) {
			throw new ServiceException("errors.required", new String[] {"security.number.year"});
		}
		if(numberTO.getType() == null || StringUtils.isEmpty(numberTO.getType())) {
			throw new ServiceException("errors.required", new String[] {"security.number.type"});
		}
		NumberTO result = null;
		result = numberHibernateDAO.createOrUpdate(numberTO);
		Logging.info(this, "End createNumber");
		return result;
	}
	
	@Transactional(readOnly=true)
	public NumberTO read(final int id) {
		Logging.info(this, "Begin readNumber");
		NumberTO result = null;
		result = numberHibernateDAO.read(id);
		Logging.info(this, "End readNumber");
		return result;
	}
	
	@Transactional(readOnly=true)
	public NumberTO read(final String year, final String type) {
		Logging.info(this, "Begin readNumber");
		NumberTO result = null;
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
	public ArrayList<NumberTO> list() {
		Logging.info(this, "Begin listNumber");
		ArrayList<NumberTO> arrayList = null;
		arrayList = numberHibernateDAO.list();
		Logging.info(this, "End listNumber");
		return arrayList;
	}
	
}
