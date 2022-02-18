package be.luxuryoverdosis.framework.business.service.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.business.query.SearchCriteria;
import be.luxuryoverdosis.framework.business.query.SearchSelect;
import be.luxuryoverdosis.framework.data.dto.SearchDTO;

public interface SearchService {
	public ArrayList<Object> search(SearchSelect searchSelect, SearchCriteria searchCriteria);
	public ArrayList<Object> search(SearchSelect searchSelect, SearchCriteria searchCriteria, final ArrayList<SearchDTO> defaultSearchDTOs);
	public ArrayList<SearchDTO> constructObjects(final SearchSelect searchSelect, final SearchCriteria searchCriteria);
}
