package be.luxuryoverdosis.framework.business.webservice.implementations;

import com.fasterxml.jackson.core.JsonProcessingException;

import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;
import be.luxuryoverdosis.framework.data.dto.BaseDTO;
import be.luxuryoverdosis.framework.data.restwrapperdto.RestWrapperDTO;

public class BaseRestService {
	public String checkUserOnThread(final RestWrapperDTO<? extends BaseDTO> restWrapperDTO) throws JsonProcessingException {
		String error = BaseSpringServiceLocator.getMessage("security.access.denied");
		return restWrapperDTO.sendRestErrorWrapperDto(error);
	}

}
