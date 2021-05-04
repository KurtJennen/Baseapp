<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/luxuryOverdosis" prefix="lo" %>
<html:form action="/detailUser.do">
	<div align="center">
		<h2><i><fmt:message key="displayDetail.title" />&nbsp;<fmt:message key="table.user" /></i></h2>
	</div>
	<lo:button image="zoom.png" method="search" key="button.search"></lo:button>
	<lo:button image="table.png" method="list" key="button.list"></lo:button>
	<lo:button image="table_add.png" method="create" key="button.create"></lo:button>
	<lo:button image="table_save.png" method="update" key="button.update"></lo:button>
	<logic:notEqual name="detailUserForm" property="id" value="-1">
		<lo:button image="table_delete.png" method="delete" key="button.delete"></lo:button>
		<lo:button image="flag_green.png" method="activateYear" key="button.activate.year"></lo:button>
		<lo:button image="flag_orange.png" method="activateHalfYear" key="button.activate.half.year"></lo:button>
		<lo:button image="flag_red.png" method="deactivate" key="button.deactivate"></lo:button>
	</logic:notEqual>
	<lo:navigation nameIds="userIds" firstVisible="${detailUserForm.firstVisible}" previousVisible="${detailUserForm.previousVisible}" nextVisible="${detailUserForm.nextVisible}" lastVisible="${detailUserForm.lastVisible}"/>
	<hr />
	<html:hidden property="id" />
	<html:hidden property="date" />
	<table>
		<tr>
			<td><fmt:message key="security.name.unique" />*:</td>
			<td><html:text property="name" size="45" maxlength="45" tabindex="1"></html:text></td>
		</tr>
		<tr>
			<td><fmt:message key="security.username" />*:</td>
			<td><html:text property="userName" size="45" maxlength="45" tabindex="2"></html:text></td>
		</tr>
		<tr>
			<td><fmt:message key="security.password" />*:</td>
			<td><html:password property="password" size="45" maxlength="45" tabindex="3"></html:password></td>
		</tr>
		<tr>
			<td><fmt:message key="security.password.confirm" />*:</td>
			<td><html:password property="passwordConfirm" size="45" maxlength="45" tabindex="4"></html:password></td>
		</tr>
		<tr>
			<td><fmt:message key="security.email" />*:</td>
			<td><html:text property="email" size="45" maxlength="45" tabindex="5"></html:text></td>
		</tr>
		<tr>
			<td><fmt:message key="security.date.expiration" />*:</td>
			<td><c:out value="${detailUserForm.date}"/></td>
		</tr>
		<tr>
			<td><fmt:message key="security.role" />*:</td>
			<td>
<%-- 				<html:select property="roleId" tabindex="6"> --%>
<%-- 					<html:option value="-1"><fmt:message key="select" /></html:option> --%>
<%-- 					<html:optionsCollection name="roleList" label="name" value="id" /> --%>
<%-- 				</html:select> --%>
				<lo:ajaxSelect property="roleId" methodAll="detailUser.do?method=ajaxSearchAllRole" methodOne="detailUser.do?method=ajaxSearchOneRole" fieldsAll="id,nameAsKey" fieldsOne="id,nameAsKey"  key="button.search" maxLength="45" size="45"></lo:ajaxSelect>
			</td>
		</tr>
	</table>
	<table>
		<tr>
			<td><fmt:message key="user.role.gekoppeld" />:</td>
			<td><fmt:message key="user.role.ontkoppeld" />:</td>
		</tr>
		<tr>
			<td>
				<html:select property="linkedRoleIds" tabindex="7" multiple="true" size="10" style="width: 600px">
					<html:optionsCollection name="userRoleLinkedList" label="roleNameAsKey" value="roleId" />
				</html:select>
			</td>
			<td>
				<html:select property="unlinkedRoleIds" tabindex="8" multiple="true" size="10" style="width: 600px">
					<html:optionsCollection name="userRoleUnLinkedList" label="nameAsKey" value="id" />
				</html:select>
			</td>
		</tr>
	</table>
</html:form>