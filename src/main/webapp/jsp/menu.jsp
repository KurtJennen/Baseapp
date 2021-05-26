<%@page import="be.luxuryoverdosis.framework.business.enumeration.RoleNameEnum"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="/luxuryOverdosis" prefix="lo" %>
<html:img src="images/user.png" altKey="security.username" />&nbsp;<fmt:message key="welcome" />&nbsp;<c:out value="${user.userName}" />
<br />
<lo:menuButton action="login" method="logout" image="door_out.png" key="button.logout"></lo:menuButton><fmt:message key="logout" />
<hr />

<lo:menuTitle key="menu1.title" roles="<%=RoleNameEnum.BEHEERDER.getCode()%>"></lo:menuTitle>
<lo:menuItem key="displayRole.title" action="detailRole" method="list" roles="<%=RoleNameEnum.BEHEERDER.getCode()%>"></lo:menuItem>
<lo:menuItem key="displayUser.title" action="detailUser" method="list" roles="<%=RoleNameEnum.BEHEERDER.getCode()%>"></lo:menuItem>