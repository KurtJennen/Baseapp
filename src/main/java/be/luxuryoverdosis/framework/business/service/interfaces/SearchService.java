package be.luxuryoverdosis.framework.business.service.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.business.query.SearchCriteria;
import be.luxuryoverdosis.framework.business.query.SearchSelect;

public interface SearchService {
	public ArrayList<Object> search(SearchSelect searchSelect, SearchCriteria searchCriteria);
	public Object[] constructObjects(final SearchSelect searchSelect, final SearchCriteria searchCriteria);
}
