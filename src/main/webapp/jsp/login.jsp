<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://ditchnet.org/jsp-tabs-taglib" prefix="tab" %>
<%@ taglib uri="/taglib" prefix="t" %> 
<%@ taglib uri="/luxuryOverdosis" prefix="lo" %>
<html:form action="/login.do">
	<div align="center">
		<t:tabpanel id="loginPanel" width="99%" height="450px">
			<t:panel selected="true">
				<t:panelTab left="0"><fmt:message key="login" /></t:panelTab>
				<t:panelBody>
					<table>
						<tr>
							<td class="center"><html:img src="images/application.gif" altKey="title.name" /></td>
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
											<lo:button image="user.png" method="register" key="button.register"></lo:button>
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
				</t:panelBody>
			</t:panel>
			<t:panel>
				<t:panelTab left="101"><fmt:message key="info" /></t:panelTab>
				<t:panelBody>
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
				</t:panelBody>
			</t:panel>
		</t:tabpanel>
<!--		<tab:tabContainer id="loginTag" skin="wireframe">-->
<!--			<tab:tabPane id="login" tabTitle="Inloggen">-->
<!--				-->
<!--			</tab:tabPane>-->
<!--			-->
<!--			<tab:tabPane id="info" tabTitle="Info">-->
<!--				-->
<!--			</tab:tabPane>-->
<!--		</tab:tabContainer>-->
	</div>
</html:form>