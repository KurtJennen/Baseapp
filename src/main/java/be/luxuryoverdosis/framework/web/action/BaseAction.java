package be.luxuryoverdosis.framework.web.action;

import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
import org.apache.struts.actions.DispatchAction;

import be.luxuryoverdosis.framework.data.dto.BaseDTO;
import be.luxuryoverdosis.framework.data.to.BaseTO;
import be.luxuryoverdosis.framework.logging.Logging;
import be.luxuryoverdosis.framework.web.BaseWebConstants;
import be.luxuryoverdosis.framework.web.form.BaseForm;
import be.luxuryoverdosis.framework.web.form.ListForm;
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
	
	public void setSelectedTab(ActionForm form, HttpServletRequest request) throws Exception {
		BaseForm baseForm = (BaseForm) form;
		
		String selectedTab = request.getParameter(BaseWebConstants.SELECTED_TAB);
		if(selectedTab != null) {
			baseForm.setSelectedTab(Integer.parseInt(selectedTab));
		}
	}
	
	public ActionForward read(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
       return read(mapping, form, request, response, BaseWebConstants.READ);
    }
	
	public ActionForward read(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String forward) throws Exception {
        Logging.info(this, "Begin Read");
        
        ListForm listForm = (ListForm) form;
        ActionRedirect actionRedirect = new ActionRedirect(mapping.findForward(forward));
        actionRedirect.addParameter(BaseWebConstants.ID, listForm.getSelectedIds()[0]);
        
        Logging.info(this, "End Read Success");
        
        return actionRedirect;
    }
}
