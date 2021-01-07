package be.luxuryoverdosis.framework.web.action.navigation;

import org.apache.struts.action.ActionForm;

import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;
import be.luxuryoverdosis.framework.business.service.interfaces.RoleService;
import be.luxuryoverdosis.framework.data.dto.RoleDTO;
import be.luxuryoverdosis.framework.web.BaseWebConstants;
import be.luxuryoverdosis.framework.web.form.DetailRoleForm;

public class DetailRoleAction extends DetailAction<DetailRoleForm, RoleDTO> {
	public boolean isNavigation() {
		return true;
	}
	
	public String getNameIds() {
		return BaseWebConstants.ROLE_IDS;
	}
	
	public String getTableName() {
		return "table.role";
	}
	
	public DetailRoleForm create(ActionForm form) {
		return (DetailRoleForm) form;
	}
	
	public void read(ActionForm form, int id) {
		RoleDTO roleDTO = getRoleService().readDTO(id);
		
		DetailRoleForm roleForm = (DetailRoleForm) form;
		roleForm.setId(roleDTO.getId());
		roleForm.setName(roleDTO.getName());
	}
	
	public RoleDTO save(ActionForm form) {
		DetailRoleForm roleForm = (DetailRoleForm) form;
		
		RoleDTO roleDTO = new RoleDTO();
		roleDTO.setId(roleForm.getId());
		roleDTO.setName(roleForm.getName());
		
		roleDTO = getRoleService().createOrUpdateDTO(roleDTO);
		
		return roleDTO;
	}

	public void delete(int id) {
		getRoleService().delete(id);
	}
	
	private RoleService getRoleService() {
		return BaseSpringServiceLocator.getBean(RoleService.class);
	}

	
}
