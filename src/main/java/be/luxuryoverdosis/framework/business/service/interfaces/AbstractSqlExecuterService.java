package be.luxuryoverdosis.framework.business.service.interfaces;

import org.springframework.dao.DataAccessException;

public interface AbstractSqlExecuterService {
	void execute() throws DataAccessException;
}
