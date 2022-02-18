package be.luxuryoverdosis.framework.data.dao.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.dto.SearchDTO;

public interface SearchHibernateDAO {
	public ArrayList<Object> search(String select, ArrayList<SearchDTO> searchDTOs);
}
