package be.luxuryoverdosis.framework.data.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

public abstract class AbstractHibernateDaoSupport extends HibernateDaoSupport {
	@Resource
	private SessionFactory sessionFactory;
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@PostConstruct
	public void doAfter() {
		setSessionFactory(sessionFactory);
		//setHibernateTemplate(new HibernateTemplate(sessionFactory));
	}
	
	public Session getCurrentSession() {
		return getSessionFactory().getCurrentSession();
	}
	
}
