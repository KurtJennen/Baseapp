package be.luxuryoverdosis.framework.business.service.implementations;

import java.sql.Connection;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.stereotype.Service;

import be.luxuryoverdosis.framework.business.service.interfaces.ReportService;
import be.luxuryoverdosis.framework.logging.Logging;
import be.luxuryoverdosis.framework.web.exception.ServiceException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@Service
public class ReportServiceSpringImpl implements ReportService {
	@Resource
	private DataSource dataSource;
	
	public byte[] create(final String realPathReport) {
		
		try {
			Logging.info(this, "Begin createReport");
			
			JasperDesign jasperDesign = JRXmlLoader.load(realPathReport);
			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			
			Connection connection = dataSource.getConnection();
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, connection);
			
			Logging.info(this, "End createReport");
			
			byte[] pdfByteArray = JasperExportManager.exportReportToPdf(jasperPrint);
			
			return pdfByteArray;
			
		} catch (Exception e) {
			throw new ServiceException("errors.exception.type", new String[]{e.getClass().getName().toLowerCase()});
		}
	}
}
