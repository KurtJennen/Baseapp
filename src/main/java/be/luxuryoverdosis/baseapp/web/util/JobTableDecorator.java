package be.luxuryoverdosis.baseapp.web.util;

import java.text.ParseException;

import org.displaytag.decorator.TableDecorator;

import be.luxuryoverdosis.baseapp.Constants;
import be.luxuryoverdosis.baseapp.business.service.SpringServiceLocator;
import be.luxuryoverdosis.framework.base.tool.DateTool;
import be.luxuryoverdosis.framework.data.to.JobTO;

public class JobTableDecorator extends TableDecorator {	
	public String getStarted() throws ParseException {
		JobTO jobTO = (JobTO)getCurrentRowObject();
		if(jobTO.getStarted() != null) {
			return DateTool.formatUtilDateTime(jobTO.getStarted());
		}
		
		return null;
	}
	
	public String getEnded() throws ParseException {
		JobTO jobTO = (JobTO)getCurrentRowObject();
		if(jobTO.getEnded() != null) {
			return DateTool.formatUtilDateTime(jobTO.getEnded());
		}
		
		return null;
	}
	
	public String getStatus() throws ParseException {
		JobTO jobTO = (JobTO)getCurrentRowObject();
		
		String status = "";
		
		if(Constants.JOB_STATUS_NOT_STARTED.equals(jobTO.getStatus())) {
			status = SpringServiceLocator.getMessage("job.status.not.started");
		}
		if(Constants.JOB_STATUS_STARTED.equals(jobTO.getStatus())) {
			status = SpringServiceLocator.getMessage("job.status.started");
		}
		if(Constants.JOB_STATUS_EXECUTED.equals(jobTO.getStatus())) {
			status = SpringServiceLocator.getMessage("job.status.executed");
		}
		
		return status;
	}
}
