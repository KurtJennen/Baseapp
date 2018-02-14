package be.luxuryoverdosis.framework.data.factory;

import be.luxuryoverdosis.framework.data.dto.DocumentDTO;
import be.luxuryoverdosis.framework.data.to.DocumentTO;

public class DocumentFactory {
	private DocumentFactory() {
	}
	
	public static DocumentDTO produceDocumentDTO(final DocumentTO documentTO) {
		DocumentDTO documentDTO = new DocumentDTO();
		documentDTO.setId(documentTO.getId());
		documentDTO.setType(documentTO.getType());
		documentDTO.getFileDTO().setFileName(documentTO.getFileName());
		documentDTO.getFileDTO().setFileSize(documentTO.getFileSize());
		documentDTO.getFileDTO().setContentType(documentTO.getContentType());
		
		return documentDTO;
	}
	
	public static DocumentTO produceDocument(DocumentTO documentTO, final DocumentDTO documentDTO) {
		if(documentTO == null) {
			documentTO = new DocumentTO();
		}
		documentTO.setId(documentDTO.getId());
		documentTO.setType(documentDTO.getType());
		if(documentDTO.getFileDTO() != null) {
			documentTO.setFileName(documentDTO.getFileDTO().getFileName());
			documentTO.setFileData(documentDTO.getFileDTO().getFileData());
			documentTO.setFileSize(documentDTO.getFileDTO().getFileSize());
			documentTO.setContentType(documentDTO.getFileDTO().getContentType());
		}
		
		return documentTO;
	}
}
