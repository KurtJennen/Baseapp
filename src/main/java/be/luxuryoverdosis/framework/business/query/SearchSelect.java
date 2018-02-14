package be.luxuryoverdosis.framework.business.query;

public class SearchSelect {
	private String select;
	private String orderby;
	private SearchParameter[] searchParameters;
	
	public String getSelect() {
		return select;
	}
	public void setSelect(String select) {
		this.select = select;
	}
	public String getOrderby() {
		return orderby;
	}
	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}
	public SearchParameter[] getSearchParameters() {
		return searchParameters;
	}
	public void setSearchParameters(SearchParameter[] searchParameters) {
		this.searchParameters = searchParameters;
	}
	public SearchParameter getSearchParameter(int index) {
		return searchParameters[index];
	}
	public void setSearchParameter(int index, SearchParameter searchParameter) {
		this.searchParameters[index] = searchParameter;
	}
	
}
