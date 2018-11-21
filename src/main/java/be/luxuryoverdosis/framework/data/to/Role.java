package be.luxuryoverdosis.framework.data.to;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name="base_role")
@Access(AccessType.FIELD)
@NamedQueries({
	@NamedQuery(name=Role.SELECT_ROLES, query=Role.Queries.SELECT_ROLES),
	@NamedQuery(name=Role.SELECT_ROLES_BY_NAME, query=Role.Queries.SELECT_ROLES_BY_NAME),
	@NamedQuery(name=Role.SELECT_ROLES_DTO_BY_NAME, query=Role.Queries.SELECT_ROLES_DTO_BY_NAME),
	@NamedQuery(name=Role.COUNT_ROLES_BY_NAME, query=Role.Queries.COUNT_ROLES_BY_NAME)
})
@Proxy(lazy=false)
public class Role extends BaseTO {
	public static final String SELECT_ROLES = "selectRoles";
	public static final String SELECT_ROLES_BY_NAME = "selectRolesByName";
	public static final String SELECT_ROLES_DTO_BY_NAME = "selectRolesDtoByName";
	public static final String COUNT_ROLES_BY_NAME = "countRolesByName";
	
	@Column(name="Name")
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public static final class Queries {
		public static final String SELECT_ROLES = "from Role r";
		
		public static final String SELECT_ROLES_BY_NAME = "from Role r "
				+ "where r.name = :name";
		
		public static final String SELECT_ROLES_DTO_BY_NAME = "select new be.luxuryoverdosis.framework.data.dto.RoleDTO( "
				+ "r.id, "
				+ "r.name "
				+ ") "
				+ "from Role r "
				+ "where name like :name";
		
		public static final String COUNT_ROLES_BY_NAME = "select count(*) "
				+ "from Role r "
				+ "where r.name = :name "
				+ "and r.id != :id";
	}
}
