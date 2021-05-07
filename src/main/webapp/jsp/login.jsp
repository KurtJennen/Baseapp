<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
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

<html:form action="/login.do">
	<html:hidden property="activation" />
	<div id="tabs" align="center">
		<ul>
			<li><a href="#tab1"><fmt:message key="login" /></a></li>
			<c:if test="${loginForm.activation==true}">
				<li><a href="#tab2"><fmt:message key="info" /></a></li>
			</c:if>
		</ul>
		<div id="tab1">
			<table>
				<tr>
					<td class="center">
						<html:img src="images/application.gif" altKey="title.name" />
					</td>
				</tr>
				<tr>
					<td class="center"><h1><fmt:message key="title.name.prefix" />&nbsp;<fmt:message key="title.name" /></h1></td>
				</tr>
				<tr>
					<td>
						<table>
							<tr>
								<td colspan="3" class="center">
									<lo:button image="door_in.png" method="login" key="button.login"></lo:button>
									<c:if test="${loginForm.activation==true}">
										<lo:button image="user.png" method="register" key="button.register"></lo:button>
									</c:if>
								</td>
							</tr>
							<tr>
								<td><fmt:message key="security.name" />:</td>
								<td><html:text property="name" size="45" maxlength="45" tabindex="1"></html:text></td>
							</tr>
							<tr>
								<td><fmt:message key="security.password" />:</td>
								<td><html:password property="password" size="45" maxlength="45" tabindex="2"></html:password></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class="center"><fmt:message key="login.title" /></td>
				</tr>
			</table>
		</div>
		<c:if test="${loginForm.activation==true}">
			<div id="tab2" class="center">
				<fmt:message key="subscription" /><br />
				<br />
				<fmt:message key="subscription1" /><br />
				<br />
				<fmt:message key="subscription2" /><br />
				<br />
				<fmt:message key="subscription3" /><br />
				<br />
				<fmt:message key="subscription4" /><br />
				<br />
				<fmt:message key="subscription5" /><br />
				<br />
				<fmt:message key="subscription6" /><br />
				<br />
				<i>
					<fmt:message key="copyright" />
					<a href="mailto:ubuntu.form.zero@skynet.be"><fmt:message key="programmer.name" /></a><br />
					<fmt:message key="programmer.title" />
				</i>
			</div>
		</c:if>
	</div>
</html:form>