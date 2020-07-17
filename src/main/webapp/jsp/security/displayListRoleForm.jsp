<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="/luxuryOverdosis" prefix="lo" %>
<script>
$(document).ready(function() {
    dialog = $( "#dialog" ).dialog({
        autoOpen: false,
        height: 400,
        width: 400,
        modal: true,
        buttons: {
           "Bewaar": addRole,
          Cancel: function() {
            dialog.dialog( "close" );
          }
        },
        close: function() {
        }
      });
    
    function addRole() {
    	javascript:doActionDetail('update');
    	dialog.dialog( "close" );
    }
});
</script>
<html:form action="/listRole.do">
	<div class="center">
		<h2><i><fmt:message key="displayList.title" />&nbsp;<fmt:message key="displayRole.title" /></i></h2>
	</div>
	<lo:button image="table_add.png" method="create" key="button.create"></lo:button>
	<lo:button image="table_edit.png" method="read" key="button.edit"></lo:button>
	<lo:button image="table_link.png" method="dialog.dialog('open')" key="button.create" submit="false"></lo:button>
	<hr />
	<lo:pqGrid selectedIds="selectedIds" url="/listRole.do?method=ajaxList" titleKey="displayRole.title" id="rollen" rPP="15">
		<lo:pqGridColumn width="200" dataIndx="name" dataType="string" titleKey="security.name"></lo:pqGridColumn>
	</lo:pqGrid>
	
	<div id="dialog">
		<table>
			<tr>
				<td><fmt:message key="security.name" />*:</td>
				<td><html:text property="name" size="45" maxlength="45" tabindex="1"></html:text></td>
			</tr>
		</table>
	</div>
</html:form>