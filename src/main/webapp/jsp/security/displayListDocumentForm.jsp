<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="/luxuryOverdosis" prefix="lo" %>
<%@page import="be.luxuryoverdosis.baseapp.web.WebConstants"%>
<html:form action="/listDocument.do">
	<div class="center">
		<h2><i><fmt:message key="displayList.title" />&nbsp;<fmt:message key="displayDocument.title" /></i></h2>
	</div>
	<lo:button image="table_add.png" method="create" key="button.create"></lo:button>
	<lo:button image="table_edit.png" method="read" key="button.edit"></lo:button>
	<hr />
	<lo:pqGrid selectedIds="selectedIds" url="/listDocument.do?method=ajaxList" titleKey="displayDocument.title" id="documents" rPP="15">
		<lo:pqGridColumn width="100" dataIndx="type" dataType="string" titleKey="document.type"></lo:pqGridColumn>
		<lo:pqGridColumn width="150" dataIndx="fileName" dataType="string" titleKey="file.name"></lo:pqGridColumn>
		<lo:pqGridColumn width="100" dataIndx="fileSize" dataType="string" titleKey="file.size"></lo:pqGridColumn>
		<lo:pqGridColumn width="400" dataIndx="contentType" dataType="string" titleKey="file.content.type"></lo:pqGridColumn>
	</lo:pqGrid>
</html:form>