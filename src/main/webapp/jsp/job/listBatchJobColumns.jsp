<%@ taglib uri="/luxuryOverdosis" prefix="lo" %>

<lo:pqGrid nameSelectedIds="selectedIds${param.name}Job" url="/list${param.url}.do?method=ajaxList${param.name}Job" titleKey="displayJob.title" id="${param.id}Job" rPP="15" rowClickMethod="read${param.name}Job">
	<lo:pqGridColumn width="200" dataIndx="jobName" dataType="string" titleKey="batchjobinstance.name"></lo:pqGridColumn>
	<lo:pqGridColumn width="200" dataIndx="batchJobExecutionVersion" dataType="string" titleKey="batchjobexecution.version"></lo:pqGridColumn>
	<lo:pqGridColumn width="200" dataIndx="batchJobExecutionCreateTimeAsString" dataType="string" titleKey="batchjobexecution.create.time"></lo:pqGridColumn>
	<lo:pqGridColumn width="200" dataIndx="batchJobExecutionStartTimeAsString" dataType="string" titleKey="batchjobexecution.start.time"></lo:pqGridColumn>
	<lo:pqGridColumn width="200" dataIndx="batchJobExecutionEndTimeAsString" dataType="string" titleKey="batchjobexecution.end.time"></lo:pqGridColumn>
	<lo:pqGridColumn width="200" dataIndx="batchJobExecutionStatusTranslated" dataType="string" titleKey="batchjobexecution.status"></lo:pqGridColumn>
	<lo:pqGridColumn width="200" dataIndx="batchJobExecutionExitCodeTranslated" dataType="string" titleKey="batchjobexecution.exit.code"></lo:pqGridColumn>
	<lo:pqGridColumn width="200" dataIndx="batchJobExecutionExitMessage" dataType="string" titleKey="batchjobexecution.exit.message"></lo:pqGridColumn>
	<lo:pqGridColumn width="200" dataIndx="batchJobExecutionLastUpdatedAsString" dataType="string" titleKey="batchjobexecution.last.updated"></lo:pqGridColumn>
</lo:pqGrid>