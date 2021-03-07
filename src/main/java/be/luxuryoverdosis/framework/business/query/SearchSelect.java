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
	public SearchParameter getSearchParameter(String name) {
		for (int i = 0; i < searchParameters.length; i++) {
			SearchParameter searchParameter = searchParameters[i];
			if(searchParameter.getName().equals(name)) {
				return searchParameter;
			}
		}
		return null;
	}
	public void setSearchParameter(String name, SearchParameter newSearchParameter) {
		for (int i = 0; i < searchParameters.length; i++) {
			SearchParameter oldSearchParameter = searchParameters[i];
			if(oldSearchParameter.getName().equals(name)) {
				this.searchParameters[i] = newSearchParameter;
			}
		}
	}
	
}
