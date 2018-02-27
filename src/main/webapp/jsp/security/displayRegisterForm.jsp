<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="/luxuryOverdosis" prefix="lo" %>
<script>
	$(document).ready(function() {
		$('#tabs').tabs({
// 			heightStyle: "fill"
		});
		
// 		function resizeUi() {
// 			var h = $("#tabs").parent().parent().height();
// 		    $("#tabs").css('height', h);
// 		};
		
// 		resizeUi();
	});
</script>

<html:form action="/loginRegister.do">
	<div id="tabs" align="center">
		<ul>
			<li><a href="#tab1"><fmt:message key="displayRegister.title" /></a>
		</ul>
		<div id="tab1">
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
		</div>
	</div>
</html:form>