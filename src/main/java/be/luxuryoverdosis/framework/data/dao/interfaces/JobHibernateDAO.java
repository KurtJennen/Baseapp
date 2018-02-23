package be.luxuryoverdosis.framework.data.dao.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.to.Job;

public interface JobHibernateDAO {
	public Job createOrUpdate(Job job);
	public Job read(int id);
	public void delete(int id);
	
	public ArrayList<Job> list(String name);
	public ArrayList<Job> listStarted(String name);
	public ArrayList<Job> listNotStarted(String name);
}
