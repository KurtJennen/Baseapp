<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/luxuryOverdosis" prefix="lo" %>
<script>
$(document).ready(function() {
	$('#tabs').tabs({
// 		heightStyle: "fill"
	active: ${jobForm.selectedTab},
	activate: function(event, ui) {
			if(ui.newPanel.selector=="#tab1") {
				$("#batchJobParamsGrid").pqGrid("refresh");
				$("#selectedTab").prop("value", 0);
			}
			if(ui.newPanel.selector=="#tab2") {
				$("#batchJobExecutionParamsGrid").pqGrid("refresh");
				$("#selectedTab").prop("value", 1);
			}
			if(ui.newPanel.selector=="#tab3") {
				$("#batchJobStepExecutionGrid").pqGrid("refresh");
				$("#selectedTab").prop("value", 2);
			}
			if(ui.newPanel.selector=="#tab4") {
				$("#jobLogGrid").pqGrid("refresh");
				$("#selectedTab").prop("value", 3);
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

<html:form action="/detailJob.do">
	<html:hidden property="selectedTab" styleId="selectedTab"/>
	<div align="center">
		<h2><i><fmt:message key="displayDetail.title" />&nbsp;<fmt:message key="table.job" /></i></h2>
	</div>
	<lo:button image="door_out.png" method="back" key="button.back"></lo:button>
	<lo:button image="arrow_down.png" method="downloadFile" key="button.download"></lo:button>
	<br/>
	<hr/>
	<fmt:message key="job.name" /> : <c:out value="${jobForm.jobName}"/>
	
	<div id="tabs">
		<ul>
			<li><a href="#tab1"><fmt:message key="job.parameters" /></a></li>
			<li><a href="#tab2"><fmt:message key="job.execution.parameters" /></a></li>
			<li><a href="#tab3"><fmt:message key="job.steps" /></a></li>
			<li><a href="#tab4"><fmt:message key="displayJobLog.title" /></a></li>
		</ul>
		<div id="tab1">
			<lo:pqGrid selectedIds="selectedIdsBatchJobParams" url="/detailJob.do?method=ajaxBatchJobParamsList" titleKey="displayJobParams.title" id="batchJobParams" rPP="15" rowClickMethod="">
				<lo:pqGridColumn width="100" dataIndx="typeCode" dataType="string" titleKey="batchjobparams.type.code"></lo:pqGridColumn>
				<lo:pqGridColumn width="100" dataIndx="keyName" dataType="string" titleKey="batchjobparams.key.name"></lo:pqGridColumn>
				<lo:pqGridColumn width="100" dataIndx="stringValue" dataType="string" titleKey="batchjobparams.string.value"></lo:pqGridColumn>
				<lo:pqGridColumn width="150" dataIndx="dateValueAsString" dataType="string" titleKey="batchjobparams.date.value"></lo:pqGridColumn>
				<lo:pqGridColumn width="100" dataIndx="longValue" dataType="string" titleKey="batchjobparams.long.value"></lo:pqGridColumn>
				<lo:pqGridColumn width="100" dataIndx="doubleValue" dataType="string" titleKey="batchjobparams.double.value"></lo:pqGridColumn>
			</lo:pqGrid>
		</div>
		<div id="tab2">
			<lo:pqGrid selectedIds="selectedIdsBatchJobExecutionParams" url="/detailJob.do?method=ajaxBatchJobExecutionParamsList" titleKey="displayJobExecutionParams.title" id="batchJobExecutionParams" rPP="15" rowClickMethod="">
				<lo:pqGridColumn width="100" dataIndx="typeCode" dataType="string" titleKey="batchjobexecutionparams.type.code"></lo:pqGridColumn>
				<lo:pqGridColumn width="100" dataIndx="keyName" dataType="string" titleKey="batchjobexecutionparams.key.name"></lo:pqGridColumn>
				<lo:pqGridColumn width="100" dataIndx="stringValue" dataType="string" titleKey="batchjobexecutionparams.string.value"></lo:pqGridColumn>
				<lo:pqGridColumn width="150" dataIndx="dateValueAsString" dataType="string" titleKey="batchjobexecutionparams.date.value"></lo:pqGridColumn>
				<lo:pqGridColumn width="100" dataIndx="longValue" dataType="string" titleKey="batchjobexecutionparams.long.value"></lo:pqGridColumn>
				<lo:pqGridColumn width="100" dataIndx="doubleValue" dataType="string" titleKey="batchjobexecutionparams.double.value"></lo:pqGridColumn>
				<lo:pqGridColumn width="100" dataIndx="identifying" dataType="string" titleKey="batchjobexecutionparams.identifying"></lo:pqGridColumn>
			</lo:pqGrid>
		</div>
		<div id="tab3">
			<lo:pqGrid selectedIds="selectedIdsBatchJobStepExecution" url="/detailJob.do?method=ajaxBatchJobStepExecutionList" titleKey="displayJob.title" id="batchJobStepExecution" rPP="15" rowClickMethod="">
				<lo:pqGridColumn width="50" dataIndx="version" dataType="string" titleKey="batchjobstepexecution.version"></lo:pqGridColumn>
				<lo:pqGridColumn width="150" dataIndx="stepName" dataType="string" titleKey="batchjobstepexecution.step.name"></lo:pqGridColumn>
				<lo:pqGridColumn width="150" dataIndx="startTimeAsString" dataType="string" titleKey="batchjobstepexecution.start.time"></lo:pqGridColumn>
				<lo:pqGridColumn width="150" dataIndx="endTimeAsString" dataType="string" titleKey="batchjobstepexecution.end.time"></lo:pqGridColumn>
				<lo:pqGridColumn width="100" dataIndx="statusTranslated" dataType="string" titleKey="batchjobstepexecution.status"></lo:pqGridColumn>
				<lo:pqGridColumn width="100" dataIndx="commitCount" dataType="string" titleKey="batchjobstepexecution.commit.count"></lo:pqGridColumn>
				<lo:pqGridColumn width="100" dataIndx="readCount" dataType="string" titleKey="batchjobstepexecution.read.count"></lo:pqGridColumn>
				<lo:pqGridColumn width="100" dataIndx="filterCount" dataType="string" titleKey="batchjobstepexecution.filter.count"></lo:pqGridColumn>
				<lo:pqGridColumn width="100" dataIndx="writeCount" dataType="string" titleKey="batchjobstepexecution.write.count"></lo:pqGridColumn>
				<lo:pqGridColumn width="100" dataIndx="readSkipCount" dataType="string" titleKey="batchjobstepexecution.read.skip.count"></lo:pqGridColumn>
				<lo:pqGridColumn width="100" dataIndx="wriyteSkipCount" dataType="string" titleKey="batchjobstepexecution.write.skip.count"></lo:pqGridColumn>
				<lo:pqGridColumn width="100" dataIndx="processSkipCount" dataType="string" titleKey="batchjobstepexecution.process.skip.count"></lo:pqGridColumn>
				<lo:pqGridColumn width="100" dataIndx="rollbackCount" dataType="string" titleKey="batchjobstepexecution.rollback.count"></lo:pqGridColumn>
				<lo:pqGridColumn width="100" dataIndx="exitCodeTranslated" dataType="string" titleKey="batchjobstepexecution.exit.code"></lo:pqGridColumn>
				<lo:pqGridColumn width="100" dataIndx="exitMessage" dataType="string" titleKey="batchjobstepexecution.exit.message"></lo:pqGridColumn>
				<lo:pqGridColumn width="150" dataIndx="lastUpdatedAsString" dataType="string" titleKey="batchjobstepexecution.last.updated"></lo:pqGridColumn>
			</lo:pqGrid>
		</div>
		<div id="tab4">
			<lo:pqGrid selectedIds="selectedIdsJobLog" url="/detailJob.do?method=ajaxJobLogList" titleKey="displayJobLog.title" id="jobLog" rPP="15" rowClickMethod="">
				<lo:pqGridColumn width="1000" dataIndx="input" dataType="string" titleKey="joblog.input"></lo:pqGridColumn>
				<lo:pqGridColumn width="1000" dataIndx="output" dataType="string" titleKey="joblog.output"></lo:pqGridColumn>
			</lo:pqGrid>
		</div>
	</div>
</html:form>