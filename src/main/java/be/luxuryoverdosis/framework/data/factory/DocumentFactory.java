package be.luxuryoverdosis.framework.data.factory;

import be.luxuryoverdosis.framework.data.dto.DocumentDTO;
import be.luxuryoverdosis.framework.data.to.Document;

public final class DocumentFactory {
	private DocumentFactory() {
	}
	
	public static DocumentDTO produceDocumentDTO(final Document document) {
		DocumentDTO documentDTO = new DocumentDTO();
		documentDTO.setId(document.getId());
		documentDTO.setType(document.getType());
		documentDTO.setFileName(document.getFileName());
		documentDTO.setFileSize(document.getFileSize());
		documentDTO.setContentType(document.getContentType());
		
		return documentDTO;
	}
	
	public static Document produceDocument(Document document, final DocumentDTO documentDTO) {
		if (document == null) {
			document = new Document();
		}
		document.setId(documentDTO.getId());
		document.setType(documentDTO.getType());
		document.setFileName(documentDTO.getFileName());
		document.setFileData(documentDTO.getFileData());
		document.setFileSize(documentDTO.getFileSize());
		document.setContentType(documentDTO.getContentType());
		
		return document;
	}
}
