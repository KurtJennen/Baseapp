package be.luxuryoverdosis.framework.web.tag;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import be.luxuryoverdosis.framework.web.message.MessageLocator;
import be.luxuryoverdosis.framework.web.ui.NavigationObject;

public class Navigation extends CommonTag {
	private static final long serialVersionUID = 1L;
	
	private String nameIds;
	private boolean firstVisible;
	private boolean previousVisible;
	private boolean nextVisible;
	private boolean lastVisible;
	
	private ArrayList<NavigationObject> navigations; 

	public void setNameIds(String nameIds) {
		this.nameIds = nameIds;
	}
	public void setFirstVisible(boolean firstVisible) {
		this.firstVisible = firstVisible;
	}
	public void setPreviousVisible(boolean previousVisible) {
		this.previousVisible = previousVisible;
	}
	public void setNextVisible(boolean nextVisible) {
		this.nextVisible = nextVisible;
	}
	public void setLastVisible(boolean lastVisible) {
		this.lastVisible = lastVisible;
	}

	public int doStartTag() throws JspException {
		try {
			HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
			
			navigations = new ArrayList<NavigationObject>();
			navigations.add(produceNavigationObject(request, "navigation.first", "first", firstVisible, "control_start_blue"));
			navigations.add(produceNavigationObject(request, "navigation.previous", "previous", previousVisible, "control_rewind_blue"));
			navigations.add(produceNavigationObject(request, "navigation.next", "next", nextVisible, "control_fastforward_blue"));
			navigations.add(produceNavigationObject(request, "navigation.last", "last", lastVisible, "control_end_blue"));
		}
		catch (Exception e) {
		}
		return EVAL_BODY_BUFFERED;
	}
	
	private NavigationObject produceNavigationObject(HttpServletRequest request, String title, String action, boolean visible, String image) {
		NavigationObject navigationObject = new NavigationObject();
		navigationObject.setTitle(MessageLocator.getMessage(request, title));
		navigationObject.setAction(action);
		navigationObject.setNameIds(nameIds);
		navigationObject.setVisible(visible);
		navigationObject.setImage(image);
		
		return navigationObject;
	}

	public int doEndTag() throws JspException {
		try {
			for (NavigationObject navigationObject : navigations) {
				produceTemplate("navigationTemplate.ftl", navigationObject);
			}
		}
		catch (Exception e) {
		}
		
		return EVAL_PAGE;
	}
}
