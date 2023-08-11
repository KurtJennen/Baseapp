<script>
    $(document).ready(function () {
      ${templateData.id}ConfirmDialog = $( "#${templateData.id}ConfirmDialog" ).dialog({
            title: "${templateData.title}",
            autoOpen: ${templateData.autoOpen?string("true", "false")},
            height: ${templateData.height},
            width: ${templateData.width},
            modal: ${templateData.modal?string("true", "false")},
            buttons: {
               "${templateData.yesLabel}": function() {
                   javascript:doActionDetail('${templateData.method}');
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

<div id="${templateData.method}ConfirmDialog">
	<div align="center">
		${templateData.message}
	</div>
</div>
