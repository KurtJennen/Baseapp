package be.luxuryoverdosis.framework.business.service.implementations;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.annotation.Resource;

import net.sf.jooreports.templates.DocumentTemplate;
import net.sf.jooreports.templates.DocumentTemplateFactory;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.InputSource;

import be.luxuryoverdosis.framework.base.FileType;
import be.luxuryoverdosis.framework.base.tool.JaxbTool;
import be.luxuryoverdosis.framework.business.service.interfaces.DocumentService;
import be.luxuryoverdosis.framework.data.dao.interfaces.DocumentHibernateDAO;
import be.luxuryoverdosis.framework.data.dto.DocumentDTO;
import be.luxuryoverdosis.framework.data.factory.DocumentFactory;
import be.luxuryoverdosis.framework.data.to.Document;
import be.luxuryoverdosis.framework.logging.Logging;
import be.luxuryoverdosis.framework.web.exception.ServiceException;
import freemarker.ext.dom.NodeModel;

@Service
public class DocumentServiceSpringImpl implements DocumentService {
	@Resource
	private DocumentHibernateDAO documentHibernateDAO;
	
	@Transactional
	public DocumentDTO createOrUpdateDTO(final DocumentDTO documentDTO) {
		Logging.info(this, "Begin createDocumentDTO");

		Document document = new Document();
		if(documentDTO.getId() > 0) {
			document = this.read(documentDTO.getId());
		}
		document = DocumentFactory.produceDocument(document, documentDTO);

		document = this.createOrUpdate(document);

		Logging.info(this, "End createDocumentDTO");
		return this.readDTO(document.getId());
	}
	
	@Transactional(readOnly=true)
	public DocumentDTO readDTO(final int id) {
		Logging.info(this, "Begin readDocumentDTO");
		
		Document document = this.read(id);
		
		DocumentDTO documentDTO = DocumentFactory.produceDocumentDTO(document);
		
		Logging.info(this, "End readDocumentDTO");
		return documentDTO;
	}
	
	@Transactional
	public Document createOrUpdate(final Document document) {
		Logging.info(this, "Begin createDocument");
		if(documentHibernateDAO.count(document.getType(), document.getFileName(), document.getId()) > 0) {
			throw new ServiceException("exists", new String[] {"table.document"});
		}
		if(document.getFileName() == null || StringUtils.isEmpty(document.getFileName())) {
			throw new ServiceException("errors.required", new String[] {"file.name"});
		}
		if(document.getFileData() == null) {
			throw new ServiceException("errors.required", new String[] {"file.data"});
		}
		if(document.getContentType() == null || StringUtils.isEmpty(document.getContentType())) {
			throw new ServiceException("errors.required", new String[] {"file.contenttype"});
		}
		if(!document.getFileName().endsWith(FileType.ODT)) {
			throw new ServiceException("ends.not.with", new String[] {"file"});
		}
		Document result = null;
		result = documentHibernateDAO.createOrUpdate(document);
		Logging.info(this, "End createDocument");
		return result;
	}
	
	@Transactional(readOnly=true)
	public Document read(final int id) {
		Logging.info(this, "Begin readDocument");
		Document result = null;
		result = documentHibernateDAO.read(id);
		Logging.info(this, "End readDocument");
		return result;
	}
	
	@Transactional
	public void delete(final int id) {
		Logging.info(this, "Begin deleteDocument");
		
		documentHibernateDAO.delete(id);
		
		Logging.info(this, "End deleteDocument");
	}

	@Transactional(readOnly=true)
	public ArrayList<Document> list() {
		Logging.info(this, "Begin listJob");
		ArrayList<Document> arrayList = null;
		arrayList = documentHibernateDAO.list();
		Logging.info(this, "End listJob");
		return arrayList;
	}
	
	@Transactional(readOnly=true)
	public ArrayList<Document> list(final String type) {
		Logging.info(this, "Begin listJob");
		ArrayList<Document> arrayList = null;
		arrayList = documentHibernateDAO.list(type);
		Logging.info(this, "End listJob");
		return arrayList;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public File createDocument(final Document document, final Object data, final Class clazz) {
		try {
			DocumentTemplateFactory documentTemplateFactory = new DocumentTemplateFactory();
			DocumentTemplate template = documentTemplateFactory.getTemplate(document.getFile().getBinaryStream());
	
			File file = new File(document.getFileName());
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			
			InputStream inputStream = JaxbTool.getInputStream(clazz, data);
			
			Logging.info(this, JaxbTool.getXml(clazz, data));
			
			InputSource dataSource = new InputSource(inputStream);
            NodeModel model = NodeModel.parse(dataSource);
			
			template.createDocument(model, fileOutputStream);
			
			return file;
		} catch (Exception e) {
			throw new ServiceException("errors.exception.type", new String[]{e.getClass().getName().toLowerCase()});
		}
	}
	
	public void writeDocument(final File outputFile, final OutputStream outputStream) {
		try {
			InputStream inputStream = new FileInputStream(outputFile);
			byte[] buffer = new byte[1024];
			int lengte;
			while((lengte = inputStream.read(buffer)) > 0) {
				outputStream.write(buffer, 0, lengte);
			}
			inputStream.close();
		} catch (Exception e) {
			throw new ServiceException("errors.exception.type", new String[]{e.getClass().getName().toLowerCase()});
		}
	}
	
}
