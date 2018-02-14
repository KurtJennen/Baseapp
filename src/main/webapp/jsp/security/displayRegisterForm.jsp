<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://ditchnet.org/jsp-tabs-taglib" prefix="tab" %>
<%@ taglib uri="/luxuryOverdosis" prefix="lo" %>
<html:form action="/loginRegister.do">
	<tab:tabContainer id="loginTag" skin="wireframe">
		
		<tab:tabPane id="login" tabTitle="Registreren">
			<div align="center">
				<h2><i><fmt:message key="displayRegister.title" /></i></h2>
			</div>
			<hr />
			<lo:button image="table_save.png" method="update" key="button.update"></lo:button>
			<hr />
			<html:hidden property="objectId" />
			<html:hidden property="roleId" value="0" />
			<table>
				<tr>
					<td><fmt:message key="security.name.unique" />:</td>
					<td><html:text property="name" size="45" maxlength="45" tabindex="1"></html:text></td>
				</tr>
				<tr>
					<td><fmt:message key="security.username" />:</td>
					<td><html:text property="userName" size="45" maxlength="45" tabindex="2"></html:text></td>
				</tr>
				<tr>
					<td><fmt:message key="security.password" />:</td>
					<td><html:password property="password" size="45" maxlength="45" tabindex="3"></html:password></td>
				</tr>
				<tr>
					<td><fmt:message key="security.password.confirm" />:</td>
					<td><html:password property="passwordConfirm" size="45" maxlength="45" tabindex="4"></html:password></td>
				</tr>
				<tr>
					<td><fmt:message key="security.email" />:</td>
					<td><html:text property="email" size="45" maxlength="45" tabindex="5"></html:text></td>
				</tr>
			</table>
		</tab:tabPane>
	</tab:tabContainer>
</html:form>