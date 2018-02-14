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
import be.luxuryoverdosis.framework.data.to.DocumentTO;
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

		DocumentTO documentTO = new DocumentTO();
		if(documentDTO.getId() > 0) {
			documentTO = this.read(documentDTO.getId());
		}
		documentTO = DocumentFactory.produceDocument(documentTO, documentDTO);

		documentTO = this.createOrUpdate(documentTO);

		Logging.info(this, "End createDocumentDTO");
		return this.readDTO(documentTO.getId());
	}
	
	@Transactional(readOnly=true)
	public DocumentDTO readDTO(final int id) {
		Logging.info(this, "Begin readDocumentDTO");
		
		DocumentTO documentTO = this.read(id);
		
		DocumentDTO documentDTO = DocumentFactory.produceDocumentDTO(documentTO);
		
		Logging.info(this, "End readDocumentDTO");
		return documentDTO;
	}
	
	@Transactional
	public DocumentTO createOrUpdate(final DocumentTO documentTO) {
		Logging.info(this, "Begin createDocument");
		if(documentHibernateDAO.count(documentTO.getType(), documentTO.getFileName(), documentTO.getId()) > 0) {
			throw new ServiceException("exists", new String[] {"table.document"});
		}
		if(documentTO.getFileName() == null || StringUtils.isEmpty(documentTO.getFileName())) {
			throw new ServiceException("errors.required", new String[] {"file.name"});
		}
		if(documentTO.getFileData() == null) {
			throw new ServiceException("errors.required", new String[] {"file.data"});
		}
		if(documentTO.getContentType() == null || StringUtils.isEmpty(documentTO.getContentType())) {
			throw new ServiceException("errors.required", new String[] {"file.contenttype"});
		}
		if(!documentTO.getFileName().endsWith(FileType.ODT)) {
			throw new ServiceException("ends.not.with", new String[] {"file"});
		}
		DocumentTO result = null;
		result = documentHibernateDAO.createOrUpdate(documentTO);
		Logging.info(this, "End createDocument");
		return result;
	}
	
	@Transactional(readOnly=true)
	public DocumentTO read(final int id) {
		Logging.info(this, "Begin readDocument");
		DocumentTO result = null;
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
	public ArrayList<DocumentTO> list() {
		Logging.info(this, "Begin listJob");
		ArrayList<DocumentTO> arrayList = null;
		arrayList = documentHibernateDAO.list();
		Logging.info(this, "End listJob");
		return arrayList;
	}
	
	@Transactional(readOnly=true)
	public ArrayList<DocumentTO> list(final String type) {
		Logging.info(this, "Begin listJob");
		ArrayList<DocumentTO> arrayList = null;
		arrayList = documentHibernateDAO.list(type);
		Logging.info(this, "End listJob");
		return arrayList;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public File createDocument(final DocumentTO documentTO, final Object data, final Class clazz) {
		try {
			DocumentTemplateFactory documentTemplateFactory = new DocumentTemplateFactory();
			DocumentTemplate template = documentTemplateFactory.getTemplate(documentTO.getFile().getBinaryStream());
	
			File file = new File(documentTO.getFileName());
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
