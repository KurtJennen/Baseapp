package be.luxuryoverdosis.framework.business.service.interfaces;

import java.io.File;
import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.dto.DocumentDTO;
import be.luxuryoverdosis.framework.data.to.Document;

public interface DocumentService {
	public DocumentDTO createOrUpdateDTO(DocumentDTO documentDTO);
	public DocumentDTO readDTO(int id);
	
	public Document createOrUpdate(Document document);
	public Document read(int id);
	public void delete(int id);
	
	public ArrayList<Document> list(String type);
	public ArrayList<DocumentDTO> listDTO();

	@SuppressWarnings("rawtypes")
	public File createDocument(final Document document, Object data, Class clazz);
}
