package be.luxuryoverdosis.framework.data.wrapperdto;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.to.BatchJobInstance;

public class ListUserWrapperDTO {
	private ArrayList<Object> searchUserList;
	private ArrayList<BatchJobInstance> batchJobInstanceImportList;
	private ArrayList<BatchJobInstance> batchJobInstanceExportList;
	
	public ArrayList<Object> getSearchUserList() {
		return searchUserList;
	}
	public void setSearchUserList(ArrayList<Object> searchUserList) {
		this.searchUserList = searchUserList;
	}
	public ArrayList<BatchJobInstance> getBatchJobInstanceImportList() {
		return batchJobInstanceImportList;
	}
	public void setBatchJobInstanceImportList(ArrayList<BatchJobInstance> batchJobInstanceImportList) {
		this.batchJobInstanceImportList = batchJobInstanceImportList;
	}
	public ArrayList<BatchJobInstance> getBatchJobInstanceExportList() {
		return batchJobInstanceExportList;
	}
	public void setBatchJobInstanceExportList(ArrayList<BatchJobInstance> batchJobInstanceExportList) {
		this.batchJobInstanceExportList = batchJobInstanceExportList;
	}
}
