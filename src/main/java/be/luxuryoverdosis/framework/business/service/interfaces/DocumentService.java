package be.luxuryoverdosis.framework.business.service.interfaces;

import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.dto.DocumentDTO;
import be.luxuryoverdosis.framework.data.to.DocumentTO;

public interface DocumentService {
	public DocumentDTO createOrUpdateDTO(DocumentDTO documentDTO);
	public DocumentDTO readDTO(int id);
	
	public DocumentTO createOrUpdate(DocumentTO documentTO);
	public DocumentTO read(int id);
	public void delete(int id);
	
	public ArrayList<DocumentTO> list();
	public ArrayList<DocumentTO> list(String type);

	@SuppressWarnings("rawtypes")
	public File createDocument(final DocumentTO documentTO, Object data, Class clazz);
	public void writeDocument(File file, OutputStream outputStream);
}
