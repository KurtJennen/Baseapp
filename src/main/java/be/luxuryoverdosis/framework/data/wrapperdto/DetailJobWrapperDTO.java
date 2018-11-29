package be.luxuryoverdosis.framework.data.wrapperdto;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.to.BatchJobExecutionParams;
import be.luxuryoverdosis.framework.data.to.BatchJobParams;
import be.luxuryoverdosis.framework.data.to.BatchStepExecution;
import be.luxuryoverdosis.framework.data.to.JobLog;

public class DetailJobWrapperDTO {
	byte[] fileData;
	private String fileName;
	private String jobName;
	private ArrayList<BatchJobParams> batchJobParamsList;
	private ArrayList<BatchJobExecutionParams> batchJobExecutionParamsList;
	private ArrayList<BatchStepExecution> batchStepExecutionList;
	private ArrayList<JobLog> jobLogList;
	
	public byte[] getFileData() {
		return fileData;
	}
	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public ArrayList<BatchJobParams> getBatchJobParamsList() {
		return batchJobParamsList;
	}
	public void setBatchJobParamsList(ArrayList<BatchJobParams> batchJobParamsList) {
		this.batchJobParamsList = batchJobParamsList;
	}
	public ArrayList<BatchJobExecutionParams> getBatchJobExecutionParamsList() {
		return batchJobExecutionParamsList;
	}
	public void setBatchJobExecutionParamsList(ArrayList<BatchJobExecutionParams> batchJobExecutionParamsList) {
		this.batchJobExecutionParamsList = batchJobExecutionParamsList;
	}
	public ArrayList<BatchStepExecution> getBatchStepExecutionList() {
		return batchStepExecutionList;
	}
	public void setBatchStepExecutionList(ArrayList<BatchStepExecution> batchStepExecutionList) {
		this.batchStepExecutionList = batchStepExecutionList;
	}
	public ArrayList<JobLog> getJobLogList() {
		return jobLogList;
	}
	public void setJobLogList(ArrayList<JobLog> jobLogList) {
		this.jobLogList = jobLogList;
	}
}
