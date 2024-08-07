<%@page import="be.luxuryoverdosis.framework.web.BaseWebConstants"%>
<%@page import="be.luxuryoverdosis.framework.business.service.BaseSpringServiceConstants"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="/luxuryOverdosis" prefix="lo" %>
<html:form action="/searchUser.do">
	<div align="center">
		<h2><i><fmt:message key="displaySearch.title" />&nbsp;<fmt:message key="displayUser.title" /></i></h2>
	</div>
	<lo:button image="table.png" method="list" key="button.list"></lo:button>
	<lo:button image="table_add.png" method="create" key="button.create"></lo:button>
	<lo:button image="report.png" method="report" key="button.report"></lo:button>
	<html:select property="documentId" tabindex="1">
		<html:option value="-1"><fmt:message key="select" /></html:option>
		<html:optionsCollection name="documentList" label="fileName" value="id" />
	</html:select>
	<lo:button image="page_white_text.png" method="createDocument" key="button.document"></lo:button>
	<lo:button image="page_white_acrobat.png" method="createDocumentAndConvertToPdf" key="button.document"></lo:button>
	<hr />
	<lo:search searchService="<%= BaseSpringServiceConstants.SEARCH_USER %>" searchName="<%= BaseWebConstants.SEARCH_USER_FORM %>"></lo:search>
</html:form>