<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	<script type="text/javascript" src="client/luxuryOverdosis.js"></script>
	<script type="text/javascript" src="client/luxuryOverdosisAjax.js"></script>
	<script type="text/javascript" src="client/menuDropdown.js"></script>
	<script type="text/javascript" src="client/jquery.js"></script>
	<script type="text/javascript" src="client/jquery.tmpl.js"></script>
	<script type="text/javascript" src="client/jquery.jmesa.js"></script>
	<script type="text/javascript" src="client/jquery-ui.js"></script>
	<script type="text/javascript" src="client/jmesa.js"></script>
	<link type="text/css" rel="stylesheet" href="css/menuDropdown.css" />
	<link type="text/css" rel="stylesheet" href="css/jmesa.css" />
	<link type="text/css" rel="stylesheet" href="css/jquery-ui.css" />
	<link type="text/css" rel="stylesheet" href="css/layout.css" />
	<title><fmt:message key="title.name" /></title>
</head>
<body>
	<table class="tiletable">
		<tr>
			<td class="ui-tabs ui-corner-all ui-widget ui-widget-content defaultheight"><tiles:insert attribute="menu" /></td>
		</tr>
		<tr>
			<td class="ui-tabs ui-corner-all ui-widget ui-widget-content defaultheight"><tiles:insert attribute="header" /></td>
		</tr>
		<tr id="body">
			<td class="ui-tabs ui-corner-all ui-widget ui-widget-content td"><tiles:insert attribute="body" /></td>
		</tr>
		<tr>
			<c:choose>
				<c:when test="${error==1}">
					<td class="ui-tabs ui-corner-all ui-widget ui-widget-content ui-state-error defaultheight"><tiles:insert attribute="messages" /></td>
				</c:when>
				<c:otherwise>
					<td class="ui-tabs ui-corner-all ui-widget ui-widget-content defaultheight"><tiles:insert attribute="messages" /></td>
				</c:otherwise>
			</c:choose>
		</tr>
	</table>
</body>
</html>