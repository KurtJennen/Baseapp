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
			<li><a href="#tab3"><fmt:message key="keycloak.token" /></a></li>
			<li><a href="#tab4"><fmt:message key="keycloak.refresh" /></a></li>
			<li><a href="#tab5"><fmt:message key="keycloak.userinfo" /></a></li>
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
						<html:textarea property="configurationResult" cols="200" rows="25" tabindex="3"></html:textarea>
					</td>
				</tr>
			</table>
		</div>
		
		<div id="tab2">
			<lo:button image="cog.png" method="authentication" key="button.keycloak.authentication"></lo:button>
			<hr />
			<table>
				<tr>
					<td><fmt:message key="keycloak.authentication" />*:</td>
					<td>
						<html:text property="authentication" size="120" maxlength="120" tabindex="4"></html:text>
					</td>
				</tr>
				<tr>
					<td><fmt:message key="keycloak.client.id" />*:</td>
					<td>
						<html:text property="clientId" size="120" maxlength="120" tabindex="5"></html:text>
					</td>
				</tr>
				<tr>
					<td><fmt:message key="keycloak.response.type" />*:</td>
					<td>
						<html:text property="responseType" size="120" maxlength="120" tabindex="6"></html:text>
					</td>
				</tr>
				<tr>
					<td><fmt:message key="keycloak.redirect.uri" />*:</td>
					<td>
						<html:text property="redirectUri" size="120" maxlength="120" tabindex="7"></html:text>
					</td>
				</tr>
				<tr>
					<td><fmt:message key="keycloak.scope" />*:</td>
					<td>
						<html:text property="scope" size="120" maxlength="120" tabindex="8"></html:text>
					</td>
				</tr>
				<tr>
					<td><fmt:message key="keycloak.result" />*:</td>
					<td>
						<html:textarea property="authenticationResult" cols="200" rows="2" tabindex="9"></html:textarea>
					</td>
				</tr>
			</table>
		</div>
		<div id="tab3">
			<lo:button image="cog.png" method="token" key="button.keycloak.token"></lo:button>
			<table>
				<tr>
					<td><fmt:message key="keycloak.token" />*:</td>
					<td>
						<html:text property="token" size="120" maxlength="120" tabindex="10"></html:text>
					</td>
				</tr>
				<tr>
					<td><fmt:message key="keycloak.grant.type" />*:</td>
					<td>
						<html:text property="grantType" size="120" maxlength="120" tabindex="11"></html:text>
					</td>
				</tr>
				<tr>
					<td><fmt:message key="keycloak.code" />*:</td>
					<td>
						<html:text property="authenticationResult" size="120" maxlength="120" tabindex="12"></html:text>
					</td>
				</tr>
				<tr>
					<td><fmt:message key="keycloak.client.id" />*:</td>
					<td>
						<html:text property="clientId" size="120" maxlength="120" tabindex="13"></html:text>
					</td>
				</tr>
				<tr>
					<td><fmt:message key="keycloak.redirect.uri" />*:</td>
					<td>
						<html:text property="tokenRedirectUri" size="120" maxlength="120" tabindex="14"></html:text>
					</td>
				</tr>
				<tr>
					<td><fmt:message key="keycloak.result" />*:</td>
					<td>
						<html:textarea property="tokenResult" cols="250" rows="25" tabindex="15"></html:textarea>
					</td>
				</tr>
				<tr>
					<td><fmt:message key="keycloak.refresh.token" />*:</td>
					<td>
						<html:textarea property="refreshToken" cols="250" rows="1" tabindex="16"></html:textarea>
					</td>
				</tr>
				<tr>
					<td><fmt:message key="keycloak.id.token" />*:</td>
					<td>
						<html:textarea property="idToken" cols="250" rows="5" tabindex="17"></html:textarea>
					</td>
				</tr>
				<tr>
					<td><fmt:message key="keycloak.id.token.header" />*:</td>
					<td>
						<html:textarea property="idTokenHeader" cols="250" rows="5" tabindex="17"></html:textarea>
					</td>
				</tr>
				<tr>
					<td><fmt:message key="keycloak.id.token.payload" />*:</td>
					<td>
						<html:textarea property="idTokenPayload" cols="250" rows="25" tabindex="18"></html:textarea>
					</td>
				</tr>
				<tr>
					<td><fmt:message key="keycloak.id.token.signature" />*:</td>
					<td>
						<html:textarea property="idTokenSignature" cols="250" rows="5" tabindex="19"></html:textarea>
					</td>
				</tr>
			</table>
		</div>
		<div id="tab4">
			<lo:button image="cog.png" method="refresh" key="button.keycloak.refresh"></lo:button>
			<table>
				<tr>
					<td><fmt:message key="keycloak.refresh" />*:</td>
					<td>
						<html:textarea property="refreshResult" cols="250" rows="25" tabindex="20"></html:textarea>
					</td>
				</tr>
			</table>
		</div>
		<div id="tab5">
			<lo:button image="cog.png" method="userinfo" key="button.keycloak.userinfo"></lo:button>
			<table>
				<tr>
					<td><fmt:message key="keycloak.userinfo" />*:</td>
					<td>
						<html:text property="userinfo" size="120" maxlength="120" tabindex="10"></html:text>
					</td>
				</tr>
				<tr>
					<td><fmt:message key="keycloak.userinfo" />*:</td>
					<td>
						<html:textarea property="userinfoResult" cols="250" rows="25" tabindex="21"></html:textarea>
					</td>
				</tr>
			</table>
		</div>
	</div>
</html:form>