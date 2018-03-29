<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/luxuryOverdosis" prefix="lo" %>
<html:form action="/detailNumber.do">
	<div align="center">
		<h2><i><fmt:message key="displayDetail.title" />&nbsp;<fmt:message key="table.number" /></i></h2>
	</div>
	<lo:button image="table.png" method="list" key="button.list"></lo:button>
	<lo:button image="table_add.png" method="create" key="button.create"></lo:button>
	<lo:button image="table_save.png" method="update" key="button.update"></lo:button>
	<logic:notEqual name="numberForm" property="id" value="-1">
		<lo:button image="table_delete.png" method="delete" key="button.delete"></lo:button>
	</logic:notEqual>
	<hr />
	<html:hidden property="id" />
	<table>
		<tr>
			<td><fmt:message key="security.number.application.code" />*:</td>
			<td><html:text property="applicationCode" size="4" maxlength="4" tabindex="1"></html:text></td>
		</tr>
		<tr>
			<td><fmt:message key="security.number.year" />*:</td>
			<td><html:text property="year" size="4" maxlength="4" tabindex="2"></html:text></td>
		</tr>
		<tr>
			<td><fmt:message key="security.number.number" />*:</td>
			<td><html:text property="number" size="10" maxlength="10" tabindex="3"></html:text></td>
		</tr>
		<tr>
			<td><fmt:message key="security.number.type" />*:</td>
			<td><html:text property="type" size="2" maxlength="2" tabindex="4"></html:text></td>
		</tr>
	</table>
</html:form>