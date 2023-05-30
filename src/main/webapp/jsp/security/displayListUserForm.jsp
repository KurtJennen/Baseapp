<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="/luxuryOverdosis" prefix="lo" %>
<script>
$(document).ready(function() {
	$('#tabs').tabs({
// 		heightStyle: "fill"
		active: ${listUserForm.selectedTab},
		activate: function(event, ui) {
	        var index = ui.newTab.index();
	        $("#selectedTab").prop("value", index);
	        
			if(ui.newPanel.selector=="#tab1") {
				$("#userGrid").pqGrid("refresh");
			}
			if(ui.newPanel.selector=="#tab2") {
				$("#exportUserJobGrid").pqGrid("refresh");
			}
			if(ui.newPanel.selector=="#tab3") {
				$("#importUserJobGrid").pqGrid("refresh");
			}
		}
	});
	
// 	function resizeUi() {
// 		var h = $("#tabs").parent().height();
// 	    $("#tabs").css('height', h);
// 	};
	
// 	resizeUi();
});
</script>

<html:form action="/listUser.do" enctype="multipart/form-data">
	<html:hidden property="selectedTab" styleId="selectedTab"/>
	<div align="center">
		<h2><i><fmt:message key="displayList.title" />&nbsp;<fmt:message key="displayUser.title" /></i></h2>
	</div>
	<div id="tabs">
		<ul>
			<li><a href="#tab1"><fmt:message key="displayUser.title" /></a></li>
			<li><a href="#tab2"><fmt:message key="job.export" /></a></li>
			<li><a href="#tab3"><fmt:message key="job.import" /></a></li>
		</ul>
		<div id="tab1">
			<lo:button image="zoom.png" method="search" key="button.search"></lo:button>
			<lo:button image="table_add.png" method="create" key="button.create"></lo:button>
			<lo:button image="table_edit.png" method="read" key="button.edit"></lo:button>
			<hr />
			<lo:pqGrid nameSelectedIds="selectedIds" url="/listUser.do?method=ajaxList" titleKey="displayUser.title" id="user" rPP="15">
				<lo:pqGridColumn width="200" dataIndx="name" dataType="string" titleKey="security.name.unique"></lo:pqGridColumn>
<%-- 				<lo:pqGridColumn width="200" dataIndx="name" dataType="string" titleKey="security.name.unique" renderFunction="renderNaamClassPq"></lo:pqGridColumn> --%>
				<lo:pqGridColumn width="200" dataIndx="userName" dataType="string" titleKey="security.username"></lo:pqGridColumn>
				<lo:pqGridColumn width="200" dataIndx="email" dataType="string" titleKey="security.email"></lo:pqGridColumn>
				<lo:pqGridColumn width="100" dataIndx="dateExpirationAsString" dataType="string" titleKey="security.date.expiration"></lo:pqGridColumn>
<%-- 				<lo:pqGridColumn width="200" dataIndx="roleName" dataType="string" titleKey="security.role"></lo:pqGridColumn> --%>
<%-- 				<lo:pqGridColumn width="200" dataIndx="bedrag" dataType="float" titleKey="bedrag" align="right" totalizable="true" currency="true" renderFunction="renderBedragClassPq"></lo:pqGridColumn> --%>
			</lo:pqGrid>
		</div>
		<div id="tab2">
			<jsp:include page="../job/exportBatchJob.jsp">
				<jsp:param value="exportUser" name="id"/>
				<jsp:param value="User" name="url"/>
				<jsp:param value="ExportUser" name="name"/>
			</jsp:include>
		</div>
		<div id="tab3">
			<jsp:include page="../job/importBatchJob.jsp">
				<jsp:param value="importUser" name="id"/>
				<jsp:param value="User" name="url"/>
				<jsp:param value="ImportUser" name="name"/>
				<jsp:param value="button.import" name="cogKey"/>
			</jsp:include>
		</div>
	</div>
</html:form>