<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="/luxuryOverdosis" prefix="lo" %>
<%@page import="be.luxuryoverdosis.baseapp.web.WebConstants"%>
<html:form action="/listRole.do">
	<div class="center">
		<h2><i><fmt:message key="displayList.title" />&nbsp;<fmt:message key="displayRole.title" /></i></h2>
	</div>
	<lo:button image="table_add.png" method="create" key="button.create"></lo:button>
	<lo:button image="table_edit.png" method="read" key="button.edit"></lo:button>
	<hr />
	<lo:pqGrid selectedIds="selectedIds" url="/listRole.do?method=ajaxList" titleKey="displayRole.title" id="rollen" rPP="15">
		<lo:pqGridColumn width="200" dataIndx="name" dataType="string" titleKey="security.name"></lo:pqGridColumn>
	</lo:pqGrid>
</html:form>