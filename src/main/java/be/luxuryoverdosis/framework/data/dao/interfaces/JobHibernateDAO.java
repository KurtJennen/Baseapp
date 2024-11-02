package be.luxuryoverdosis.framework.data.dao.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.to.Job;

public interface JobHibernateDAO {
	Job createOrUpdate(Job job);
	Job read(int id);
	void delete(int id);
	
	ArrayList<Job> list(String name);
	ArrayList<Job> listStarted(String name);
	ArrayList<Job> listNotStarted(String name);
}
