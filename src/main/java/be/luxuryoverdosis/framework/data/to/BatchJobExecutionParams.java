package be.luxuryoverdosis.framework.data.to;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Proxy;

import be.luxuryoverdosis.framework.base.tool.DateTool;

@Entity
@Table(name = "batch_job_execution_params")
@Access(AccessType.FIELD)
@NamedQueries({
	@NamedQuery(name = BatchJobExecutionParams.SELECT_JOB_EXECUTION_PARAMS_BY_JOB_EXECUTION, query = BatchJobExecutionParams.Queries.SELECT_JOB_EXECUTION_PARAMS_BY_JOB_EXECUTION),
	@NamedQuery(name = BatchJobExecutionParams.SELECT_JOB_EXECUTION_PARAMS_BY_JOB_EXECUTION_AND_KEY_NAME, query = BatchJobExecutionParams.Queries.SELECT_JOB_EXECUTION_PARAMS_BY_JOB_EXECUTION_AND_KEY_NAME)
})
@Proxy(lazy = false)
public class BatchJobExecutionParams implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String SELECT_JOB_EXECUTION_PARAMS_BY_JOB_EXECUTION = "selectJobExecutionParamsByJobExecution";
	public static final String SELECT_JOB_EXECUTION_PARAMS_BY_JOB_EXECUTION_AND_KEY_NAME = "selectJobExecutionParamsByJobExecutionAndKeyName";
	
	@Id
	@ManyToOne
	@JoinColumn(name = "Job_Execution_Id")
	private BatchJobExecution batchJobExecution;
	
	@Column(name = "Type_Cd")
	private String typeCode;
	
	@Id
	@Column(name = "Key_name")
	private String keyName;
	
	@Column(name = "String_Val")
	private String stringValue;
	
	@Column(name = "Date_Val")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateValue;
	
	@Column(name = "Long_Val")
	private long longValue;
	
	@Column(name = "Double_Val")
	private double doubleValue;
	
	@Column(name = "Identifying")
	private String identifying;
	
	public BatchJobExecution getBatchJobExecution() {
		return batchJobExecution;
	}
	public void setBatchJobExecution(final BatchJobExecution batchJobExecution) {
		this.batchJobExecution = batchJobExecution;
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
	public String getDateValueAsString() {
		return DateTool.formatUtilDateTime(dateValue);
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
	public String getIdentifying() {
		return identifying;
	}
	public void setIdentifying(final String identifying) {
		this.identifying = identifying;
	}
	
	public static final class Queries {
		public static final String SELECT_JOB_EXECUTION_PARAMS_BY_JOB_EXECUTION = "from BatchJobExecutionParams bjep "
				+ "where bjep.batchJobExecution.id = :jobExecutionId";
		
		public static final String SELECT_JOB_EXECUTION_PARAMS_BY_JOB_EXECUTION_AND_KEY_NAME = "from BatchJobExecutionParams bjep "
				+ "where bjep.batchJobExecution.id = :jobExecutionId "
				+ "and bjep.keyName = :keyName";
	}
}
