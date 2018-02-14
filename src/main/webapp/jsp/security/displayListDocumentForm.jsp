<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="/luxuryOverdosis" prefix="lo" %>
<%@page import="be.luxuryoverdosis.baseapp.web.WebConstants"%>
<html:form action="/detailDocument.do">
	<div class="center">
		<h2><i><fmt:message key="displayList.title" />&nbsp;<fmt:message key="displayDocument.title" /></i></h2>
	</div>
	<lo:button image="table_add.png" method="create" key="button.create"></lo:button>
	<hr />
	<div class="jmesatag">
		<%= request.getAttribute(WebConstants.DOCUMENT_LIST) %>
	</div>
</html:form>