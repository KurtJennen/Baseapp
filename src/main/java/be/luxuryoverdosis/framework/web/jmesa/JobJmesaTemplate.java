package be.luxuryoverdosis.framework.web.jmesa;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jmesa.core.filter.DateFilterMatcher;
import org.jmesa.core.filter.FilterMatcher;
import org.jmesa.core.filter.MatcherKey;
import org.jmesa.facade.TableFacade;
import org.jmesa.view.component.Column;
import org.jmesa.view.component.Row;
import org.jmesa.view.editor.BasicCellEditor;
import org.jmesa.view.editor.CellEditor;
import org.jmesa.view.editor.DateCellEditor;

import be.luxuryoverdosis.framework.base.tool.DateTool;
import be.luxuryoverdosis.framework.business.enumeration.JobStatusType;
import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;
import be.luxuryoverdosis.framework.web.BaseWebConstants;

public class JobJmesaTemplate extends CustomTableFacadeTemplate {
	public JobJmesaTemplate(TableFacade tableFacade, ArrayList<?> items, HttpServletRequest request) {
		super(tableFacade, items, request, "displayJob.title", BaseWebConstants.JOB_IDS);
	}

	@Override
	protected void addFilterMatchers(Map<MatcherKey, FilterMatcher> filterMatchers) {
		filterMatchers.put(new MatcherKey(Date.class, getColumnProperties()[2]), new DateFilterMatcher(DateTool.UTIL_DATETIME_PATTERN));
		filterMatchers.put(new MatcherKey(Date.class, getColumnProperties()[3]), new DateFilterMatcher(DateTool.UTIL_DATETIME_PATTERN));
	}

	@Override
	protected String[] getColumnProperties() {
		ArrayList<String> columnsList = new ArrayList<String>();
		columnsList.add("name");
		columnsList.add("fileName");
		columnsList.add("started");
		columnsList.add("ended");
		columnsList.add("status");
		if (isExporting()) {
			columnsList.add("id");
		}
		return getColumnsInString(columnsList);
	}
	
	protected void setTitles(Row row) {
		int teller = 0;
		teller = setTitleKeyRow(row, teller, "job.name");
		teller = setTitleKeyRow(row, teller, "file.name");
		teller = setTitleKeyRow(row, teller, "job.started", new DateCellEditor(DateTool.UTIL_DATETIME_PATTERN));
		teller = setTitleKeyRow(row, teller, "job.ended", new DateCellEditor(DateTool.UTIL_DATETIME_PATTERN));
		Column status = row.getColumn(getColumnProperties()[teller]);
		teller = setTitleKeyRow(row, teller, "job.status");
		
		status.getCellRenderer().setCellEditor(new CellEditor() {
		    public Object getValue(Object item, String property, int rowcount) {
		    	String status = null;
		        Object value = new BasicCellEditor().getValue(item, property, rowcount);
		        
		        if(JobStatusType.NOT_STARTED.getCode().equals(value)) {
		        	status = BaseSpringServiceLocator.getMessage("JobStatusType.N");
				}
				if(JobStatusType.STARTED.getCode().equals(value)) {
					status = BaseSpringServiceLocator.getMessage("JobStatusType.Y");
				}
				if(JobStatusType.EXECUTED.getCode().equals(value)) {
					status = BaseSpringServiceLocator.getMessage("JobStatusType.E");
				}
				
				return status;
		    }
		});
	}
}
