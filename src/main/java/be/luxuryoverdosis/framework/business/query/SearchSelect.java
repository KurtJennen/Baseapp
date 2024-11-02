package be.luxuryoverdosis.framework.business.query;

public class SearchSelect {
	private String select;
	private String orderby;
	private SearchParameter[] searchParameters;
	
	public String getSelect() {
		return select;
	}
	public void setSelect(final String select) {
		this.select = select;
	}
	public String getOrderby() {
		return orderby;
	}
	public void setOrderby(final String orderby) {
		this.orderby = orderby;
	}
	public SearchParameter[] getSearchParameters() {
		return searchParameters;
	}
	public void setSearchParameters(final SearchParameter[] searchParameters) {
		this.searchParameters = searchParameters;
	}
	public SearchParameter getSearchParameter(final int index) {
		return searchParameters[index];
	}
	public void setSearchParameter(final int index, final SearchParameter searchParameter) {
		this.searchParameters[index] = searchParameter;
	}
	public SearchParameter getSearchParameter(final String name) {
		for (int i = 0; i < searchParameters.length; i++) {
			SearchParameter searchParameter = searchParameters[i];
			if (searchParameter.getName().equals(name)) {
				return searchParameter;
			}
		}
		return null;
	}
	public void setSearchParameter(final String name, final SearchParameter newSearchParameter) {
		for (int i = 0; i < searchParameters.length; i++) {
			SearchParameter oldSearchParameter = searchParameters[i];
			if (oldSearchParameter.getName().equals(name)) {
				this.searchParameters[i] = newSearchParameter;
			}
		}
	}
	
}
