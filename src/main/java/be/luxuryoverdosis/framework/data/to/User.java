package be.luxuryoverdosis.framework.data.to;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.hibernate.annotations.Proxy;

import be.luxuryoverdosis.framework.base.tool.adapter.DateAdapter;

@Entity
@Table(name = "base_user")
@Access(AccessType.FIELD)
@NamedQueries({
	@NamedQuery(name = User.SELECT_USERS, query = User.Queries.SELECT_USERS),
	@NamedQuery(name = User.SELECT_USERS_BY_NAME, query = User.Queries.SELECT_USERS_BY_NAME),
	@NamedQuery(name = User.SELECT_USERS_DTO, query = User.Queries.SELECT_USERS_DTO),
	@NamedQuery(name = User.SELECT_USERS_DTO_BY_NAME, query = User.Queries.SELECT_USERS_DTO_BY_NAME),
	@NamedQuery(name = User.COUNT_USERS_BY_NAME, query = User.Queries.COUNT_USERS_BY_NAME)
})
@Proxy(lazy = false)
@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class User extends BaseTO {
	public static final String SELECT_USERS = "selectUsers";
	public static final String SELECT_USERS_BY_NAME = "selectUsersByName";
	public static final String SELECT_USERS_DTO = "selectUsersDto";
	public static final String SELECT_USERS_DTO_BY_NAME = "selectUsersDtoByName";
	public static final String COUNT_USERS_BY_NAME = "countUsersByName";
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "UserName")
	private String userName;
	
	@Column(name = "Password")
	private String encryptedPassword;
	
	@Column(name = "Email")
	private String email;
	
	@Column(name = "DateExp")
	@XmlJavaTypeAdapter(value = DateAdapter.class)
	private Date dateExpiration;
	
	public User() {
		super();
	}
	
	public String getName() {
		return name;
	}
	public void setName(final String name) {
		this.name = name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(final String userName) {
		this.userName = userName;
	}
	public String getEncryptedPassword() {
		return encryptedPassword;
	}
	public void setEncryptedPassword(final String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(final String email) {
		this.email = email;
	}
	public Date getDateExpiration() {
		return dateExpiration;
	}
	public void setDateExpiration(final Date dateExpiration) {
		this.dateExpiration = dateExpiration;
	}

	public static final class Queries {
        public static final String SELECT_USERS = "from User u "
                + "order by u.name";
		
		public static final String SELECT_USERS_BY_NAME = "from User u "
				+ "where u.name = :name";
		
		public static final String SELECT_USERS_DTO = "select new be.luxuryoverdosis.framework.data.dto.UserDTO("
				+ "u.id, "
				+ "u.name, "
				+ "u.userName, "
				+ "u.email,"
				+ "u.dateExpiration "
				+ ") "
				+ "from User u "
		        + "order by u.name";
		
		public static final String SELECT_USERS_DTO_BY_NAME = "select new be.luxuryoverdosis.framework.data.dto.UserDTO("
				+ "u.id, "
				+ "u.name, "
				+ "u.userName, "
				+ "u.email,"
				+ "u.dateExpiration "
				+ ") "
				+ "from User u "
				+ "where u.name like :name "
				+ "order by u.name";
		
		public static final String COUNT_USERS_BY_NAME = "select count(*) "
				+ "from User u "
				+ "where u.name = :name "
				+ "and u.id != :id";
		
	}

}
