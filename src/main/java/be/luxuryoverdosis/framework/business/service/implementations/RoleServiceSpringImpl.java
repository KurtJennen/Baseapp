package be.luxuryoverdosis.framework.business.service.implementations;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.luxuryoverdosis.framework.business.service.interfaces.RoleService;
import be.luxuryoverdosis.framework.data.dao.interfaces.RoleHibernateDAO;
import be.luxuryoverdosis.framework.data.dao.interfaces.UserHibernateDAO;
import be.luxuryoverdosis.framework.data.dto.RoleDTO;
import be.luxuryoverdosis.framework.data.factory.RoleFactory;
import be.luxuryoverdosis.framework.data.to.Role;
import be.luxuryoverdosis.framework.logging.Logging;
import be.luxuryoverdosis.framework.web.exception.ServiceException;

@Service
public class RoleServiceSpringImpl implements RoleService {
	@Resource
	private RoleHibernateDAO roleHibernateDAO;
	@Resource
	private UserHibernateDAO userHibernateDAO;
	
	@Transactional
	public RoleDTO createOrUpdateDTO(final RoleDTO roleDTO) {
		Logging.info(this, "Begin createRoleDTO");
		
		Role role = new Role();
		if(roleDTO.getId() > 0) {
			role = this.read(roleDTO.getId());
		}
		role = RoleFactory.produceRole(role, roleDTO);
		
		role = this.createOrUpdate(role);
		
		Logging.info(this, "End createRoleDTO");
		return this.readDTO(role.getId());
	}
	
	@Transactional(readOnly=true)
	public RoleDTO readDTO(final int id) {
		Logging.info(this, "Begin readRoleDTO");
		
		Role role = this.read(id);
		
		RoleDTO roleDTO = RoleFactory.produceRoleDTO(role);
		
		Logging.info(this, "End readRoleDTO");
		return roleDTO;
	}
	
	@Transactional
	public Role createOrUpdate(final Role role) {
		Logging.info(this, "Begin createRole");
		if(roleHibernateDAO.count(role.getName(), role.getId()) > 0) {
			throw new ServiceException("exists", new String[] {"table.role"});
		}
		if(StringUtils.isEmpty(role.getName())) {
			throw new ServiceException("errors.required", new String[] {"security.name"});
		}
		Role result = null;
		result = roleHibernateDAO.createOrUpdate(role);
		Logging.info(this, "End createRole");
		return result;
	}
	
	@Transactional(readOnly=true)
	public Role read(final int id) {
		Logging.info(this, "Begin readRole");
		Role result = null;
		result = roleHibernateDAO.read(id);
		Logging.info(this, "End readRole");
		return result;
	}
	
	@Transactional(readOnly=true)
	public Role readName(final String name) {
		Logging.info(this, "Begin readNameRole");
		Role result = null;
		result = roleHibernateDAO.readName(name);
		Logging.info(this, "End readNameRole");
		return result;
	}

	@Transactional
	public void delete(final int id) {
		Logging.info(this, "Begin deleteRole");
		if(userHibernateDAO.count(id) > 0) {
			throw new ServiceException("delete.failed.foreign.key", new String[] {"table.role", "table.user"});
		}
		roleHibernateDAO.delete(id);
		Logging.info(this, "End deleteRole");
	}

	@Transactional(readOnly=true)
	public ArrayList<Role> list() {
		Logging.info(this, "Begin listRole");
		ArrayList<Role> arrayList = null;
		arrayList = roleHibernateDAO.list();
		Logging.info(this, "End listRole");
		return arrayList;
	}
	
	
	@Transactional(readOnly=true)
	public ArrayList<RoleDTO> listDTO(String searchValue) {
		Logging.info(this, "Begin listRole");
		ArrayList<RoleDTO> arrayList = null;
		arrayList = roleHibernateDAO.listDTO(searchValue);
		Logging.info(this, "End listRole");
		return arrayList;
	}
	
	@Transactional(readOnly=true)
	public long count(final String name, final int id) {
		Logging.info(this, "Begin countRole");
		Long countRole = new Long(0); 
		countRole = roleHibernateDAO.count(name, id);
		Logging.info(this, "End countRole");
		return countRole.longValue();
	}
}
