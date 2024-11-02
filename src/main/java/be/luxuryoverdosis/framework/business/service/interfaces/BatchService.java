package be.luxuryoverdosis.framework.business.service.interfaces;

import be.luxuryoverdosis.framework.data.dto.FileDTO;

public interface BatchService {
	void exportUserJob(FileDTO fileDTO) throws Exception;
	void importUserJob(FileDTO fileDTO) throws Exception;
}
