<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="/luxuryOverdosis" prefix="lo" %>
<%@page import="be.luxuryoverdosis.baseapp.web.WebConstants"%>
<html:form action="/listNumber.do">
	<div class="center">
		<h2><i><fmt:message key="displayList.title" />&nbsp;<fmt:message key="displayNumber.title" /></i></h2>
	</div>
	<lo:button image="table_edit.png" method="read" key="button.edit"></lo:button>
	<lo:button image="table_add.png" method="create" key="button.create"></lo:button>
	<hr />
	<lo:pqGrid selectedIds="selectedIds" url="/listNumber.do?method=ajaxList" titleKey="displayNumber.title" id="numbers" rPP="15">
		<lo:pqGridColumn width="100" dataIndx="applicationCode" dataType="string" titleKey="security.number.application.code"></lo:pqGridColumn>
		<lo:pqGridColumn width="50" dataIndx="year" dataType="string" titleKey="security.number.year"></lo:pqGridColumn>
		<lo:pqGridColumn width="100" dataIndx="number" dataType="integer" titleKey="security.number.number"></lo:pqGridColumn>
		<lo:pqGridColumn width="100" dataIndx="type" dataType="string" titleKey="security.number.type"></lo:pqGridColumn>
	</lo:pqGrid>
</html:form>