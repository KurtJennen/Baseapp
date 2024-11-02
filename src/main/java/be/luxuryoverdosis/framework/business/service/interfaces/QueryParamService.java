package be.luxuryoverdosis.framework.business.service.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.to.QueryParam;

public interface QueryParamService {
	QueryParam createOrUpdate(QueryParam queryParam);
	QueryParam read(int id);
	void delete(int id);
	void deleteForQuery(int queryId);
	
	ArrayList<QueryParam> list(int queryId);
	
	long count(int queryId);
}
