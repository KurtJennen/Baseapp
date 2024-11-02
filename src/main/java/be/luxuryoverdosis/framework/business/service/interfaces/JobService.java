package be.luxuryoverdosis.framework.business.service.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.to.Job;
import be.luxuryoverdosis.framework.data.wrapperdto.DetailJobWrapperDTO;

public interface JobService {
	Job createOrUpdate(Job job);
	Job read(int id);
	void delete(int jobId);
	void delete(int[] jobInstanceId);
	Job downloadFile(int jobInstanceId);
	
	ArrayList<Job> list(String name);
	ArrayList<Job> list(String name, boolean started);
	
	DetailJobWrapperDTO getDetailJobWrapperDTO(int jobInstanceId);
	
	void startJobs(ArrayList<Job> jobs);
	void endJobs(ArrayList<Job> jobs);
}
