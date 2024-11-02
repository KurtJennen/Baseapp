package be.luxuryoverdosis.framework.data.wrapperdto;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.to.BatchJobExecutionParams;
import be.luxuryoverdosis.framework.data.to.BatchJobParams;
import be.luxuryoverdosis.framework.data.to.BatchStepExecution;
import be.luxuryoverdosis.framework.data.to.JobLog;

public class DetailJobWrapperDTO {
	private byte[] fileData;
	private String fileName;
	private String jobName;
	private ArrayList<BatchJobParams> batchJobParamsList = new ArrayList<BatchJobParams>();
	private ArrayList<BatchJobExecutionParams> batchJobExecutionParamsList = new ArrayList<BatchJobExecutionParams>();
	private ArrayList<BatchStepExecution> batchStepExecutionList = new ArrayList<BatchStepExecution>();
	private ArrayList<JobLog> jobLogList;
	
	public byte[] getFileData() {
		return fileData;
	}
	public void setFileData(final byte[] fileData) {
		this.fileData = fileData;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(final String fileName) {
		this.fileName = fileName;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(final String jobName) {
		this.jobName = jobName;
	}
	public ArrayList<BatchJobParams> getBatchJobParamsList() {
		return batchJobParamsList;
	}
	public void setBatchJobParamsList(final ArrayList<BatchJobParams> batchJobParamsList) {
		this.batchJobParamsList = batchJobParamsList;
	}
	public ArrayList<BatchJobExecutionParams> getBatchJobExecutionParamsList() {
		return batchJobExecutionParamsList;
	}
	public void setBatchJobExecutionParamsList(final ArrayList<BatchJobExecutionParams> batchJobExecutionParamsList) {
		this.batchJobExecutionParamsList = batchJobExecutionParamsList;
	}
	public ArrayList<BatchStepExecution> getBatchStepExecutionList() {
		return batchStepExecutionList;
	}
	public void setBatchStepExecutionList(final ArrayList<BatchStepExecution> batchStepExecutionList) {
		this.batchStepExecutionList = batchStepExecutionList;
	}
	public ArrayList<JobLog> getJobLogList() {
		return jobLogList;
	}
	public void setJobLogList(final ArrayList<JobLog> jobLogList) {
		this.jobLogList = jobLogList;
	}
}
