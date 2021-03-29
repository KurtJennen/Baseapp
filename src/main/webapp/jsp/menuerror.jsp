<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<html:form action="/login.do">
	<div class="error" align="center">
		<html:img src="images/application.gif" altKey="title.name" />
		<h3>
			<fmt:message key="error" /><br/>
			<fmt:message key="errors" /><br/>
			<fmt:message key="menu.disabled.for.pay" />
		</h3>
	</div>
</html:form>