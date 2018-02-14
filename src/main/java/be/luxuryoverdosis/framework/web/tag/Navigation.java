package be.luxuryoverdosis.framework.web.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import be.luxuryoverdosis.framework.web.message.MessageLocator;

public class Navigation implements Tag {
	PageContext pageContext;
	private String nameIds;
	private boolean firstVisible;
	private boolean previousVisible;
	private boolean nextVisible;
	private boolean lastVisible;

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

	public void setParent(Tag t) {
	}
	
	public void setPageContext(PageContext p) {
		pageContext = p;
	}
	
	public void release() {
	}
	
	public Tag getParent() {
		return null;
	}
	
	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
			
			//first
			if(firstVisible) {
				out.print("<button onclick=\"javascript:doActionNavigation('first','" + nameIds + "');\" title=\"" + MessageLocator.getMessage(request, "navigation.first") + "\">");
				out.print("<img src=\"images/control_start_blue.png\"/>");
				out.println("</button>");
			} else {
				out.print("<button disabled=\"disabled\" title=\"" + MessageLocator.getMessage(request, "navigation.first") + "\">");
				out.print("<img src=\"images/control_start_blue_disabled.png\"/>");
				out.println("</button>");
			}
			//previous
			if(previousVisible) {
				out.print("<button onclick=\"javascript:doActionNavigation('previous','" + nameIds + "');\" title=\"" + MessageLocator.getMessage(request, "navigation.previous") + "\">");
				out.print("<img src=\"images/control_rewind_blue.png\"/>");
				out.println("</button>");
			} else {
				out.print("<button disabled=\"disabled\" title=\"" + MessageLocator.getMessage(request, "navigation.previous") + "\">");
				out.print("<img src=\"images/control_rewind_blue_disabled.png\"/>");
				out.println("</button>");
			}
			//next
			if(nextVisible) {
				out.print("<button onclick=\"javascript:doActionNavigation('next','" + nameIds + "');\" title=\"" + MessageLocator.getMessage(request, "navigation.next") + "\">");
				out.print("<img src=\"images/control_fastforward_blue.png\"/>");
				out.println("</button>");
			} else {
				out.print("<button disabled=\"disabled\" title=\"" + MessageLocator.getMessage(request, "navigation.next") + "\">");
				out.print("<img src=\"images/control_fastforward_blue_disabled.png\"/>");
				out.println("</button>");
			}
			//last
			if(lastVisible) {
				out.print("<button onclick=\"javascript:doActionNavigation('last','" + nameIds + "');\" title=\"" + MessageLocator.getMessage(request, "navigation.last") + "\">");
				out.print("<img src=\"images/control_end_blue.png\"/>");
				out.println("</button>");
			} else {
				out.print("<button disabled=\"disabled\" title=\"" + MessageLocator.getMessage(request, "navigation.last") + "\">");
				out.print("<img src=\"images/control_end_blue_disabled.png\"/>");
				out.println("</button>");
			}
		}
		catch (Exception e) {
		}
		return EVAL_BODY_INCLUDE;
	}

	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
}
