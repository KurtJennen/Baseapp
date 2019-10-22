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

import be.luxuryoverdosis.framework.base.tool.DateTool;

@Deprecated
public class BatchJobExecutionParamsJmesaTemplate extends CustomTableFacadeTemplate {

	public BatchJobExecutionParamsJmesaTemplate(TableFacade tableFacade, ArrayList<?> items, HttpServletRequest request) {
		super(tableFacade, items, request, "displayJobExecutionParams.title");
		setClickable(false);
	}

	@Override
	protected void addFilterMatchers(Map<MatcherKey, FilterMatcher> filterMatchers) {
		filterMatchers.put(new MatcherKey(Date.class, getColumnProperties()[3]), new DateFilterMatcher(DateTool.UTIL_DATETIME_PATTERN));
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
		columnsList.add("identifying");
		if (isExporting()) {
			columnsList.add("id");
		}
		return getColumnsInString(columnsList);
	}

	protected void setTitles(Row row) {
		int teller = 0;
		teller = setTitleKeyRow(row, teller, "batchjobexecutionparams.type.code");
		teller = setTitleKeyRow(row, teller, "batchjobexecutionparams.key.name");
		teller = setTitleKeyRow(row, teller, "batchjobexecutionparams.string.value");
		teller = setTitleKeyRow(row, teller, "batchjobexecutionparams.date.value");
		teller = setTitleKeyRow(row, teller, "batchjobexecutionparams.long.value");
		teller = setTitleKeyRow(row, teller, "batchjobexecutionparams.double.value");
		teller = setTitleKeyRow(row, teller, "batchjobexecutionparams.identifying");
	}
}
