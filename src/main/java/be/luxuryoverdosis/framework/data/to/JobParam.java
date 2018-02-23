package be.luxuryoverdosis.framework.data.to;

public class JobParam extends BaseTO {
	private String name;
	private String value;
	private Job job;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	};	
	
}
