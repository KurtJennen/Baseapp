package be.luxuryoverdosis.framework.data.to;

public class JobParamTO extends BaseTO {
	private String name;
	private String value;
	private JobTO job;
	
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
	public JobTO getJob() {
		return job;
	}
	public void setJob(JobTO job) {
		this.job = job;
	};	
	
}
