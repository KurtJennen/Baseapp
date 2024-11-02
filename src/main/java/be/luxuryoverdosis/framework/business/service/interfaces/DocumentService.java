package be.luxuryoverdosis.framework.business.service.interfaces;

import java.io.File;
import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.dto.DocumentDTO;
import be.luxuryoverdosis.framework.data.to.Document;

public interface DocumentService {
	DocumentDTO createOrUpdateDTO(DocumentDTO documentDTO);
	DocumentDTO readDTO(int id);
	
	Document createOrUpdate(Document document);
	Document read(int id);
	void delete(int id);
	byte[] downloadFile(int id);
	
	ArrayList<Document> list(String type);
	ArrayList<DocumentDTO> listDTO();

	@SuppressWarnings("rawtypes")
	File createDocument(Document document, Object data, Class clazz);
	@SuppressWarnings("rawtypes")
	File createDocumentAndConvertToPdf(Document document, Object data, Class clazz);
}
