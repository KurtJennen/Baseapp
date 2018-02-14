package be.luxuryoverdosis.framework.business.service.implementations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import be.luxuryoverdosis.Constants;
import be.luxuryoverdosis.baseapp.business.service.SpringServiceLocator;
import be.luxuryoverdosis.framework.base.Config;
import be.luxuryoverdosis.framework.business.service.interfaces.UserService;
import be.luxuryoverdosis.framework.data.dao.interfaces.MenuHibernateDAO;
import be.luxuryoverdosis.framework.data.dao.interfaces.RoleHibernateDAO;
import be.luxuryoverdosis.framework.data.dao.interfaces.UserHibernateDAO;
import be.luxuryoverdosis.framework.data.to.RoleTO;
import be.luxuryoverdosis.framework.data.to.UserTO;
import be.luxuryoverdosis.framework.mother.RoleTOMother;
import be.luxuryoverdosis.framework.mother.UserTOMother;
import be.luxuryoverdosis.framework.web.exception.ServiceException;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceSpringImplTest {
	
	@InjectMocks
	private UserServiceSpringImpl userServiceSpringImpl;
	
	@Mock
	private UserHibernateDAO userHibernateDAO;
	
	@Mock
	private RoleHibernateDAO roleHibernateDAO;
	
	@Mock
	private MenuHibernateDAO menuHibernateDAO;
	
	@Before
	public void before() {
		Config config = Config.getInstance();
		config.addKeyValue(javax.servlet.jsp.jstl.core.Config.FMT_LOCALE, "nl_BE");
		
		SpringServiceLocator.getSpringServiceLocator();
	}
	
	@Test
	public void testcreateOrUpdate() {
		RoleTO roleTO = RoleTOMother.produceRoleTO();
		
		UserTO userTO = UserTOMother.produceUserTO();
		
		when(userHibernateDAO.count(anyString(), anyInt())).thenReturn(0L);
		when(roleHibernateDAO.readName(anyString())).thenReturn(roleTO);
		when(userHibernateDAO.createOrUpdate(userTO)).thenReturn(userTO);
		
		userServiceSpringImpl.createOrUpdate(userTO);
		
		verify(userHibernateDAO).count(anyString(), anyInt());
		verify(roleHibernateDAO).readName(Constants.ROLE_NORMALE_GEBRUIKER);
		verify(userHibernateDAO).createOrUpdate(userTO);
	}
	
	@Test
	public void testcreateOrUpdateExists() {
		try {
			UserTO userTO = UserTOMother.produceUserTO();
			
			when(userHibernateDAO.count(anyString(), anyInt())).thenReturn(1L);
			
			userServiceSpringImpl.createOrUpdate(userTO);
			
			verify(userHibernateDAO).count(anyString(), anyInt());
		} catch (Exception e) {
			assertThat(e).isInstanceOf(ServiceException.class);
			assertThat(e.getMessage()).isEqualTo("Gebruiker record bestaat reeds");
		}
	}
	
	@Test
	public void testcreateOrUpdateDate() {
		RoleTO roleTO = RoleTOMother.produceRoleTO();
		
		UserTO userTO = UserTOMother.produceUserTODate();
		
		when(userHibernateDAO.count(anyString(), anyInt())).thenReturn(0L);
		when(roleHibernateDAO.readName(anyString())).thenReturn(roleTO);
		when(userHibernateDAO.createOrUpdate(userTO)).thenReturn(userTO);
		
		userServiceSpringImpl.createOrUpdate(userTO);
		
		verify(userHibernateDAO).count(anyString(), anyInt());
		verify(roleHibernateDAO).readName(anyString());
		verify(userHibernateDAO).createOrUpdate(userTO);
	}
	
	@Test
	public void testcreateOrUpdateRole() {
		UserTO userTO = UserTOMother.produceUserTORole();
		
		when(userHibernateDAO.count(anyString(), anyInt())).thenReturn(0L);
		when(userHibernateDAO.createOrUpdate(userTO)).thenReturn(userTO);
		
		userServiceSpringImpl.createOrUpdate(userTO);
		
		verify(userHibernateDAO).count(anyString(), anyInt());
		verify(userHibernateDAO).createOrUpdate(userTO);
	}
	
	@Test
	public void testcreateOrUpdateNull() {
		RoleTO roleTO = RoleTOMother.produceRoleTO();
		UserTO userTO = UserTOMother.produceUserTODate();
		
		when(userHibernateDAO.count(anyString(), anyInt())).thenReturn(0L);
		when(roleHibernateDAO.readName(anyString())).thenReturn(roleTO);
		when(userHibernateDAO.createOrUpdate(userTO)).thenReturn(null);
		
		userServiceSpringImpl.createOrUpdate(userTO);
		
		verify(userHibernateDAO).count(anyString(), anyInt());
		verify(roleHibernateDAO).readName(anyString());
		verify(userHibernateDAO).createOrUpdate(userTO);
	}

	@Test
	public void testRead() {
		UserTO userTO = UserTOMother.produceUserTO();
		
		when(userHibernateDAO.read(anyInt())).thenReturn(userTO);
		
		userServiceSpringImpl.read(anyInt());
		
		verify(userHibernateDAO).read(anyInt());
	}
	
	@Test
	public void testReadName() {
		UserTO userTO = UserTOMother.produceUserTO();
		
		when(userHibernateDAO.readName(anyString())).thenReturn(userTO);
		
		userServiceSpringImpl.readName(anyString());
		
		verify(userHibernateDAO).readName(anyString());
	}
	
	@Test
	public void testDelete() {
		doNothing().when(menuHibernateDAO).deleteForUser(anyInt());
		doNothing().when(userHibernateDAO).delete(anyInt());
		
		userServiceSpringImpl.delete(anyInt());
		
		verify(userHibernateDAO).delete(anyInt());
	}
	
	@Test
	public void testCount() {
		when(userHibernateDAO.count(anyString(), anyInt())).thenReturn(0L);
		
		userServiceSpringImpl.count(anyString(), anyInt());
		
		verify(userHibernateDAO).count(anyString(), anyInt());
	}
	
	@Test
	public void testList() {
		ArrayList<UserTO> arrayList = UserTOMother.produceListUserTO();
		
		when(userHibernateDAO.list()).thenReturn(arrayList);
		
		userServiceSpringImpl.list();
		
		verify(userHibernateDAO).list();
		
	}
	
	@Test
	public void testActivateYear() {
		RoleTO roleTO = RoleTOMother.produceRoleTO();
		UserTO userTO = UserTOMother.produceUserTODate();
		
		when(userHibernateDAO.read(anyInt())).thenReturn(userTO);
		when(roleHibernateDAO.readName(anyString())).thenReturn(roleTO);
		
		userServiceSpringImpl.activate(anyInt(), UserService.YEAR);
		
		verify(userHibernateDAO).read(anyInt());
		verify(roleHibernateDAO).readName(anyString());
	}
}
