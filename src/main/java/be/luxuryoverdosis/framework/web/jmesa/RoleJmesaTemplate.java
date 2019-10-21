package be.luxuryoverdosis.framework.web.jmesa;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jmesa.core.filter.FilterMatcher;
import org.jmesa.core.filter.MatcherKey;
import org.jmesa.facade.TableFacade;
import org.jmesa.view.component.Row;

import be.luxuryoverdosis.framework.web.BaseWebConstants;

@Deprecated
public class RoleJmesaTemplate extends CustomTableFacadeTemplate {

	public RoleJmesaTemplate(TableFacade tableFacade, ArrayList<?> items, HttpServletRequest request) {
		super(tableFacade, items, request, "displayRole.title", BaseWebConstants.ROLE_IDS);
	}

	@Override
	protected void addFilterMatchers(Map<MatcherKey, FilterMatcher> filterMatchers) {
	}

	@Override
	protected String[] getColumnProperties() {
		ArrayList<String> columnsList = new ArrayList<String>();
		columnsList.add("name");
		if (isExporting()) {
			columnsList.add("id");
		}
		return getColumnsInString(columnsList);
	}

	protected void setTitles(Row row) {
		int teller = 0;
		teller = setTitleKeyRow(row, teller, "security.name");
	}
}
