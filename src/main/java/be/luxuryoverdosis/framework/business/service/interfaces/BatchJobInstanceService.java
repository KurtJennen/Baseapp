package be.luxuryoverdosis.framework.business.service.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.dto.BatchJobInstanceDTO;
import be.luxuryoverdosis.framework.data.to.BatchJobInstance;

public interface BatchJobInstanceService {
	BatchJobInstance read(long id);
	
	ArrayList<BatchJobInstanceDTO> list(String... jobNames);
	ArrayList<BatchJobInstanceDTO> list(String keyName, long longValue, String... jobNames);
}
