package be.luxuryoverdosis.framework.web.action;

import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.actions.DispatchAction;

import be.luxuryoverdosis.framework.data.dto.BaseDTO;
import be.luxuryoverdosis.framework.data.to.BaseTO;
import be.luxuryoverdosis.framework.web.sessionmanager.SessionManager;

public class BaseAction extends DispatchAction {
	public int[] setIds(HttpServletRequest request, ArrayList<?> items, String nameIds) {
		if(nameIds == null) {
			return null;
		}
		
		int[] ids = new int[items.size()];
		int teller = 0;
		
		for (Iterator<?> iterator = items.iterator(); iterator.hasNext();) {
			Object record = iterator.next();
			if (record instanceof BaseTO) {
				BaseTO baseTO = (BaseTO) record;
				ids[teller] = baseTO.getId();
			}
			if (record instanceof BaseDTO) {
				BaseDTO baseDTO = (BaseDTO) record;
				ids[teller] = baseDTO.getId();
			}
			
			teller++;
		}
	
		SessionManager.putInSession(request, nameIds, ids);
		
		return ids;
	}
}
