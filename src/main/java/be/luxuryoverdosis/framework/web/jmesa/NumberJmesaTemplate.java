package be.luxuryoverdosis.framework.web.jmesa;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jmesa.core.filter.FilterMatcher;
import org.jmesa.core.filter.MatcherKey;
import org.jmesa.core.filter.NumberFilterMatcher;
import org.jmesa.facade.TableFacade;
import org.jmesa.view.component.Row;

import be.luxuryoverdosis.framework.base.tool.IntegerTool;
import be.luxuryoverdosis.framework.web.BaseWebConstants;

@Deprecated
public class NumberJmesaTemplate extends CustomTableFacadeTemplate {

	public NumberJmesaTemplate(TableFacade tableFacade, ArrayList<?> items, HttpServletRequest request) {
		super(tableFacade, items, request, "displayNumber.title", BaseWebConstants.NUMBER_IDS);
	}

	@Override
	protected void addFilterMatchers(Map<MatcherKey, FilterMatcher> filterMatchers) {
		filterMatchers.put(new MatcherKey(Integer.class, getColumnProperties()[1]), new NumberFilterMatcher(IntegerTool.INTEGER_PATTERN));
	}

	@Override
	protected String[] getColumnProperties() {
		ArrayList<String> columnsList = new ArrayList<String>();
		columnsList.add("applicationCode");
		columnsList.add("year");
		columnsList.add("number");
		columnsList.add("type");
		if (isExporting()) {
			columnsList.add("id");
		}
		return getColumnsInString(columnsList);
	}

	protected void setTitles(Row row) {
		int teller = 0;
		teller = setTitleKeyRow(row, teller, "security.number.application.code");
		teller = setTitleKeyRow(row, teller, "security.number.year");
		teller = setTitleKeyRow(row, teller, "security.number.number");
		teller = setTitleKeyRow(row, teller, "security.number.type");
		
		
	}
}
