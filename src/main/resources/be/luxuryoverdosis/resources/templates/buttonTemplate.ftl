<button
	<#if templateData.buttonType == "DE">
		onclick="javascript:doAction('${templateData.method}')"
	</#if>
	<#if templateData.buttonType == "DI" || templateData.buttonType == "C">
		onclick="javascript:doDialogCopy('${templateData.dialogId}', '${templateData.method}')"
	</#if>

		title="${templateData.title}"
	type = "${templateData.type}"
 >
 	<img src="images/${templateData.image}" />
 	
	<#if templateData.showKey = true>
		&nbsp; ${templateData.key}
	</#if>
</button>

<#if templateData.buttonType == "C">
	<script>
	    $(document).ready(function () {
	      ${templateData.method}ConfirmDialog = $( "#${templateData.method}ConfirmDialog" ).dialog({
	            title: "${templateData.title}",
	            autoOpen: ${templateData.autoOpen?string("true", "false")},
	            height: ${templateData.height},
	            width: ${templateData.width},
	            modal: ${templateData.modal?string("true", "false")},
	            buttons: {
	                   "${templateData.yesLabel}": function() {
	                       javascript:doAction('${templateData.method}');
	                   },
	                "${templateData.noLabel}": function() {
	                    ${templateData.method}ConfirmDialog.dialog( "close" );
	                }
	            },
	            close: function() {
	                ${templateData.method}ConfirmDialog.dialog( "close" );
	            }
	          });
	    });
	</script>
	
	<div id=" ${templateData.method}ConfirmDialog">
	    <fmt:message key="message.confirm" />
	</div>
</#if>