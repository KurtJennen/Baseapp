<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/luxuryOverdosis" prefix="lo" %>

<html:form action="/listRole.do">
	<div class="center">
		<h2><i><fmt:message key="displayList.title" />&nbsp;<fmt:message key="displayRole.title" /></i></h2>
	</div>
	<lo:button image="table_add.png" method="create" key="button.create"></lo:button>
	<lo:button image="table_edit.png" method="read" key="button.edit"></lo:button>
	<lo:button image="application_form.png" method="roleDialog.dialog('open')" key="button.create" submit="false"></lo:button>
	<hr />
	<lo:pqGrid nameSelectedIds="selectedIds" url="/listRole.do?method=ajaxList" titleKey="displayRole.title" id="rollen" rPP="15">
		<lo:pqGridColumn width="250" dataIndx="name" dataType="string" titleKey="security.name"></lo:pqGridColumn>
	</lo:pqGrid>
</html:form>

<lo:dialog id="role" titleKey="table.role" />
<div id="roleDialog">
	<html:form action="/listRole.do" styleId="roleDialogForm">
		<table class="tiletable">
			<tr>
				<td><fmt:message key="security.name" />*:</td>
				<td>
					<lo:enumSelect clazz="be.luxuryoverdosis.baseapp.business.enumeration.RoleNameEnum" tabindex="1" property="dialogName" value="${listRoleForm.dialogName}" method="getAllCodes" />
				</td>
			</tr>
		</table>
		<c:if test="${error==1}">
			<table class="tiletable">
				<tr>
					<td class="ui-tabs ui-corner-all ui-widget ui-widget-content ui-state-error defaultheight">
						<jsp:include page="../messages.jsp"></jsp:include>
					</td>
				</tr>
			</table>
		</c:if>
	</html:form>
</div>
