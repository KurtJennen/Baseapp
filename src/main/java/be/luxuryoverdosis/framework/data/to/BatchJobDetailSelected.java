package be.luxuryoverdosis.framework.data.to;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "batch_job_dtl_selected")
@Access(AccessType.FIELD)
@NamedQueries({
	@NamedQuery(name = BatchJobDetailSelected.SELECT_BATCHJOBDETAILSELECTED_BY_BATCHJOBHEADERSELECTED, query = BatchJobDetailSelected.Queries.SELECT_BATCHJOBDETAILSELECTED_BY_BATCHJOBHEADERSELECTED),
	@NamedQuery(name = BatchJobDetailSelected.DELETE_BATCHJOBDETAILSELECTED_BY_BATCHJOBHEADERSELECTED, query = BatchJobDetailSelected.Queries.DELETE_BATCHJOBDETAILSELECTED_BY_BATCHJOBHEADERSELECTED)
})
@Proxy(lazy = false)
public class BatchJobDetailSelected extends BaseTO {
	public static final String SELECT_BATCHJOBDETAILSELECTED_BY_BATCHJOBHEADERSELECTED = "selectBatchJobDetailSelectedByBatchJobHeaderSelected";
	public static final String DELETE_BATCHJOBDETAILSELECTED_BY_BATCHJOBHEADERSELECTED = "deleteBatchJobDetailSelectedByBatchJobHeaderSelected";
	
	@ManyToOne
	@JoinColumn(name = "Batch_Job_Hdr_Selected_Id")
	private BatchJobHeaderSelected batchJobHeaderSelected;
	
	@Column(name = "Selected_id")
	private int selectedId;
	
	public BatchJobHeaderSelected getBatchJobHeaderSelected() {
		return batchJobHeaderSelected;
	}
	public void setBatchJobHeaderSelected(final BatchJobHeaderSelected batchJobHeaderSelected) {
		this.batchJobHeaderSelected = batchJobHeaderSelected;
	}
	public int getSelectedId() {
		return selectedId;
	}
	public void setSelectedId(final int selectedId) {
		this.selectedId = selectedId;
	}

	public static final class Queries {
		public static final String SELECT_BATCHJOBDETAILSELECTED_BY_BATCHJOBHEADERSELECTED = "select bjds.selectedId "
				+ "from BatchJobDetailSelected bjds "
				+ "where bjds.batchJobHeaderSelected.id = :jobHeaderSelectedId";
		
		public static final String DELETE_BATCHJOBDETAILSELECTED_BY_BATCHJOBHEADERSELECTED = "delete BatchJobDetailSelected bjds "
				+ "where bjds.batchJobHeaderSelected.id = :jobHeaderSelectedId";
	}
	
}
