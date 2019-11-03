<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<html:form action="/login.do">
	<div align="center">
		<h2><i><fmt:message key="displayWelcome.title" /></i></h2>
		<html:img src="images/application.gif" altKey="title.name" />
		<c:if test="${loginForm.deactivation==true}">
			<h1><c:out value="${loginForm.deactivationMessage}"/></h1>
		</c:if>
	</div>
</html:form>