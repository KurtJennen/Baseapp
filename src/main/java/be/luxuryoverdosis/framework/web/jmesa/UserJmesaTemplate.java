package be.luxuryoverdosis.framework.web.jmesa;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jmesa.core.filter.FilterMatcher;
import org.jmesa.core.filter.MatcherKey;
import org.jmesa.facade.TableFacade;
import org.jmesa.view.component.Row;
import org.jmesa.view.editor.DateCellEditor;

import be.luxuryoverdosis.framework.base.tool.DateTool;
import be.luxuryoverdosis.framework.web.BaseWebConstants;

@Deprecated
public class UserJmesaTemplate extends CustomTableFacadeTemplate {
	public UserJmesaTemplate(TableFacade tableFacade, ArrayList<?> items, HttpServletRequest request) {
		super(tableFacade, items, request, "displayUser.title", BaseWebConstants.USER_IDS);
	}

	@Override
	protected void addFilterMatchers(Map<MatcherKey, FilterMatcher> filterMatchers) {
	}

	@Override
	protected String[] getColumnProperties() {
		ArrayList<String> columnsList = new ArrayList<String>();
		columnsList.add("name");
		columnsList.add("userName");
		columnsList.add("email");
		columnsList.add("dateExpiration");
		columnsList.add("roleName");
		if(isExporting()) {
			columnsList.add("id");
		}
		
		return getColumnsInString(columnsList);
	}

	
	protected void setTitles(Row row) {
		int teller = 0;
		
		teller = setTitleKeyRow(row, teller, "security.name.unique");
		teller = setTitleKeyRow(row, teller, "security.username");
		teller = setTitleKeyRow(row, teller, "security.email");
		teller = setTitleKeyRow(row, teller, "security.date.expiration", new DateCellEditor(DateTool.UTIL_DATE_PATTERN));
		teller = setTitleKeyRow(row, teller, "security.role");
	}
}
