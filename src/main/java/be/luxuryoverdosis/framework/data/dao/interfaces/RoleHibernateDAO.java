package be.luxuryoverdosis.framework.data.dao.interfaces;

import java.util.ArrayList;

import be.luxuryoverdosis.framework.data.dto.RoleDTO;
import be.luxuryoverdosis.framework.data.to.RoleTO;

public interface RoleHibernateDAO {
	public RoleTO createOrUpdate(RoleTO roleTO);
	public RoleTO read(int id);
	public RoleTO readName(String name);
	public void delete(int id);
	
	public ArrayList<RoleTO> list();
	public ArrayList<RoleDTO> listDTO(String searchValue);
	
	public long count(String name, int id);
}
