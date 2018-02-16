package be.luxuryoverdosis.framework.business.service.implementations;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.luxuryoverdosis.framework.business.service.interfaces.JobParamService;
import be.luxuryoverdosis.framework.data.dao.interfaces.JobParamHibernateDAO;
import be.luxuryoverdosis.framework.data.to.JobParam;
import be.luxuryoverdosis.framework.logging.Logging;

@Service
public class JobParamServiceSpringImpl implements JobParamService {
	@Resource
	private JobParamHibernateDAO jobParamHibernateDAO;
	
	@Transactional
	public JobParam createOrUpdate(final JobParam jobParam) {
		Logging.info(this, "Begin createJobParam");
		JobParam result = null;
		result = jobParamHibernateDAO.createOrUpdate(jobParam);
		Logging.info(this, "End createJobParam");
		return result;
	}
	
	@Transactional(readOnly=true)
	public JobParam read(final int id) {
		Logging.info(this, "Begin readJobParam");
		JobParam result = null;
		result = jobParamHibernateDAO.read(id);
		Logging.info(this, "End readJobParam");
		return result;
	}

	@Transactional
	public void delete(final int id) {
		Logging.info(this, "Begin deleteJobParam");
		jobParamHibernateDAO.delete(id);
		Logging.info(this, "End deleteJobParam");
	}
	
	@Transactional
	public void deleteForJob(final int jobId) {
		Logging.info(this, "Begin deleteForJobJobParam");
		jobParamHibernateDAO.deleteForJob(jobId);
		Logging.info(this, "End deleteForJobJobParam");
	}

	@Transactional(readOnly=true)
	public ArrayList<JobParam> list(final int jobId) {
		Logging.info(this, "Begin listJobParam");
		ArrayList<JobParam> arrayList = null;
		arrayList = jobParamHibernateDAO.list(jobId);
		Logging.info(this, "End listJobParam");
		return arrayList;
	}
}
