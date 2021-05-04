package be.luxuryoverdosis.framework.data.to;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name="base_user_role")
@Access(AccessType.FIELD)
@NamedQueries({
	@NamedQuery(name=UserRole.SELECT_USERROLES_DTO_BY_USER, query=UserRole.Queries.SELECT_USERROLES_DTO_BY_USER),
	@NamedQuery(name=UserRole.COUNT_USERROLES_BY_USER, query=UserRole.Queries.COUNT_USERROLES_BY_USER),
	@NamedQuery(name=UserRole.COUNT_USERROLES_BY_ROLE, query=UserRole.Queries.COUNT_USERROLES_BY_ROLE),
	@NamedQuery(name=UserRole.DELETE_USERROLES_BY_USER_AND_ROLE, query=UserRole.Queries.DELETE_USERROLES_BY_USER_AND_ROLE)
})
@Proxy(lazy=false)
@XmlType
public class UserRole extends BaseTO {
	public static final String SELECT_USERROLES_DTO_BY_USER = "selectUserRolesDtoByUser";
	public static final String COUNT_USERROLES_BY_USER = "countUserRolesByUser";
	public static final String COUNT_USERROLES_BY_ROLE = "countUserRolesByRole";
	public static final String DELETE_USERROLES_BY_USER_AND_ROLE = "deleteUserRolesByUserAndRole";
	
	@ManyToOne
	@JoinColumn(name="User_Id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="Role_Id")
	private Role role;
	
	public UserRole() {
		super();
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	public static final class Queries {
		public static final String SELECT_USERROLES_DTO_BY_USER = "select new be.luxuryoverdosis.framework.data.dto.UserRoleDTO( "
        		+ "ur.id, "
        		+ "r.id, "
        		+ "r.name "
        		+ ") "
        		+ "from UserRole ur "
        		+ "inner join ur.user u "
        		+ "inner join ur.role r "
                + "where u.id = :userId "
                + "order by r.name";
		
		public static final String COUNT_USERROLES_BY_USER = "select count(*) "
				+ "from UserRole ur "
				+ "where ur.user.id = :userId ";
		
		public static final String COUNT_USERROLES_BY_ROLE = "select count(*) "
				+ "from UserRole ur "
				+ "where ur.role.id = :roleId ";
		
		public static final String DELETE_USERROLES_BY_USER_AND_ROLE = "delete "
				+ "from UserRole ur "
				+ "where ur.user.id = :userId "
				+ "and ur.role.id = :roleId ";
	}

}
