package be.luxuryoverdosis.framework.data.dao.implementations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import be.luxuryoverdosis.framework.data.dto.UserDTO;
import be.luxuryoverdosis.framework.data.to.User;
import be.luxuryoverdosis.framework.mother.UserDTOMother;
import be.luxuryoverdosis.framework.mother.UserMother;

@RunWith(MockitoJUnitRunner.class)
public class UserHibernateDAOMySQLImplTest {
	
	@InjectMocks
	private UserHibernateDAOMySQLImpl userHibernateDAOMySQLImpl;
	
	@Mock
	private SessionFactory sessionFactory;
	
	@Mock
	private Session session;
	
	@Mock
	private Query<User> userQuery;
	
	@Mock
	private Query<UserDTO> userDTOQuery;
	
	@Mock
	private Query<Long> longQuery;
	
	@Before
	public void before() {
		userHibernateDAOMySQLImpl.setSessionFactory(sessionFactory);
		when(userHibernateDAOMySQLImpl.getCurrentSession()).thenReturn(session);
	}
	
	@Test
	public void testCreateOrUpdate() {
		User user = UserMother.produceUser();
		
		doNothing().when(session).saveOrUpdate(user);
		
		userHibernateDAOMySQLImpl.createOrUpdate(user);
		
		verify(session).saveOrUpdate(user);
	}
	
	@Test
	public void testRead() {
		User user = UserMother.produceUser();
		
		when(session.load(User.class, user.getId())).thenReturn(user);
		
		userHibernateDAOMySQLImpl.read(user.getId());
		
		verify(session).load(User.class, user.getId());
	}
	
	@Test
	public void testDelete() {
		User user = UserMother.produceUser();
		
		when(session.load(User.class, user.getId())).thenReturn(user);
		doNothing().when(session).delete(user);
		
		userHibernateDAOMySQLImpl.delete(user.getId());
		
		verify(session).load(User.class, user.getId());
		verify(session).delete(user);
	}
	
	@Test
	public void testReadNameNull() {
		User user = UserMother.produceUser();
		ArrayList<User> arrayList = new ArrayList<User>();
		
		when(session.getNamedQuery(anyString())).thenReturn(userQuery);
		when(userQuery.list()).thenReturn(arrayList);
		
		userHibernateDAOMySQLImpl.readName(user.getName());
		
		verify(session).getNamedQuery(anyString());
		verify(userQuery).list();
	}
	
	@Test
	public void testReadNameNotNull() {
		User user = UserMother.produceUser();
		ArrayList<User> arrayList = UserMother.produceListUser();
		
		when(session.getNamedQuery(anyString())).thenReturn(userQuery);
		when(userQuery.list()).thenReturn(arrayList);
		
		userHibernateDAOMySQLImpl.readName(user.getName());
		
		verify(session).getNamedQuery(anyString());
		verify(userQuery).list();
	}
	
	@Test
	public void testList() {
		ArrayList<User> arrayList = UserMother.produceListUser();
		
		when(session.getNamedQuery(anyString())).thenReturn(userQuery);
		when(userQuery.list()).thenReturn(arrayList);
		
		userHibernateDAOMySQLImpl.list();
		
		verify(session).getNamedQuery(anyString());
		verify(userQuery).list();
	}
	
	@Test
	public void testListDTO() {
		ArrayList<UserDTO> arrayList = UserDTOMother.produceListUserDTO();
		
		when(session.getNamedQuery(anyString())).thenReturn(userDTOQuery);
		when(userDTOQuery.list()).thenReturn(arrayList);
		
		userHibernateDAOMySQLImpl.listDTO();
		
		verify(session).getNamedQuery(anyString());
		verify(userDTOQuery).list();
	}
	
	@Test
	public void testListDTOSearchValue() {
		ArrayList<UserDTO> arrayList = UserDTOMother.produceListUserDTO();
		
		when(session.getNamedQuery(anyString())).thenReturn(userDTOQuery);
		when(userDTOQuery.list()).thenReturn(arrayList);
		
		userHibernateDAOMySQLImpl.listDTO(arrayList.get(0).getName());
		
		verify(session).getNamedQuery(anyString());
		verify(userDTOQuery).list();
	}
	
	@Test
	public void testCount() {
		User user = UserMother.produceUser();
		ArrayList<Long> arrayList = UserDTOMother.produceListLong();
		
		when(session.getNamedQuery(anyString())).thenReturn(longQuery);
		when(longQuery.list()).thenReturn(arrayList);
		
		long count = userHibernateDAOMySQLImpl.count(user.getName(), user.getId());
		
		verify(session).getNamedQuery(anyString());
		verify(longQuery).list();
		
		assertThat(count).isEqualTo(0);
	}
	
	@Test
	public void testCountRoleId() {
		User user = UserMother.produceUserRole();
		ArrayList<Long> arrayList = UserDTOMother.produceListLong();
		
		when(session.getNamedQuery(anyString())).thenReturn(longQuery);
		when(longQuery.list()).thenReturn(arrayList);
		
		long count = userHibernateDAOMySQLImpl.count(user.getRole().getId());
		
		verify(session).getNamedQuery(anyString());
		verify(longQuery).list();
		
		assertThat(count).isEqualTo(0);
	}
}
