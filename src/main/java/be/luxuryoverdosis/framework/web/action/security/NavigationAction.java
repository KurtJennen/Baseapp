package be.luxuryoverdosis.framework.web.action.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import be.luxuryoverdosis.framework.base.tool.ArrayTool;
import be.luxuryoverdosis.framework.logging.Logging;
import be.luxuryoverdosis.framework.web.BaseWebConstants;
import be.luxuryoverdosis.framework.web.action.ajaxaction.AjaxAction;
import be.luxuryoverdosis.framework.web.form.BaseForm;
import be.luxuryoverdosis.framework.web.sessionmanager.SessionManager;

public abstract class NavigationAction extends AjaxAction {	
	
	public abstract String getNameIds();
	public abstract ActionForward read(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ActionForward first(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		Logging.info(this, "Begin First");
		Logging.info(this, "End First Success");
		
		return navigate(mapping, form, request, response);
	}
	
	public ActionForward previous(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		Logging.info(this, "Begin Previous");
		Logging.info(this, "End Previous Success");
		
		return navigate(mapping, form, request, response);
	}
	
	public ActionForward next(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		Logging.info(this, "Begin Next");
		Logging.info(this, "End Next Success");
		
		return navigate(mapping, form, request, response);
	}
	
	public ActionForward last(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		Logging.info(this, "Begin Last");
		Logging.info(this, "End Last Success");
		
		return navigate(mapping, form, request, response);
	}
	
	public ActionForward navigate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		Logging.info(this, "Begin Navigate");
		
		BaseForm baseForm = (BaseForm) form;
		
		int id = getId(baseForm, request);
		
		ActionRedirect redirect = new ActionRedirect(mapping.getPath() + ".do?method=read");
		redirect.addParameter(BaseWebConstants.ID, id);
		
		Logging.info(this, "End Navigate Success");
		
		return redirect;
	}
	
	private int getId(BaseForm baseForm, HttpServletRequest request) {
		int position = getPosition(baseForm, request);
		return baseForm.getIds()[position];
	}
	
	public void setNavigationButtons(ActionForm form, HttpServletRequest request) {
		Logging.info(this, "Begin Visible");
		
		BaseForm baseForm = (BaseForm) form;
		
		int position = getCurrentPosition(baseForm, request);
		
		if(position == -1) {
			position = saveIds(baseForm, request);
		}
		if(position == 0) {
			baseForm.setFirstVisible(false);
			baseForm.setPreviousVisible(false);
		}
		if(position == baseForm.getIds().length - 1) {
			baseForm.setNextVisible(false);
			baseForm.setLastVisible(false);
		}
		
		Logging.info(this, "End Visible Success");
	}

	private int getCurrentPosition(BaseForm baseForm, HttpServletRequest request) {
		loadIds(baseForm, request);
		
		return ArrayTool.positionValueInArray(baseForm.getIds(), baseForm.getId());
	}
	
	private int getPosition(BaseForm baseForm, HttpServletRequest request) {
		int position = getCurrentPosition(baseForm, request);
		
		if(BaseWebConstants.FIRST.equals(baseForm.getMethod())) {
			return 0;
		}
		if(BaseWebConstants.PREVIOUS.equals(baseForm.getMethod())) {
			if(position != 0) {
				position--;
			}
			return position;
		}
		if(BaseWebConstants.NEXT.equals(baseForm.getMethod())) {
			if(position != baseForm.getIds().length - 1) {
				position++;
			}
			return position;
		}
		if(BaseWebConstants.LAST.equals(baseForm.getMethod())) {
			return baseForm.getIds().length - 1;
		}
		return position;
	}
	
	private void loadIds(BaseForm baseForm, HttpServletRequest request) {
		int[] ids = (int[])SessionManager.getFromSession(request, getNameIds());
		
		baseForm.setIds(ids);
	}
	
	private int saveIds(BaseForm baseForm, HttpServletRequest request) {
		int[] ids = ArrayTool.addToArray(baseForm.getIds(), baseForm.getId());
		
		baseForm.setIds(ids);
		
		SessionManager.putInSession(request, getNameIds(), ids);
		
		return ArrayTool.positionValueInArray(baseForm.getIds(), baseForm.getId());
	}
	
}
