<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/taglib" prefix="t" %> 
<%@ taglib uri="/luxuryOverdosis" prefix="lo" %>
<%@page import="be.luxuryoverdosis.baseapp.web.WebConstants"%>
<html:form action="/detailJob.do">
	<div align="center">
		<h2><i><fmt:message key="displayDetail.title" />&nbsp;<fmt:message key="table.job" /></i></h2>
	</div>
	<lo:button image="door_out.png" method="back" key="button.back"></lo:button>
	<lo:button image="arrow_down.png" method="downloadFile" key="button.download"></lo:button>
	<br/>
	<hr/>
	<fmt:message key="job.name" />:<c:out value="${jobForm.jobName}"/>
	<t:tabpanel id="userPanel" width="99%" height="450px">
		<t:panel selected="true">
			<t:panelTab left="0"><fmt:message key="job.parameters" /></t:panelTab>
			<t:panelBody>
				<hr />
				<div class="jmesatag">
					<%= request.getAttribute(WebConstants.BATCH_JOB_PARAMS_LIST) %>
				</div>
			</t:panelBody>
		</t:panel>
		<t:panel>
			<t:panelTab left="101"><fmt:message key="job.steps" /></t:panelTab>
			<t:panelBody>
				<hr />
				<div class="jmesatag">
					<%= request.getAttribute(WebConstants.BATCH_JOB_STEP_EXECUTION_LIST) %>
				</div>
			</t:panelBody>
		</t:panel>
		<t:panel>
			<t:panelTab left="201"><fmt:message key="displayJobLog.title" /></t:panelTab>
			<t:panelBody>
				<hr />
				<div class="jmesatag">
					<%= request.getAttribute(WebConstants.JOB_LOG_LIST) %>
				</div>
			</t:panelBody>
		</t:panel>
	</t:tabpanel>
</html:form>