<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/luxuryOverdosis" prefix="lo" %>
<%@ page import="be.luxuryoverdosis.baseapp.Constants"%>
<html:form action="/detailDocument.do" enctype="multipart/form-data">
	<div align="center">
		<h2><i><fmt:message key="displayDetail.title" />&nbsp;<fmt:message key="table.document" /></i></h2>
	</div>
	<lo:button image="table.png" method="list" key="button.list"></lo:button>
	<lo:button image="table_add.png" method="create" key="button.create"></lo:button>
	<lo:button image="table_save.png" method="update" key="button.update"></lo:button>
	<logic:notEqual name="detailDocumentForm" property="objectId" value="-1">
		<lo:button image="table_delete.png" method="delete" key="button.delete"></lo:button>
	</logic:notEqual>
	<hr />
	<html:hidden property="objectId" />
	<html:hidden property="fileName" />
	<html:hidden property="fileSize" />
	<html:hidden property="contentType" />
	<table>
		<tr>
			<td><fmt:message key="document.type" />*:</td>
			<td>
				<html:select property="type" tabindex="1">
					<html:option value="<%= Constants.DOCUMENTYPE_USER %>"><%= Constants.DOCUMENTYPE_USER %></html:option>
				</html:select>
			</td>
		</tr>
		<tr>
			<td><fmt:message key="file" />*:</td>
			<td><html:file property="formFile" size="100" maxlength="256" tabindex="2"></html:file></td>
		</tr>
		<tr>
			<td><fmt:message key="file.name" />:</td>
			<td><c:out value="${detailDocumentForm.fileName}"/></td>
		</tr>
		<tr>
			<td><fmt:message key="file.size" />:</td>
			<td><c:out value="${detailDocumentForm.fileSize}"/></td>
		</tr>
		<tr>
			<td><fmt:message key="file.content.type" />:</td>
			<td><c:out value="${detailDocumentForm.contentType}"/></td>
		</tr>
	</table>
</html:form>