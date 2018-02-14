<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="/luxuryOverdosis" prefix="lo" %>
<%@page import="be.luxuryoverdosis.baseapp.web.WebConstants"%>
<%@page import="be.luxuryoverdosis.baseapp.business.service.SpringServiceConstants"%>
<html:form action="/searchUser.do">
	<div align="center">
		<h2><i><fmt:message key="displaySearch.title" />&nbsp;<fmt:message key="displayUser.title" /></i></h2>
	</div>
	<lo:button image="table.png" method="list" key="button.list"></lo:button>
	<lo:button image="table_add.png" method="create" key="button.create"></lo:button>
	<html:select property="documentId" tabindex="1">
		<html:option value="-1"><fmt:message key="select" /></html:option>
		<html:optionsCollection name="documentList" label="fileName" value="id" />
	</html:select>
	<lo:button image="page_white_text.png" method="createDocument" key="button.document"></lo:button>
	<hr />
	<lo:search searchService="<%= SpringServiceConstants.SEARCH_USER %>" searchName="<%= WebConstants.SEARCH_USER_FORM %>"></lo:search>
</html:form>