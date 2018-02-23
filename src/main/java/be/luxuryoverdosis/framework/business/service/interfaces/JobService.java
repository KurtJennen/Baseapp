package be.luxuryoverdosis.framework.business.service.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.to.Job;

public interface JobService {
	public Job createOrUpdate(Job job);
	public Job read(int id);
	public void delete(int id);
	public Job downloadFile(int jobInstanceId);
	
	public ArrayList<Job> list(String name);
	public ArrayList<Job> list(String name, boolean started);
	
	public void startJobs(ArrayList<Job> jobs);
	public void endJobs(ArrayList<Job> jobs);
}
