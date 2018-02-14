<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<c:if test="${user.userName!=null}">
	<html:img src="images/user.png" altKey="security.username" />&nbsp;<fmt:message key="welcome" />&nbsp;<c:out value="${user.userName}" />
</c:if>