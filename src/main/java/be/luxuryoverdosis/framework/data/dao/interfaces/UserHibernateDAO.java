package be.luxuryoverdosis.framework.data.dao.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.dto.UserDTO;
import be.luxuryoverdosis.framework.data.to.UserTO;

public interface UserHibernateDAO {
	public UserTO createOrUpdate(UserTO userTO);
	public UserTO read(int id);
	public UserTO readName(String name);
	public void delete(int id);
	
	public ArrayList<UserTO> list();
	public ArrayList<UserDTO> listDTO();
	public ArrayList<UserDTO> listDTO(String searchValue);
	
	public long count(String name, int id);
	public long count(int roleId);
}
