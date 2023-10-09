<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="/luxuryOverdosis" prefix="lo" %>

<script>
$(document).ready(function() {
	$('#tabs').tabs({
// 		heightStyle: "fill"
		active: ${keycloakForm.selectedTab},
		activate: function(event, ui) {
	        var index = ui.newTab.index();
	        $("#selectedTab").prop("value", index);
		}
	});
	
// 	function resizeUi() {
// 		var h = $("#tabs").parent().height();
// 	    $("#tabs").css('height', h);
// 	};
	
// 	resizeUi();
});
</script>


<html:form action="/keycloak.do">
	<div align="center">
		<h2><i><fmt:message key="displayKeycloak.title" /></i></h2>
	</div>
	
	<div id="tabs">
		<ul>
			<li><a href="#tab1"><fmt:message key="keycloak.configuration" /></a></li>
			<li><a href="#tab2"><fmt:message key="keycloak.authentication" /></a></li>
		</ul>
		
		<div id="tab1">
			<lo:button image="cog.png" method="configuration" key="button.keycloak.configuration"></lo:button>
			<hr />
			<table>
				<tr>
					<td><fmt:message key="keycloak.default.uri" />*:</td>
					<td>
						<html:text property="defaultUri" size="120" maxlength="120" tabindex="1"></html:text>
					</td>
				</tr>
				<tr>
					<td><fmt:message key="keycloak.configuration" />*:</td>
					<td>
						<html:text property="configuration" size="120" maxlength="120" tabindex="2"></html:text>
					</td>
				</tr>
				<tr>
					<td><fmt:message key="keycloak.result" />*:</td>
					<td>
						<html:textarea property="result" cols="200" rows="25"></html:textarea>
					</td>
				</tr>
			</table>
		</div>
		
		<div id="tab2">
			<lo:button image="cog.png" method="authentication" key="button.keycloak.configuration"></lo:button>
			<hr />
			<table>
				<tr>
					<td><fmt:message key="keycloak.authentication" />*:</td>
					<td>
						<html:text property="authentication" size="120" maxlength="120" tabindex="3"></html:text>
					</td>
				</tr>
				<tr>
					<td><fmt:message key="keycloak.client.id" />*:</td>
					<td>
						<html:text property="clientId" size="120" maxlength="120" tabindex="4"></html:text>
					</td>
				</tr>
				<tr>
					<td><fmt:message key="keycloak.response.type" />*:</td>
					<td>
						<html:text property="responseType" size="120" maxlength="120" tabindex="4"></html:text>
					</td>
				</tr>
				<tr>
					<td><fmt:message key="keycloak.redirect.uri" />*:</td>
					<td>
						<html:text property="redirectUri" size="120" maxlength="120" tabindex="4"></html:text>
					</td>
				</tr>
				<tr>
					<td><fmt:message key="keycloak.scope" />*:</td>
					<td>
						<html:text property="scope" size="120" maxlength="120" tabindex="4"></html:text>
					</td>
				</tr>
			</table>
		</div>
		
	</div>
</html:form>