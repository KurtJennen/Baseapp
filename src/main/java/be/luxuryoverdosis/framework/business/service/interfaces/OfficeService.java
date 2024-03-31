package be.luxuryoverdosis.framework.business.service.interfaces;

import org.jodconverter.core.DocumentConverter;

public interface OfficeService {
	public void startOfficeManager();
	public void stopOfficeManager();
	public DocumentConverter getDocumentConverter();
}
