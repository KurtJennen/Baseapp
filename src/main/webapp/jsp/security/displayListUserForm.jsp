<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="/luxuryOverdosis" prefix="lo" %>
<%@page import="be.luxuryoverdosis.baseapp.web.WebConstants"%>
<script>
$(document).ready(function() {
	$('#tabs').tabs({
// 		heightStyle: "fill"
	activate: function(event, ui) {
			if(ui.newPanel.selector="#tab1") {
				$( "#usersGrid" ).pqGrid( "refresh" );
			}
			if(ui.newPanel.selector="#tab2") {
				$( "#usersJobExportGrid" ).pqGrid( "refresh" );
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
			<hr />
			<lo:pqGrid selectedIds="selectedIds" url="/listUser.do?method=ajaxList" titleKey="displayUser.title" id="users" rPP="15">
				<lo:pqGridColumn width="200" dataIndx="name" dataType="string" titleKey="security.name.unique"></lo:pqGridColumn>
				<lo:pqGridColumn width="200" dataIndx="userName" dataType="string" titleKey="security.username"></lo:pqGridColumn>
				<lo:pqGridColumn width="200" dataIndx="email" dataType="string" titleKey="security.email"></lo:pqGridColumn>
				<lo:pqGridColumn width="100" dataIndx="dateExpirationAsString" dataType="string" titleKey="security.date.expiration"></lo:pqGridColumn>
				<lo:pqGridColumn width="200" dataIndx="roleName" dataType="string" titleKey="security.role"></lo:pqGridColumn>
			</lo:pqGrid>
		</div>
		<div id="tab2">
			<lo:button image="cog.png" method="exportUserJob" key="button.export"></lo:button>
			<hr />
			<lo:pqGrid selectedIds="selectedIdsJobExport" url="/listUser.do?method=ajaxListJobExport" titleKey="displayJob.title" id="usersJobExport" rPP="15" rowClickMethod="readJob">
				<lo:pqGridColumn width="200" dataIndx="jobName" dataType="string" titleKey="batchjobinstance.name"></lo:pqGridColumn>
				<lo:pqGridColumn width="200" dataIndx="batchJobExecutionVersion" dataType="string" titleKey="batchjobexecution.version"></lo:pqGridColumn>
				<lo:pqGridColumn width="200" dataIndx="batchJobExecutionCreateTimeAsString" dataType="string" titleKey="batchjobexecution.create.time"></lo:pqGridColumn>
				<lo:pqGridColumn width="200" dataIndx="batchJobExecutionStartTimeAsString" dataType="string" titleKey="batchjobexecution.start.time"></lo:pqGridColumn>
				<lo:pqGridColumn width="200" dataIndx="batchJobExecutionEndTimeAsString" dataType="string" titleKey="batchjobexecution.end.time"></lo:pqGridColumn>
				<lo:pqGridColumn width="200" dataIndx="batchJobExecutionStatusTranslated" dataType="string" titleKey="batchjobexecution.status"></lo:pqGridColumn>
				<lo:pqGridColumn width="200" dataIndx="batchJobExecutionExitCode" dataType="string" titleKey="batchjobexecution.create.exit.code"></lo:pqGridColumn>
				<lo:pqGridColumn width="200" dataIndx="batchJobExecutionExitMessage" dataType="string" titleKey="batchjobexecution.create.exit.message"></lo:pqGridColumn>
				<lo:pqGridColumn width="200" dataIndx="batchJobExecutionLastUpdatedAsString" dataType="string" titleKey="batchjobexecution.last.updated"></lo:pqGridColumn>
			</lo:pqGrid>
		</div>
		<div id="tab3">
			<fmt:message key="file" />*:
			<html:file property="formFile" size="100" maxlength="256" tabindex="1"></html:file>
			<lo:button image="cog.png" method="importUserJob" key="button.import"></lo:button>
			<hr />
			<div>
				<%= request.getSession().getAttribute(WebConstants.USER_IMPORT_LIST) %>
			</div>
		</div>
	</div>
</html:form>