package be.luxuryoverdosis.framework.business.service.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.to.BatchJobParamsTO;

public interface BatchJobParamsService {
	public ArrayList<BatchJobParamsTO> list(long jobInstanceId);
}
