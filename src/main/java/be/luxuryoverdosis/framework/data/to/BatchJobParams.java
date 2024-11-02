package be.luxuryoverdosis.framework.data.to;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "batch_job_params")
@Access(AccessType.FIELD)
@NamedQueries({
	@NamedQuery(name = BatchJobParams.SELECT_BATCH_JOB_PARAMS_BY_JOB_INSTANCE, query = BatchJobParams.Queries.SELECT_BATCH_JOB_PARAMS_BY_JOB_INSTANCE),
	@NamedQuery(name = BatchJobParams.SELECT_BATCH_JOB_PARAMS_BY_JOB_INSTANCE_AND_KEY_NAME, query = BatchJobParams.Queries.SELECT_BATCH_JOB_PARAMS_BY_JOB_INSTANCE_AND_KEY_NAME)
})
@Proxy(lazy = false)
public class BatchJobParams {
	public static final String SELECT_BATCH_JOB_PARAMS_BY_JOB_INSTANCE = "selectBatchJobParamsByJobInstance";
	public static final String SELECT_BATCH_JOB_PARAMS_BY_JOB_INSTANCE_AND_KEY_NAME = "selectBatchJobParamsByJobInstanceAndKeyName";
	
	@Id
	@Column(name = "Job_Instance_Id")
	private long id;
	
	@Column(name = "Type_Cd")
	private String typeCode;
	
	@Column(name = "Key_name")
	private String keyName;
	
	@Column(name = "String_Val")
	private String stringValue;
	
	@Column(name = "Date_Val")
	private Date dateValue;
	
	@Column(name = "Long_Val")
	private long longValue;
	
	@Column(name = "Double_Val")
	private double doubleValue;
	
	public long getId() {
		return id;
	}
	public void setId(final long id) {
		this.id = id;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(final String typeCode) {
		this.typeCode = typeCode;
	}
	public String getKeyName() {
		return keyName;
	}
	public void setKeyName(final String keyName) {
		this.keyName = keyName;
	}
	public String getStringValue() {
		return stringValue;
	}
	public void setStringValue(final String stringValue) {
		this.stringValue = stringValue;
	}
	public Date getDateValue() {
		return dateValue;
	}
	public void setDateValue(final Date dateValue) {
		this.dateValue = dateValue;
	}
	public long getLongValue() {
		return longValue;
	}
	public void setLongValue(final long longValue) {
		this.longValue = longValue;
	}
	public double getDoubleValue() {
		return doubleValue;
	}
	public void setDoubleValue(final double doubleValue) {
		this.doubleValue = doubleValue;
	}
	
	public static final class Queries {
		public static final String SELECT_BATCH_JOB_PARAMS_BY_JOB_INSTANCE = "from BatchJobParams bjp "
				+ "where bjp.id = :jobInstanceId";
		
		public static final String SELECT_BATCH_JOB_PARAMS_BY_JOB_INSTANCE_AND_KEY_NAME = "from BatchJobParams bjp "
				+ "where bjp.id = :jobInstanceId "
				+ "and bjp.keyName = :keyName";
	}
}
