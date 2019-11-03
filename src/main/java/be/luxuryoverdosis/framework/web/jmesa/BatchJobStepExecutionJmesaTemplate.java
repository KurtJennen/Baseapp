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
import org.springframework.batch.core.ExitStatus;

import be.luxuryoverdosis.framework.base.tool.DateTool;
import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;

public class BatchJobStepExecutionJmesaTemplate extends CustomTableFacadeTemplate {

	public BatchJobStepExecutionJmesaTemplate(TableFacade tableFacade, ArrayList<?> items, HttpServletRequest request) {
		super(tableFacade, items, request, "displayJob.title");
		setClickable(false);
	}

	@Override
	protected void addFilterMatchers(Map<MatcherKey, FilterMatcher> filterMatchers) {
		filterMatchers.put(new MatcherKey(Date.class, getColumnProperties()[2]), new DateFilterMatcher(DateTool.UTIL_DATETIME_PATTERN));
		filterMatchers.put(new MatcherKey(Date.class, getColumnProperties()[3]), new DateFilterMatcher(DateTool.UTIL_DATETIME_PATTERN));
		filterMatchers.put(new MatcherKey(Date.class, getColumnProperties()[15]), new DateFilterMatcher(DateTool.UTIL_DATETIME_PATTERN));
	}

	@Override
	protected String[] getColumnProperties() {
		ArrayList<String> columnsList = new ArrayList<String>();
		columnsList.add("version");
		columnsList.add("stepName");
		columnsList.add("startTime");
		columnsList.add("endTime");
		columnsList.add("status");
		columnsList.add("commitCount");
		columnsList.add("readCount");
		columnsList.add("filterCount");
		columnsList.add("writeCount");
		columnsList.add("readSkipCount");
		columnsList.add("writeSkipCount");
		columnsList.add("processSkipCount");
		columnsList.add("rollbackCount");
		columnsList.add("exitCode");
		columnsList.add("exitMessage");
		columnsList.add("lastUpdated");
		if (isExporting()) {
			columnsList.add("id");
		}
		return getColumnsInString(columnsList);
	}

	protected void setTitles(Row row) {
		int teller = 0;
		teller = setTitleKeyRow(row, teller, "batchjobstepexecution.version");
		teller = setTitleKeyRow(row, teller, "batchjobstepexecution.step.name");
		teller = setTitleKeyRow(row, teller, "batchjobstepexecution.start.time");
		teller = setTitleKeyRow(row, teller, "batchjobstepexecution.end.time");
		
		Column status = row.getColumn(getColumnProperties()[teller]);
		teller = setTitleKeyRow(row, teller, "batchjobstepexecution.status");
		
		status.getCellRenderer().setCellEditor(new CellEditor() {
		    public Object getValue(Object item, String property, int rowcount) {
		    	String status = null;
		        Object value = new BasicCellEditor().getValue(item, property, rowcount);
		        
		        if(ExitStatus.COMPLETED.getExitCode().equals(value)) {
		        	status = BaseSpringServiceLocator.getMessage("batchjobexecution.status.completed");
				}
		        if(ExitStatus.EXECUTING.getExitCode().equals(value)) {
		        	status = BaseSpringServiceLocator.getMessage("batchjobexecution.status.executing");
		        }
		        if(ExitStatus.FAILED.getExitCode().equals(value)) {
		        	status = BaseSpringServiceLocator.getMessage("batchjobexecution.status.failed");
		        }
		        if(ExitStatus.NOOP.getExitCode().equals(value)) {
		        	status = BaseSpringServiceLocator.getMessage("batchjobexecution.status.noop");
		        }
		        if(ExitStatus.STOPPED.getExitCode().equals(value)) {
		        	status = BaseSpringServiceLocator.getMessage("batchjobexecution.status.stopped");
		        }
		        if(ExitStatus.UNKNOWN.getExitCode().equals(value)) {
		        	status = BaseSpringServiceLocator.getMessage("batchjobexecution.status.unknown");
		        }
				
				
				return status;
		    }
		});
		
		teller = setTitleKeyRow(row, teller, "batchjobstepexecution.commit.count");
		teller = setTitleKeyRow(row, teller, "batchjobstepexecution.read.count");
		teller = setTitleKeyRow(row, teller, "batchjobstepexecution.filter.count");
		teller = setTitleKeyRow(row, teller, "batchjobstepexecution.write.count");
		teller = setTitleKeyRow(row, teller, "batchjobstepexecution.read.skip.count");
		teller = setTitleKeyRow(row, teller, "batchjobstepexecution.write.skip.count");
		teller = setTitleKeyRow(row, teller, "batchjobstepexecution.process.skip.count");
		teller = setTitleKeyRow(row, teller, "batchjobstepexecution.rollback.count");
		teller = setTitleKeyRow(row, teller, "batchjobstepexecution.exit.code");
		teller = setTitleKeyRow(row, teller, "batchjobstepexecution.exit.message");
		teller = setTitleKeyRow(row, teller, "batchjobstepexecution.last.updated");
	}
}
