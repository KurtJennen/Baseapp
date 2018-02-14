package be.luxuryoverdosis.framework.business.service.implementations;

import junit.framework.TestCase;

import org.easymock.EasyMock;
import org.junit.Ignore;

import be.luxuryoverdosis.framework.data.dao.interfaces.UserHibernateDAO;

@Ignore
public class UserServiceSpringImplTestEasyMock extends TestCase {
	private UserServiceSpringImpl userServiceSpringImpl;
	private UserHibernateDAO userHibernateDAO;

	@Override
	protected void setUp() throws Exception {
		userServiceSpringImpl = new UserServiceSpringImpl();
		userHibernateDAO = EasyMock.createStrictMock(UserHibernateDAO.class);
	}

}
