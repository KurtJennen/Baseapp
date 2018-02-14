<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="/taglib" prefix="t" %> 
<%@ taglib uri="/luxuryOverdosis" prefix="lo" %>
<%@page import="be.luxuryoverdosis.baseapp.web.WebConstants"%>
<html:form action="/listUser.do" enctype="multipart/form-data">
	<div align="center">
		<h2><i><fmt:message key="displayList.title" />&nbsp;<fmt:message key="displayUser.title" /></i></h2>
	</div>
	<t:tabpanel id="userPanel" width="99%" height="450px">
		<t:panel selected="true">
			<t:panelTab left="0"><fmt:message key="displayUser.title" /></t:panelTab>
			<t:panelBody>
				<lo:button image="zoom.png" method="search" key="button.search"></lo:button>
				<lo:button image="table_add.png" method="create" key="button.create"></lo:button>
				<hr />
				<div class="jmesatag">
					<%= request.getSession().getAttribute(WebConstants.USER_LIST) %>
				</div>
			</t:panelBody>
		</t:panel>
		<t:panel>
			<t:panelTab left="101"><fmt:message key="job.export" /></t:panelTab>
			<t:panelBody>
				<lo:button image="cog.png" method="exportUserJob" key="button.export"></lo:button>
				<hr />
				<div class="jmesatag">
					<%= request.getSession().getAttribute(WebConstants.USER_EXPORT_LIST) %>
				</div>
			</t:panelBody>
		</t:panel>
		<t:panel>
			<t:panelTab left="201"><fmt:message key="job.import" /></t:panelTab>
			<t:panelBody>
				<fmt:message key="file" />*:
				<html:file property="formFile" size="100" maxlength="256" tabindex="1"></html:file>
				<lo:button image="cog.png" method="importUserJob" key="button.import"></lo:button>
				<hr />
				<div class="jmesatag">
					<%= request.getSession().getAttribute(WebConstants.USER_IMPORT_LIST) %>
				</div>
			</t:panelBody>
		</t:panel>
	</t:tabpanel>
</html:form>