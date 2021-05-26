package be.luxuryoverdosis.framework.data.to;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name="base_user")
@Access(AccessType.FIELD)
@NamedQueries({
	@NamedQuery(name=User.SELECT_USERS, query=User.Queries.SELECT_USERS),
	@NamedQuery(name=User.SELECT_USERS_BY_NAME, query=User.Queries.SELECT_USERS_BY_NAME),
	@NamedQuery(name=User.SELECT_USERS_DTO, query=User.Queries.SELECT_USERS_DTO),
	@NamedQuery(name=User.SELECT_USERS_DTO_BY_NAME, query=User.Queries.SELECT_USERS_DTO_BY_NAME),
	@NamedQuery(name=User.COUNT_USERS_BY_NAME, query=User.Queries.COUNT_USERS_BY_NAME)
})
@Proxy(lazy=false)
@XmlType
public class User extends BaseTO {
	public static final String SELECT_USERS = "selectUsers";
	public static final String SELECT_USERS_BY_NAME = "selectUsersByName";
	public static final String SELECT_USERS_DTO = "selectUsersDto";
	public static final String SELECT_USERS_DTO_BY_NAME = "selectUsersDtoByName";
	public static final String COUNT_USERS_BY_NAME = "countUsersByName";
	
	@Column(name="Name")
	private String name;
	
	@Column(name="UserName")
	private String userName;
	
	@Column(name="Password")
	private String encryptedPassword;
	
	@Column(name="Email")
	private String email;
	
	@Column(name="DateExp")
	private Date dateExpiration;
	
	public User() {
		super();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEncryptedPassword() {
		return encryptedPassword;
	}
	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDateExpiration() {
		return dateExpiration;
	}
	public void setDateExpiration(Date dateExpiration) {
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
				+ "u.email "
				+ ") "
				+ "from User u "
		        + "order by u.name";
		
		public static final String SELECT_USERS_DTO_BY_NAME = "select new be.luxuryoverdosis.framework.data.dto.UserDTO("
				+ "u.id, "
				+ "u.name, "
				+ "u.userName, "
				+ "u.email "
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
