<%@page import="be.luxuryoverdosis.framework.data.dto.UserDTO"%>
<%@page import="be.luxuryoverdosis.framework.business.enumeration.RoleNameEnum"%>
<%@ taglib uri="http://struts-menu.sf.net/tag-el" prefix="menu" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@page import="be.luxuryoverdosis.baseapp.Constants"%>
<%@page import="be.luxuryoverdosis.baseapp.web.WebConstants"%>
<menu:useMenuDisplayer name="ListMenu" bundle="org.apache.struts.action.MESSAGE">
	<c:if test="<%=((UserDTO)request.getSession().getAttribute(WebConstants.USER)).getRoles().contains(RoleNameEnum.BEHEERDER.getCode())%>">
		<menu:displayMenu name="menu1Beheerder" />
		<menu:displayMenu name="menu2Beheerder" />
	</c:if>
	<c:if test="<%=((UserDTO)request.getSession().getAttribute(WebConstants.USER)).getRoles().contains(RoleNameEnum.UITGEBREIDEGEBRUIKER.getCode())%>">
		<menu:displayMenu name="menu1Beheerder" />
		<menu:displayMenu name="menu3Beheerder" />
	</c:if>
	<c:if test="<%=((UserDTO)request.getSession().getAttribute(WebConstants.USER)).getRoles().contains(RoleNameEnum.NORMALEGEBRUIKER.getCode())%>">
		<menu:displayMenu name="menu1Beheerder" />
	</c:if>
</menu:useMenuDisplayer>