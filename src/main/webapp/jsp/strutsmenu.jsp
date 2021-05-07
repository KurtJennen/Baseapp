<%@page import="be.luxuryoverdosis.baseapp.business.enumeration.RoleNameEnum"%>
<%@ taglib uri="http://struts-menu.sf.net/tag-el" prefix="menu" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@page import="be.luxuryoverdosis.baseapp.Constants"%>
<%@page import="be.luxuryoverdosis.baseapp.web.WebConstants"%>
<%@page import="be.luxuryoverdosis.framework.data.to.User"%>
<c:if test="<%=((User)request.getSession().getAttribute(WebConstants.USER)).getRoles().contains(RoleNameEnum.BEHEERDER.getCode())%>">
	<menu:useMenuDisplayer name="ListMenu" bundle="org.apache.struts.action.MESSAGE">
		<menu:displayMenu name="menuBeheerder" />
		<menu:displayMenu name="menu1Beheerder" />
	</menu:useMenuDisplayer>
</c:if>
<c:if test="<%=((User)request.getSession().getAttribute(WebConstants.USER)).getRoles().contains(RoleNameEnum.UITGEBREIDE_GEBRUIKER.getCode())%>">
	<menu:useMenuDisplayer name="ListMenu" bundle="org.apache.struts.action.MESSAGE">
		<menu:displayMenu name="menuBeheerder" />
		<menu:displayMenu name="menu1Beheerder" />
	</menu:useMenuDisplayer>
</c:if>
<c:if test="<%=((User)request.getSession().getAttribute(WebConstants.USER)).getRoles().contains(RoleNameEnum.NORMALE_GEBRUIKER.getCode())%>">
	<menu:useMenuDisplayer name="ListMenu" bundle="org.apache.struts.action.MESSAGE">
		<menu:displayMenu name="menuBeheerder" />
	</menu:useMenuDisplayer>
</c:if>