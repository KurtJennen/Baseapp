package be.luxuryoverdosis.baseapp.business.service.interfaces;

import be.luxuryoverdosis.framework.data.dao.DAOException;

public interface SqlExecuterService {
	public void execute() throws DAOException;
}
