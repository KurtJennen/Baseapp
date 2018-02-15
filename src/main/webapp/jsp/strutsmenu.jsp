<%@ taglib uri="http://struts-menu.sf.net/tag-el" prefix="menu" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@page import="be.luxuryoverdosis.baseapp.Constants"%>
<%@page import="be.luxuryoverdosis.baseapp.web.WebConstants"%>
<%@page import="be.luxuryoverdosis.framework.data.to.UserTO"%>
<c:if test="<%= Constants.ROLE_BEHEERDER.equals(((UserTO)request.getSession().getAttribute(WebConstants.USER)).getRole().getName()) %>">
	<menu:useMenuDisplayer name="ListMenu" bundle="org.apache.struts.action.MESSAGE">
		<menu:displayMenu name="menuBeheerder" />
		<menu:displayMenu name="menu1Beheerder" />
	</menu:useMenuDisplayer>
</c:if>
<c:if test="<%= Constants.ROLE_UITGEBREIDE_GEBRUIKER.equals(((UserTO)request.getSession().getAttribute(WebConstants.USER)).getRole().getName()) %>">
	<menu:useMenuDisplayer name="ListMenu" bundle="org.apache.struts.action.MESSAGE">
		<menu:displayMenu name="menuBeheerder" />
		<menu:displayMenu name="menu1Beheerder" />
	</menu:useMenuDisplayer>
</c:if>
<c:if test="<%= Constants.ROLE_NORMALE_GEBRUIKER.equals(((UserTO)request.getSession().getAttribute(WebConstants.USER)).getRole().getName()) %>">
	<menu:useMenuDisplayer name="ListMenu" bundle="org.apache.struts.action.MESSAGE">
		<menu:displayMenu name="menuBeheerder" />
	</menu:useMenuDisplayer>
</c:if>