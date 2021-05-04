package be.luxuryoverdosis.framework.business.service.implementations;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.luxuryoverdosis.framework.business.service.interfaces.UserRoleService;
import be.luxuryoverdosis.framework.data.dao.interfaces.UserRoleHibernateDAO;
import be.luxuryoverdosis.framework.data.dto.UserRoleDTO;

@Service
public class UserRoleServiceSpringImpl implements UserRoleService {
	@Resource
	private UserRoleHibernateDAO userRoleHibernateDAO;
	
	@Transactional(readOnly=true)
    public ArrayList<UserRoleDTO> listDTO(int userId) {
        return userRoleHibernateDAO.listDTO(userId);
    }
	
}
