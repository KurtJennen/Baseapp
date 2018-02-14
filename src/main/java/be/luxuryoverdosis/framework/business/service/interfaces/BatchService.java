package be.luxuryoverdosis.framework.business.service.interfaces;

import be.luxuryoverdosis.framework.data.dto.FileDTO;

public interface BatchService {
	public void exportUserJob(FileDTO fileDTO) throws Exception;
	public void importUserJob(FileDTO fileDTO) throws Exception;
}
