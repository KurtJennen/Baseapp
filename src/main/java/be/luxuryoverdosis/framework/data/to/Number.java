package be.luxuryoverdosis.framework.data.to;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name="base_number")
@Access(AccessType.FIELD)
@NamedQueries({
	@NamedQuery(name=Number.SELECT_NUMBERS, query=Number.Queries.SELECT_NUMBERS),
	@NamedQuery(name=Number.SELECT_NUMBERS_BY_APPLICATION_CODE_AND_YEAR_AND_TYPE, query=Number.Queries.SELECT_NUMBERS_BY_APPLICATION_CODE_AND_YEAR_AND_TYPE),
	@NamedQuery(name=Number.COUNT_NUMBERS_BY_APPLICATION_CODE_AND_YEAR_AND_TYPE, query=Number.Queries.COUNT_NUMBERS_BY_APPLICATION_CODE_AND_YEAR_AND_TYPE),
})
@NamedNativeQueries({
	@NamedNativeQuery(name=Number.SELECT_NUMBERS_BY_APPLICATION_CODE_AND_YEAR_AND_TYPE_FOR_UPDATE, query=Number.NativeQueries.SELECT_NUMBERS_BY_APPLICATION_CODE_AND_YEAR_AND_TYPE_FOR_UPDATE),
	@NamedNativeQuery(name=Number.UPDATE_NUMBERS_BY_APPLICATION_CODE_AND_YEAR_AND_TYPE, query=Number.NativeQueries.UPDATE_NUMBERS_BY_APPLICATION_CODE_AND_YEAR_AND_TYPE)
})
@Proxy(lazy=false)
public class Number extends BaseTO {
	public static final String SELECT_NUMBERS = "selectNumbers";
	public static final String SELECT_NUMBERS_BY_APPLICATION_CODE_AND_YEAR_AND_TYPE = "selectNumbersByApplicationCodeAndYearAndType";
	public static final String SELECT_NUMBERS_BY_APPLICATION_CODE_AND_YEAR_AND_TYPE_FOR_UPDATE = "selectNumbersByApplicationCodeAndYearAndTypeForUpdate";
	public static final String UPDATE_NUMBERS_BY_APPLICATION_CODE_AND_YEAR_AND_TYPE = "updateNumbersByApplicationCodeAndYearAndType";
	public static final String COUNT_NUMBERS_BY_APPLICATION_CODE_AND_YEAR_AND_TYPE = "countNumbersByApplicationCodeAndYearAndType";
	
	@Column(name="AppCode")
	private String applicationCode;
	
	@Column(name="Year")
	private String year;
	
	@Column(name="Number")
	private int number;
	
	@Column(name="Type")
	private String type;
	
	public String getApplicationCode() {
		return applicationCode;
	}
	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public static final class Queries {
		public static final String SELECT_NUMBERS = "from Number n";
		
		public static final String SELECT_NUMBERS_BY_APPLICATION_CODE_AND_YEAR_AND_TYPE = "from Number n "
				+ " where n.applicationCode = :applicationCode "
				+ " and n.year = :year "
				+ " and n.type = :type";
		
		public static final String COUNT_NUMBERS_BY_APPLICATION_CODE_AND_YEAR_AND_TYPE = "select count(*) "
				+ "from Number n "
				+ "where n.applicationCode = :applicationCode "
				+ "and n.year = :year "
				+ "and n.type = :type "
				+ "and n.id != :id";
		
	}
	
	public static final class NativeQueries {
		public static final String SELECT_NUMBERS_BY_APPLICATION_CODE_AND_YEAR_AND_TYPE_FOR_UPDATE = "select number "
				+ "from base_number "
				+ "where appcode = :applicationCode "
				+ "and year = :year "
				+ "and type = :type "
				+ "for update";
		
		public static final String UPDATE_NUMBERS_BY_APPLICATION_CODE_AND_YEAR_AND_TYPE = "update base_number "
				+ "set number = :number "
				+ "where appcode = :applicationCode "
				+ "and year = :year "
				+ "and type = :type";
	}
}
