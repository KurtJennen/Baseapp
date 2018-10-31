<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/luxuryOverdosis" prefix="lo" %>
<%@page import="be.luxuryoverdosis.baseapp.web.WebConstants"%>
<script>
	$(document).ready(function() {
		$('#tabs').tabs({
// 			heightStyle: "fill"
		});
		
// 		function resizeUi() {
// 			var h = $("#tabs").parent().parent().height();
// 		    $("#tabs").css('height', h);
// 		};
		
// 		resizeUi();
	});
</script>

<html:form action="/detailJob.do">
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
			<div>
				<%= request.getAttribute(WebConstants.BATCH_JOB_PARAMS_LIST) %>
			</div>
		</div>
		<div id="tab2">
			<div>
				<%= request.getAttribute(WebConstants.BATCH_JOB_EXECUTION_PARAMS_LIST) %>
			</div>
		</div>
		<div id="tab3">
			<div>
				<%= request.getAttribute(WebConstants.BATCH_JOB_STEP_EXECUTION_LIST) %>
			</div>
		</div>
		<div id="tab4">
			<div>
				<%= request.getAttribute(WebConstants.JOB_LOG_LIST) %>
			</div>
		</div>
	</div>
</html:form>