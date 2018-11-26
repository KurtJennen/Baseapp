package be.luxuryoverdosis.framework.web.form;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import be.luxuryoverdosis.framework.business.enumeration.JaNeeType;
import be.luxuryoverdosis.framework.data.dto.MenuDTO;
import be.luxuryoverdosis.framework.logging.Logging;
import be.luxuryoverdosis.framework.web.BaseWebConstants;

public class MenuForm extends BaseForm {
	private static final long serialVersionUID = 1L;
	
	private int userId;
	private String[] hidden;
	private String[] disabled;
	private String[] forPay;
	private String[] payed;
	private ArrayList<MenuDTO> menus;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String[] getHidden() {
		return hidden;
	}
	public JaNeeType[] getJaNeeTypeHidden() {
		return JaNeeType.convert(hidden);
	}
	public void setHidden(String[] hidden) {
		this.hidden = hidden;
	}
	public String[] getDisabled() {
		return disabled;
	}
	public JaNeeType[] getJaNeeTypeDisabled() {
		return JaNeeType.convert(disabled);
	}
	public void setDisabled(String[] disabled) {
		this.disabled = disabled;
	}
	public String[] getForPay() {
		return forPay;
	}
	public JaNeeType[] getJaNeeTypeForPay() {
		return JaNeeType.convert(forPay);
	}
	public void setForPay(String[] forPay) {
		this.forPay = forPay;
	}
	public String[] getPayed() {
		return payed;
	}
	public JaNeeType[] getJaNeeTypePayed() {
		return JaNeeType.convert(payed);
	}
	public void setPayed(String[] payed) {
		this.payed = payed;
	}
	public ArrayList<MenuDTO> getMenus() {
		return menus;
	}
	public void setMenus(ArrayList<MenuDTO> menus) {
		this.menus = menus;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		this.setUserId(-1);
	}
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		Logging.info(this, "Begin Validating");
		
		ActionErrors errors = new ActionErrors();
		
		if(this.getMethod().equals(BaseWebConstants.UPDATE)) {
			errors = super.validate(mapping, request);
		}
		
		if(errors.size() > 0) {
			request.setAttribute(BaseWebConstants.ERROR, 1);
		}
			
		Logging.info(this, "End Validating");
		
		return errors;
	}
}
