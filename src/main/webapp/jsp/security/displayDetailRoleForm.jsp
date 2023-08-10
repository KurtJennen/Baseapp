<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/luxuryOverdosis" prefix="lo" %>
<html:form action="/detailRole.do">
	<div align="center">
		<h2><i><fmt:message key="displayDetail.title" />&nbsp;<fmt:message key="table.role" /></i></h2>
	</div>
	<lo:button image="table.png" method="list" key="button.list"></lo:button>
	<lo:button image="table_add.png" method="create" key="button.create"></lo:button>
	<lo:button image="table_save.png" method="update" key="button.update"></lo:button>
	<logic:notEqual name="detailRoleForm" property="id" value="-1">
		<lo:button image="table_delete.png" method="delete" key="button.delete"></lo:button>
	</logic:notEqual>
	<lo:navigation nameIds="roleIds" firstVisible="${detailRoleForm.firstVisible}" previousVisible="${detailRoleForm.previousVisible}" nextVisible="${detailRoleForm.nextVisible}" lastVisible="${detailRoleForm.lastVisible}"/>
	<hr />
	<html:hidden property="id" />
	<table>
		<tr>
			<td><fmt:message key="security.name" />*:</td>
			<td>
				<lo:enumSelect clazz="be.luxuryoverdosis.framework.business.enumeration.RoleNameEnum" tabindex="1" property="name" value="${detailRoleForm.name}" method="getAllCodes"/>
			</td>
<%-- 			<td><lo:enumRadio clazz="be.luxuryoverdosis.framework.business.enumeration.RoleNameEnum" tabindex="1" property="name" value="${detailRoleForm.name}"  method="getAllCodes"/> </td> --%>
		</tr>
	</table>
</html:form>