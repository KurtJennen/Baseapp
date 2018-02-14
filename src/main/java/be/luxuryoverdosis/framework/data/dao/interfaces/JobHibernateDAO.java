package be.luxuryoverdosis.framework.data.dao.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.to.JobTO;

public interface JobHibernateDAO {
	public JobTO createOrUpdate(JobTO jobTO);
	public JobTO read(int id);
	public void delete(int id);
	
	public ArrayList<JobTO> list(String name);
	public ArrayList<JobTO> listStarted(String name);
	public ArrayList<JobTO> listNotStarted(String name);
}
