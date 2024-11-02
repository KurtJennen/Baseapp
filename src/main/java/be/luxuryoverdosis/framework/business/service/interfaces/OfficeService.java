package be.luxuryoverdosis.framework.business.service.interfaces;

import org.jodconverter.core.DocumentConverter;

public interface OfficeService {
	void startOfficeManager();
	void stopOfficeManager();
	DocumentConverter getDocumentConverter();
}
