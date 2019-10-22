<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="/luxuryOverdosis" prefix="lo" %>
<html:form action="/jobUser.do">
	<div align="center">
		<h2><i><fmt:message key="displayList.title" />&nbsp;<fmt:message key="displayJob.title" />&nbsp;<fmt:message key="displayUser.title" /></i></h2>
	</div>
	<lo:button image="door_out.png" method="back" key="button.back"></lo:button>
	<hr />
</html:form>