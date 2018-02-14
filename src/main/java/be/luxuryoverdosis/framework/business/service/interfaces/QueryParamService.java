package be.luxuryoverdosis.framework.business.service.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.to.QueryParamTO;

public interface QueryParamService {
	public QueryParamTO createOrUpdate(QueryParamTO queryParamTO);
	public QueryParamTO read(int id);
	public void delete(int id);
	public void deleteForQuery(int queryId);
	
	public ArrayList<QueryParamTO> list(int queryId);
	
	public long count(int queryId);
}
