package be.luxuryoverdosis.framework.data.to;

import java.sql.Blob;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name="base_document")
@Access(AccessType.FIELD)
@NamedQueries({
	@NamedQuery(name=Document.SELECT_DOCUMENTS, query=Document.Queries.SELECT_DOCUMENTS),
	@NamedQuery(name=Document.SELECT_DOCUMENTS_BY_TYPE, query=Document.Queries.SELECT_DOCUMENTS_BY_TYPE),
	@NamedQuery(name=Document.COUNT_DOCUMENTS_BY_TYPE_AND_FILENAME, query=Document.Queries.COUNT_DOCUMENTS_BY_TYPE_AND_FILENAME)
})
@Proxy(lazy=false)
public class Document extends BaseTO {
	public static final String SELECT_DOCUMENTS = "selectDocuments";
	public static final String SELECT_DOCUMENTS_BY_TYPE = "selectDocumentsByType";
	public static final String COUNT_DOCUMENTS_BY_TYPE_AND_FILENAME = "countDocumentsByTypeAndFilename";
	
	@Column(name="type")
	private String type;
	
	@Column(name="Filename")
	private String fileName;
	
	@Column(name="File")
	private Blob file;
	
	@Transient
	private byte[] fileData;
	
	@Column(name="Filesize")
	private int fileSize;
	
	@Column(name="Contenttype")
	private String contentType;
	
	public Document() {
		super();
	}
	
	public Document(int id, String type, String fileName, int fileSize, String contentType) {
		super();
		setId(id);
		this.type = type;
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.contentType = contentType;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Blob getFile() {
		return file;
	}
	public void setFile(Blob file) {
		this.file = file;
	}
	public byte[] getFileData() {
		return fileData;
	}
	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}
	public int getFileSize() {
		return fileSize;
	}
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	public static final class Queries {
		public static final String SELECT_DOCUMENTS = "select new be.luxuryoverdosis.framework.data.to.Document( "
				+ "d.id, "
				+ "d.type, "
				+ "d.fileName, "
				+ "d.fileSize, "
				+ "d.contentType "
				+ ") "
				+ "from Document d "
				+ "order by d.type, d.fileName";
		
		public static final String SELECT_DOCUMENTS_BY_TYPE = "from Document d "
				+ "where d.type = :type "
				+ "order by d.fileName asc";
		
		public static final String COUNT_DOCUMENTS_BY_TYPE_AND_FILENAME = "select count(*) "
				+ "from Document d "
				+ "where d.type = :type "
				+ "and d.fileName = :fileName "
				+ "and d.id != :id";
	}
}
