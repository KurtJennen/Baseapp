package be.luxuryoverdosis.framework.business.service.implementations;

import org.jodconverter.core.DocumentConverter;
import org.jodconverter.core.office.OfficeException;
import org.jodconverter.core.office.OfficeManager;
import org.jodconverter.local.LocalConverter;
import org.jodconverter.local.office.LocalOfficeManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import be.luxuryoverdosis.framework.business.service.interfaces.OfficeService;
import be.luxuryoverdosis.framework.logging.Logging;

@Service
public class OfficeServiceSpringImpl implements OfficeService {
	@Value("${office.home}")
	private String officeHome;
	@Value("${office.port}")
	private String officePort;
	
	private OfficeManager officeManager;
	private DocumentConverter documentConverter;

	public OfficeManager getOfficeManager() {
		return officeManager;
	}

	public DocumentConverter getDocumentConverter() {
		return documentConverter;
	}

	@Override
	public void startOfficeManager() {
		try {
			final LocalOfficeManager.Builder builder = LocalOfficeManager.builder();
			builder.portNumbers(Integer.valueOf(officePort));
			builder.officeHome(officeHome);
			
			officeManager = builder.build();
			
			officeManager.start();
			
			documentConverter = LocalConverter.make(officeManager);
		} catch (OfficeException oe) {
			Logging.error(this, "OfficeManager start error " + oe.getMessage());
		}
	}
	
	@Override
	public void stopOfficeManager() {
		try {
			officeManager.stop();
		} catch (OfficeException oe) {
			Logging.error(this, "OfficeManager stop error " + oe.getMessage());
		}
	}

}
