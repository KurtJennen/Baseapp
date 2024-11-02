package be.luxuryoverdosis.framework.data.wrapperdto;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.dto.BatchJobInstanceDTO;

public class ListUserWrapperDTO {
	private ArrayList<Object> searchUserList;
	private ArrayList<BatchJobInstanceDTO> batchJobInstanceImportList = new ArrayList<BatchJobInstanceDTO>();
	private ArrayList<BatchJobInstanceDTO> batchJobInstanceExportList = new ArrayList<BatchJobInstanceDTO>();
	
	public ArrayList<Object> getSearchUserList() {
		return searchUserList;
	}
	public void setSearchUserList(final ArrayList<Object> searchUserList) {
		this.searchUserList = searchUserList;
	}
	public ArrayList<BatchJobInstanceDTO> getBatchJobInstanceImportList() {
		return batchJobInstanceImportList;
	}
	public void setBatchJobInstanceImportList(final ArrayList<BatchJobInstanceDTO> batchJobInstanceImportList) {
		this.batchJobInstanceImportList = batchJobInstanceImportList;
	}
	public ArrayList<BatchJobInstanceDTO> getBatchJobInstanceExportList() {
		return batchJobInstanceExportList;
	}
	public void setBatchJobInstanceExportList(final ArrayList<BatchJobInstanceDTO> batchJobInstanceExportList) {
		this.batchJobInstanceExportList = batchJobInstanceExportList;
	}
}
