package be.luxuryoverdosis.framework.business.service.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.to.QueryParam;

public interface QueryParamService {
	public QueryParam createOrUpdate(QueryParam queryParam);
	public QueryParam read(int id);
	public void delete(int id);
	public void deleteForQuery(int queryId);
	
	public ArrayList<QueryParam> list(int queryId);
	
	public long count(int queryId);
}
