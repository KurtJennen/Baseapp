package be.luxuryoverdosis.framework.business.service.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.to.BatchJobParams;

public interface BatchJobParamsService {
	ArrayList<BatchJobParams> list(long jobInstanceId);
}
