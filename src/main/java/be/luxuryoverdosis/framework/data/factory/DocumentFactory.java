package be.luxuryoverdosis.framework.data.factory;

import be.luxuryoverdosis.framework.data.dto.DocumentDTO;
import be.luxuryoverdosis.framework.data.to.Document;

public class DocumentFactory {
	private DocumentFactory() {
	}
	
	public static DocumentDTO produceDocumentDTO(final Document document) {
		DocumentDTO documentDTO = new DocumentDTO();
		documentDTO.setId(document.getId());
		documentDTO.setType(document.getType());
		documentDTO.getFileDTO().setFileName(document.getFileName());
		documentDTO.getFileDTO().setFileSize(document.getFileSize());
		documentDTO.getFileDTO().setContentType(document.getContentType());
		
		return documentDTO;
	}
	
	public static Document produceDocument(Document document, final DocumentDTO documentDTO) {
		if(document == null) {
			document = new Document();
		}
		document.setId(documentDTO.getId());
		document.setType(documentDTO.getType());
		if(documentDTO.getFileDTO() != null) {
			document.setFileName(documentDTO.getFileDTO().getFileName());
			document.setFileData(documentDTO.getFileDTO().getFileData());
			document.setFileSize(documentDTO.getFileDTO().getFileSize());
			document.setContentType(documentDTO.getFileDTO().getContentType());
		}
		
		return document;
	}
}
