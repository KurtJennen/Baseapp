package be.luxuryoverdosis.framework.business.service.interfaces;

import java.util.Map;

public interface ReportService {
	public byte[] create(String realPathReport);
    public byte[] create(String realPathReport, Map<String, Object> parameters);
}
