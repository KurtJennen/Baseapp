package be.luxuryoverdosis.framework.web.jmesa;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jmesa.core.filter.DateFilterMatcher;
import org.jmesa.core.filter.FilterMatcher;
import org.jmesa.core.filter.MatcherKey;
import org.jmesa.facade.TableFacade;
import org.jmesa.view.component.Row;

import be.luxuryoverdosis.framework.BaseConstants;
import be.luxuryoverdosis.framework.base.tool.DateTool;

public class BatchJobParamsJmesaTemplate extends CustomTableFacadeTemplate {

	public BatchJobParamsJmesaTemplate(final TableFacade tableFacade, final ArrayList<?> items, final HttpServletRequest request) {
		super(tableFacade, items, request, "displayJobParams.title");
		setClickable(false);
	}

	@Override
	protected void addFilterMatchers(final Map<MatcherKey, FilterMatcher> filterMatchers) {
		filterMatchers.put(new MatcherKey(Date.class, getColumnProperties()[BaseConstants.DRIE]), new DateFilterMatcher(DateTool.UTIL_DATETIME_PATTERN));
	}

	@Override
	protected String[] getColumnProperties() {
		ArrayList<String> columnsList = new ArrayList<String>();
		columnsList.add("typeCode");
		columnsList.add("keyName");
		columnsList.add("stringValue");
		columnsList.add("dateValue");
		columnsList.add("longValue");
		columnsList.add("doubleValue");
		if (isExporting()) {
			columnsList.add("id");
		}
		return getColumnsInString(columnsList);
	}

	protected void setTitles(final Row row) {
		int teller = 0;
		teller = setTitleKeyRow(row, teller, "batchjobparams.type.code");
		teller = setTitleKeyRow(row, teller, "batchjobparams.key.name");
		teller = setTitleKeyRow(row, teller, "batchjobparams.string.value");
		teller = setTitleKeyRow(row, teller, "batchjobparams.date.value");
		teller = setTitleKeyRow(row, teller, "batchjobparams.long.value");
		teller = setTitleKeyRow(row, teller, "batchjobparams.double.value");
	}
}
