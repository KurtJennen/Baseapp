package be.luxuryoverdosis.framework.data.to;

import java.sql.Blob;

public class JobLogTO extends BaseTO {
	private String input;
	private String output;
	private Blob file;
	private byte[] fileData;
	private JobTO job;
	
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	public String getOutput() {
		return output;
	}
	public void setOutput(String output) {
		this.output = output;
	}
	public Blob getFile() {
		return file;
	}
	public void setFile(Blob file) {
		this.file = file;
	}
	public JobTO getJob() {
		return job;
	}
	public byte[] getFileData() {
		return fileData;
	}
	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}
	public void setJob(JobTO job) {
		this.job = job;
	};	
	
}
