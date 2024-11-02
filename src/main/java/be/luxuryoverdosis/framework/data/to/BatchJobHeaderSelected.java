package be.luxuryoverdosis.framework.data.to;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "batch_job_hdr_selected")
@Access(AccessType.FIELD)
@Proxy(lazy = false)
public class BatchJobHeaderSelected extends BaseTO {
	@Column(name = "Job_Name")
	private String jobName;

	public String getJobName() {
		return jobName;
	}
	public void setJobName(final String jobName) {
		this.jobName = jobName;
	}
	
}
