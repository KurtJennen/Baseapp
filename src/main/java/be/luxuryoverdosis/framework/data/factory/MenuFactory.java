package be.luxuryoverdosis.framework.data.factory;

import net.sf.navigator.menu.MenuComponent;
import be.luxuryoverdosis.Constants;
import be.luxuryoverdosis.framework.business.service.BaseSpringServiceLocator;
import be.luxuryoverdosis.framework.data.dto.MenuDTO;
import be.luxuryoverdosis.framework.data.to.MenuTO;

public class MenuFactory {
	private MenuFactory() {
	}
	
	public static MenuDTO produceMenuDTO(final MenuComponent menuComponent, final String parentTitle, String fullLevel, int level) {
		MenuDTO menuDTO = new MenuDTO();
		menuDTO.setId(-1);
		menuDTO.setFullName(parentTitle + menuComponent.getName());
		menuDTO.setName(menuComponent.getName());
		menuDTO.setTitle(BaseSpringServiceLocator.getMessage(menuComponent.getTitle()));
		menuDTO.setFullLevel(fullLevel);
		menuDTO.setLevel(level);
		menuDTO.setHidden(Constants.CODE_NEE);
		menuDTO.setDisabled(Constants.CODE_NEE);
		menuDTO.setForPay(Constants.CODE_NEE);
		menuDTO.setPayed(Constants.CODE_NEE);
		
		return menuDTO;
	}
	
	public static MenuDTO produceMenuDTO(final MenuTO menuTO, MenuDTO menuDTO) {
		if(menuDTO == null) {
			menuDTO = new MenuDTO();
		}
		menuDTO.setId(menuTO.getId());
		menuDTO.setFullName(menuTO.getFullName());
		menuDTO.setName(menuTO.getName());
		menuDTO.setTitle(menuTO.getTitle());
		menuDTO.setLevel(menuTO.getLevel());
		menuDTO.setHidden(menuTO.getHidden());
		menuDTO.setDisabled(menuTO.getDisabled());
		menuDTO.setForPay(menuTO.getForPay());
		menuDTO.setPayed(menuTO.getPayed());
		if(menuTO.getUser() != null) {
			menuDTO.setUserId(menuTO.getUser().getId());
		}
		
		return menuDTO;
	}
	
	public static MenuTO produceMenu(MenuTO menuTO, final MenuDTO menuDTO) {
		if(menuTO == null) {
			menuTO = new MenuTO();
		}
		menuTO.setId(menuDTO.getId());
		menuTO.setFullName(menuDTO.getFullName());
		menuTO.setName(menuDTO.getName());
		menuTO.setTitle(menuDTO.getTitle());
		menuTO.setFullLevel(menuDTO.getFullLevel());
		menuTO.setLevel(menuDTO.getLevel());
		menuTO.setHidden(menuDTO.getHidden());
		menuTO.setDisabled(menuDTO.getDisabled());
		menuTO.setForPay(menuDTO.getForPay());
		menuTO.setPayed(menuDTO.getPayed());
		
		return menuTO;
	}
}
