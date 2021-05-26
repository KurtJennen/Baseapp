package be.luxuryoverdosis.framework.business.service.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.dto.UserRoleDTO;

public interface UserRoleService {
	 public ArrayList<UserRoleDTO> listDTO(int userId);
}
