package be.luxuryoverdosis.framework.data.to;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Proxy;

import be.luxuryoverdosis.framework.base.tool.DateTool;

@Entity
@Table(name="batch_job_execution_params")
@Access(AccessType.FIELD)
@NamedQueries({
	@NamedQuery(name=BatchJobExecutionParams.SELECT_JOB_EXECUTION_PARAMS_BY_JOB_EXECUTION, query=BatchJobExecutionParams.Queries.SELECT_JOB_EXECUTION_PARAMS_BY_JOB_EXECUTION),
	@NamedQuery(name=BatchJobExecutionParams.SELECT_JOB_EXECUTION_PARAMS_BY_JOB_EXECUTION_AND_KEY_NAME, query=BatchJobExecutionParams.Queries.SELECT_JOB_EXECUTION_PARAMS_BY_JOB_EXECUTION_AND_KEY_NAME)
})
@Proxy(lazy=false)
public class BatchJobExecutionParams implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String SELECT_JOB_EXECUTION_PARAMS_BY_JOB_EXECUTION = "selectJobExecutionParamsByJobExecution";
	public static final String SELECT_JOB_EXECUTION_PARAMS_BY_JOB_EXECUTION_AND_KEY_NAME = "selectJobExecutionParamsByJobExecutionAndKeyName";
	
	@Id
	@Column(name="Job_Execution_Id")
	private long id;
	
	@Column(name="Type_Cd")
	private String typeCode;
	
	@Id
	@Column(name="Key_name")
	private String keyName;
	
	@Column(name="String_Val")
	private String stringValue;
	
	@Column(name="Date_Val")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateValue;
	
	@Column(name="Long_Val")
	private long longValue;
	
	@Column(name="Double_Val")
	private double doubleValue;
	
	@Column(name="Identifying")
	private String identifying;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getKeyName() {
		return keyName;
	}
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	public String getStringValue() {
		return stringValue;
	}
	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}
	public Date getDateValue() {
		return dateValue;
	}
	public void setDateValue(Date dateValue) {
		this.dateValue = dateValue;
	}
	public String getDateValueAsString() {
		return DateTool.formatUtilDateTime(dateValue);
	}
	public long getLongValue() {
		return longValue;
	}
	public void setLongValue(long longValue) {
		this.longValue = longValue;
	}
	public double getDoubleValue() {
		return doubleValue;
	}
	public void setDoubleValue(double doubleValue) {
		this.doubleValue = doubleValue;
	}
	public String getIdentifying() {
		return identifying;
	}
	public void setIdentifying(String identifying) {
		this.identifying = identifying;
	}
	
	public static final class Queries {
		public static final String SELECT_JOB_EXECUTION_PARAMS_BY_JOB_EXECUTION = "from BatchJobExecutionParams bep "
				+ "where bep.id = :jobExecutionId";
		
		public static final String SELECT_JOB_EXECUTION_PARAMS_BY_JOB_EXECUTION_AND_KEY_NAME = "from BatchJobExecutionParams bep "
				+ "where bep.id = :jobExecutionId "
				+ "and bep.keyName = :keyName";
	}
}
