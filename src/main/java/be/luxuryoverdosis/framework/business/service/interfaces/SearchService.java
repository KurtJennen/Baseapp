package be.luxuryoverdosis.framework.business.service.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.business.query.SearchCriteria;
import be.luxuryoverdosis.framework.business.query.SearchSelect;
import be.luxuryoverdosis.framework.data.dto.SearchDTO;

public interface SearchService {
	ArrayList<Object> search(SearchSelect searchSelect, SearchCriteria searchCriteria);
	ArrayList<Object> search(SearchSelect searchSelect, SearchCriteria searchCriteria, ArrayList<SearchDTO> defaultSearchDTOs);
	ArrayList<SearchDTO> constructObjects(SearchSelect searchSelect, SearchCriteria searchCriteria);
}
