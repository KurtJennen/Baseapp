<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/luxuryOverdosis" prefix="lo" %>

<lo:button image="table_edit.png" method="read${param.name}Job" key="button.edit"></lo:button>
<c:if test="${param.cogVisible == true}">
	<lo:button image="cog.png" method="create${param.name}Job" key="${param.cogKey}"></lo:button>
</c:if>
<lo:button image="table_delete.png" method="delete${param.name}Job" key="button.delete"></lo:button>
<hr />
<jsp:include page="listBatchJobColumns.jsp">
	<jsp:param value="${param.id}" name="id"/>
	<jsp:param value="${param.url}" name="url"/>
	<jsp:param value="${param.name}" name="name"/>
</jsp:include>
