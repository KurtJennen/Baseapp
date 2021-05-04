package be.luxuryoverdosis.framework.business.service.implementations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mail.javamail.JavaMailSender;

import be.luxuryoverdosis.baseapp.business.enumeration.RoleNameEnum;
import be.luxuryoverdosis.baseapp.business.service.SpringServiceLocator;
import be.luxuryoverdosis.framework.base.Config;
import be.luxuryoverdosis.framework.business.service.interfaces.MenuService;
import be.luxuryoverdosis.framework.business.service.interfaces.UserService;
import be.luxuryoverdosis.framework.data.dao.interfaces.RoleHibernateDAO;
import be.luxuryoverdosis.framework.data.dao.interfaces.UserHibernateDAO;
import be.luxuryoverdosis.framework.data.dto.UserDTO;
import be.luxuryoverdosis.framework.data.to.Role;
import be.luxuryoverdosis.framework.data.to.User;
import be.luxuryoverdosis.framework.mother.RoleMother;
import be.luxuryoverdosis.framework.mother.UserDTOMother;
import be.luxuryoverdosis.framework.mother.UserMother;
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
	private MenuService menuService;
	
	@Mock
	JavaMailSender javaMailSender;
	
	@Before
	public void before() {
		Config config = Config.getInstance();
		config.addKeyValue(javax.servlet.jsp.jstl.core.Config.FMT_LOCALE, "nl_BE");
		
		SpringServiceLocator.getSpringServiceLocator();
	}
	
	@Test
	public void testCreateOrUpdateDTOUserNotExists() {
		Role role = RoleMother.produceRole();
		User user = UserMother.produceUserRole();
		UserDTO userDTO = UserDTOMother.produceUserDTO();
		MimeMessage msg = new MimeMessage((Session)null);
		
		when(roleHibernateDAO.read(anyInt())).thenReturn(role);
		when(userHibernateDAO.count(anyString(), anyInt())).thenReturn(0L);
		when(userHibernateDAO.createOrUpdate(anyObject())).thenReturn(user);
		when(userHibernateDAO.read(anyInt())).thenReturn(user);
		when(javaMailSender.createMimeMessage()).thenReturn(msg);
		
		userServiceSpringImpl.createOrUpdateDTO(userDTO);
		
		verify(roleHibernateDAO).read(anyInt());
		verify(userHibernateDAO).count(anyString(), anyInt());
		verify(userHibernateDAO).createOrUpdate(anyObject());
		verify(userHibernateDAO).read(anyInt());
	}
	
	@Test
	public void testReadDTO() {
		User user = UserMother.produceUser();
		
		when(userHibernateDAO.read(anyInt())).thenReturn(user);
		
		UserDTO userDTO = userServiceSpringImpl.readDTO(anyInt());
		
		verify(userHibernateDAO).read(anyInt());
		
		assertThat(userDTO.getId()).isEqualTo(0);
	}
	
	@Test
	public void testCreateOrUpdate() {
		Role role = RoleMother.produceRole();
		User user = UserMother.produceUser();
		MimeMessage msg = new MimeMessage((Session)null);
		
		when(userHibernateDAO.count(anyString(), anyInt())).thenReturn(0L);
		when(roleHibernateDAO.readName(anyObject())).thenReturn(role);
		when(userHibernateDAO.createOrUpdate(anyObject())).thenReturn(user);
		when(javaMailSender.createMimeMessage()).thenReturn(msg);
		
		userServiceSpringImpl.createOrUpdate(user);
		
		verify(userHibernateDAO).count(anyString(), anyInt());
		verify(roleHibernateDAO).readName(RoleNameEnum.NORMALE_GEBRUIKER.getCode());
		verify(userHibernateDAO).createOrUpdate(user);
	}
	
	@Test
	public void testCreateOrUpdateExists() {
		try {
			User user = UserMother.produceUser();
			
			when(userHibernateDAO.count(anyString(), anyInt())).thenReturn(1L);
			
			userServiceSpringImpl.createOrUpdate(user);
			
			verify(userHibernateDAO).count(anyString(), anyInt());
		} catch (Exception e) {
			assertThat(e).isInstanceOf(ServiceException.class);
			assertThat(e.getMessage()).isEqualTo("Gebruiker record bestaat reeds");
		}
	}
	
	@Test
	public void testCreateOrUpdateDate() {
		Role role = RoleMother.produceRole();
		User user = UserMother.produceUserDate();
		MimeMessage msg = new MimeMessage((Session)null);
		
		when(userHibernateDAO.count(anyString(), anyInt())).thenReturn(0L);
		when(roleHibernateDAO.readName(anyObject())).thenReturn(role);
		when(userHibernateDAO.createOrUpdate(anyObject())).thenReturn(user);
		when(javaMailSender.createMimeMessage()).thenReturn(msg);
		
		userServiceSpringImpl.createOrUpdate(user);
		
		verify(userHibernateDAO).count(anyString(), anyInt());
		verify(roleHibernateDAO).readName(anyObject());
		verify(userHibernateDAO).createOrUpdate(user);
	}
	
	@Test
	public void testCreateOrUpdateRole() {
		User user = UserMother.produceUserRole();
		MimeMessage msg = new MimeMessage((Session)null);
		
		when(userHibernateDAO.count(anyString(), anyInt())).thenReturn(0L);
		when(userHibernateDAO.createOrUpdate(anyObject())).thenReturn(user);
		when(javaMailSender.createMimeMessage()).thenReturn(msg);
		
		userServiceSpringImpl.createOrUpdate(user);
		
		verify(userHibernateDAO).count(anyString(), anyInt());
		verify(userHibernateDAO).createOrUpdate(user);
	}
	
	@Test
	public void testCreateOrUpdateNull() {
		Role role = RoleMother.produceRole();
		User user = UserMother.produceUserDate();
		
		when(userHibernateDAO.count(anyString(), anyInt())).thenReturn(0L);
		when(roleHibernateDAO.readName(anyObject())).thenReturn(role);
		when(userHibernateDAO.createOrUpdate(anyObject())).thenReturn(null);
		
		userServiceSpringImpl.createOrUpdate(user);
		
		verify(userHibernateDAO).count(anyString(), anyInt());
		verify(roleHibernateDAO).readName(anyObject());
		verify(userHibernateDAO).createOrUpdate(user);
	}

	@Test
	public void testRead() {
		User user = UserMother.produceUser();
		
		when(userHibernateDAO.read(anyInt())).thenReturn(user);
		
		userServiceSpringImpl.read(anyInt());
		
		verify(userHibernateDAO).read(anyInt());
	}
	
	@Test
	public void testReadName() {
		User user = UserMother.produceUser();
		
		when(userHibernateDAO.readName(anyString())).thenReturn(user);
		
		userServiceSpringImpl.readName(anyString());
		
		verify(userHibernateDAO).readName(anyString());
	}
	
	@Test
	public void testDelete() {
		doNothing().when(menuService).deleteForUser(anyInt());
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
		ArrayList<User> arrayList = UserMother.produceListUser();
		
		when(userHibernateDAO.list()).thenReturn(arrayList);
		
		userServiceSpringImpl.list();
		
		verify(userHibernateDAO).list();
		
	}
	
	@Test
	public void testActivateYear() {
		Role role = RoleMother.produceRole();
		User user = UserMother.produceUserDate();
		
		when(userHibernateDAO.read(anyInt())).thenReturn(user);
		when(roleHibernateDAO.readName(anyObject())).thenReturn(role);
		
		userServiceSpringImpl.activate(anyInt(), UserService.YEAR);
		
		verify(userHibernateDAO).read(anyInt());
		verify(roleHibernateDAO).readName(anyObject());
	}
}
