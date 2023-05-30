<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="/luxuryOverdosis" prefix="lo" %>

<lo:button image="table_edit.png" method="read${param.name}Job" key="button.edit"></lo:button>
<fmt:message key="file" />*:
<html:file property="formFile" size="100" maxlength="256" tabindex="1"></html:file>
<lo:button image="cog.png" method="create${param.name}Job" key="${param.cogKey}"></lo:button>
<lo:button image="table_delete.png" method="delete${param.name}Job" key="button.delete"></lo:button>
<hr />
<jsp:include page="listBatchJobColumns.jsp">
	<jsp:param value="${param.id}" name="id"/>
	<jsp:param value="${param.url}" name="url"/>
	<jsp:param value="${param.name}" name="name"/>
</jsp:include>
