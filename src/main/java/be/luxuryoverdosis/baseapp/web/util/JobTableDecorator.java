package be.luxuryoverdosis.baseapp.web.util;

import java.text.ParseException;

import org.displaytag.decorator.TableDecorator;

import be.luxuryoverdosis.baseapp.Constants;
import be.luxuryoverdosis.baseapp.business.service.SpringServiceLocator;
import be.luxuryoverdosis.framework.base.tool.DateTool;
import be.luxuryoverdosis.framework.data.to.Job;

public class JobTableDecorator extends TableDecorator {	
	public String getStarted() throws ParseException {
		Job job = (Job)getCurrentRowObject();
		if(job.getStarted() != null) {
			return DateTool.formatUtilDateTime(job.getStarted());
		}
		
		return null;
	}
	
	public String getEnded() throws ParseException {
		Job job = (Job)getCurrentRowObject();
		if(job.getEnded() != null) {
			return DateTool.formatUtilDateTime(job.getEnded());
		}
		
		return null;
	}
	
	public String getStatus() throws ParseException {
		Job job = (Job)getCurrentRowObject();
		
		String status = "";
		
		if(Constants.JOB_STATUS_NOT_STARTED.equals(job.getStatus())) {
			status = SpringServiceLocator.getMessage("job.status.not.started");
		}
		if(Constants.JOB_STATUS_STARTED.equals(job.getStatus())) {
			status = SpringServiceLocator.getMessage("job.status.started");
		}
		if(Constants.JOB_STATUS_EXECUTED.equals(job.getStatus())) {
			status = SpringServiceLocator.getMessage("job.status.executed");
		}
		
		return status;
	}
}
